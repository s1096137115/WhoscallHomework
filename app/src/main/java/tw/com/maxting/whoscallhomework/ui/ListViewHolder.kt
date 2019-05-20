package tw.com.maxting.whoscallhomework.ui

import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_image.view.*
import tw.com.maxting.whoscallhomework.data.Image
import tw.com.maxting.whoscallhomework.util.px

class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var mImage: Image

    fun bind(image: Image) {
        this.mImage = image

        itemView.apply {
            ivImage.layoutParams = LinearLayout.LayoutParams(image.previewWidth.px, image.previewHeight.px)

            Glide.with(this)
                .load(image.previewURL)
                .fitCenter()
                .into(ivImage)
        }
    }
}