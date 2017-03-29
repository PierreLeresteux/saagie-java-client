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

/**
 * Created by pierre on 3/29/17.
 */
enum class WorkflowConstants(val value: String) {
    ALL_WORKFLOWS("""[{"id":15,"name":"echo toto","createDate":"2016-11-29T15:29:31+0000","modificationDate":"2016-12-15T09:33:40+0000","platformId":1,"jobCount":2,"lastInstance":{"id":575,"startDate":"2016-11-29T15:37:46+0000","endDate":"2016-11-29T15:38:08+0000","workflowId":15,"status":"REQUESTED"},"runningInstances":0},{"id":16,"name":"Pierre Pipeline","createDate":"2017-03-29T14:34:32+0000","modificationDate":"2017-03-29T14:34:32+0000","platformId":1,"jobCount":2,"lastInstance":{"id":578,"startDate":"2017-03-29T14:35:25+0000","endDate":"2017-03-29T14:35:36+0000","workflowId":16,"status":"SUCCESS"},"runningInstances":0}]"""),
    A_WORKFLOW("""{"id":16,"name":"Pierre Pipeline","createDate":"2017-03-29T14:34:32+0000","modificationDate":"2017-03-29T14:34:32+0000","platformId":1,"jobs":[{"id":490,"name":"5sec","capsule_code":"sqoop","category":"extract","position":0,"current":{"number":1}},{"id":488,"name":"Stats dev2","capsule_code":"java-scala","category":"extract","position":1,"current":{"number":1}}],"lastInstance":{"id":578,"startDate":"2017-03-29T14:35:25+0000","endDate":"2017-03-29T14:35:36+0000","workflowId":16,"status":"SUCCESS"},"runningInstances":0}""")
}