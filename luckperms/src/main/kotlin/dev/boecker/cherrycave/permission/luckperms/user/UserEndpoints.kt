package dev.boecker.cherrycave.permission.luckperms.user

import dev.boecker.cherrycave.permission.luckperms.LuckpermsRest
import dev.boecker.cherrycave.permission.luckperms.user.response.LPPermissionCheckResult
import dev.boecker.cherrycave.permission.luckperms.user.response.LPUserData
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.HttpStatusCode
import io.ktor.http.encodeURLPathPart
import java.util.*

class UserEndpoints(val luckperms: LuckpermsRest) {

    suspend fun getUser(uuid: UUID): LPUserData? {
        val response = luckperms.ktorClient.get(luckperms.luckpermsUrl.resolve("/user/$uuid").toURL())

        if (response.status == HttpStatusCode.NotFound) {
            return null
        }

        return response.body<LPUserData>()
    }

    suspend fun permissionCheck(uuid: UUID, permission: String): LPPermissionCheckResult? {
        val response = luckperms.ktorClient.get(
            luckperms.luckpermsUrl.resolve("/user/$uuid/permission-check?permission=${permission.encodeURLPathPart()}")
                .toURL()
        )

        if (response.status == HttpStatusCode.NotFound) {
            return null
        }

        return response.body<LPPermissionCheckResult>()
    }
}