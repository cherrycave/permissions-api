package dev.boecker.cherrycave.permission.luckperms.user.response

import dev.boecker.cherrycave.permission.luckperms.common.LPMetadata
import dev.boecker.cherrycave.permission.luckperms.common.LPNode
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Serializable
data class LPUserData(
    val uniqueId: Uuid,
    val username: String = "unknown",
    val parentGroups: List<String>,
    val nodes: List<LPNode>,
    val metadata: LPMetadata
)