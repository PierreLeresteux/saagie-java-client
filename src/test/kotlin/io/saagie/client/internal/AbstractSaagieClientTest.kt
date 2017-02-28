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

import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldEqualTo
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

/**
 * Created by pierre on 24/02/2017.
 */
internal class AbstractSaagieClientTest : Spek({

    describe("an AbstractClientString") {
        val abstractSaagieClient = AbstractSaagieClient()
        on("creation without fields") {
            it("should have default values for properties") {
                abstractSaagieClient.baseURL shouldEqualTo "https://manager.prod.saagie.io/api/v1"
                abstractSaagieClient.user.shouldBeEmpty()
                abstractSaagieClient.password.shouldBeEmpty()
                abstractSaagieClient.timeout shouldEqualTo 20
            }
        }
        on("creation with fields") {
            it("should have overriden properties") {
                val overridenAbstractSaagieClient = AbstractSaagieClient(
                        baseURL = "http://new",
                        user = "newUser",
                        password = "newPassword",
                        timeout = 10)
                overridenAbstractSaagieClient.baseURL shouldEqualTo "http://new"
                overridenAbstractSaagieClient.user shouldEqualTo "newUser"
                overridenAbstractSaagieClient.password shouldEqualTo "newPassword"
                overridenAbstractSaagieClient.timeout shouldEqualTo 10
            }
        }
        /*on("checkResponse") {
            it("should not return an exception if status code = 200") {
                try {
                    abstractSaagieClient.checkResponse(200,"")
                } catch (ise: IllegalStateException) {
                    fail("should not return an exception if status code=200")
                }
            }
            it("should not return an exception if status code = 299") {
                try {
                    abstractSaagieClient.checkResponse(299,"")
                } catch (ise: IllegalStateException) {
                    fail("should not return an exception if status code=299")
                }
            }
            it("should return an exception if status code > 299") {
                try {
                    abstractSaagieClient.checkResponse(300,"")
                    fail("should return an exception if status code > 299")
                } catch (ise: IllegalStateException) {
                }
            }
        }*/
        on("constructUrl") {
            it("should construct an url with one argument") {
                val constructURL = abstractSaagieClient.constructURL("one")
                constructURL shouldEqualTo "https://manager.prod.saagie.io/api/v1/one"
            }
            it("should construct an url with two argument") {
                val constructURL = abstractSaagieClient.constructURL("one", "two")
                constructURL shouldEqualTo "https://manager.prod.saagie.io/api/v1/one/two"
            }
        }
    }
})
