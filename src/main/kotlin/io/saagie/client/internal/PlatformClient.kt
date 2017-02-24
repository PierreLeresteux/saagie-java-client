package io.saagie.client.internal

import khttp.get
import khttp.responses.Response
import khttp.structures.authorization.BasicAuthorization

/**
 * Created by pierre on 24/02/2017.
 */
open class PlatformClient {

    fun getAllPlatforms(client: AbstractSaagieClient): Response {
        val response = get(client.baseURL + "/platform", auth = BasicAuthorization(client.user, client.password), timeout = client.timeout)
        client.checkResponse(response)
        return response
    }

    fun getAPlatform(id: Int, client: AbstractSaagieClient): Response {
        val response = get(client.baseURL + "/platform/" + id, auth = BasicAuthorization(client.user, client.password), timeout = client.timeout)
        client.checkResponse(response)
        return response
    }


}