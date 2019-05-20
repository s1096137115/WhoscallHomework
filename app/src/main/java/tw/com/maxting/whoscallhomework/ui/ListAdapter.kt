package tw.com.maxting.whoscallhomework.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import tw.com.maxting.whoscallhomework.R
import tw.com.maxting.whoscallhomework.data.Image

class ListAdapter : PagedListAdapter<Image, ListViewHolder>(ImageDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        getItem(position)?.apply { holder.bind(this) }
    }

}