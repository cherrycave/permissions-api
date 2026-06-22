package dev.boecker.cherrycave.permission.minestom.util

import dev.boecker.cherrycave.permission.minestom.PermissionsAPI
import dev.boecker.cherrycave.permission.minestom.PermissionsAPI.groupAssignments
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.Player
import java.util.*

internal suspend fun updateAllRanks() {
    MinecraftServer.getConnectionManager().onlinePlayers.forEach { player ->
        updateRank(player)
    }
}

internal suspend fun updateRank(player: UUID) {
    val player = MinecraftServer.getConnectionManager().onlinePlayers.find { it.uuid.equals(player) }
    if (player == null) {
        return
    }

    updateRank(player)
}

internal suspend fun updateRank(player: Player) {
    val teamManager = MinecraftServer.getTeamManager()
    val existingTeam = teamManager.teams.find { team ->
        team.players.any { teamPlayer -> teamPlayer.uuid.equals(player.uuid) }
    }

    val playerData = PermissionsAPI.luckperms.user.getUser(player.uuid) ?: return

    println(playerData)

    groupAssignments[player.uuid] = playerData.parentGroups

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