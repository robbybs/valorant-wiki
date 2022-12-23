package com.rbs.valorantwiki

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rbs.valorantwiki.data.AgentRepository
import com.rbs.valorantwiki.model.OrderAgent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: AgentRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderAgent>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderAgent>> get() = _uiState

    fun getAgentById(agentId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderAgentById(agentId))
        }
    }
}