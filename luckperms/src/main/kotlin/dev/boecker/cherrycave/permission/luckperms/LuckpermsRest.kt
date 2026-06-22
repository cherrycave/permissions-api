package dev.boecker.cherrycave.permission.luckperms

import dev.boecker.cherrycave.permission.luckperms.event.EventEndpoints
import dev.boecker.cherrycave.permission.luckperms.ktor.RemoveDefaultAccept
import dev.boecker.cherrycave.permission.luckperms.serializer.UUIDModule
import dev.boecker.cherrycave.permission.luckperms.user.UserEndpoints
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.plugin
import io.ktor.client.plugins.sse.*
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import java.net.URI
import kotlin.time.Duration.Companion.seconds

class LuckpermsRest(luckpermsUrl: String) {

    internal val luckpermsUrl = URI(luckpermsUrl)

    internal val json = Json {
        serializersModule = UUIDModule
    }

    internal val ktorClient: HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(json)
        }
        install(SSE) {
            maxReconnectionAttempts = 4
            reconnectionTime = 2.seconds
        }
    }
    internal val sseClient: HttpClient = HttpClient(CIO) {
        install(SSE) {
            showCommentEvents()
            showRetryEvents()
            maxReconnectionAttempts = 4
            reconnectionTime = 2.seconds
        }
    }

    init {
        sseClient.plugin(HttpSend).intercept { request ->
            request.headers.remove(HttpHeaders.Accept)
            execute(request)
        }
    }

    val user = UserEndpoints(this)
    val event = EventEndpoints(this)
}