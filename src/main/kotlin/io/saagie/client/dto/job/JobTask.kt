package io.saagie.client.dto.job

/**
 * Created by pierre on 3/29/17.
 */
data class JobTask(
        val id: Int,
        val status: String,
        val startDateTime: String,
        val endDateTime: String
)