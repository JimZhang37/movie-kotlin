package com.example.mykotlinproject.movielist


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mykotlinproject.MoviesApplication
import com.example.mykotlinproject.R
import com.example.mykotlinproject.data.Movie
import com.example.mykotlinproject.databinding.FragmentFragmentMovieListBinding

/**
 * A simple [Fragment] subclass.
 */
class FragmentMovieList :
    Fragment(),
    AdapterMovieList.ListItemClickListener {
    private val SharedPrefKey = "MOVIE_TYPE"
    private lateinit var binding: FragmentFragmentMovieListBinding
    private lateinit var sharedPref: SharedPreferences
    private lateinit var movieType: MovieType

    private lateinit var mAdapterMovieList: AdapterMovieList
    private val viewModel by viewModels<MovieListViewModel> {
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

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        mTextView = view.findViewById<TextView>(R.id.mytext)
//        mButton = view.findViewById<Button>(R.id.mybutton)
//
//        mButton.setOnClickListener(View.OnClickListener { viewModel.refreshData()})
        movieType = getMovieType()
        setupAdapter(movieType)

    }


    private fun setupAdapter(type: MovieType) {
        viewModel.dataPopular.removeObservers(this)
        viewModel.dataTopRated.removeObservers(this)
        when (type) {
            MovieType.TOP_RATED -> {
                viewModel.dataTopRated.observe(this, Observer<List<Movie>> {
                    //Log.d("setupListAdapter", it.toString())
                    mAdapterMovieList.setMovie(it)
                })
            }
            MovieType.POPULAR -> {
                viewModel.dataPopular.observe(this, Observer<List<Movie>> {
                    //Log.d("setupListAdapter", it.toString())
                    mAdapterMovieList.setMovie(it)
                })
            }
        }
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.movie_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.popular -> {
                filterMovies(MovieType.POPULAR.type)
                setupAdapter(MovieType.POPULAR)
                true
            }
            R.id.favorites -> {
                filterMovies(3)
            }
            R.id.top_rated -> {
                filterMovies(MovieType.TOP_RATED.type)
                setupAdapter(MovieType.TOP_RATED)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun filterMovies(movieType: Int): Boolean {
//            if (movieType in 1..3) {
        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)?:return false
        with(sharedPref.edit()){
            putInt(SharedPrefKey, movieType)
            apply()
            Log.d("Save Movie Type:", "type $movieType")
        }


        Toast.makeText(activity, "movieType= $movieType", Toast.LENGTH_SHORT)
        //modify viewModel data TODO()
//            } else {
//                Toast.makeText(activity, "nothing founded", Toast.LENGTH_SHORT)
//            }
        return true
    }

    private fun getMovieType():MovieType {

        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)?:(return MovieType.POPULAR)

        return when (sharedPref?.getInt(SharedPrefKey, MovieType.POPULAR.type)) {
            1 -> {
                Log.d("Get Movie Type:", "Popular")
                MovieType.POPULAR
            }
            2 -> {
                Log.d("Get Movie Type:", "Top Rated")
                MovieType.TOP_RATED
            }
            3 -> MovieType.FAVORITE
            else -> {
                Log.d("Get Movie Type:", "Else")

                MovieType.POPULAR
            }
        }//Do I need a listener
    }
}

enum class MovieType(val type: Int) {
    POPULAR(1), TOP_RATED(2), FAVORITE(3)
}