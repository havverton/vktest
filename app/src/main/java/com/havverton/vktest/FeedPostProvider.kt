package com.havverton.vktest

import android.util.Log
import com.havverton.vktest.models.CustomPost
import com.havverton.vktest.models.Item
import com.havverton.vktest.models.Photo
import com.havverton.vktest.network.RetrofitModule
import java.util.*
import kotlin.IllegalArgumentException

class FeedPostProvider {
    suspend fun getPostList(): List<CustomPost> {
        val response = RetrofitModule.vkApi.getFeed(MainActivity.token!!).response
        val items = response.items
        val groups = response.groups
        val profiles = response.profiles

        var post: CustomPost? = null

        var postList = mutableListOf<CustomPost>()
        items.forEach { item: Item ->
            val authorID = item.sourceID
            var authorName = ""
            var authorAvatarPath = ""
            val imagesList = mutableListOf<String>()

            val attachments = item.attachments
            var attachPhotoCounter = 0

            attachments.forEach { attachment ->
                if (attachment.type == "photo") {
                    attachPhotoCounter++
                }
            }

            attachments.forEach { attachment ->
                if (attachment.type == "photo") {
                    when(attachPhotoCounter){
                        1 -> imagesList.add(getCorrectImageLink("y", attachment.photo!!))
                        in 2..3 -> imagesList.add(attachment.photo!!.sizes[3].url)
                        else -> imagesList.add(attachment.photo!!.sizes[2].url)
                    }
                }
            }

            if (authorID > 0) {
                profiles.forEach {
                    if (it.id == authorID) {
                        authorName = "${it.firstName} ${it.lastName}"
                        authorAvatarPath = it.photo50
                    }
                }
            } else {
                groups.forEach {
                    if (it.id == authorID.inv() + 1) {
                        authorName = it.name
                        authorAvatarPath = it.photo50
                    }
                }
            }
            val date = postTimeConvert(item.date)
            val likes = item.likes?.count
            val reposts = item.reposts?.count
            val comments = item.comments?.count
            val views = item.views?.count


            if (attachments.isEmpty()) {
                imagesList.clear()
            }
            post = CustomPost(
                authorName,
                date,
                authorAvatarPath,
                item.text,
                imagesList,
                likes!!, comments!!, reposts!!, views!!
            )
            postList.add(post!!)
        }

        return postList
    }
    fun getCorrectImageLink(photoType:String, currentPhoto:Photo): String{
        currentPhoto.sizes.forEach { size ->
            if (size.type == photoType){
                return size.url
            }
            if (size.type == "x"){
                return size.url
            }
        }
        Log.d("Image" ,"Не нашелся нужный размер", IllegalArgumentException())
        return ""
    }

    fun postTimeConvert(postTime: Long): String {
        val currentTime = System.currentTimeMillis()
        val postDate = currentTime - postTime

        return when{
            ((postDate % 60) < 61) -> {
                val date = (postDate % 60)
                "$date секунд назад"
            }
            ((postDate / 60 % 60) < 61) -> {
                val date = (postDate / 60 % 60)
                "$date минут назад"
            }
            ((postDate / 3600 % 24)<25) -> {
                val date = postDate / 3600 % 24
                "$date часов назад"
            }
            else -> Date(postDate).toString()
        }
    }
}

