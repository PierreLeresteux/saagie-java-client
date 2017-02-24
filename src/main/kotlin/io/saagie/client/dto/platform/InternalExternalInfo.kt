package io.saagie.client.dto.platform

/**
 * Created by pierre on 24/02/2017.
 */
data class InternalExternalInfo(
        var name: String,
        var url: String?,
        var link: Boolean?,
        var instance: List<Instance>?
)