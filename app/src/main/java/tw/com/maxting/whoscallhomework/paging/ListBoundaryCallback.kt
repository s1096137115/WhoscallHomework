package tw.com.maxting.whoscallhomework.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList

class ListBoundaryCallback<Value>(private val listLiveData: MutableLiveData<ListStatus>) : PagedList.BoundaryCallback<Value>() {
    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        listLiveData.value = Empty()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Value) {
        super.onItemAtEndLoaded(itemAtEnd)
        listLiveData.value = End()
    }

    override fun onItemAtFrontLoaded(itemAtFront: Value) {
        super.onItemAtFrontLoaded(itemAtFront)
        listLiveData.value = AtFrontEnd()
    }
}
