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

import io.saagie.client.dto.platform.EnvVar
import io.saagie.client.internal.AbstractSaagieClient
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by pierre on 24/02/2017.
 */
class SaagieClientJson(override var baseURL: String = "https://manager.prod.saagie.io/api/v1",
                       override var user: String = "",
                       override var password: String = "",
                       override var timeout: Long = 20) : AbstractSaagieClient() {

    // PLATFORM
    fun getAllPlatforms(): JSONArray {
        return JSONArray(platformClient.getAllPlatforms().body().string())
    }

    fun getAPlatform(platformId: Int): JSONObject {
        return JSONObject(platformClient.getAPlatform(platformId).body().string())
    }

    fun getAllCapsulesForAPlatorm(platformId: Int): JSONArray {
        return JSONArray(platformClient.getAllCapsulesForAPlatorm(platformId).body().string())
    }

    fun getACapsuleForAPlatform(platformId: Int, capsuleCode: String): JSONObject {
        return JSONObject(platformClient.getACapsuleForAPlatform(platformId, capsuleCode).body().string())
    }

    fun getAllEnvVarsForAPlatform(platformId: Int): JSONArray {
        return JSONArray(platformClient.getAllEnvVarsForAPlatform(platformId).body().string())
    }

    fun createEnvVarForAPlatform(platformId: Int, envVar: EnvVar): JSONObject {
        return JSONObject(platformClient.createEnvVarForAPlatform(platformId, envVar).body().string())
    }

    fun createEnvVarForAPlatform(envVar: EnvVar): JSONObject {
        return JSONObject(platformClient.createEnvVarForAPlatform(envVar.platformId!!, envVar).body().string())
    }

    fun deleteEnvVarForAPlatform(platformId: Int, envvarId: Int) {
        platformClient.deleteEnvVarForAPlatform(platformId, envvarId).body().string()
    }

    fun deleteEnvVarForAPlatform(envVar: EnvVar) {
        platformClient.deleteEnvVarForAPlatform(envVar.platformId!!, envVar.id!!).body().string()
    }

    fun editEnvVarForAPlatform(platformId: Int, envvarId: Int, envVar: EnvVar): JSONObject {
        return JSONObject(platformClient.editEnvVarForAPlatform(platformId, envvarId, envVar).body().string())
    }

    fun editEnvVarForAPlatform(envVar: EnvVar): JSONObject {
        return JSONObject(platformClient.editEnvVarForAPlatform(envVar.platformId!!, envVar.id!!, envVar).body().string())
    }

    // JOB
    fun getAllJobsForAPlatform(platformId: Int): JSONArray {
        return JSONArray(jobClient.getAllJobsForAPlatform(platformId).body().string())
    }
}