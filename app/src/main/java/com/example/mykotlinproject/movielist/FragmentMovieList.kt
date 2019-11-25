package com.example.mykotlinproject.movielist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mykotlinproject.MoviesApplication
import com.example.mykotlinproject.data.Movie
import com.example.mykotlinproject.databinding.FragmentFragmentMovieListBinding

/**
 * A simple [Fragment] subclass.
 */
class FragmentMovieList :
    Fragment(),
    AdapterMovieList.ListItemClickListener {
    private lateinit var binding: FragmentFragmentMovieListBinding

    private lateinit var mAdapterMovieList: AdapterMovieList
    private val viewModel by viewModels<MovieListViewModel>{
        MovieListViewModelFactory((requireContext().applicationContext as MoviesApplication).moviesRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFragmentMovieListBinding.inflate(
            inflater,
            container,
            false
        )

        //TODO()why we need lifecycleOwner
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.mybutton.setOnClickListener { viewModel.download() }

        //recyclerView
        binding.recyclerView.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.layoutManager = layoutManager
        mAdapterMovieList = AdapterMovieList(context!!, this)
        binding.recyclerView.adapter = mAdapterMovieList

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        mTextView = view.findViewById<TextView>(R.id.mytext)
//        mButton = view.findViewById<Button>(R.id.mybutton)
//
//        mButton.setOnClickListener(View.OnClickListener { viewModel.refreshData()})


        setupAdapter()

    }


    private fun setupAdapter() {
        viewModel.data?.observe(this, Observer<List<Movie>> {
            //Log.d("setupListAdapter", it.toString())
            mAdapterMovieList.setMovie(it)
        })

    }


    /***
     * TODO(set the listener of image to this function)
     */
    private fun openMovieDetail(movieID: String) {
        val action =
            FragmentMovieListDirections.actionFragmentMovieListToMovieDetailFragment(movieID)
        findNavController().navigate(action)
    }

    override fun onListItemClick(movieID: String) {
        openMovieDetail(movieID)
    }
}
