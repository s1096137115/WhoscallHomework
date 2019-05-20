package tw.com.maxting.whoscallhomework.ui

import androidx.recyclerview.widget.DiffUtil
import tw.com.maxting.whoscallhomework.data.Image

class ImageDiffUtil : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }

}