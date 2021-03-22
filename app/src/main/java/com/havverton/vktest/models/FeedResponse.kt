package com.havverton.vktest.models

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Serializable
data class MainFeedResponse (
    val response: FeedResponse
)

@Serializable
data class FeedResponse (
    val items: List<Item>,
    val profiles: List<Profile>,
    val groups: List<Group>,

    @SerialName("next_from")
    val nextFrom: String
)

@Serializable
data class Group (
    val id: Long,
    val name: String,

    @SerialName("screen_name")
    val screenName: String,

    @SerialName("is_closed")
    val isClosed: Long,

    val type: String,

   /* @SerialName("is_admin")
    val isAdmin: Long,*/

    /*@SerialName("is_member")
    val isMember: Long,

    @SerialName("is_advertiser")
    val isAdvertiser: Long,
*/
    @SerialName("photo_50")
    val photo50: String,

    @SerialName("photo_100")
    val photo100: String,

    @SerialName("photo_200")
    val photo200: String
)

@Serializable
data class Item (
    @SerialName("source_id")
    val sourceID: Long,

    val date: Long,

    @SerialName("can_doubt_category")
    val canDoubtCategory: Boolean = false,

    @SerialName("can_set_category")
    val canSetCategory: Boolean = false,

    @SerialName("post_type")
    val postType: String = "",

    val text: String = "",
    val attachments: List<Attachment> = emptyList(),

    val comments: Comments? = null,
    val likes: Likes? = null,
    val reposts: Reposts? = null,
    val views: Views? = null,
/*
    @SerialName("marked_as_ads")
    val markedAsAds: Long,



    @SerialName("post_source")
    val postSource: PostSource,



    @SerialName("is_favorite")
    val isFavorite: Boolean,

    val donut: Donut,

    @SerialName("short_text_rate")
    val shortTextRate: Double,
*/
    @SerialName("post_id")
    val postID: Long,

    val type: String
)

@Serializable
data class Attachment (
    val type: String = "",
    val photo: Photo? = null
)

@Serializable
data class Photo (
    @SerialName("album_id")
    val albumID: Long  = 0L,

    val date: Long = 0L ,
    val id: Long = 0L,

    @SerialName("owner_id")
    val ownerID: Long = 0L,

    @SerialName("has_tags")
    val hasTags: Boolean = false,

    @SerialName("access_key")
    val accessKey: String = "",

    @SerialName("post_id")
    val postID: Long = 0L,

    val sizes: List<Size>,
    val text: String = "",

    @SerialName("user_id")
    val userID: Long = 0L
)

@Serializable
data class Size (
    val height: Long = 0L,
    val url: String = "",
    val type: String ="",
    val width: Long = 0L
)

@Serializable
data class Comments (
    val count: Long,

    @SerialName("can_post")
    val canPost: Long
)

/*@Serializable
data class Donut (
    @SerialName("is_donut")
    val isDonut: Boolean
)*/

@Serializable
data class Likes (
    val count: Long,

    @SerialName("user_likes")
    val userLikes: Long,

    @SerialName("can_like")
    val canLike: Long,

    @SerialName("can_publish")
    val canPublish: Long
)

@Serializable
data class PostSource (
    val type: String
)

@Serializable
data class Reposts (
    val count: Long,

    @SerialName("user_reposted")
    val userReposted: Long
)

@Serializable
data class Views (
    val count: Long
)

@Serializable
data class Profile (
    @SerialName("first_name")
    val firstName: String = "",

    val id: Long = 0L,

    @SerialName("last_name")
    val lastName: String = "",

    @SerialName("can_access_closed")
    val canAccessClosed: Boolean = false,

    @SerialName("is_closed")
    val isClosed: Boolean = false,

    val sex: Long = 0L,

    @SerialName("screen_name")
    val screenName: String = "",

    @SerialName("photo_50")
    val photo50: String = "",

    @SerialName("photo_100")
    val photo100: String = "",

    @SerialName("online_info")
    val onlineInfo: OnlineInfo? = null,

    val online: Long = 0L
)

@Serializable
data class OnlineInfo (
    val visible: Boolean,
    val status: String
)
