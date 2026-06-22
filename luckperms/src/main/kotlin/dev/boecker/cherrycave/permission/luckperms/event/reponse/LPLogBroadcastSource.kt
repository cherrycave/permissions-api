package dev.boecker.cherrycave.permission.luckperms.event.reponse

import dev.boecker.cherrycave.permission.luckperms.serializer.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
@Serializable
data class LPLogBroadcastSource(
    @Serializable(with = UUIDSerializer::class)
    val uniqueId: UUID,
    val name: String
)