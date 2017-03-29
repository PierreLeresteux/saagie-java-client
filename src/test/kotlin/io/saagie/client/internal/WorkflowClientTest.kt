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

import io.saagie.client.mockserver.SaagieManagerMockServer
import io.saagie.client.mockserver.WorkflowConstants
import org.amshove.kluent.shouldEqualTo
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

/**
 * Created by pierre on 01/03/2017.
 */
internal class WorkflowClientTest : Spek({
    var workflowClient = WorkflowClient(AbstractSaagieClient())
    val mockServer = SaagieManagerMockServer()

    describe("in a context of a WorkflowClient") {

        beforeGroup {
            mockServer.init()
            workflowClient = WorkflowClient(
                    AbstractSaagieClient(baseURL = mockServer.baseUrl())
            )
        }

        on("call all workflows") {
            it("should return the list of all workflows") {
                val response = workflowClient.getAllWorkflows(1)
                response.code() shouldEqualTo 200
                response.body().string() shouldEqualTo WorkflowConstants.ALL_WORKFLOWS.value
            }
        }
        on("call a workflow") {
            it("should return the workflow") {
                val response = workflowClient.getAWorkflow(1, 1)
                response.code() shouldEqualTo 200
                response.body().string() shouldEqualTo WorkflowConstants.A_WORKFLOW.value
            }
        }
        on("call all workflow instances") {
            it("should return the paginated list of all workflow instances") {
                val response = workflowClient.getAllWorkflowInstances(1, 1)
                response.code() shouldEqualTo 200
                response.body().string() shouldEqualTo WorkflowConstants.ALL_INSTANCES.value
            }
        }
    }
    afterGroup {
        mockServer.shutdown()
    }
})