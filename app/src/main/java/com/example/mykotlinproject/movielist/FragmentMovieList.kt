package com.example.mykotlinproject.movielist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.mykotlinproject.movielist.FragmentMovieListDirections
import com.example.mykotlinproject.R

/**
 * A simple [Fragment] subclass.
 */
class FragmentMovieList : Fragment() {
    private lateinit var button: Button
    private lateinit var listAdapter: MovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button = view.findViewById<Button>(R.id.button2)

        setupNavigation()
        //setupListAdapter()

    }

    /***
     * this is a fake function to test Navigation feature
     */
    private fun setupNavigation(){
        button.setOnClickListener {
            val action =
                FragmentMovieListDirections.actionFragmentMovieListToFragmentMovieDetail(
                    11
                )
            findNavController().navigate(action)
        }
    }
    private fun setupListAdapter(){TODO()}

    private fun openMovieDetail(){
        TODO()}

}
