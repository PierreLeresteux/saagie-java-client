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

import io.saagie.client.mockserver.PlatformConstants
import io.saagie.client.mockserver.SaagieManagerMockServer
import org.amshove.kluent.shouldEqualTo
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on


/**
 * Created by pierre on 28/02/2017.
 */
internal class PlatformClientTest : Spek({
    var platformClient = PlatformClient(AbstractSaagieClient())
    val mockServer = SaagieManagerMockServer()

    describe("in a context of a PlatformClient") {

        beforeGroup {
            mockServer.init()
            platformClient = PlatformClient(
                    AbstractSaagieClient(baseURL = mockServer.baseUrl())
            )
        }
        on("call all platforms") {
            it("should return the list of all platforms") {
                val response = platformClient.getAllPlatforms()
                response.code() shouldEqualTo 200
                response.body().string() shouldEqualTo PlatformConstants.ALL_PLATFORMS.value
            }
        }

        on("call one platform") {
            it("should return the desired platform") {
                val response = platformClient.getAPlatform(2)
                response.code() shouldEqualTo 200
                response.body().string() shouldEqualTo PlatformConstants.ONE_PLATFORM.value
            }
        }

        on("call connection information for a platform") {
            it("should return the list of connection information for a platform") {
                val response = platformClient.getAllCapsulesForAPlatorm(2)
                response.code() shouldEqualTo 200
                response.body().string() shouldEqualTo PlatformConstants.ALL_CAPSULES.value
            }
        }

        on("call connection information for a platform and a capsulecode") {
            it("should return the connection information for a platform and a capsulecode") {
                val response = platformClient.getACapsuleForAPlatform(2, "mongo")
                response.code() shouldEqualTo 200
                response.body().string() shouldEqualTo PlatformConstants.MONGO_CAPSULE.value
            }
        }

        on("call all envVar for a platform") {
            it("should return the list of environnement variable for a platform") {
                val response = platformClient.getAllEnvVarsForAPlatform(2)
                response.code() shouldEqualTo 200
                response.body().string() shouldEqualTo PlatformConstants.ALL_ENVVARS.value
            }
        }

        afterGroup {
            mockServer.shutdown()
        }
    }
})