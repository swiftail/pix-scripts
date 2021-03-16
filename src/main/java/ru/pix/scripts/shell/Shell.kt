package ru.pix.scripts.shell

import groovy.lang.Binding
import groovy.lang.GroovyClassLoader
import groovy.lang.GroovyShell
import groovy.lang.Script
import org.codehaus.groovy.control.CompilerConfiguration
import ru.pix.scripts.shell.groovy.base.ScriptBase

class Shell(private val baseClass: Class<out ScriptBase>) {

    private fun createShell(): GroovyShell {

        val compilerConfiguration = CompilerConfiguration()

        compilerConfiguration.scriptBaseClass = baseClass.name

        val scriptClassLoader = GroovyClassLoader(baseClass.classLoader, compilerConfiguration)

        return GroovyShell(scriptClassLoader, Binding(), compilerConfiguration)
    }

    private val groovy by lazy(::createShell)

    fun compile(script: String): Script {
        return groovy.parse(script)
    }

}
