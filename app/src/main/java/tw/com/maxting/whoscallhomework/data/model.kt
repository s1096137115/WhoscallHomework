package tw.com.maxting.whoscallhomework.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    @SerialName("collections")
    val collections: Int = 0,
    @SerialName("comments")
    val comments: Int = 0,
    @SerialName("downloads")
    val downloads: Int = 0,
    @SerialName("id")
    val id: Int = 0,
    @SerialName("imageHeight")
    val imageHeight: Int = 0,
    @SerialName("imageSize")
    val imageSize: Int = 0,
    @SerialName("imageWidth")
    val imageWidth: Int = 0,
    @SerialName("largeImageURL")
    val largeImageURL: String = "",
    @SerialName("likes")
    val likes: Int = 0,
    @SerialName("pageURL")
    val pageURL: String = "",
    @SerialName("previewHeight")
    val previewHeight: Int = 0,
    @SerialName("previewURL")
    val previewURL: String = "",
    @SerialName("previewWidth")
    val previewWidth: Int = 0,
    @SerialName("tags")
    val tags: String = "",
    @SerialName("type")
    val type: String = "",
    @SerialName("user")
    val user: String = "",
    @SerialName("user_id")
    val userId: Int = 0,
    @SerialName("userImageURL")
    val userImageURL: String = "",
    @SerialName("views")
    val views: Int = 0,
    @SerialName("webformatHeight")
    val webformatHeight: Int = 0,
    @SerialName("webformatURL")
    val webformatURL: String = "",
    @SerialName("webformatWidth")
    val webformatWidth: Int = 0
)

@Serializable
data class SearchImageResponse(
        val totalHits: Int, //500
        val hits: List<Image>,
        val total: Int //39850
)

data class SearchImageRequest(
        val perPage: Int?,
        val page: Int?,
        val q: String?
)


