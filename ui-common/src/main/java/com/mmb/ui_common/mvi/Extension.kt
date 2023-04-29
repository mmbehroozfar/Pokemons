package com.mmb.ui_common.mvi

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.compose.runtime.State as ComposeState

@SuppressLint("ComposableNaming")
@Composable
fun <INTENT : Intent, STATE : State, EFFECT : Effect> BaseViewModel<INTENT, STATE, EFFECT>.collectEffect(
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    sideEffect: (suspend (sideEffect: EFFECT) -> Unit),
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(effect, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(lifecycleState) {
            effect.collect { sideEffect(it) }
        }
    }
}

@Composable
fun <INTENT : Intent, STATE : State, EFFECT : Effect> BaseViewModel<INTENT, STATE, EFFECT>.collectAsState(
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
): ComposeState<STATE> {
    val lifecycleOwner = LocalLifecycleOwner.current

    val stateFlowLifecycleAware = remember(state, lifecycleOwner) {
        state.flowWithLifecycle(lifecycleOwner.lifecycle, lifecycleState)
    }

    // Need to access the initial value to convert to State - collectAsState() suppresses this lint warning too
    @SuppressLint("StateFlowValueCalledInComposition")
    val initialValue = state.value
    return stateFlowLifecycleAware.collectAsState(initialValue)
}