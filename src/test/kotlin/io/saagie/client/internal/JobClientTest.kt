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
import io.saagie.client.mockserver.ClientConstants
import io.saagie.client.mockserver.SaagieManagerMockServer
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
    val gson = Gson()

    describe("in a context of a JobClient") {

        beforeGroup {
            mockServer.init()
            jobClient = JobClient(
                    AbstractSaagieClient(baseURL = mockServer.baseUrl())
            )
        }

        on("call all jobs") {
            it("should return the list of all jobs") {
                val response = jobClient.getAllJobsForAPlatform(2)
                response.code() shouldEqualTo 200
                response.body().string() shouldEqualTo ClientConstants.ALL_JOBS.value
            }
        }
        on("call a job") {
            it("should return the job") {
                val response = jobClient.getAJobForAPlatform(2, 1)
                response.code() shouldEqualTo 200
                response.body().string() shouldEqualTo ClientConstants.A_JOB.value
            }
        }
    }
    afterGroup {
        mockServer.shutdown()
    }
})