/**
 * Copyright © 2017 Saagie (pierre@saagie.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.saagie.client.internal

import com.google.gson.Gson
import io.saagie.client.dto.platform.EnvVar
import okhttp3.*

/**
 * Created by pierre on 24/02/2017.
 */
open class PlatformClient(var client: AbstractSaagieClient) {

    val PLATFORM = "platform"
    val CONNECTIONINFO = "connectioninfo"
    val ENVVARS = "envvars"
    val gson = Gson()

    fun getAllPlatforms(): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .build();

        var response = client.httpClient.newCall(request).execute()
        client.checkResponse(response.code(), response)
        return response
    }

    fun getAPlatform(id: Int): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, id))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .build();

        var response = client.httpClient.newCall(request).execute()
        client.checkResponse(response.code(), response)
        return response
    }

    fun getConnectionInformationForAPlatform(id: Int): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, id, CONNECTIONINFO))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .build();

        var response = client.httpClient.newCall(request).execute()
        client.checkResponse(response.code(), response)
        return response
    }

    fun getCapsuleConnectionInformationForAPlatform(id: Int, capsuleCode: String): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, id, CONNECTIONINFO, capsuleCode))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .build();

        var response = client.httpClient.newCall(request).execute()
        client.checkResponse(response.code(), response)
        return response
    }

    fun getAllEnvVarsForAPlatform(id: Int): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, id, ENVVARS))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .build();

        var response = client.httpClient.newCall(request).execute()
        client.checkResponse(response.code(), response)
        return response
    }

    fun createEnvVarForAPlatform(id: Int, envVar: EnvVar): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, id, ENVVARS))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .post(RequestBody.create(MediaType.parse("application/json"),
                        gson.toJson(envVar)))
                .build();

        var response = client.httpClient.newCall(request).execute()
        client.checkResponse(response.code(), response)
        return response
    }
}