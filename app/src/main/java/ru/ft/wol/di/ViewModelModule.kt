package ru.ft.wol.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.ft.wol.ui.editscreen.EditViewModel
import ru.ft.wol.ui.listscreen.ListViewModel
import ru.ft.wol.ui.listscreen.WakeUpViewModel

val viewModuleModule = module {
    viewModelOf(::ListViewModel)
    viewModelOf(::WakeUpViewModel)

    singleOf(EditViewModel::Factory)
}