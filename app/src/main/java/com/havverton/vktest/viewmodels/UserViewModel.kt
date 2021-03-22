package com.havverton.vktest.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {

    private val _first_name = MutableLiveData<String>()
    private val _second_name = MutableLiveData<String>()
    private val _id = MutableLiveData<Long>()

     val first_name = _first_name
     val second_name = _second_name
     val id = _id


    fun setUserInfo(first_name:String, second_name:String, id:Long){
        _first_name.postValue(first_name)
        _second_name.postValue(second_name)
        _id.postValue(id)
    }
}