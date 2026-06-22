package dev.boecker.cherrycave.permission.luckperms.event.reponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class LPLogBroadcastOrigin {
    @SerialName("LOCAL")
    Local,
    @SerialName("LOCAL_API")
    LocalApi,
    @SerialName("REMOTE")
    Remote,
}