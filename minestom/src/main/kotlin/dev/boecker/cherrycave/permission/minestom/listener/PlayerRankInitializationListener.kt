package dev.boecker.cherrycave.permission.minestom.listener

import dev.boecker.cherrycave.permission.minestom.PermissionsCoroutineScope
import dev.boecker.cherrycave.permission.minestom.util.updateRank
import kotlinx.coroutines.launch
import net.minestom.server.event.player.PlayerLoadedEvent

val rankInitHandler: (PlayerLoadedEvent) -> Unit = { event: PlayerLoadedEvent ->
    PermissionsCoroutineScope.launch {
        updateRank(event.player)
    }

}