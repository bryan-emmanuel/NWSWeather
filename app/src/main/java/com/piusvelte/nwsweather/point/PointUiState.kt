package com.piusvelte.nwsweather.point

import com.piusvelte.nwsweather.ui.ConsumableEvent
import com.piusvelte.nwsweather.ui.NoOp

data class PointUiState(
    val isLoading: Boolean = false,
    val pointId: String = "",
    val pointGridId: String = "",
    val pointGridX: Int = 0,
    val pointGridY: Int = 0,
    val error: ConsumableEvent<String> = NoOp,
)