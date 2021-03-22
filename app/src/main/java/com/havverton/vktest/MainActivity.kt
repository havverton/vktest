package com.havverton.vktest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.havverton.vktest.fragments.FeedFragment
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKTokenExpiredHandler
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    var feedFragment:FeedFragment? = null
    var sharedPreferences : SharedPreferences? = null

    companion object{
        var token: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences("token", MODE_PRIVATE)


        val tokenTracker = object: VKTokenExpiredHandler {
            override fun onTokenExpired() {
                // token expired
            }
        }
        VK.addTokenExpiredHandler(tokenTracker)
       if(VK.isLoggedIn()){
           if(sharedPreferences!!.contains("token")) {
                   token = sharedPreferences!!.getString("token", "")
               }
            feedFragment = FeedFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.main_frame,feedFragment!!,"feed")
                .commit()
        }else{
           VK.login(this, arrayListOf(VKScope.WALL, VKScope.PHOTOS,VKScope.FRIENDS))
        }


          // VK.login(this, arrayListOf(VKScope.WALL, VKScope.PHOTOS))

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object: VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {

                feedFragment = FeedFragment()
                supportFragmentManager.beginTransaction()
                    .add(R.id.main_frame,feedFragment!!,"feed")
                    .commit()
                MainActivity.token = token.accessToken
               sharedPreferences!!.edit()!!.putString("token", token.accessToken).apply()



            }

            override fun onLoginFailed(errorCode: Int) {

            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}