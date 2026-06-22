package dev.boecker.cherrycave.permission.minestom

import dev.boecker.cherrycave.permission.luckperms.LuckpermsRest
import dev.boecker.cherrycave.permission.minestom.command.ReloadRanksCommand
import dev.boecker.cherrycave.permission.minestom.listener.rankInitHandler
import dev.boecker.cherrycave.permission.minestom.update.luckpermsUpdateHandler
import dev.boecker.cherrycave.permission.minestom.util.updateAllRanks
import kotlinx.coroutines.launch
import net.kyori.adventure.text.minimessage.MiniMessage
import net.minestom.server.MinecraftServer
import net.minestom.server.event.GlobalEventHandler
import net.minestom.server.event.player.PlayerLoadedEvent
import java.util.*

object PermissionsAPI {

    private val globalEventHandler: GlobalEventHandler = MinecraftServer.getGlobalEventHandler()

    internal lateinit var luckperms: LuckpermsRest

    internal val miniMessage = MiniMessage.miniMessage()

    internal val groupAssignments = mutableMapOf<UUID, List<String>>()

    fun init(luckpermsUrl: String) {
        luckperms = LuckpermsRest(luckpermsUrl)

        globalEventHandler.addListener(PlayerLoadedEvent::class.java, rankInitHandler)

        MinecraftServer.getCommandManager().register(ReloadRanksCommand())

        PermissionsCoroutineScope.launch {
            luckperms.event.logBroadcast(luckpermsUpdateHandler)
        }
    }

    var disableRankDisplay = false
        set(value) {
            field = value

            if (value) {
                PermissionsCoroutineScope.launch {
                    updateAllRanks()
                }
            } else {
                val teamManager = MinecraftServer.getTeamManager()
                teamManager.teams.forEach { team ->
                    teamManager.deleteTeam(team)
                }
            }
        }

}