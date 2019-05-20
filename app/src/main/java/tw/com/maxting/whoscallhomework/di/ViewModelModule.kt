package tw.com.maxting.whoscallhomework.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tw.com.maxting.whoscallhomework.ui.MainViewModel

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}