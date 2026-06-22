package dev.boecker.cherrycave.permission.luckperms.event.reponse

import dev.boecker.cherrycave.permission.luckperms.serializer.UUIDSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class LPLogBroadcastTarget(
    @Serializable(with = UUIDSerializer::class)
    val uniqueId: UUID? = null,
    val name: String,
    val type: LPLogBroadcastTargetType
)

@Serializable
enum class LPLogBroadcastTargetType {
    @SerialName("USER")
    User,
    @SerialName("GROUP")
    Group,
    @SerialName("TRACK")
    Track,
}
