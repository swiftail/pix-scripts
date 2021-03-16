package ru.pix.scripts

import org.spongepowered.api.Sponge
import org.spongepowered.api.service.permission.PermissionDescription
import org.spongepowered.api.service.permission.PermissionService
import org.spongepowered.api.text.Text

object Permissions {

    const val ADMIN = "ru.pix.scripts.admin"

    fun register(plugin: Scripts) {
        val ps = Sponge
            .getServiceManager()
            .provide(PermissionService::class.java)
            .get()

        ps
            .newDescriptionBuilder(plugin)
            .id(ADMIN)
            .description(Text.of("Scripts admin permission"))
            .assign(PermissionDescription.ROLE_ADMIN, true)
            .register()
    }

}
