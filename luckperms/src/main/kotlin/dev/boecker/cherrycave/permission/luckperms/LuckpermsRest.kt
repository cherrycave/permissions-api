package dev.boecker.cherrycave.permission.luckperms

import dev.boecker.cherrycave.permission.luckperms.user.UserEndpoints
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import java.net.URI

class LuckpermsRest(luckpermsUrl: String) {

    internal val luckpermsUrl = URI(luckpermsUrl)

    internal val ktorClient: HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    val user = UserEndpoints(this)
}