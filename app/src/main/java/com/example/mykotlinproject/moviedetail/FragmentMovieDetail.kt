package com.example.mykotlinproject.moviedetail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.mykotlinproject.moviedetail.FragmentMovieDetailArgs
import com.example.mykotlinproject.R

/**
 * A simple [Fragment] subclass.
 */

class FragmentMovieDetail : Fragment() {

    val args: FragmentMovieDetailArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tv: TextView = view.findViewById(R.id.detail_text)
        val amount = args.firstArgu
        tv.text = amount.toString()
    }

}
