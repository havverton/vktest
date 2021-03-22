package com.havverton.vktest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory : ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        UserViewModel::class.java -> UserViewModel()
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}