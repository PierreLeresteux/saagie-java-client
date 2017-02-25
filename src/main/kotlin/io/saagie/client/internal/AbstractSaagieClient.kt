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

import khttp.responses.Response

/**
 * Created by pierre on 24/02/2017.
 */
open class AbstractSaagieClient {

    var baseURL: String = "https://manager.prod.saagie.io/api/v1"
    var user: String = ""
    var password: String = ""
    var timeout: Double = 20.0

    var platformClient = PlatformClient(this)

    internal fun checkResponse(response: Response) {
        if (response.statusCode > 299)
            throw IllegalStateException("\n---------------------\nError during call to API \n - StatusCode :  '" + response.statusCode + "'\n - Message : '" + response.text + "'\n---------------------\n")
    }

}