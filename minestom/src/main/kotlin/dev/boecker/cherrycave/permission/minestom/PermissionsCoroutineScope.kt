package dev.boecker.cherrycave.permission.minestom

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

internal object PermissionsCoroutineScope : CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.IO + Job()
}