package ru.ft.wol.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.ft.wol.data.repository.AppRepositoryImpl
import ru.ft.wol.data.room.AppDatabase
import ru.ft.wol.domain.repository.AppRepository

val repositoryModule = module {

    singleOf(AppDatabase::newInstance)
    single { get<AppDatabase>().clientsDao }

    singleOf(::AppRepositoryImpl) { bind<AppRepository>() }
}