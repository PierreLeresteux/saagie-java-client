package io.saagie.client.dto.platform

/**
 * Created by pierre on 27/02/2017.
 */
data class EnvVar(
        var id: Int,
        var name: String,
        var value: String?,
        var isPassword: Boolean,
        var description: String?,
        var platformId: Int?
)