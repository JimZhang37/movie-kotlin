package com.example.mykotlinproject.movielist


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinproject.EventObserver
import com.example.mykotlinproject.R
import com.example.mykotlinproject.data.Movie
import com.example.mykotlinproject.databinding.FragmentFragmentMovieListBinding
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass.
 */
class FragmentMovieList :
    Fragment(),
    MovieListAdapter.ListItemClickListener {
    private lateinit var binding: FragmentFragmentMovieListBinding
    private lateinit var mRV: RecyclerView
    private lateinit var mAdapter: MovieListAdapter
    private val viewModel by viewModels<MovieListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFragmentMovieListBinding.inflate(
            inflater,
            container,
            false
        )
        binding.movie1 = Movie(mTitle = "created movie")
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.mybutton.setOnClickListener { viewModel.download() }
        binding.recyclerView.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.layoutManager = layoutManager
        mAdapter = MovieListAdapter(context!!, this)
        binding.recyclerView.adapter = mAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        mTextView = view.findViewById<TextView>(R.id.mytext)
//        mButton = view.findViewById<Button>(R.id.mybutton)
//
//        mButton.setOnClickListener(View.OnClickListener { viewModel.refreshData()})

        setupListAdapter()
        Picasso
            .get()
            .load("http://image.tmdb.org/t/p/w185//qdfARIhgpgZOBh3vfNhWS4hmSo3.jpg")
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.mistake)
            .into(binding.testImage)
        //setupListAdapter()

    }


    private fun setupListAdapter() {
        viewModel.data?.observe(this, Observer<List<Movie>> {
            //Log.d("setupListAdapter", it.toString())
            mAdapter.setMovie(it)
        })

    }


    /***
     * TODO(set the listener of image to this function)
     */
    private fun openMovieDetail(movieID: String) {
        val action =
            FragmentMovieListDirections.actionFragmentMovieListToFragmentMovieDetail(movieID)
        findNavController().navigate(action)
    }

    override fun onListItemClick(movieID: String) {
//        viewModel.openMovieDetail(movieID)
    }
}
