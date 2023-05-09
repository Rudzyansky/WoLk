package ru.ft.wol.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.ft.wol.data.room.dao.ClientsDao
import ru.ft.wol.domain.entity.Client
import ru.ft.wol.domain.repository.AppRepository

class AppRepositoryImpl(
    private val dao: ClientsDao
) : AppRepository {
    override fun getClientList(): Flow<List<Client>> {
        return dao.getClients().map { list -> list.map { it.toClient() } }
    }

    override fun getClient(id: Int): Flow<Client?> {
        return dao.getClientById(id).map { it?.toClient() }
    }

    override suspend fun putClient(client: Client) {
        dao.putClient(client.toClientData())
    }
}