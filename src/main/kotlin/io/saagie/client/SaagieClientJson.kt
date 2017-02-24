package io.saagie.client

import io.saagie.client.internal.AbstractSaagieClient
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by pierre on 24/02/2017.
 */
class SaagieClientJson : AbstractSaagieClient() {

    fun getAllPlatforms(): JSONArray {
        return platformClient.getAllPlatforms(this).jsonArray
    }

    fun getAPlatform(id: Int): JSONObject {
        return platformClient.getAPlatform(id, this).jsonObject
    }
}