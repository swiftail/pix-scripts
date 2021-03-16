package ru.pix.scripts.shell

import org.spongepowered.api.text.Text
import org.spongepowered.api.text.format.TextColors
import ru.pix.scripts.ScriptKind
import ru.pix.scripts.shell.groovy.base.ScriptBase

data class CompiledScript <T : ScriptBase> (
    val source: String,
    val kind: ScriptKind<T>,
    private val filename: String,
    val groovyScript: T
) {

    val id = "${kind.prefix}@${filename}".toLowerCase()

    val textId = Text.join(
        Text.of(TextColors.GREEN, "${kind.prefix}@"),
        Text.of(TextColors.RESET, filename)
    )

}
