package com.rbs.valorantwiki

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rbs.valorantwiki.data.AgentRepository
import com.rbs.valorantwiki.model.OrderAgent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: AgentRepository) : ViewModel() {
//    private val _query = mutableStateOf("")
//    val query: State<String> get() = _query

    //    fun search(newQuery: String) {
//        _query.value = newQuery
//        _groupedAgents.value = repository.searchAgents(_query.value)
//            .sortedBy { it.name }
//            .groupBy { it.name[0] }
//    }
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

//    fun search(newQuery: String) {
//        viewModelScope.launch {
//            repository.getAllAgents()
//                .catch {
//                    _uiState.value = UiState.Error(it.message.toString())
//                }
//                .collect { orderRewards ->
//                    _query.value = newQuery
//                    _uiState.value = repository.searchAgents(_query.value)
//                }
//        }
//    }
}