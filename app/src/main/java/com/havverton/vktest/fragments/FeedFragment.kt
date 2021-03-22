package com.havverton.vktest.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.havverton.vktest.*
import com.havverton.vktest.adapters.FeedAdapter
import com.havverton.vktest.network.RetrofitModule

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class FeedFragment : Fragment() {
    var viewModel: UserViewModel? = null
    var recyclerView:RecyclerView? = null
    var adapter:FeedAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(activity!!.viewModelStore, ViewModelFactory()).get(UserViewModel::class.java)





        return inflater.inflate(R.layout.fragment_feed, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val swipeContainer = view.findViewById<SwipeRefreshLayout>(R.id.swipeContainer)
        swipeContainer?.setOnRefreshListener {
            loadData()
            swipeContainer.isRefreshing = false
        }
        swipeContainer?.setColorSchemeResources(R.color.teal_200,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_blue_dark)


       recyclerView = view.findViewById(R.id.feed_rv)
        recyclerView?.layoutManager = LinearLayoutManager(context)
         adapter = FeedAdapter()
         val scope = CoroutineScope(Dispatchers.IO)
         scope.launch {
             val feedList = FeedPostProvider().getPostList()
             adapter!!.addAll(feedList)
             withContext(Dispatchers.Main){recyclerView?.adapter = adapter}
         }
    }

    fun loadData(){
        recyclerView = view?.findViewById(R.id.feed_rv)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        adapter = FeedAdapter()
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val feedList = FeedPostProvider().getPostList()
            adapter!!.addAll(feedList)
            withContext(Dispatchers.Main){recyclerView?.adapter = adapter}
        }
    }

}