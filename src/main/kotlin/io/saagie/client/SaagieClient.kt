package io.saagie.client

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.saagie.client.dto.platform.Platform
import io.saagie.client.internal.AbstractSaagieClient

/**
 * Created by pierre on 24/02/2017.
 */
class SaagieClient : AbstractSaagieClient() {

    val mapper = jacksonObjectMapper()

    fun getAllPlatforms(): List<Platform>? {
        val platforms: List<Platform>? = mapper.readValue(platformClient.getAllPlatforms(this).text)
        return platforms
    }

    fun getAPlatform(id: Int): Platform? {
        val platform: Platform? = mapper.readValue(platformClient.getAPlatform(id, this).text)
        return platform
    }

}
