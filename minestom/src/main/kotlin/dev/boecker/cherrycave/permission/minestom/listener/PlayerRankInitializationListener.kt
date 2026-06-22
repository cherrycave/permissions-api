package dev.boecker.cherrycave.permission.minestom.listener

import dev.boecker.cherrycave.permission.minestom.util.updateRank
import net.minestom.server.event.player.PlayerLoadedEvent

val rankInitHandler = { event: PlayerLoadedEvent ->
    updateRank(event.player)
}