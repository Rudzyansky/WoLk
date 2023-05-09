package ru.ft.wol.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.ft.wol.domain.entity.Client

interface AppRepository {

    fun getClientList(): Flow<List<Client>>

    fun getClient(id: Int): Flow<Client?>
    suspend fun putClient(client: Client)
}