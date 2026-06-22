package dev.boecker.cherrycave.permission.minestom.update

import dev.boecker.cherrycave.permission.luckperms.event.reponse.LPLogBroadcastEvent
import dev.boecker.cherrycave.permission.luckperms.event.reponse.LPLogBroadcastTargetType
import dev.boecker.cherrycave.permission.minestom.PermissionsAPI
import dev.boecker.cherrycave.permission.minestom.PermissionsCoroutineScope
import dev.boecker.cherrycave.permission.minestom.util.updateRank
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.minestom.server.MinecraftServer
import kotlin.time.Duration.Companion.seconds
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
val luckpermsUpdateHandler = { event: LPLogBroadcastEvent ->
    val updateTarget = event.entry.target

    when (updateTarget.type) {
        LPLogBroadcastTargetType.User -> {
            if (updateTarget.uniqueId != null) {
                val player = MinecraftServer.getConnectionManager().onlinePlayers.find { it.uuid == updateTarget.uniqueId }

                if (player != null) {
                    updateRank(player)
                }
            }
        }
        LPLogBroadcastTargetType.Group -> {
            // TODO: This update doesnt work, because the state of the group meta is not reflected in the playerdata request
            val groupName = updateTarget.name

            PermissionsAPI.groupAssignments.filter { (_, value) -> value.contains(groupName) }.forEach { (uuid, _) ->
                updateRank(uuid)
            }
        }
        LPLogBroadcastTargetType.Track -> {

        }
    }
}