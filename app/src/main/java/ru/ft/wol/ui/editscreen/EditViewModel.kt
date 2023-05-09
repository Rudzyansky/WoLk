package ru.ft.wol.ui.editscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import ru.ft.wol.domain.entity.Client
import ru.ft.wol.domain.repository.AppRepository

class EditViewModel(
    private val repository: AppRepository,
    clientInit: Client?
) : ViewModel() {

    private val _client = MutableStateFlow(clientInit)
    val client get() = _client.asStateFlow().filterNotNull()

    suspend fun storeClient(name: String, address: String, macAddress: String) {
        val client = Client(
            id = _client.value?.id ?: 0,
            name = name,
            address = address,
            macAddress = macAddress
        )
        repository.putClient(client)
    }

    class Factory(private val repository: AppRepository) {
        fun create(client: Client?): EditViewModel {
            return EditViewModel(repository, client)
        }
    }

    companion object {
        fun factory(vmFactory: Factory, client: Client?) = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return vmFactory.create(client) as T
            }
        }
    }
}