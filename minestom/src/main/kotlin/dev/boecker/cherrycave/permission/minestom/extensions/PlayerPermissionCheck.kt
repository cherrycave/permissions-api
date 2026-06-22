package dev.boecker.cherrycave.permission.minestom.extensions

import dev.boecker.cherrycave.permission.luckperms.user.response.LPPermissionCheckResultTypes
import dev.boecker.cherrycave.permission.minestom.PermissionsAPI
import net.minestom.server.entity.Player

suspend fun Player.hasPermission(permission: String, defaultValue: Boolean = false): Boolean {
    val permissionCheckResult = PermissionsAPI.luckperms.user.permissionCheck(this.uuid, permission)

    when (permissionCheckResult?.result ?: return defaultValue) {
        LPPermissionCheckResultTypes.True -> return true
        LPPermissionCheckResultTypes.False -> return false
        LPPermissionCheckResultTypes.Undefined -> return defaultValue
    }
}