package dev.boecker.cherrycave.permission.luckperms.user.response

import dev.boecker.cherrycave.permission.luckperms.common.LPNode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LPPermissionCheckResult(
    val result: LPPermissionCheckResultTypes,
    val node: LPNode
)

@Serializable
enum class LPPermissionCheckResultTypes {
    @SerialName("true")
    True,
    @SerialName("false")
    False,
    @SerialName("undefined")
    Undefined,
}
