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

import okhttp3.*

/**
 * Created by pierre on 3/29/17.
 */
open class WorkflowClient(var client: AbstractSaagieClient) {

    val PLATFORM = "platform"
    val WORKFLOW = "workflow"
    val INSTANCE = "instance"
    val RUN = "run"
    val STOP = "stop"

    fun getAllWorkflows(platformId: Int): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, platformId, WORKFLOW))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .build();

        val response = client.httpClient.newCall(request).execute()
        client.checkResponse(response)
        return response
    }

    fun getAWorkflow(platformId: Int, workflowId: Int): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, platformId, WORKFLOW, workflowId))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .build();

        val response = client.httpClient.newCall(request).execute()
        client.checkResponse(response)
        return response
    }

    fun getAllWorkflowInstances(platformId: Int, workflowId: Int): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, platformId, WORKFLOW, workflowId, INSTANCE))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .build();

        val response = client.httpClient.newCall(request).execute()
        client.checkResponse(response)
        return response
    }

    fun getAWorkflowInstance(platformId: Int, workflowId: Int, instanceId: Int): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, platformId, WORKFLOW, workflowId, INSTANCE, instanceId))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .build();

        val response = client.httpClient.newCall(request).execute()
        client.checkResponse(response)
        return response
    }

    fun runAWorkflow(platformId: Int, workflowId: Int): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, platformId, WORKFLOW, workflowId, RUN))
                .post(RequestBody.create(MediaType.parse("application/json"), "{}"))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .build();

        val response = client.httpClient.newCall(request).execute()
        client.checkResponse(response)
        return response
    }

    fun stopAWorkflow(platformId: Int, workflowId: Int): Response {
        val request = Request.Builder()
                .url(client.constructURL(PLATFORM, platformId, WORKFLOW, workflowId, STOP))
                .post(RequestBody.create(MediaType.parse("application/json"), "{}"))
                .header("Authorization", Credentials.basic(client.user, client.password))
                .build();

        val response = client.httpClient.newCall(request).execute()
        client.checkResponse(response)
        return response
    }

}