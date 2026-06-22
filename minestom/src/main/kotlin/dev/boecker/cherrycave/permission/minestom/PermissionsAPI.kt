package dev.boecker.cherrycave.permission.minestom

import dev.boecker.cherrycave.permission.luckperms.LuckpermsRest
import dev.boecker.cherrycave.permission.minestom.listener.rankInitHandler
import dev.boecker.cherrycave.permission.minestom.util.updateAllRanks
import net.kyori.adventure.text.minimessage.MiniMessage
import net.minestom.server.MinecraftServer
import net.minestom.server.event.GlobalEventHandler
import net.minestom.server.event.player.PlayerLoadedEvent

object PermissionsAPI {

    private val globalEventHandler: GlobalEventHandler = MinecraftServer.getGlobalEventHandler()

    internal lateinit var luckperms: LuckpermsRest

    internal val miniMessage = MiniMessage.miniMessage()

    fun init(luckpermsUrl: String) {
        luckperms = LuckpermsRest(luckpermsUrl)

        globalEventHandler.addListener(PlayerLoadedEvent::class.java, rankInitHandler)
    }

    var disableRankDisplay = false
        set(value) {
            field = value

            if (value) {
                updateAllRanks()
            } else {
                val teamManager = MinecraftServer.getTeamManager()
                teamManager.teams.forEach { team ->
                    teamManager.deleteTeam(team)
                }
            }
        }

}