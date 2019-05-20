package tw.com.maxting.whoscallhomework.util

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import tw.com.maxting.whoscallhomework.data.PixabayApiService
import tw.com.maxting.whoscallhomework.data.PixabayUrl

object NetworkUtils {

    fun providePixabalServices(): PixabayApiService {
        val retrofit = Retrofit.Builder()
                .baseUrl(PixabayUrl.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
                .build()

        return retrofit.create(PixabayApiService::class.java)
    }

}