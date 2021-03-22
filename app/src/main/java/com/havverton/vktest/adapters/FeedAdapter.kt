package com.havverton.vktest.adapters

import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.havverton.vktest.R
import com.havverton.vktest.models.CustomPost
import com.havverton.vktest.models.FeedResponse
import com.havverton.vktest.models.VkUser
import com.havverton.vktest.network.RetrofitModule
import java.sql.Date

class FeedAdapter():RecyclerView.Adapter<FeedItemViewHolder>() {
    var postList:List<CustomPost>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.feed_frame_text,parent,false)


        val vh = FeedItemViewHolder(view)
        return vh
    }

    override fun onBindViewHolder(holder: FeedItemViewHolder, position: Int) {
        val imageList = postList?.get(position)!!.imageList
        val post = postList?.get(position)

        holder.author.text = post?.authorName ?: "Заглушка"
        holder.postTime.text = post?.postTime
        holder.postText.text =  post?.text
        Glide
            .with(holder.itemView)
            .load(postList?.get(position)?.avatar50)
            .circleCrop()
            .into(holder.avatar)
        if(imageList.isNotEmpty()) {
            val layoutManager = StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL)
            //layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
            holder.imageRV.layoutManager = layoutManager
            //holder.imageRV.setHasFixedSize(true)

            val adapter = FeedImagePostAdapter()
            adapter.setImageList(imageList)
            holder.imageRV.adapter = adapter
        }/*else if (imageList.size == 1){
            holder.imageRV.layoutManager = StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL)

            val adapter = FeedImagePostAdapter()
            adapter.setImageList(postList?.get(position)!!.imageList)
            holder.imageRV.adapter = adapter
        }*/
        holder.likes.text = post?.likesCount.toString()
        if(post?.text == ""){
            holder.postText.height = 0
        }
        holder.comments.text = post?.commentsCount.toString()
        holder.reposts.text = post?.repostCount.toString()
        holder.postViews.text = post?.viewsCount.toString()
    }

    override fun getItemCount(): Int {
        return postList!!.size
    }

    fun addAll(postList:List<CustomPost>){
        this.postList = postList
    }
    fun clear(){
        postList = emptyList()
    }




}

class FeedItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
   val avatar = itemView.findViewById<ImageView>(R.id.feedItemAvatar)
    val author = itemView.findViewById<TextView>(R.id.feedAuthorName)
    val postTime = itemView.findViewById<TextView>(R.id.feedPostTime)
    val postText = itemView.findViewById<TextView>(R.id.feedPostText)
    val imageRV = itemView.findViewById<RecyclerView>(R.id.post_photo_rv)
    val likes = itemView.findViewById<TextView>(R.id.post_likes_counter)
    val comments = itemView.findViewById<TextView>(R.id.post_comment_counter)
    val reposts = itemView.findViewById<TextView>(R.id.post_repost_counter)
    val postViews = itemView.findViewById<TextView>(R.id.post_views_counter)


}