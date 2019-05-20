package tw.com.maxting.whoscallhomework.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import tw.com.maxting.whoscallhomework.paging.ListBoundaryCallback
import tw.com.maxting.whoscallhomework.paging.ListStatus

abstract class BaseViewModel : ViewModel() {
    protected val mDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()
    }

    private fun makePagedListConfig(pageSize: Int, distance: Int): PagedList.Config =
        PagedList.Config.Builder().apply {
            setPageSize(pageSize) //设置每一页加载的数量
            setInitialLoadSizeHint(pageSize) //设置首次加载的数量
            setPrefetchDistance(distance) //设置距离最后还有多少个item时，即寻呼库开始加载下一页的数据
            setEnablePlaceholders(false)
        }.build()

    fun <Key, Value> makePagedList(
        dataSourceFactory: DataSource.Factory<Key, Value>,
        listStatus: MutableLiveData<ListStatus>,
        pageSize: Int = 50,
        distance: Int = 10
    ): LiveData<PagedList<Value>> =
        LivePagedListBuilder<Key, Value>(dataSourceFactory, makePagedListConfig(pageSize, distance))
            .setBoundaryCallback(ListBoundaryCallback(listStatus))
            .build()

}