package com.example.a1lab

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val yearTextView: TextView = itemView.findViewById(R.id.yearTextView)
        private val thumbnailImageView: ImageView = itemView.findViewById(R.id.thumbnailImageView)

        fun bind(movie: Movie) {
            titleTextView.text = movie.title
            yearTextView.text = movie.year.toString()
            Glide.with(itemView.context).load(movie.thumbnail).into(thumbnailImageView)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, MovieDetailsActivity::class.java).apply {
                    putExtra("MOVIE", movie)
                }
                itemView.context.startActivity(intent)
            }
        }
    }
}