package dev.boecker.cherrycave.permission.luckperms.event.reponse

import kotlinx.serialization.Serializable
import kotlin.time.Clock

@Serializable
data class LPLogBroadcastEvent(
    val entry: LPLogBroadcastEntry,
    val origin: LPLogBroadcastOrigin
)