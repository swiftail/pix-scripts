package ru.pix.scripts.trigger

import com.flowpowered.math.vector.Vector3i
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.format.TextColors
import ru.pix.core.commons.math.Area

sealed class TriggerType {
    data class CLICK(val block: Vector3i) : TriggerType() {
        override fun describe(): Text {
            return Text.of("Click: ", TextColors.YELLOW, block.toString())
        }
    }

    data class WALK(val block: Vector3i) : TriggerType() {
        override fun describe(): Text {
            return Text.of("Walk: ", TextColors.YELLOW, block.toString())
        }
    }

    data class AREA_JOIN(val area: Area) : TriggerType() {
        override fun describe(): Text {
            return Text.of(
                "Area join:\n",
                TextColors.YELLOW, area.p1, TextColors.RESET, ",\n",
                TextColors.YELLOW, area.p2
            )
        }
    }

    data class AREA_LEAVE(val area: Area) : TriggerType() {
        override fun describe(): Text {
            return Text.of(
                "Area leave:\n",
                TextColors.YELLOW, area.p1, TextColors.RESET, ",\n",
                TextColors.YELLOW, area.p2
            )
        }
    }

    object MANUAL : TriggerType() {
        override fun describe(): Text {
            return Text.of("Manual")
        }

        override fun toString(): String {
            return "TriggerType.MANUAL"
        }
    };

    abstract fun describe(): Text

    fun name(): String {
        return this.javaClass.simpleName
    }

}
