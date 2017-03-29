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

import okhttp3.OkHttpClient
import okhttp3.Response
import java.util.concurrent.TimeUnit


/**
 * Created by pierre on 24/02/2017.
 */
open class AbstractSaagieClient(
        open var baseURL: String = "https://manager.prod.saagie.io/api/v1",
        open var user: String = "",
        open var password: String = "",
        open var timeout: Long = 20
) {

    val httpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .build();

    var platformClient = PlatformClient(this)
    var jobClient = JobClient(this)
    var workflowClient = WorkflowClient(this)

    internal fun checkResponse(response: Response) {
        if (response.code() > 299)
            throw IllegalStateException("\n---------------------\nError during call to API \n - StatusCode :  '" + response.code() + "'\n - Message : '" + response.body().string() + "'\n---------------------\n")
    }

    internal fun constructURL(vararg args: Any): String {
        var url = baseURL
        for (item in args)
            url += "/" + item
        return url
    }
}
