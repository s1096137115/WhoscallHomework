package tw.com.maxting.whoscallhomework.ui

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import tw.com.maxting.whoscallhomework.base.BaseViewModel
import tw.com.maxting.whoscallhomework.data.Image
import tw.com.maxting.whoscallhomework.data.Repository
import tw.com.maxting.whoscallhomework.data.SearchImageRequest
import tw.com.maxting.whoscallhomework.paging.ListStatus

class MainViewModel(private val repository: Repository) : BaseViewModel() {

    override fun onCleared() {
        super.onCleared()
        factory.getDataSource()?.dispose()
    }

    val factory: ImageListDataSource.Factory by lazy {
        ImageListDataSource.Factory(listStatus, repository, mQuery)
    }

    fun loadImagePaged() = makePagedList(factory, listStatus)

    val listStatus = MutableLiveData<ListStatus>()

    val mImages = MutableLiveData<List<Image>>()

    val mQuery = MutableLiveData<String>()

    fun searchImages(query: String) {
        mQuery.value = query
        factory.getDataSource()?.invalidate()
    }

    fun searchImages(request: SearchImageRequest) {
        repository
            .searchImages(request)
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    mImages.postValue(it.hits)
                },
                onError = {
                    //do nothing
                }
            )
            .addTo(mDisposable)
    }
}
