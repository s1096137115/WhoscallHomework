package tw.com.maxting.whoscallhomework.di

import org.koin.dsl.module
import tw.com.maxting.whoscallhomework.util.NetworkUtils

val utilModule = module {
    factory { NetworkUtils.providePixabalServices() }
}