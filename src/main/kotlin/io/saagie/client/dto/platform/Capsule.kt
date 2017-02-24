package io.saagie.client.dto.platform

/**
 * Created by pierre on 24/02/2017.
 */
data class Capsule(
        var code: String,
        var version: String?,
        var platformId: String,
        var internal: List<InternalExternalInfo>?,
        var external: List<InternalExternalInfo>?

)