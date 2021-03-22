package com.havverton.vktest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.havverton.vktest.models.VkUser
import com.vk.api.sdk.auth.VKAccessToken

class UserViewModel : ViewModel() {

    private val _user = MutableLiveData<VkUser>()
    val user = _user

    private val _userToken = MutableLiveData<VKAccessToken>()
    val userToken = _userToken


    fun selectUser(user:VkUser){
        _user.postValue(user)
    }
}