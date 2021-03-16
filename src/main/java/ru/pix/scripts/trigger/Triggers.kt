@file:Suppress("unused")

package ru.pix.scripts.trigger

import java.io.Serializable

data class TriggerEntry<T : TriggerType> (val type: T, val id: String) : Serializable

data class Triggers(
    var triggers: MutableSet<TriggerEntry<out TriggerType>>
) : Serializable {

    // Jackson
    constructor(): this(hashSetOf())

}
