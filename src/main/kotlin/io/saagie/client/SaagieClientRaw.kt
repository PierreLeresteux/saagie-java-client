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

/**
 * Created by pierre on 24/02/2017.
 */
class SaagieClientRaw(override var baseURL: String = "https://manager.prod.saagie.io/api/v1",
                      override var user: String = "",
                      override var password: String = "",
                      override var timeout: Long = 20) : AbstractSaagieClient() {

    //PLATFORM
    fun getAllPlatforms(): String {
        return platformClient.getAllPlatforms().body().string()
    }

    fun getAPlatform(platformId: Int): String {
        return platformClient.getAPlatform(platformId).body().string()
    }

    fun getAllCapsules(platformId: Int): String {
        return platformClient.getAllCapsules(platformId).body().string()
    }

    fun getACapsule(platformId: Int, capsuleCode: String): String {
        return platformClient.getACapsule(platformId, capsuleCode).body().string()
    }

    fun getAllEnvVars(platformId: Int): String {
        return platformClient.getAllEnvVars(platformId).body().string()
    }

    fun createEnvVar(platformId: Int, envVar: EnvVar): String {
        return platformClient.createEnvVar(platformId, envVar).body().string()
    }

    fun createEnvVar(envVar: EnvVar): String {
        return platformClient.createEnvVar(envVar.platformId!!, envVar).body().string()
    }

    fun deleteEnvVar(platformId: Int, envvarId: Int) {
        platformClient.deleteEnvVar(platformId, envvarId).body().string()
    }

    fun deleteEnvVar(envVar: EnvVar) {
        platformClient.deleteEnvVar(envVar.platformId!!, envVar.id!!)
    }

    fun editEnvVar(platformId: Int, envvarId: Int, envVar: EnvVar): String {
        return platformClient.editEnvVar(platformId, envvarId, envVar).body().string()
    }

    fun editEnvVar(envVar: EnvVar): String {
        return platformClient.editEnvVar(envVar.platformId!!, envVar.id!!, envVar).body().string()
    }

    // JOB
    fun getAllJobs(platformId: Int): String {
        return jobClient.getAllJobs(platformId).body().string()
    }

    fun getAJob(platformId: Int, jobId: Int): String {
        return jobClient.getAJob(platformId, jobId).body().string()
    }

    fun runAJob(platformId: Int, jobId: Int) {
        jobClient.runAJob(platformId, jobId)
    }

    fun stopAJob(platformId: Int, jobId: Int) {
        jobClient.stopAJob(platformId, jobId)
    }

    fun getJobTasksForAJob(platformId: Int, jobId: Int): String {
        return jobClient.getJobTasksForAJob(platformId, jobId).body().string()
    }
}