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
import com.example.mykotlinproject.R
import com.example.mykotlinproject.data.*

import com.example.mykotlinproject.databinding.FragmentMovieDetailBinding
import com.squareup.picasso.Picasso

/**
 * A simple [Fragment] subclass.
 */
class FragmentMovieDetail :
    Fragment(),
    AdapterMovieTrailers.ListItemClickListener {

    lateinit var dataBinding: FragmentMovieDetailBinding
    private val args: FragmentMovieDetailArgs by navArgs()
    private lateinit var mReviewAdapterMovieReviews: AdapterMovieReviews
    private lateinit var mTrailerAdapterMovieTrailers: AdapterMovieTrailers
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


        dataBinding.rvMovieReviews.setHasFixedSize(true)
        val layoutManager1 = GridLayoutManager(context, 1)
        dataBinding.rvMovieReviews.layoutManager = layoutManager1
        mReviewAdapterMovieReviews = AdapterMovieReviews(context!!)
        dataBinding.rvMovieReviews.adapter = mReviewAdapterMovieReviews

        dataBinding.rvMovieTrailers.setHasFixedSize(true)
        val layoutManager2 = GridLayoutManager(context, 1)
        dataBinding.rvMovieTrailers.layoutManager = layoutManager2
        mTrailerAdapterMovieTrailers = AdapterMovieTrailers(context!!, this)
        dataBinding.rvMovieTrailers.adapter = mTrailerAdapterMovieTrailers

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
            mReviewAdapterMovieReviews.setMovieReviews(it)

        })

        viewModel.trailers.observe(this, Observer<List<TrailerDB>> {
            //Log.d("setupListAdapter", it.toString())
            mTrailerAdapterMovieTrailers.setMovieTrailers(it)

        })

        viewModel.movie.observe(this, Observer<Movie> {
            //Log.d("setupListAdapter", it.toString())
            dataBinding.txMovieTitleValue.text = it.mTitle
            dataBinding.txMovieSummaryValue.text = it.mSynopsis
            Picasso
                .get()
                .load("http://image.tmdb.org/t/p/w185/"+it.mImage)///qdfARIhgpgZOBh3vfNhWS4hmSo3.jpg
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.mistake)
                .into(dataBinding.ivMovieImage)
        })
    }

    /***
     * when a trailer is clicked, play the trailer
     */
    override fun onListItemClick(trailerID: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
