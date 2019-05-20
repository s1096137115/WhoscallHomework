package tw.com.maxting.whoscallhomework.data

class Repository constructor(
        private val service: PixabayApiService
) {
    fun searchImages(request: SearchImageRequest) =
            service.searchImages(
                    query = request.q,
                    perPage = request.perPage,
                    page = request.page
            )
}