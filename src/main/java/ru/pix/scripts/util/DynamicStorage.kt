package ru.pix.scripts.util

object DynamicStorage {

    private val storage = hashMapOf<String,Any>()

    fun put(key: Any, value: Any) {
        storage += (key.toString() to value)
    }

    fun has(key: Any): Boolean {
        return key.toString() in storage
    }

    fun remove(key: Any) {
        storage.remove(key.toString())
    }

    fun get(key: Any): Any? {
        return storage[key.toString()]
    }

}
