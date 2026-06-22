package dev.boecker.cherrycave.permission.luckperms.ktor

import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders

internal val RemoveDefaultAccept = createClientPlugin("RemoveDefaultAccept") {
    transformRequestBody { request, _, _ ->
        val firstHeader = request.headers.getAll(HttpHeaders.Accept)?.first() ?: return@transformRequestBody null
        request.headers.remove(HttpHeaders.Accept, ContentType.Any.toString())
        request.headers.append(HttpHeaders.Accept, firstHeader)

        null
    }
}