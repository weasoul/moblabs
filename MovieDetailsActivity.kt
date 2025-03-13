package com.example.a1lab

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movie = intent.getParcelableExtra<Movie>("MOVIE")

        if (movie != null) {
            val titleTextView: TextView = findViewById(R.id.detailsTitleTextView)
            val yearTextView: TextView = findViewById(R.id.detailsYearTextView)
            val extractTextView: TextView = findViewById(R.id.extractTextView)
            val thumbnailImageView: ImageView = findViewById(R.id.detailsThumbnailImageView)
            val watchButton: Button = findViewById(R.id.watchButton)

            titleTextView.text = movie.title
            yearTextView.text = movie.year.toString()
            extractTextView.text = movie.extract
            Glide.with(this).load(movie.thumbnail).into(thumbnailImageView)

            watchButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.imdb.com/find?q=${movie.title}"))
                startActivity(intent)
            }
        }
        else{
            Toast.makeText(this, "Movie data is missing", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}