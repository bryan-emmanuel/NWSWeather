package com.piusvelte.domain.common

import java.util.concurrent.atomic.AtomicBoolean

interface ConsumableEvent<T> {
    fun consume(block: (T) -> Unit)
}

class Consumable<T>(val data: T) : ConsumableEvent<T> {
    private var consumed = AtomicBoolean()

    override fun consume(block: (T) -> Unit) {
        if (!consumed.getAndSet(true)) {
            block(data)
        }
    }
}

class NoOp : ConsumableEvent<Nothing> {
    override fun consume(block: (Nothing) -> Unit) {
        // noop
    }
}
