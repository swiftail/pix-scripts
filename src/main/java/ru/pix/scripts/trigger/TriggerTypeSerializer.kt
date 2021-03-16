package ru.pix.scripts.trigger

import com.google.gson.*
import java.lang.reflect.Type

class TriggerTypeSerializer : JsonSerializer<TriggerType>, JsonDeserializer<TriggerType> {

    private val gson = Gson()

    override fun serialize(obj: TriggerType, type: Type, ctx: JsonSerializationContext): JsonElement {

        val json = JsonObject()

        json.add("type", JsonPrimitive(obj.javaClass.name))
        json.add("data", gson.toJsonTree(obj))

        return json

    }

    override fun deserialize(e: JsonElement, type: Type, ctx: JsonDeserializationContext): TriggerType {

        val o = e.asJsonObject

        val typeClass = o.get("type").asString
        val dataClass = Class.forName(typeClass)

        val data = o.get("data")

        return gson.fromJson(data, dataClass) as TriggerType

    }

}
