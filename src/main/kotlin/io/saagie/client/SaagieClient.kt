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
package io.saagie.client

import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import io.saagie.client.dto.platform.Capsule
import io.saagie.client.dto.platform.EnvVar
import io.saagie.client.dto.platform.Platform
import io.saagie.client.internal.AbstractSaagieClient

/**
 * Created by pierre on 24/02/2017.
 */
class SaagieClient(override var baseURL: String = "https://manager.prod.saagie.io/api/v1",
                   override var user: String = "",
                   override var password: String = "",
                   override var timeout: Long = 20) : AbstractSaagieClient() {

    val gson = Gson()

    fun getAllPlatforms(): List<Platform> {
        return gson.fromJson(platformClient.getAllPlatforms().body().charStream())
    }

    fun getAPlatform(platformId: Int): Platform {
        return gson.fromJson(platformClient.getAPlatform(platformId).body().string())
    }

    fun getAllCapsulesForAPlatorm(platformId: Int): List<Capsule> {
        return gson.fromJson(platformClient.getAllCapsulesForAPlatorm(platformId).body().string())
    }

    fun getACapsuleForAPlatform(platformId: Int, capsuleCode: String): Capsule {
        return gson.fromJson(platformClient.getACapsuleForAPlatform(platformId, capsuleCode).body().string())
    }

    fun getAllEnvVarsForAPlatform(platformId: Int): List<EnvVar> {
        return gson.fromJson(platformClient.getAllEnvVarsForAPlatform(platformId).body().string())
    }

    fun createEnvVarForAPlatform(platformId: Int, envVar: EnvVar): EnvVar {
        return gson.fromJson(platformClient.createEnvVarForAPlatform(platformId, envVar).body().string())
    }

    fun deleteEnvVarForAPlatform(platformId: Int, envvarId: Int) {
        platformClient.deleteEnvVarForAPlatform(platformId, envvarId).body().string()
    }
}
