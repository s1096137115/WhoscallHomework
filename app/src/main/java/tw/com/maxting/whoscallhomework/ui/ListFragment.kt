package tw.com.maxting.whoscallhomework.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.android.synthetic.main.search_view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import tw.com.maxting.whoscallhomework.R
import tw.com.maxting.whoscallhomework.paging.*


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
        setupRecyclerView(R.id.grid)
        observeViewModel()
    }

    private fun setupToolbar() {

        toolbar.apply {
            inflateMenu(R.menu.layout)
            setOnMenuItemClickListener { item ->
                setupRecyclerView(item.itemId)
                true
            }
        }

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

    private fun setupRecyclerView(layout: Int) {
        recyclerView.layoutManager = when (layout) {
            R.id.list -> {
                LinearLayoutManager(context)
            }
            R.id.grid -> {
                GridLayoutManager(context, 3)
            }
            R.id.staggered -> {
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            }
            else -> {
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            }
        }
        recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.apply {
            loadImagePaged().observe(viewLifecycleOwner, Observer(adapter::submitList))

            listStatus.observe(viewLifecycleOwner, Observer { status ->
                when (status) {
                    is Initialize -> {
                        loadingProgress.visibility = View.VISIBLE
                    }
                    is InitializeSuccess -> {
                        loadingProgress.visibility = View.INVISIBLE
                    }
                    is InitializeError -> {
                        loadingProgress.visibility = View.INVISIBLE
                    }
                    is Empty -> {
                        loadingProgress.visibility = View.INVISIBLE
                    }
                    is LoadMoreIn -> {
                        loadingProgress.visibility = View.VISIBLE
                    }
                    is LoadMoreSuccess -> {
                        loadingProgress.visibility = View.INVISIBLE
                    }
                    is LoadMoreError -> {
                        loadingProgress.visibility = View.INVISIBLE
                    }
                    is End -> {
                        loadingProgress.visibility = View.INVISIBLE
                    }
                    is AtFrontLoadMoreIn -> {
                        loadingProgress.visibility = View.VISIBLE
                    }
                    is AtFrontLoadMoreSuccess -> {
                        loadingProgress.visibility = View.INVISIBLE
                    }
                    is AtFrontLoadMoreError -> {
                        loadingProgress.visibility = View.INVISIBLE
                    }
                    is AtFrontEnd -> {
                        loadingProgress.visibility = View.INVISIBLE
                    }
                }
            })
        }
    }

}
