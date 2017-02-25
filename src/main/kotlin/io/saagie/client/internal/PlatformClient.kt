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

import khttp.get
import khttp.responses.Response
import khttp.structures.authorization.BasicAuthorization

/**
 * Created by pierre on 24/02/2017.
 */
open class PlatformClient(var client: AbstractSaagieClient) {

    fun getAllPlatforms(): Response {
        val response = get(client.baseURL + "/platform", auth = BasicAuthorization(client.user, client.password), timeout = client.timeout)
        client.checkResponse(response)
        return response
    }

    fun getAPlatform(id: Int): Response {
        val response = get(client.baseURL + "/platform/" + id, auth = BasicAuthorization(client.user, client.password), timeout = client.timeout)
        client.checkResponse(response)
        return response
    }

    fun getConnectionInformationForAPlatform(id: Int): Response {
        val response = get(client.baseURL + "/platform/" + id + "/connectioninfo", auth = BasicAuthorization(client.user, client.password), timeout = client.timeout)
        client.checkResponse(response)
        return response
    }

    fun getCapsuleConnectionInformationForAPlatform(id: Int, capsuleCode: String): Response {
        val response = get(client.baseURL + "/platform/" + id + "/connectioninfo/" + capsuleCode, auth = BasicAuthorization(client.user, client.password), timeout = client.timeout)
        client.checkResponse(response)
        return response
    }

}