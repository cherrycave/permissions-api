package dev.boecker.cherrycave.permission.minestom.util

import dev.boecker.cherrycave.permission.minestom.PermissionsAPI
import dev.boecker.cherrycave.permission.minestom.PermissionsCoroutineScope
import kotlinx.coroutines.launch
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.Player
import java.util.*

internal fun updateAllRanks() {
    MinecraftServer.getConnectionManager().onlinePlayers.forEach { player ->
        updateRank(player)
    }
}

internal fun updateRank(player: UUID) {
    val player = MinecraftServer.getConnectionManager().onlinePlayers.find { it.uuid == player }
    if (player == null) {
        return
    }

    updateRank(player)
}

@Suppress("UnstableApiUsage")
internal fun updateRank(player: Player) {
    val teamManager = MinecraftServer.getTeamManager()
    val existingTeam = teamManager.teams.find { team ->
        team.players.any { teamPlayer -> teamPlayer.uuid == player.uuid }
    }

    PermissionsCoroutineScope.launch {
        val playerData = PermissionsAPI.luckperms.user.getUser(player.uuid) ?: return@launch

        if (existingTeam != null) {
            teamManager.deleteTeam(existingTeam)
        }

        val weight = 100 - (playerData.metadata.meta["weight"] ?: "10").toInt()

        val team = teamManager.createTeam(
            "${weight}${player.username}",
            PermissionsAPI.miniMessage.deserialize(playerData.metadata.prefix ?: ""),
            NamedTextColor.NAMES.value(playerData.metadata.meta["color"] ?: "gray"),
            PermissionsAPI.miniMessage.deserialize(playerData.metadata.suffix ?: ""),
        )

        team.addMember(player.username)
    }
}