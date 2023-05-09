package ru.ft.wol.ui.listscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import ru.ft.wol.domain.repository.AppRepository

class ListViewModel(
    private val repository: AppRepository
) : ViewModel() {

    private val _clients = repository.getClientList()
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)
    val clients get() = _clients.filterNotNull()

}