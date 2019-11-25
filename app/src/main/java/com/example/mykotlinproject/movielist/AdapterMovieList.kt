package com.example.mykotlinproject.movielist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinproject.R
import com.example.mykotlinproject.data.Movie
import com.squareup.picasso.Picasso
import com.example.mykotlinproject.movielist.AdapterMovieList.ImageViewHolder

class AdapterMovieList(
    private val mContext: Context,
    val mListItemClickListener: ListItemClickListener
) : RecyclerView.Adapter<ImageViewHolder>() {

    private var mMovieList: List<Movie>? = null
    private var mNumberOfViewHolder = 0

    interface ListItemClickListener {
        fun onListItemClick(movieID: String)
    }

    fun setMovie(movie: List<Movie>) {
        mMovieList = movie
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutIdForListItem = R.layout.image_viewholder
        val inflater = LayoutInflater.from(mContext)
        val shouldAttachToParentImmediately = false

        val view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately)
        val viewHolder = ImageViewHolder(view)
        mNumberOfViewHolder++

        return viewHolder
    }

    override fun getItemCount(): Int {
        return if (mMovieList == null) {
            0
        } else mMovieList!!.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val path = "http://image.tmdb.org/t/p/w185/" + mMovieList?.get(position)?.mImage
        //Log.d("onBindViewHolder", path)

        holder.updateWithUrl(path)
    }

    //inner class can access to outer class's member
    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var mMoviePoster: ImageView = itemView.findViewById(R.id.image_holder)
        //var mMovieTitle: TextView = itemView.findViewById(R.id.text_holder)

        init {

            mMoviePoster.setOnClickListener(this)
        }

        fun updateWithUrl(url: String) {
            Picasso
                .get()
                .load(url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.mistake)
                .into(mMoviePoster)

            //mMovieTitle.text = url
        }

        override fun onClick(p0: View?) {
            val movieID = mMovieList!![adapterPosition].mMovieID
            mListItemClickListener.onListItemClick(movieID)
        }


    }
}