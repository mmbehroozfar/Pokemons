package com.mmb.ui_pokemon.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun LoadingItem() {
    LinearProgressIndicator(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    )
}
