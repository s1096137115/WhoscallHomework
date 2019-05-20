package tw.com.maxting.whoscallhomework.di

import org.koin.dsl.module
import tw.com.maxting.whoscallhomework.data.Repository

val singleModule = module {
    single { Repository(get()) }
}