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
import io.saagie.client.dto.job.JobCreate
import okhttp3.*
import java.io.File

/**
 * Created by pierre on 01/03/2017.
 */
open class JobClient(var client: AbstractSaagieClient) {

    val PLATFORM = "platform"
    val JOB = "job"
    val RUN = "run"
    val STOP = "stop"
    val UPLOAD = "upload"
    val JOBTASK = "jobtask"
    val gson = Gson()

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

    fun runAJob(platformId: Int, jobId: Int): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, platformId, JOB, jobId, RUN))
                .post(RequestBody.create(MediaType.parse("application/json"), "{}"))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .build();

        val response = client.httpClient.newCall(request).execute()
        client.checkResponse(response)
        return response
    }

    fun stopAJob(platformId: Int, jobId: Int): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, platformId, JOB, jobId, STOP))
                .post(RequestBody.create(MediaType.parse("application/json"), "{}"))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .build();

        val response = client.httpClient.newCall(request).execute()
        client.checkResponse(response)
        return response
    }

    fun getJobTasksForAJob(platformId: Int, jobId: Int): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, platformId, JOB, jobId, JOBTASK))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .build();

        val response = client.httpClient.newCall(request).execute()
        client.checkResponse(response)
        return response
    }

    fun getAJobTask(jobTaskid: Int): Response {
        val request = Request.Builder()
                .url(client.constructURL(JOBTASK, jobTaskid))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .build();

        val response = client.httpClient.newCall(request).execute()
        client.checkResponse(response)
        return response
    }

    fun uploadFile(platformId: Int, file: File): Response {
        val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.name,
                        RequestBody.create(MediaType.parse("application/octet-stream"), file))
                .build()

        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, platformId, JOB, UPLOAD))
                .post(requestBody)
                .header("Authorization", Credentials.basic(client.user, client.password))
                .build();

        val response = client.httpClient.newCall(request).execute()
        client.checkResponse(response)
        return response
    }

    fun createAJob(jobCreate: JobCreate): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, jobCreate.platformId, JOB))
                .post(RequestBody.create(MediaType.parse("application/json"), gson.toJson(jobCreate)))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .build();

        val response = client.httpClient.newCall(request).execute()
        client.checkResponse(response)
        return response
    }
}