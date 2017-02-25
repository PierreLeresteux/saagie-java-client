package io.saagie.client

import io.saagie.client.internal.AbstractSaagieClient
import io.saagie.client.internal.PlatformClient
import khttp.responses.Response
import org.amshove.kluent.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.api.dsl.xdescribe


/**
 * Created by pierre on 25/02/2017.
 */
internal class SaagieClientRawTest : Spek({
    xdescribe("a SaagieClientRaw") {
        val saagieClientRaw = SaagieClientRaw()
        val abstractClient = AbstractSaagieClient()
        val platformClientMock = mock(PlatformClient::class)
        platformClientMock.client = abstractClient
        val responseMock = mock(Response::class)
        saagieClientRaw.platformClient = platformClientMock

        When calling platformClientMock.getAllPlatforms() itReturns responseMock
        When calling responseMock.statusCode itReturns 200
        When calling responseMock.text itReturns "[]"
        on("try to get all platforms") {
            it("should have a proper JSON in String") {
                val allPlatforms = saagieClientRaw.getAllPlatforms()
                allPlatforms shouldEqualTo "[]"
            }
        }
    }
})