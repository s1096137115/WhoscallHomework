package tw.com.maxting.whoscallhomework.ui

import androidx.recyclerview.widget.DiffUtil
import tw.com.maxting.whoscallhomework.data.Image

class ListDiffUtil constructor(
    private val old: List<Image>,
    private val new: List<Image>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition].id == new[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }
}