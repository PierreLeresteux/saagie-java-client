package io.saagie.client.internal

import khttp.responses.Response

/**
 * Created by pierre on 24/02/2017.
 */
open class AbstractSaagieClient {

    var baseURL: String = "https://manager.prod.saagie.io/api/v1"
    var user: String = ""
    var password: String = ""
    var timeout: Double = 20.0

    var platformClient = PlatformClient()


    fun changePlatformClient(value: PlatformClient) {
        platformClient = value
    }

    internal fun checkResponse(response: Response) {
        if (response.statusCode > 299)
            throw IllegalStateException("\n---------------------\nError during call to API \n - StatusCode :  '" + response.statusCode + "'\n - Message : '" + response.text + "'\n---------------------\n")
    }

}