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

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.saagie.client.dto.platform.Capsule
import io.saagie.client.dto.platform.Platform
import io.saagie.client.internal.AbstractSaagieClient

/**
 * Created by pierre on 24/02/2017.
 */
class SaagieClient : AbstractSaagieClient() {

    val mapper = jacksonObjectMapper()

    fun getAllPlatforms(): List<Platform>? {
        val platforms: List<Platform>? = mapper.readValue(platformClient.getAllPlatforms().text)
        return platforms
    }

    fun getAPlatform(id: Int): Platform? {
        val platform: Platform? = mapper.readValue(platformClient.getAPlatform(id).text)
        return platform
    }

    fun getConnectionInformationForAPlatform(id: Int): List<Capsule>? {
        val connectionInfo: List<Capsule>? = mapper.readValue(platformClient.getConnectionInformationForAPlatform(id).text)
        return connectionInfo
    }

    fun getCapsuleConnectionInformationForAPlatform(id: Int, capsuleCode: String): Capsule? {
        val capsule: Capsule? = mapper.readValue(platformClient.getCapsuleConnectionInformationForAPlatform(id, capsuleCode).text)
        return capsule
    }

}
