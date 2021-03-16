package ru.pix.scripts.shell

import ru.pix.scripts.ScriptKind
import ru.pix.scripts.shell.globals.Globals
import ru.pix.scripts.shell.groovy.Trigger
import ru.pix.scripts.shell.groovy.base.EnchancedScript
import java.nio.file.Files
import java.nio.file.Path
import kotlin.streams.asSequence

class ScriptLoader(
    private val dir: Path,
    private val registry: ScriptRegistry,
    private val globals: Globals
) {

    private val triggerShell = Shell(Trigger::class.java)
    private val triggerDir = Files.createDirectories(dir.resolve("trigger"))

    private fun readFileToString(path: Path): String {
        val bytes = Files.readAllBytes(path)
        return String(bytes, Charsets.UTF_8)
    }

    private fun <T : EnchancedScript> load(kind: ScriptKind<T>, dir: Path, shell: Shell): Set<CompiledScript<T>> {
        println("Loading kind:$kind dir:$dir")
        return Files.walk(dir)
            .asSequence()
            .filter {
                println("Loading: $it")
                it.toFile().extension == "groovy"
            }
            .map { path ->
                val filename = path.toFile().nameWithoutExtension
                println("Compiling: $filename")

                val content = readFileToString(path)

                @Suppress("UNCHECKED_CAST")
                val script = shell.compile(content) as T

                script.globals = globals

                CompiledScript(content, kind, filename, script)
            }
            .toSet()
    }

    fun load(): Int {

        val triggers = load(ScriptKind.TRIGGER, triggerDir, triggerShell)

        registry.set(mapOf(
            ScriptKind.TRIGGER to triggers
        ))

        return registry.getIds().size

    }

}
