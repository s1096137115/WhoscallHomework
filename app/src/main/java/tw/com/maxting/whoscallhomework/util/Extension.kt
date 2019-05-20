package tw.com.maxting.whoscallhomework.util

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import tw.com.maxting.whoscallhomework.R

fun FragmentManager.openFragment(fragment: Fragment, containerViewId: Int = R.id.container) {
    this.beginTransaction()
        .replace(containerViewId, fragment)
        .commitAllowingStateLoss()
}

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

