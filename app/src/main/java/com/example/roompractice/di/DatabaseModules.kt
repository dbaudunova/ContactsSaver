package com.example.roompractice.di

import com.example.roompractice.data.local.repository.UserRepository
import com.example.roompractice.ui.UserViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(androidContext()) }
    single { provideDao(get()) }
    factory { UserRepository(get()) }
    viewModel { UserViewModel(get()) }
}