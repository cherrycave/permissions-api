package dev.boecker.cherrycave.permission.luckperms.common

import kotlinx.serialization.Serializable

@Serializable
data class LPNode(
    val key: String,
    val type: String,
    val value: Boolean,
    val context: List<LPContext>,
    val expiry: Long? = null,
)
