package ru.ft.wol.di

import org.koin.dsl.module

val appModule = module {
    includes(
        repositoryModule,
        viewModuleModule
    )
}