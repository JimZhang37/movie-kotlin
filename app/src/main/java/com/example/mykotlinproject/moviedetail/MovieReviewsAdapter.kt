package com.example.mykotlinproject.moviedetail

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinproject.R

import com.example.mykotlinproject.data.ReviewDB


class MovieReviewsAdapter(
    private val mContext: Context
) : RecyclerView.Adapter<MovieReviewsAdapter.ReviewViewHolder>() {

    private var mMovieReviews: List<ReviewDB>? = null
    private var mNumberOfViewHolder = 0


    fun setMovieReviews(reviews: List<ReviewDB>) {
        mMovieReviews = reviews
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val layoutIdForListItem = R.layout.review_viewholder
        val inflater = LayoutInflater.from(mContext)
        val shouldAttachToParentImmediately = false

        val view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately)
        val viewHolder = ReviewViewHolder(view)
        mNumberOfViewHolder++
        return viewHolder
    }

    override fun getItemCount(): Int {
        return if (mMovieReviews == null) {

            0
        } else {

            mMovieReviews!!.size
        }
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        if (mMovieReviews != null) {
            holder.text.text = mMovieReviews!![position].content
        }
    }

    //inner class can access to outer class's member
    inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.txReview)

    }
}