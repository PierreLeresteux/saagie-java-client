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

    fun getAllCapsules(platformId: Int): JSONArray {
        return JSONArray(platformClient.getAllCapsules(platformId).body().string())
    }

    fun getACapsule(platformId: Int, capsuleCode: String): JSONObject {
        return JSONObject(platformClient.getACapsule(platformId, capsuleCode).body().string())
    }

    fun getAllEnvVars(platformId: Int): JSONArray {
        return JSONArray(platformClient.getAllEnvVars(platformId).body().string())
    }

    fun createEnvVar(platformId: Int, envVar: EnvVar): JSONObject {
        return JSONObject(platformClient.createEnvVar(platformId, envVar).body().string())
    }

    fun createEnvVar(envVar: EnvVar): JSONObject {
        return JSONObject(platformClient.createEnvVar(envVar.platformId!!, envVar).body().string())
    }

    fun deleteEnvVar(platformId: Int, envvarId: Int) {
        platformClient.deleteEnvVar(platformId, envvarId).body().string()
    }

    fun deleteEnvVar(envVar: EnvVar) {
        platformClient.deleteEnvVar(envVar.platformId!!, envVar.id!!).body().string()
    }

    fun editEnvVar(platformId: Int, envvarId: Int, envVar: EnvVar): JSONObject {
        return JSONObject(platformClient.editEnvVar(platformId, envvarId, envVar).body().string())
    }

    fun editEnvVar(envVar: EnvVar): JSONObject {
        return JSONObject(platformClient.editEnvVar(envVar.platformId!!, envVar.id!!, envVar).body().string())
    }

    // JOB
    fun getAllJobs(platformId: Int): JSONArray {
        return JSONArray(jobClient.getAllJobs(platformId).body().string())
    }

    fun getAJob(platformId: Int, jobId: Int): JSONObject {
        return JSONObject(jobClient.getAJob(platformId, jobId).body().string())
    }

    fun runAJob(platformId: Int, jobId: Int) {
        jobClient.runAJob(platformId, jobId)
    }

    fun stopAJob(platformId: Int, jobId: Int) {
        jobClient.stopAJob(platformId, jobId)
    }

    fun getJobTasksForAJob(platformId: Int, jobId: Int): JSONObject {
        return JSONObject(jobClient.getJobTasksForAJob(platformId, jobId).body().string())
    }

    fun getAJobTask(jobTaskid: Int): JSONObject {
        return JSONObject(jobClient.getAJobTask(jobTaskid).body().string())
    }

    // WORKFLOW
    fun getAllWorkflows(platformId: Int): JSONArray {
        return JSONArray(workflowClient.getAllWorkflows(platformId).body().string())
    }

    fun getAWorkflow(platformId: Int, workflowId: Int): JSONObject {
        return JSONObject(workflowClient.getAWorkflow(platformId, workflowId).body().string())
    }

    fun getAllWorkflowInstances(platformId: Int, workflowId: Int): JSONObject {
        return JSONObject(workflowClient.getAllWorkflowInstances(platformId, workflowId).body().string())
    }

    fun getAWorkflowInstance(platformId: Int, workflowId: Int, instanceId: Int): JSONObject {
        return JSONObject(workflowClient.getAWorkflowInstance(platformId, workflowId, instanceId).body().string())
    }

    fun runAWorkflow(platformId: Int, workflowId: Int) {
        workflowClient.runAWorkflow(platformId, workflowId)
    }

    fun stopAWorkflow(platformId: Int, workflowId: Int) {
        workflowClient.stopAWorkflow(platformId, workflowId)
    }

    fun runAWorkflowInstance(platformId: Int, workflowId: Int, instanceId: Int) {
        workflowClient.stopAWorkflowInstance(platformId, workflowId, instanceId)
    }
}