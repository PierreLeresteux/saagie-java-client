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

import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import io.saagie.client.dto.PaginatedObject
import io.saagie.client.dto.job.Job
import io.saagie.client.dto.job.JobTask
import io.saagie.client.dto.platform.Capsule
import io.saagie.client.dto.platform.EnvVar
import io.saagie.client.dto.platform.Platform
import io.saagie.client.dto.workflow.Workflow
import io.saagie.client.internal.AbstractSaagieClient

/**
 * Created by pierre on 24/02/2017.
 */
class SaagieClient(override var baseURL: String = "https://manager.prod.saagie.io/api/v1",
                   override var user: String = "",
                   override var password: String = "",
                   override var timeout: Long = 20) : AbstractSaagieClient() {

    val gson = Gson()

    // PLATFORM
    fun getAllPlatforms(): List<Platform> {
        return gson.fromJson(platformClient.getAllPlatforms().body().charStream())
    }

    fun getAPlatform(platformId: Int): Platform {
        return gson.fromJson(platformClient.getAPlatform(platformId).body().string())
    }

    fun getAllCapsules(platformId: Int): List<Capsule> {
        return gson.fromJson(platformClient.getAllCapsules(platformId).body().string())
    }

    fun getACapsule(platformId: Int, capsuleCode: String): Capsule {
        return gson.fromJson(platformClient.getACapsule(platformId, capsuleCode).body().string())
    }

    fun getAllEnvVars(platformId: Int): List<EnvVar> {
        return gson.fromJson(platformClient.getAllEnvVars(platformId).body().string())
    }

    fun createEnvVar(platformId: Int, envVar: EnvVar): EnvVar {
        return gson.fromJson(platformClient.createEnvVar(platformId, envVar).body().string())
    }

    fun createEnvVar(envVar: EnvVar): EnvVar {
        return gson.fromJson(platformClient.createEnvVar(envVar.platformId!!, envVar).body().string())
    }

    fun deleteEnvVar(platformId: Int, envvarId: Int) {
        platformClient.deleteEnvVar(platformId, envvarId).body().string()
    }

    fun deleteEnvVar(envVar: EnvVar) {
        platformClient.deleteEnvVar(envVar.platformId!!, envVar.id!!).body().string()
    }

    fun editEnvVar(platformId: Int, envvarId: Int, envVar: EnvVar): EnvVar {
        return gson.fromJson(platformClient.editEnvVar(platformId, envvarId, envVar).body().string())
    }

    fun editEnvVar(envVar: EnvVar): EnvVar {
        return gson.fromJson(platformClient.editEnvVar(envVar.platformId!!, envVar.id!!, envVar).body().string())
    }

    // JOB
    fun getAllJobs(platformId: Int): List<Job> {
        return gson.fromJson(jobClient.getAllJobs(platformId).body().string())
    }

    fun getAJob(platformId: Int, jobId: Int): Job {
        return gson.fromJson(jobClient.getAJob(platformId, jobId).body().string())
    }

    fun runAJob(platformId: Int, jobId: Int) {
        jobClient.runAJob(platformId, jobId)
    }

    fun stopAJob(platformId: Int, jobId: Int) {
        jobClient.stopAJob(platformId, jobId)
    }

    fun getJobTasksForAJob(platformId: Int, jobId: Int): PaginatedObject<JobTask> {
        return gson.fromJson(jobClient.getJobTasksForAJob(platformId, jobId).body().string())
    }

    fun getJobTask(jobTaskid: Int): JobTask {
        return gson.fromJson(jobClient.getJobTask(jobTaskid).body().string())
    }

    // WORKFLOW
    fun getAllWorkflows(platformId: Int): List<Workflow> {
        return gson.fromJson(workflowClient.getAllWorkflows(platformId).body().string())
    }
}
