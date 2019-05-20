package tw.com.maxting.chocohomework.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tw.com.maxting.whoscallhomework.R
import tw.com.maxting.whoscallhomework.data.Image
import tw.com.maxting.whoscallhomework.ui.ListDiffUtil

class ListAdapter : RecyclerView.Adapter<ListViewHolder>() {
    var mList = mutableListOf<Image>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    fun update(list: MutableList<Image>) {
        val result = DiffUtil.calculateDiff(ListDiffUtil(this.mList, list))
        result.dispatchUpdatesTo(this)
        this.mList = list
    }
}