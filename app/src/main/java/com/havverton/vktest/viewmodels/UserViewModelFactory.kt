package com.havverton.vktest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.havverton.vktest.UserViewModel

class UserViewModelFactory : ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        UserViewModel::class.java -> UserViewModel()
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}