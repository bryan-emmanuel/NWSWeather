package com.piusvelte.nwsweather.common

import android.view.View
import androidx.core.view.isVisible
import com.piusvelte.domain.common.Hideable

fun <V : View, T : Any> V.show(hideable: Hideable<T>, block: V.(T) -> Unit = {}) {
    when (hideable) {
        is Hideable.Show -> {
            isVisible = true
            block(hideable.data)
        }

        is Hideable.Hide -> isVisible = false
    }
}
