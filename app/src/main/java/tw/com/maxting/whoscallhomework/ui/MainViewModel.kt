package tw.com.maxting.whoscallhomework.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import tw.com.maxting.whoscallhomework.data.Image
import tw.com.maxting.whoscallhomework.data.Repository
import tw.com.maxting.whoscallhomework.data.SearchImageRequest

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val mDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()
    }

    val mImages = MutableLiveData<List<Image>>()

    fun searchImages(request: SearchImageRequest) {
        repository
            .searchImages(request)
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    mImages.postValue(it.hits)
                },
                onError = {
                    val a = 1
                    //do nothing
                }
            )
            .addTo(mDisposable)
    }
}
