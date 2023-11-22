package com.piusvelte.nwsweather.point

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun PointScreen(
    viewModel: PointViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    PointScreen(
        state = state,
        modifier = modifier,
    )
}

@Composable
fun PointScreen(
    state: PointUiState,
    modifier: Modifier = Modifier,
) {
    Column {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            Text(text = state.pointId)
            Text(text = state.pointGridId)
            Text(text = state.pointGridX.toString())
            Text(text = state.pointGridY.toString())
        }
    }
}

@Composable
@Preview
fun PointScreenPreview() {
    val state = PointUiState()
    PointScreen(state)
}