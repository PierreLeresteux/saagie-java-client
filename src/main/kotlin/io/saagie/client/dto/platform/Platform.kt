package io.saagie.client.dto.platform


/**
 * Created by pierre on 24/02/2017.
 */
data class Platform(
        val id: Int,
        val name: String,
        val capsules: List<Capsule>
)