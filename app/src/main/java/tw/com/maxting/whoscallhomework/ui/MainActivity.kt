package tw.com.maxting.whoscallhomework.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import tw.com.maxting.whoscallhomework.R
import tw.com.maxting.whoscallhomework.util.openFragment

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.openFragment(ListFragment.newInstance())
    }
}
