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
 * Created by pierre on 01/03/2017.
 */
enum class JobConstants(val value: String) {
    ALL_JOBS("""[{"id":307,"capsule_code":"sqoop","current":{"id":305,"job_id":307,"number":1,"template":"do this","creation_date":"2016-02-23T17:56:16+00:00","options":[],"enableAuth":true},"versions":[{"id":305,"job_id":307,"number":1,"template":"do this","creation_date":"2016-02-23T17:56:16+00:00","options":[],"enableAuth":true}],"streaming":false,"category":"extract","name":"job1","platform_id":2,"manual":true,"schedule":"R0\/2016-02-23T16:56:02.908Z\/P0Y0M1DT0H0M0S","retry":"","last_state":{"id":82122,"state":"STOPPED","date":"2016-04-11T17:22:24+00:00","lastTaskStatus":"SUCCESS","lastTaskId":168749},"workflows":[],"deletable":true},{"id":9313,"job_id":4284,"number":2,"template":"Rscript {file} arg1 arg2","file":"1488379031-example_v2.r","creation_date":"2017-03-01T14:37:13+00:00","options":[],"cpu":0.4,"memory":512,"disk":512,"releaseNote":"","streaming":false,"category":"processing","name":"job2","email":"","platform_id":2,"manual":true,"schedule":"R0\/2017-03-01T14:36:25.114Z\/P0Y0M1DT0H0M0S","retry":"","last_state":{"id":199547,"state":"STOPPED","date":"2017-03-01T14:37:13+00:00","lastTaskStatus":"KILLED","lastTaskId":786485},"workflows":[],"deletable":true}]"""),
    A_JOB("""{"id":307,"capsule_code":"sqoop","current":{"id":305,"job_id":307,"number":1,"template":"do this","creation_date":"2016-02-23T17:56:16+00:00","options":[],"enableAuth":true},"versions":[{"id":305,"job_id":307,"number":1,"template":"do this","creation_date":"2016-02-23T17:56:16+00:00","options":[],"enableAuth":true}],"streaming":false,"category":"extract","name":"job1","platform_id":2,"manual":true,"schedule":"R0\/2016-02-23T16:56:02.908Z\/P0Y0M1DT0H0M0S","retry":"","last_state":{"id":82122,"state":"STOPPED","date":"2016-04-11T17:22:24+00:00","lastTaskStatus":"SUCCESS","lastTaskId":168749},"workflows":[],"deletable":true}"""),
    ALL_JOBTASKS("""{"content":[{"id":15682,"status":"KILLED","startDateTime":"2017-03-29T08:27:42+00:00","endDateTime":"2017-03-29T08:27:44+00:00"},{"id":15681,"status":"KILLED","startDateTime":"2017-03-29T07:54:36+00:00","endDateTime":"2017-03-29T07:54:38+00:00"},{"id":15680,"status":"SUCCESS","startDateTime":"2017-03-29T07:54:06+00:00","endDateTime":"2017-03-29T07:54:11+00:00"}],"totalElements":3,"totalPages":1,"size":20,"number":1,"first":true,"last":true,"numberOfElements":3}"""),
    A_JOBTASK("""{"id":1,"job_id":490,"platform_id":1,"status":"SUCCESS","version_number":1,"startDateTime":"2017-03-29T07:54:06+00:00","endDateTime":"2017-03-29T07:54:11+00:00","logs_out":"Forked command at 14146\nStart\nStop\nCommand exited with status 0 (pid: 14146)\n","logs_err":"I0329 09:54:06.348508 14136 exec.cpp:162] Version: 1.1.0\nI0329 09:54:06.349452 14139 exec.cpp:237] Executor registered on agent 89890189-3815-487e-a007-3c9b193df085-S1\n"}""")
}