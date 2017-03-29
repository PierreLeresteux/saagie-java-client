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
import io.saagie.client.mockserver.ClientConstants
import io.saagie.client.mockserver.PlatformConstants
import io.saagie.client.mockserver.SaagieManagerMockServer
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldEqualTo
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by pierre on 28/02/2017.
 */
internal class SaagieClientsTest : Spek({
    val mockServer = SaagieManagerMockServer()
    var saagieClientRaw = SaagieClientRaw()
    var saagieClientJson = SaagieClientJson()
    var saagieClient = SaagieClient()
    val gson = Gson()

    describe("in a context of a SaagieClients") {

        beforeGroup {
            mockServer.init()
            saagieClientRaw = SaagieClientRaw(baseURL = mockServer.baseUrl())
            saagieClientJson = SaagieClientJson(baseURL = mockServer.baseUrl())
            saagieClient = SaagieClient(baseURL = mockServer.baseUrl())
        }

        on("call all platforms") {
            it("should return the list of all platforms") {
                val rawResponse = saagieClientRaw.getAllPlatforms()
                rawResponse shouldEqualTo PlatformConstants.ALL_PLATFORMS.value
                val jsonResponse = saagieClientJson.getAllPlatforms()
                jsonResponse.toString() shouldEqualTo JSONArray(PlatformConstants.ALL_PLATFORMS.value).toString()
                val response = saagieClient.getAllPlatforms()
                response shouldEqual gson.fromJson<List<Platform>>(PlatformConstants.ALL_PLATFORMS.value)
            }
        }

        on("call one platform") {
            it("should return the desired platform") {
                val rawResponse = saagieClientRaw.getAPlatform(2)
                rawResponse shouldEqualTo PlatformConstants.ONE_PLATFORM.value
                val jsonResponse = saagieClientJson.getAPlatform(2)
                jsonResponse.toString() shouldEqualTo JSONObject(PlatformConstants.ONE_PLATFORM.value).toString()
                val response = saagieClient.getAPlatform(2)
                response shouldEqual gson.fromJson<Platform>(PlatformConstants.ONE_PLATFORM.value)
            }
        }

        on("call all capsules for a platform") {
            it("should return the list of capsules for a platform") {
                val rawResponse = saagieClientRaw.getAllCapsules(2)
                rawResponse shouldEqualTo PlatformConstants.ALL_CAPSULES.value
                val jsonResponse = saagieClientJson.getAllCapsules(2)
                jsonResponse.toString() shouldEqualTo JSONArray(PlatformConstants.ALL_CAPSULES.value).toString()
                val response = saagieClient.getAllCapsules(2)
                response shouldEqual gson.fromJson<List<Capsule>>(PlatformConstants.ALL_CAPSULES.value)
            }
        }

        on("call capsule for a platform and a capsulecode") {
            it("should return the capsule for a platform and a capsulecode") {
                val rawResponse = saagieClientRaw.getACapsule(2, "mongo")
                rawResponse shouldEqualTo PlatformConstants.MONGO_CAPSULE.value
                val jsonResponse = saagieClientJson.getACapsule(2, "mongo")
                jsonResponse.toString() shouldEqualTo JSONObject(PlatformConstants.MONGO_CAPSULE.value).toString()
                val response = saagieClient.getACapsule(2, "mongo")
                response shouldEqual gson.fromJson<Capsule>(PlatformConstants.MONGO_CAPSULE.value)
            }
        }

        on("call all envVar for a platform") {
            it("should return the list of environnement variable for a platform") {
                val rawResponse = saagieClientRaw.getAllEnvVars(2)
                rawResponse shouldEqualTo PlatformConstants.ALL_ENVVARS.value
                val jsonResponse = saagieClientJson.getAllEnvVars(2)
                jsonResponse.toString() shouldEqualTo JSONArray(PlatformConstants.ALL_ENVVARS.value).toString()
                val response = saagieClient.getAllEnvVars(2)
                response shouldEqual gson.fromJson<List<EnvVar>>(PlatformConstants.ALL_ENVVARS.value)
            }
        }

        on("call create an envVar for a platform") {
            it("should return the created envvar for a platform") {
                val rawResponse = saagieClientRaw.createEnvVar(2, gson.fromJson<EnvVar>(PlatformConstants.CREATED_ENVVAR.value))
                rawResponse shouldEqualTo PlatformConstants.CREATED_ENVVAR.value
                val jsonResponse = saagieClientJson.createEnvVar(2, gson.fromJson<EnvVar>(PlatformConstants.CREATED_ENVVAR.value))
                jsonResponse.toString() shouldEqualTo JSONObject(PlatformConstants.CREATED_ENVVAR.value).toString()
                val response = saagieClient.createEnvVar(2, gson.fromJson<EnvVar>(PlatformConstants.CREATED_ENVVAR.value))
                response shouldEqual gson.fromJson<EnvVar>(PlatformConstants.CREATED_ENVVAR.value)
            }
        }

        on("call create an envVar for a platform without platformId") {
            it("should return the created envvar for a platform") {
                val rawResponse = saagieClientRaw.createEnvVar(gson.fromJson<EnvVar>(PlatformConstants.CREATED_ENVVAR.value))
                rawResponse shouldEqualTo PlatformConstants.CREATED_ENVVAR.value
                val jsonResponse = saagieClientJson.createEnvVar(gson.fromJson<EnvVar>(PlatformConstants.CREATED_ENVVAR.value))
                jsonResponse.toString() shouldEqualTo JSONObject(PlatformConstants.CREATED_ENVVAR.value).toString()
                val response = saagieClient.createEnvVar(gson.fromJson<EnvVar>(PlatformConstants.CREATED_ENVVAR.value))
                response shouldEqual gson.fromJson<EnvVar>(PlatformConstants.CREATED_ENVVAR.value)
            }
        }

        on("call update an envVar for a platform") {
            it("should return the updated envvar for a platform") {
                val rawResponse = saagieClientRaw.editEnvVar(2, 1, gson.fromJson<EnvVar>(PlatformConstants.CREATED_ENVVAR.value))
                rawResponse shouldEqualTo PlatformConstants.CREATED_ENVVAR.value
                val jsonResponse = saagieClientJson.editEnvVar(2, 1, gson.fromJson<EnvVar>(PlatformConstants.CREATED_ENVVAR.value))
                jsonResponse.toString() shouldEqualTo JSONObject(PlatformConstants.CREATED_ENVVAR.value).toString()
                val response = saagieClient.editEnvVar(2, 1, gson.fromJson<EnvVar>(PlatformConstants.CREATED_ENVVAR.value))
                response shouldEqual gson.fromJson<EnvVar>(PlatformConstants.CREATED_ENVVAR.value)
            }
        }

        on("call update an envVar for a platform without platformId") {
            it("should return the updated envvar for a platform") {
                val rawResponse = saagieClientRaw.editEnvVar(gson.fromJson<EnvVar>(PlatformConstants.CREATED_ENVVAR.value))
                rawResponse shouldEqualTo PlatformConstants.CREATED_ENVVAR.value
                val jsonResponse = saagieClientJson.editEnvVar(gson.fromJson<EnvVar>(PlatformConstants.CREATED_ENVVAR.value))
                jsonResponse.toString() shouldEqualTo JSONObject(PlatformConstants.CREATED_ENVVAR.value).toString()
                val response = saagieClient.editEnvVar(gson.fromJson<EnvVar>(PlatformConstants.CREATED_ENVVAR.value))
                response shouldEqual gson.fromJson<EnvVar>(PlatformConstants.CREATED_ENVVAR.value)
            }
        }

        on("call all jobs") {
            it("should return the list of all jobs") {
                val rawResponse = saagieClientRaw.getAllJobs(2)
                rawResponse shouldEqualTo ClientConstants.ALL_JOBS.value
                val jsonResponse = saagieClientJson.getAllJobs(2)
                jsonResponse.toString() shouldEqualTo JSONArray(ClientConstants.ALL_JOBS.value).toString()
                val response = saagieClient.getAllJobs(2)
                response shouldEqual gson.fromJson<List<Job>>(ClientConstants.ALL_JOBS.value)
            }
        }

        on("call a job") {
            it("should return the job") {
                val rawResponse = saagieClientRaw.getAJob(2, 1)
                rawResponse shouldEqualTo ClientConstants.A_JOB.value
                val jsonResponse = saagieClientJson.getAJob(2, 1)
                jsonResponse.toString() shouldEqualTo JSONObject(ClientConstants.A_JOB.value).toString()
                val response = saagieClient.getAJob(2, 1)
                response shouldEqual gson.fromJson<Job>(ClientConstants.A_JOB.value)
            }
        }
        on("get all jobtasks for a job") {
            it("should return the paginated jobtasks list of a job") {
                val rawResponse = saagieClientRaw.getJobTasksForAJob(2, 1)
                rawResponse shouldEqualTo ClientConstants.ALL_JOBTASKS.value
                val jsonResponse = saagieClientJson.getJobTasksForAJob(2, 1)
                jsonResponse.toString() shouldEqualTo JSONObject(ClientConstants.ALL_JOBTASKS.value).toString()
                val response = saagieClient.getJobTasksForAJob(2, 1)
                response shouldEqual gson.fromJson<PaginatedObject<JobTask>>(ClientConstants.ALL_JOBTASKS.value)
            }
        }

        afterGroup {
            mockServer.shutdown()
        }
    }
})