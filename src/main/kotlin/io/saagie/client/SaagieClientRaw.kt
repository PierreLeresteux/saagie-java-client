package io.saagie.client

import io.saagie.client.internal.AbstractSaagieClient

/**
 * Created by pierre on 24/02/2017.
 */
class SaagieClientRaw : AbstractSaagieClient() {

    fun getAllPlatforms(): String {
        return platformClient.getAllPlatforms(this).text
    }

    fun getAPlatform(id: Int): String {
        return platformClient.getAPlatform(id, this).text
    }
}