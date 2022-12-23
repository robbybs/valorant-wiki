package com.rbs.valorantwiki.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rbs.valorantwiki.config.UiState
import com.rbs.valorantwiki.data.AgentRepository
import com.rbs.valorantwiki.model.OrderAgent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: AgentRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderAgent>>> =
        MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<List<OrderAgent>>> get() = _uiState

    fun getAllAgents() {
        viewModelScope.launch {
            repository.getAllAgents()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { agents ->
                    _uiState.value = UiState.Success(agents)
                }
        }
    }
}