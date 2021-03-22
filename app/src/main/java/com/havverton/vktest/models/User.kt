package com.havverton.vktest.models

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Serializable
data class Response (
    val response: List<VkUser>
)

@Serializable
data class VkUser (
    val id: Long,

    @SerialName("first_name")
    val firstName: String,

    @SerialName("last_name")
    val lastName: String,

    @SerialName("is_closed")
    val isClosed: Boolean,

    @SerialName("can_access_closed")
    val canAccessClosed: Boolean,

)
