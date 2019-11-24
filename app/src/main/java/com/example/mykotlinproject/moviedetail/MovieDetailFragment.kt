package com.example.mykotlinproject.moviedetail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mykotlinproject.MoviesApplication
import com.example.mykotlinproject.data.*

import com.example.mykotlinproject.databinding.FragmentMovieDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class MovieDetailFragment :
    Fragment(),
    MovieTrailersAdapter.ListItemClickListener {

    lateinit var dataBinding: FragmentMovieDetailBinding
    private val args: MovieDetailFragmentArgs by navArgs()
    private lateinit var mReviewAdapter: MovieReviewsAdapter
    private lateinit var mTrailerAdapter: MovieTrailersAdapter
    private val viewModel by viewModels<MovieDetailViewModel> {
        MovieDetailViewModelFactory(
            (requireContext().applicationContext as MoviesApplication).moviesRepository,
            args.movieId
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentMovieDetailBinding.inflate(
            inflater,
            container,
            false)
        dataBinding.lifecycleOwner = this
        dataBinding.movie = viewModel.movie.value

//        dataBinding.myMovieTitle.text = args.movieId


        dataBinding.myMovieReviews.setHasFixedSize(true)
        val layoutManager1 = GridLayoutManager(context, 1)
        dataBinding.myMovieReviews.layoutManager = layoutManager1
        mReviewAdapter = MovieReviewsAdapter(context!!)
        dataBinding.myMovieReviews.adapter = mReviewAdapter

        dataBinding.myMovieTrailers.setHasFixedSize(true)
        val layoutManager2 = GridLayoutManager(context, 1)
        dataBinding.myMovieTrailers.layoutManager = layoutManager2
        mTrailerAdapter = MovieTrailersAdapter(context!!, this)
        dataBinding.myMovieTrailers.adapter = mTrailerAdapter



        return dataBinding.root
    }

    /***
     * setupAdapter is called in this lifecycle event
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservation()
        viewModel.getMovieDetails()
    }

    /***
     * provide data for two recyclerviews, for reviews and trailers respectivly
     */
    private fun setupObservation() {
        viewModel.reviews.observe(this, Observer<List<ReviewDB>> {
            //Log.d("setupListAdapter", it.toString())
            mReviewAdapter.setMovieReviews(it)

        })

        viewModel.trailers.observe(this, Observer<List<TrailerDB>> {
            //Log.d("setupListAdapter", it.toString())
            mTrailerAdapter.setMovieTrailers(it)

        })

        viewModel.movie.observe(this, Observer<Movie> {
            //Log.d("setupListAdapter", it.toString())
            dataBinding.myMovieTitle.text = it.mTitle
            dataBinding.myMovieSummary.text = it.mSynopsis

        })
    }

    /***
     * when a trailer is clicked, play the trailer
     */
    override fun onListItemClick(trailerID: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
