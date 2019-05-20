package tw.com.maxting.chocohomework.ui.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_image.view.*
import tw.com.maxting.whoscallhomework.data.Image

class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var mImage: Image

    init {
        itemView.setOnClickListener {
        }
    }

    fun bind(image: Image) {
        this.mImage = image
        itemView.apply {
            Glide.with(this)
                    .load(image.previewURL)
                    .fitCenter()
                    .into(ivImage)
        }
    }

}