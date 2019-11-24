package com.example.mykotlinproject.moviedetail

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinproject.R
import com.example.mykotlinproject.data.Trailer
import com.example.mykotlinproject.data.TrailerDB

class MovieTrailersAdapter(
    private val mContext: Context,
    val mListItemClickListener: ListItemClickListener
) : RecyclerView.Adapter<MovieTrailersAdapter.TrailerViewHolder>() {

    private var mMovieTrailers: List<TrailerDB>? = null
    private var mNumberOfViewHolder = 0

    interface ListItemClickListener {
        fun onListItemClick(trailerID: String)
    }

    fun setMovieTrailers(trailers: List<TrailerDB>) {
        mMovieTrailers = trailers
        Log.d("MovieTrailersAdapter", trailers.toString())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val layoutIdForListItem = R.layout.trailer_viewholder
        val inflater = LayoutInflater.from(mContext)
        val shouldAttachToParentImmediately = false

        val view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately)
        val viewHolder = TrailerViewHolder(view)
        mNumberOfViewHolder++

        return viewHolder
    }

    override fun getItemCount(): Int {
        return if (mMovieTrailers == null) {
            0
        } else mMovieTrailers!!.size
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        holder.mTrailer.text = mMovieTrailers!!.get(position).name
    }

    //inner class can access to outer class's member
    inner class TrailerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var mTrailer: TextView = itemView.findViewById(R.id.txTrailer)

        init {

            mTrailer.setOnClickListener(this)
        }


        override fun onClick(p0: View?) {
//            val movieID = mMovieReviews!![adapterPosition].mMovieID
//            mListItemClickListener.onListItemClick(movieID)
        }


    }
}