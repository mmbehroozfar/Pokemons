package com.mmb.ui_common.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<INTENT : Intent, STATE : State, EFFECT : Effect>(
    initialState: STATE,
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()
    protected val currentState: STATE get() = _state.value

    private val _effect = Channel<EFFECT>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    private val _intent = Channel<INTENT>(Channel.BUFFERED)

    init {
        viewModelScope.launch {
            _intent.consumeEach { action ->
                handleIntent(action)
            }
        }
    }

    protected abstract suspend fun handleIntent(intent: INTENT)

    fun dispatchIntent(intent: INTENT) = viewModelScope.launch {
        _intent.send(intent)
    }

    protected fun dispatchEffect(effect: EFFECT) = viewModelScope.launch {
        _effect.send(effect)
    }

    protected fun reduceState(reducer: STATE.() -> STATE) {
        _state.update(reducer)
    }

}