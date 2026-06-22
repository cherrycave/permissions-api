package dev.boecker.cherrycave.permission.luckperms.common

import kotlinx.serialization.Serializable

@Serializable
data class LPMetadata(
    val meta: Map<String, String>,
    val prefix: String? = null,
    val suffix: String? = null,
    val primaryGroup: String? = null,
)
