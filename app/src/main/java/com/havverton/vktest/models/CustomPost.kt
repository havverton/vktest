package com.havverton.vktest.models

import androidx.recyclerview.widget.RecyclerView
import kotlinx.serialization.*


@Serializable
data class CustomPost (
    val authorName:String,
    val postTime:String,
    val avatar50:String,
    val text:String,
    val imageList:List<String>,
    val likesCount:Long,
    val commentsCount:Long,
    val repostCount:Long,
    val viewsCount:Long
)