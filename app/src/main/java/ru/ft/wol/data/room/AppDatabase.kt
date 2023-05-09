package ru.ft.wol.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.ft.wol.data.room.dao.ClientsDao
import ru.ft.wol.data.room.entity.ClientData

@Database(
    entities = [
        ClientData::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract val clientsDao: ClientsDao

    companion object {
        private const val DATABASE_NAME = "items"

        fun newInstance(context: Context) = Room
            .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .build()
    }
}