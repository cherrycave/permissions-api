package dev.boecker.cherrycave.permission.luckperms.event.reponse

import kotlinx.serialization.Serializable
import kotlin.time.Clock

@Serializable
data class LPLogBroadcastEntry(
    val timestamp: Long = Clock.System.now().toEpochMilliseconds(),
    val source: LPLogBroadcastSource,
    val target: LPLogBroadcastTarget,
    val description: String,
)