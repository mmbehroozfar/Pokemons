package com.mmb.ui_pokemon.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mmb.domain.error.NetworkError
import com.mmb.ui_pokemon.R

@Composable
internal fun ErrorItem(
    onRetry: () -> Unit,
    error: Throwable?,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(1.dp, MaterialTheme.colors.primary, MaterialTheme.shapes.medium)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        val errorMessageRes = when (error) {
            NetworkError.NoInternet -> R.string.internet_is_unavailable
            NetworkError.Server -> R.string.it_s_not_you_it_s_us
            NetworkError.Timeout -> R.string.connection_problem
            else -> R.string.something_went_wrong
        }

        Text(text = stringResource(errorMessageRes))

        Button(onClick = onRetry) {
            Text(text = stringResource(R.string.try_again))
        }
    }
}
