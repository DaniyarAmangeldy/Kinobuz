package kz.arbuz.kinobuz.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kz.arbuz.kinobuz.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopMoviesFragment : Fragment(R.layout.fragment_top_movies) {

    private val moviesViewModel: MovieViewModel by viewModel()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.movie_list)
        val adapter = MovieAdapter()
        recyclerView.adapter = adapter

        moviesViewModel.top250Movies.observe(this, (adapter::submitList))
        moviesViewModel.error.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
}