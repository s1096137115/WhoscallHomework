package tw.com.maxting.whoscallhomework

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import tw.com.maxting.whoscallhomework.di.appModule

class WhoscallApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WhoscallApp)
            androidLogger()
            modules(appModule)
        }
    }
}