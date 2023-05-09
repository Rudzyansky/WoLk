package ru.ft.wol.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.ft.wol.data.room.entity.ClientData

@Dao
interface ClientsDao {

    @Query("SELECT * FROM ClientData ORDER BY id DESC")
    fun getClients(): Flow<List<ClientData>>

    @Query("SELECT * FROM ClientData WHERE id = :id")
    fun getClientById(id: Int): Flow<ClientData?>

    @Insert(onConflict = REPLACE)
    suspend fun putClient(clientData: ClientData)
}