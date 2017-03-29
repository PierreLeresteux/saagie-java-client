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
package io.saagie.client.mockserver

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest


/**
 * Created by pierre on 28/02/2017.
 */
class SaagieManagerMockServer {

    var server = MockWebServer()
    val baseUrl = server.url("/v1/api")

    fun init() {
        //server.start();
        val dispatcher = object : Dispatcher() {

            @Throws(InterruptedException::class)
            override fun dispatch(request: RecordedRequest): MockResponse {
                when (request.path) {
                    baseUrl.encodedPath() + "/platform" -> return MockResponse().setResponseCode(200).setBody(PlatformConstants.ALL_PLATFORMS.value)
                    baseUrl.encodedPath() + "/platform/2" -> return MockResponse().setResponseCode(200).setBody(PlatformConstants.ONE_PLATFORM.value)
                    baseUrl.encodedPath() + "/platform/2/connectioninfo" -> return MockResponse().setResponseCode(200).setBody(PlatformConstants.ALL_CAPSULES.value)
                    baseUrl.encodedPath() + "/platform/2/connectioninfo/mongo" -> return MockResponse().setResponseCode(200).setBody(PlatformConstants.MONGO_CAPSULE.value)
                    baseUrl.encodedPath() + "/platform/2/envvars" -> {
                        if (request.method == "POST")
                            return MockResponse().setResponseCode(200).setBody(PlatformConstants.CREATED_ENVVAR.value)
                        return MockResponse().setResponseCode(200).setBody(PlatformConstants.ALL_ENVVARS.value)
                    }
                    baseUrl.encodedPath() + "/platform/2/envvars/1" -> {
                        if (request.method == "DELETE")
                            return MockResponse().setResponseCode(204)
                        return MockResponse().setResponseCode(200).setBody(PlatformConstants.CREATED_ENVVAR.value)
                    }
                    baseUrl.encodedPath() + "/platform/2/job" -> return MockResponse().setResponseCode(200).setBody(JobConstants.ALL_JOBS.value)
                    baseUrl.encodedPath() + "/platform/2/job/1" -> {
                        if (request.method == "POST")
                            return MockResponse().setResponseCode(204)
                        return MockResponse().setResponseCode(200).setBody(JobConstants.A_JOB.value)
                    }
                    baseUrl.encodedPath() + "/platform/2/job/1/run" -> return MockResponse().setResponseCode(204)
                    baseUrl.encodedPath() + "/platform/2/job/1/stop" -> return MockResponse().setResponseCode(204)
                    baseUrl.encodedPath() + "/platform/2/job/1/jobtask" -> return MockResponse().setResponseCode(200).setBody(JobConstants.ALL_JOBTASKS.value)
                    baseUrl.encodedPath() + "/jobtask/1" -> return MockResponse().setResponseCode(200).setBody(JobConstants.A_JOBTASK.value)
                    baseUrl.encodedPath() + "/platform/1/workflow" -> return MockResponse().setResponseCode(200).setBody(WorkflowConstants.ALL_WORKFLOWS.value)
                    baseUrl.encodedPath() + "/platform/1/workflow/1" -> return MockResponse().setResponseCode(200).setBody(WorkflowConstants.A_WORKFLOW.value)
                }
                return MockResponse().setResponseCode(404)
            }
        }
        server.setDispatcher(dispatcher)
    }

    fun baseUrl(): String {
        return "http://" + server.hostName + ":" + server.port + baseUrl.encodedPath()
    }

    fun shutdown() {
        server.shutdown()
    }
}