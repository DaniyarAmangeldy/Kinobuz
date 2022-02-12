package kz.arbuz.kinobuz.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kz.arbuz.kinobuz.R
import kz.arbuz.kinobuz.domain.entity.Movie

class MovieAdapter : ListAdapter<Movie, MovieAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_movie, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie) {
            val nameTextView = itemView.findViewById<TextView>(R.id.name)
            val posterImageView = itemView.findViewById<ImageView>(R.id.image)
            val yearTextView = itemView.findViewById<TextView>(R.id.year)
            val ratingTextView = itemView.findViewById<TextView>(R.id.rating)

            nameTextView.text = movie.title
            yearTextView.text = movie.year.toString()
            ratingTextView.text = "rating: ${movie.rating}"
            posterImageView.load(movie.image)
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem == newItem
    }
}