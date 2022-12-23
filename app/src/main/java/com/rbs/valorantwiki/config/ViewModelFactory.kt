package com.rbs.valorantwiki.config

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rbs.valorantwiki.data.AgentRepository
import com.rbs.valorantwiki.ui.detail.DetailViewModel
import com.rbs.valorantwiki.ui.home.HomeViewModel

class ViewModelFactory(private val repository: AgentRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}