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
        client.checkResponse(response)
        return response
    }

    fun getAPlatform(platformId: Int): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, platformId))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .build();

        var response = client.httpClient.newCall(request).execute()
        client.checkResponse(response)
        return response
    }

    fun getAllCapsulesForAPlatorm(platformId: Int): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, platformId, CONNECTIONINFO))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .build();

        var response = client.httpClient.newCall(request).execute()
        client.checkResponse(response)
        return response
    }

    fun getACapsuleForAPlatform(platformId: Int, capsuleCode: String): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, platformId, CONNECTIONINFO, capsuleCode))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .build();

        var response = client.httpClient.newCall(request).execute()
        client.checkResponse(response)
        return response
    }

    fun getAllEnvVarsForAPlatform(platformId: Int): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, platformId, ENVVARS))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .build();

        var response = client.httpClient.newCall(request).execute()
        client.checkResponse(response)
        return response
    }

    fun createEnvVarForAPlatform(platformId: Int, envVar: EnvVar): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, platformId, ENVVARS))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .post(RequestBody.create(MediaType.parse("application/json"),
                        gson.toJson(envVar)))
                .build();

        var response = client.httpClient.newCall(request).execute()
        client.checkResponse(response)
        return response
    }

    fun deleteEnvVarForAPlatform(platformId: Int, envvarId: Int): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, platformId, ENVVARS, envvarId))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .delete()
                .build();

        var response = client.httpClient.newCall(request).execute()
        client.checkResponse(response)
        return response
    }
}