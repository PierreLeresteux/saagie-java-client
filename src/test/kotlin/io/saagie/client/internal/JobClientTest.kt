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

import io.saagie.client.mockserver.ClientConstants
import io.saagie.client.mockserver.SaagieManagerMockServer
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldEqualTo
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

/**
 * Created by pierre on 01/03/2017.
 */
internal class JobClientTest : Spek({
    var jobClient = JobClient(AbstractSaagieClient())
    val mockServer = SaagieManagerMockServer()

    describe("in a context of a JobClient") {

        beforeGroup {
            mockServer.init()
            jobClient = JobClient(
                    AbstractSaagieClient(baseURL = mockServer.baseUrl())
            )
        }

        on("call all jobs") {
            it("should return the list of all jobs") {
                val response = jobClient.getAllJobs(2)
                response.code() shouldEqualTo 200
                response.body().string() shouldEqualTo ClientConstants.ALL_JOBS.value
            }
        }
        on("call a job") {
            it("should return the job") {
                val response = jobClient.getAJob(2, 1)
                response.code() shouldEqualTo 200
                response.body().string() shouldEqualTo ClientConstants.A_JOB.value
            }
        }
        on("run a job") {
            it("should run the job and return a 204") {
                val response = jobClient.runAJob(2, 1)
                response.code() shouldEqualTo 204
                response.body().string().shouldBeEmpty()
            }

        }
        on("stop a job") {
            it("should stop the job and return a 204") {
                val response = jobClient.stopAJob(2, 1)
                response.code() shouldEqualTo 204
                response.body().string().shouldBeEmpty()
            }

        }
        on("get all jobtasks for a job") {
            it("should return the paginated list of jobtasks") {
                val response = jobClient.getJobTasksForAJob(2, 1)
                response.code() shouldEqualTo 200
                response.body().string() shouldEqualTo ClientConstants.ALL_JOBTASKS.value
            }
        }
        on("get one jobtask") {
            it("should return the jobtask") {
                val response = jobClient.getJobTask(1)
                response.code() shouldEqualTo 200
                response.body().string() shouldEqualTo ClientConstants.A_JOBTASK.value
            }
        }
    }
    afterGroup {
        mockServer.shutdown()
    }
})