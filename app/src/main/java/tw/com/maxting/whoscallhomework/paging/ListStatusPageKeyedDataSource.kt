package tw.com.maxting.whoscallhomework.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource

abstract class ListStatusPageKeyedDataSource<Key, Value>(private val status: MutableLiveData<ListStatus>) :
    PageKeyedDataSource<Key, Value>() {

    final override fun loadInitial(params: LoadInitialParams<Key>, callback: LoadInitialCallback<Key, Value>) {
        status.postValue(Initialize())
        onLoadInitial(params, ListStatusPageKeyedLoadInitialCallback(callback, status))
    }

    abstract fun onLoadInitial(
        params: LoadInitialParams<Key>,
        callback: ListStatusPageKeyedLoadInitialCallback<Key, Value>
    )

    final override fun loadAfter(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
        status.postValue(LoadMoreIn())
        onLoadAfter(params, ListStatusPageKeyedLoadCallback(callback, status))
    }

    abstract fun onLoadAfter(params: LoadParams<Key>, callback: ListStatusPageKeyedLoadCallback<Key, Value>)

    final override fun loadBefore(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
        status.postValue(AtFrontLoadMoreIn())
        onLoadBefore(params, ListStatusAtFrontPageKeyedLoadCallback(callback, status))
    }

    abstract fun onLoadBefore(params: LoadParams<Key>, callback: ListStatusAtFrontPageKeyedLoadCallback<Key, Value>)
}


class ListStatusPageKeyedLoadInitialCallback<Key, Value>(
    private val callback: PageKeyedDataSource.LoadInitialCallback<Key, Value>,
    private val listStatus: MutableLiveData<ListStatus>
) : PageKeyedDataSource.LoadInitialCallback<Key, Value>() {

    override fun onResult(
        data: MutableList<Value>,
        position: Int,
        totalCount: Int,
        previousPageKey: Key?,
        nextPageKey: Key?
    ) {
        callback.onResult(data, position, totalCount, previousPageKey, nextPageKey)
        if (!data.isEmpty()) {
            listStatus.postValue(InitializeSuccess())
        } else {
            // 当列表为空时 BoundaryCallback 会回调 Empty 状态因此这里不用处理
        }
    }

    override fun onResult(data: MutableList<Value>, previousPageKey: Key?, nextPageKey: Key?) {
        callback.onResult(data, previousPageKey, nextPageKey)
        if (!data.isEmpty()) {
            listStatus.postValue(InitializeSuccess())
        } else {
            // 当列表为空时 BoundaryCallback 会回调 Empty 状态因此这里不用处理
        }
    }

    fun onError(throwable: Throwable) {
        listStatus.postValue(InitializeError(throwable))
    }
}

class ListStatusPageKeyedLoadCallback<Key, Value>(
    private val callback: PageKeyedDataSource.LoadCallback<Key, Value>,
    private val listStatus: MutableLiveData<ListStatus>
) : PageKeyedDataSource.LoadCallback<Key, Value>() {

    override fun onResult(data: MutableList<Value>, adjacentPageKey: Key?) {
        callback.onResult(data, adjacentPageKey)
        listStatus.postValue(LoadMoreSuccess())
    }

    fun onError(throwable: Throwable) {
        listStatus.postValue(LoadMoreError(throwable))
    }
}

class ListStatusAtFrontPageKeyedLoadCallback<Key, Value>(
    private val callback: PageKeyedDataSource.LoadCallback<Key, Value>,
    private val listStatus: MutableLiveData<ListStatus>
) : PageKeyedDataSource.LoadCallback<Key, Value>() {

    override fun onResult(data: MutableList<Value>, adjacentPageKey: Key?) {
        callback.onResult(data, adjacentPageKey)
        listStatus.postValue(AtFrontLoadMoreSuccess())
    }

    fun onError(throwable: Throwable) {
        listStatus.postValue(AtFrontLoadMoreError(throwable))
    }
}