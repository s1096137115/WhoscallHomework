package tw.com.maxting.whoscallhomework.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.android.synthetic.main.search_view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import tw.com.maxting.chocohomework.ui.list.ListAdapter
import tw.com.maxting.whoscallhomework.R


class ListFragment : Fragment() {

    private val viewModel: MainViewModel by sharedViewModel()
    private val adapter by lazy {
        ListAdapter()
    }

    companion object {
        fun newInstance() = ListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupToolbar() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
//                viewModel.searchImages(SearchImageRequest(40, 1, query))

                query?.let { viewModel.searchImages(it) }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setupRecyclerView() {
//        recyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
//                .apply {
//                    gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
//                }
//        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                (recyclerView.layoutManager as StaggeredGridLayoutManager).invalidateSpanAssignments()
//            }
//        })

        recyclerView.layoutManager = GridLayoutManager(context, 3)

        recyclerView.adapter = adapter
    }

    private fun observeViewModel() {

        viewModel.apply {
            //            mImages.observe(viewLifecycleOwner, Observer {
//                adapter.update(it.toMutableList())
//            })

            loadImagePaged().observe(viewLifecycleOwner, Observer(adapter::submitList))
        }
    }

}
