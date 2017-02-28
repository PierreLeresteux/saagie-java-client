package io.saagie.client

import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import io.saagie.client.dto.platform.Capsule
import io.saagie.client.dto.platform.EnvVar
import io.saagie.client.dto.platform.Platform
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
                val rawResponse = saagieClientRaw.getAllCapsulesForAPlatorm(2)
                rawResponse shouldEqualTo PlatformConstants.ALL_CAPSULES.value
                val jsonResponse = saagieClientJson.getAllCapsulesForAPlatorm(2)
                jsonResponse.toString() shouldEqualTo JSONArray(PlatformConstants.ALL_CAPSULES.value).toString()
                val response = saagieClient.getAllCapsulesForAPlatorm(2)
                response shouldEqual gson.fromJson<List<Capsule>>(PlatformConstants.ALL_CAPSULES.value)
            }
        }

        on("call capsule for a platform and a capsulecode") {
            it("should return the capsule for a platform and a capsulecode") {
                val rawResponse = saagieClientRaw.getACapsuleForAPlatform(2, "mongo")
                rawResponse shouldEqualTo PlatformConstants.MONGO_CAPSULE.value
                val jsonResponse = saagieClientJson.getACapsuleForAPlatform(2, "mongo")
                jsonResponse.toString() shouldEqualTo JSONObject(PlatformConstants.MONGO_CAPSULE.value).toString()
                val response = saagieClient.getACapsuleForAPlatform(2, "mongo")
                response shouldEqual gson.fromJson<Capsule>(PlatformConstants.MONGO_CAPSULE.value)
            }
        }

        on("call all envVar for a platform") {
            it("should return the list of environnement variable for a platform") {
                val rawResponse = saagieClientRaw.getAllEnvVarsForAPlatform(2)
                rawResponse shouldEqualTo PlatformConstants.ALL_ENVVARS.value
                val jsonResponse = saagieClientJson.getAllEnvVarsForAPlatform(2)
                jsonResponse.toString() shouldEqualTo JSONArray(PlatformConstants.ALL_ENVVARS.value).toString()
                val response = saagieClient.getAllEnvVarsForAPlatform(2)
                response shouldEqual gson.fromJson<List<EnvVar>>(PlatformConstants.ALL_ENVVARS.value)
            }
        }

        on("call create an envVar for a platform") {
            it("should return the created envvar for a platform") {
                val rawResponse = saagieClientRaw.createEnvVarForAPlatform(2, gson.fromJson<EnvVar>(PlatformConstants.CREATED_ENVVAR.value))
                rawResponse shouldEqualTo PlatformConstants.CREATED_ENVVAR.value
                val jsonResponse = saagieClientJson.createEnvVarForAPlatform(2, gson.fromJson<EnvVar>(PlatformConstants.CREATED_ENVVAR.value))
                jsonResponse.toString() shouldEqualTo JSONObject(PlatformConstants.CREATED_ENVVAR.value).toString()
                val response = saagieClient.createEnvVarForAPlatform(2, gson.fromJson<EnvVar>(PlatformConstants.CREATED_ENVVAR.value))
                response shouldEqual gson.fromJson<EnvVar>(PlatformConstants.CREATED_ENVVAR.value)
            }
        }

        afterGroup {
            mockServer.shutdown()
        }
    }
})