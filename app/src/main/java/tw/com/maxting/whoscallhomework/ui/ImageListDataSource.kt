package tw.com.maxting.whoscallhomework.ui

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import tw.com.maxting.whoscallhomework.data.Image
import tw.com.maxting.whoscallhomework.data.Repository
import tw.com.maxting.whoscallhomework.data.SearchImageRequest
import tw.com.maxting.whoscallhomework.paging.*

class ImageListDataSource(
    status: MutableLiveData<ListStatus>,
    private val repository: Repository,
    private val query: String?
) : ListStatusPageKeyedDataSource<Int, Image>(status) {

    private val mDisposable = CompositeDisposable()

    companion object {
        const val INIT_PAGE = 1
    }

    override fun onLoadInitial(
        params: LoadInitialParams<Int>,
        callback: ListStatusPageKeyedLoadInitialCallback<Int, Image>
    ) {

        repository.searchImages(SearchImageRequest(params.requestedLoadSize, INIT_PAGE, query))
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { response ->

                    callback.onResult(response.hits.toMutableList(), null, INIT_PAGE + 1)

                },
                onError = {
                    callback.onError(it)
                })
            .addTo(mDisposable)
    }

    override fun onLoadAfter(params: LoadParams<Int>, callback: ListStatusPageKeyedLoadCallback<Int, Image>) {

        repository.searchImages(SearchImageRequest(params.requestedLoadSize, params.key, query))
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = { response ->

                    callback.onResult(response.hits.toMutableList(), params.key + 1)

                },
                onError = {
                    callback.onError(it)
                })
            .addTo(mDisposable)
    }

    override fun onLoadBefore(params: LoadParams<Int>, callback: ListStatusAtFrontPageKeyedLoadCallback<Int, Image>) {
    }

    fun dispose() {
        mDisposable.clear()
    }

    class Factory(
        private val status: MutableLiveData<ListStatus>,
        private val repository: Repository,
        private val query: MutableLiveData<String>
    ) : DataSource.Factory<Int, Image>() {

        private var dataSource: ImageListDataSource? = null

        override fun create(): DataSource<Int, Image> {
            return ImageListDataSource(status, repository, query.value).also { dataSource = it }
        }

        fun getDataSource() = dataSource
    }

}