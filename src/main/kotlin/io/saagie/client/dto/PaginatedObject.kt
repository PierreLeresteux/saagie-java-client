package io.saagie.client.dto

/**
 * Created by pierre on 3/29/17.
 */
data class PaginatedObject<T>(
        val totalElements: Int,
        val totalPages: Int,
        val size: Int,
        val number: Int,
        val first: Boolean,
        val last: Boolean,
        val numberOfElements: Int,
        val content: List<T>
)