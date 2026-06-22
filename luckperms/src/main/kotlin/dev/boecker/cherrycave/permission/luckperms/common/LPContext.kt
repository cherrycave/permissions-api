package dev.boecker.cherrycave.permission.luckperms.common

import kotlinx.serialization.Serializable

@Serializable
data class LPContext(
    val key: String,
    val value: String,
)
