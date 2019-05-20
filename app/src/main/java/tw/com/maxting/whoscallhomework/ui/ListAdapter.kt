package tw.com.maxting.chocohomework.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import tw.com.maxting.whoscallhomework.R
import tw.com.maxting.whoscallhomework.data.Image
import tw.com.maxting.whoscallhomework.ui.ImageDiffUtil

class ListAdapter : PagedListAdapter<Image, ListViewHolder>(ImageDiffUtil()) {
//    var mList = mutableListOf<Image>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false))
    }

//    override fun getItemCount(): Int {
//        return mList.size
//    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        getItem(position)?.apply { holder.bind(this) }
    }

//    fun update(list: MutableList<Image>) {
//        val result = DiffUtil.calculateDiff(ImageDiffUtil(this.mList, list))
//        result.dispatchUpdatesTo(this)
//        this.mList = list
//    }
}