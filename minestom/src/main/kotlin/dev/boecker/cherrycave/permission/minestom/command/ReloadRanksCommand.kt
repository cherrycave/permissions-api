package dev.boecker.cherrycave.permission.minestom.command

import dev.boecker.cherrycave.permission.minestom.PermissionsCoroutineScope
import dev.boecker.cherrycave.permission.minestom.extensions.hasPermission
import dev.boecker.cherrycave.permission.minestom.util.updateAllRanks
import kotlinx.coroutines.launch
import net.minestom.server.command.builder.Command
import net.minestom.server.entity.Player

class ReloadRanksCommand : Command("reload-ranks") {
    init {
        setDefaultExecutor { sender, _ ->
            PermissionsCoroutineScope.launch {
                if (sender is Player && sender.hasPermission("cherrycave.permissions.reloadranks")) {
                    return@launch
                }

                updateAllRanks()
            }
        }
    }
}