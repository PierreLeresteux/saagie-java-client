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

/**
 * Created by pierre on 24/02/2017.
 */
class SaagieClientRaw(override var baseURL: String = "https://manager.prod.saagie.io/api/v1",
                      override var user: String = "",
                      override var password: String = "",
                      override var timeout: Long = 20) : AbstractSaagieClient() {

    fun getAllPlatforms(): String {
        return platformClient.getAllPlatforms().body().string()
    }

    fun getAPlatform(id: Int): String {
        return platformClient.getAPlatform(id).body().string()
    }

    fun getAllCapsulesForAPlatorm(id: Int): String {
        return platformClient.getAllCapsulesForAPlatorm(id).body().string()
    }

    fun getACapsuleForAPlatform(id: Int, capsuleCode: String): String {
        return platformClient.getACapsuleForAPlatform(id, capsuleCode).body().string()
    }

    fun getAllEnvVarsForAPlatform(id: Int): String {
        return platformClient.getAllEnvVarsForAPlatform(id).body().string()
    }

    fun createEnvVarForAPlatform(id: Int, envVar: EnvVar): String {
        return platformClient.createEnvVarForAPlatform(id, envVar).body().string()
    }
}