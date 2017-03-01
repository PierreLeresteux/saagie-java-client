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
package io.saagie.client.dto.job

import io.saagie.client.dto.workflow.Workflow

/**
 * Created by pierre on 01/03/2017.
 */
data class Job(
        val id: Int?,
        val capsule_code: String,
        val current: Version?,
        val version: List<Version>?,
        val streaming: Boolean?,
        val category: String,
        val name: String,
        val platformId: Int,
        val manual: Boolean?,
        val schedule: String?,
        val retry: String?,
        val last_state: LastState?,
        val workflow: List<Workflow>?,
        val deletable: Boolean?,
        val description: String?
)