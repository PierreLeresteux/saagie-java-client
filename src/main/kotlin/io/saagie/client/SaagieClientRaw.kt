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

import io.saagie.client.internal.AbstractSaagieClient

/**
 * Created by pierre on 24/02/2017.
 */
class SaagieClientRaw : AbstractSaagieClient() {

    fun getAllPlatforms(): String {
        return platformClient.getAllPlatforms().text
    }

    fun getAPlatform(id: Int): String {
        return platformClient.getAPlatform(id).text
    }

    fun getConnectionInformationForAPlatform(id: Int): String {
        return platformClient.getConnectionInformationForAPlatform(id).text
    }

    fun getCapsuleConnectionInformationForAPlatform(id: Int, capsuleCode: String): String {
        return platformClient.getCapsuleConnectionInformationForAPlatform(id, capsuleCode).text
    }
}