package com.piusvelte.nwsweather.ui

import java.util.concurrent.atomic.AtomicBoolean

interface ConsumableEvent<out T : Any> {
    fun consume(block: (T) -> Unit)
}

class Consumable<T : Any>(private val data: T) : ConsumableEvent<T> {
    private var consumed = AtomicBoolean()

    override fun consume(block: (T) -> Unit) {
        if (!consumed.getAndSet(true)) {
            block(data)
        }
    }
}

object NoOp : ConsumableEvent<Nothing> {
    override fun consume(block: (Nothing) -> Unit) {
        // noop
    }
}

fun <T : Any> T?.mapConsumable() = this?.let { Consumable(it) } ?: NoOp
