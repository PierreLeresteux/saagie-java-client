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

import okhttp3.Credentials
import okhttp3.Request
import okhttp3.Response

/**
 * Created by pierre on 01/03/2017.
 */
open class JobClient(var client: AbstractSaagieClient) {

    val PLATFORM = "platform"
    val JOB = "job"

    fun getAllJobs(platformId: Int): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, platformId, JOB))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .build();

        val response = client.httpClient.newCall(request).execute()
        client.checkResponse(response)
        return response
    }

    fun getAJob(platformId: Int, jobId: Int): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, platformId, JOB, jobId))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .build();

        val response = client.httpClient.newCall(request).execute()
        client.checkResponse(response)
        return response
    }
}