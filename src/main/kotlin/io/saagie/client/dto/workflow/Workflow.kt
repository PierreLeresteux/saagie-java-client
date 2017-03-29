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
package io.saagie.client.dto.workflow

import io.saagie.client.dto.job.Job

/**
 * Created by pierre on 01/03/2017.
 */
data class Workflow(
        val inCurrent: Boolean?,
        val id: Int,
        val name: String,
        val createDate: String?,
        val modificationDate: String?,
        val platformId: Int?,
        val jobCount: Int?,
        val lastInstance: Instance?,
        val jobs: List<Job>?,
        val runningInstances: Int
)