package io.saagie.client.internal

import khttp.responses.Response
import org.amshove.kluent.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions.fail

/**
 * Created by pierre on 24/02/2017.
 */
internal class AbstractSaagieClientTest : Spek({

    describe("an AbstractClientString") {
        val abstractSaagieClient = AbstractSaagieClient()
        val response = mock(Response::class)
        on("creation") {
            it("should have default values for properties") {
                abstractSaagieClient.baseURL shouldEqualTo "https://manager.prod.saagie.io/api/v1"
                abstractSaagieClient.user.shouldBeEmpty()
                abstractSaagieClient.password.shouldBeEmpty()
                abstractSaagieClient.timeout shouldEqualTo 20.0
            }
        }
        on("checkResponse") {
            it("should not return an exception if status code = 200") {
                When calling response.statusCode itReturns 200
                try {
                    abstractSaagieClient.checkResponse(response)
                } catch (ise: IllegalStateException) {
                    fail("should not return an exception if status code=200")
                }
            }
            it("should not return an exception if status code = 299") {
                val response = mock(Response::class)
                When calling response.statusCode itReturns 299
                try {
                    abstractSaagieClient.checkResponse(response)
                } catch (ise: IllegalStateException) {
                    fail("should not return an exception if status code=299")
                }
            }
            it("should return an exception if status code > 299") {
                val response = mock(Response::class)
                When calling response.statusCode itReturns 300
                try {
                    abstractSaagieClient.checkResponse(response)
                    fail("should return an exception if status code > 299")
                } catch (ise: IllegalStateException) {
                }

            }
        }
    }
})
