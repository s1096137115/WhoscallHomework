package tw.com.maxting.whoscallhomework.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApiService {
    @GET(".")
    fun searchImages(@Query("key") key: String = "12525831-9e8849616329b010e21e6236a",
                     @Query("per_page") perPage: Int?,
                     @Query("page") page: Int?,
                     @Query("q") query: String?
    ): Single<SearchImageResponse>
}

object PixabayUrl {
    const val BASE_URL = "https://pixabay.com/api/"
}