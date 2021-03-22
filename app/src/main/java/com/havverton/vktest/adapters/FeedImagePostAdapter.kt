package com.havverton.vktest.adapters

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
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.havverton.vktest.R
import com.havverton.vktest.models.CustomPost
import com.havverton.vktest.models.FeedResponse
import com.havverton.vktest.models.VkUser
import com.havverton.vktest.network.RetrofitModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.Date

class FeedImagePostAdapter():RecyclerView.Adapter<FeedImagePostAdapter.FeedImagesViewHolder>() {
    var imagesList: List<String>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedImagesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.post_images_card, parent, false)
        val vh = FeedImagesViewHolder(view)
        return vh
    }


    override fun onBindViewHolder(holder: FeedImagesViewHolder, position: Int) {
        Glide
            .with(holder.itemView.context)
            .load(imagesList?.get(position))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.image)
       }


    override fun getItemCount(): Int {
        return imagesList!!.size
    }

    fun setImageList(list:List<String>){
        imagesList = list

    }

    class FeedImagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.place_pic)

    }
}