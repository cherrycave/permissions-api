package dev.boecker.cherrycave.permission.luckperms.event

import dev.boecker.cherrycave.permission.luckperms.LuckpermsRest
import dev.boecker.cherrycave.permission.luckperms.event.reponse.LPLogBroadcastEvent
import io.ktor.client.plugins.sse.*
import java.util.function.Consumer

class EventEndpoints(val luckperms: LuckpermsRest) {

    suspend fun logBroadcast(logBroadcast: Consumer<LPLogBroadcastEvent>) {
        luckperms.sseClient.sse(luckperms.luckpermsUrl.resolve("/event/log-broadcast").toString()) {
            while (true) {
                incoming.collect { event ->
                    when (event.event) {
                        "message" -> {
                            if (event.data == null) {
                                return@collect
                            }

                            val data = luckperms.json.decodeFromString<LPLogBroadcastEvent>(event.data!!)

                            logBroadcast.accept(data)
                        }
                    }
                }
            }
        }
    }

}