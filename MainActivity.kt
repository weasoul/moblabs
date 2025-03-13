package com.example.a1lab

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val ratingGroup1: RadioGroup = findViewById(R.id.ratingGroup1)
        val ratingGroup2: RadioGroup = findViewById(R.id.ratingGroup2)
        val ratingGroup3: RadioGroup = findViewById(R.id.ratingGroup3)

        ratingGroup1.setOnCheckedChangeListener { group, checkedId ->
            showRating(1, checkedId)
        }

        ratingGroup2.setOnCheckedChangeListener { group, checkedId ->
            showRating(2, checkedId)
        }

        ratingGroup3.setOnCheckedChangeListener { group, checkedId ->
            showRating(3, checkedId)
        }

        val jsonString = assets.open("movies-2020s.json").bufferedReader().use { it.readText() }
        val movieType = object : TypeToken<List<Movie>>() {}.type
        val movies: List<Movie> = Gson().fromJson(jsonString, movieType)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MovieAdapter(movies)
    }

    private fun showRating(cardNumber: Int, checkedId: Int) {
        val radioButton: RadioButton = findViewById(checkedId)
        val rating = radioButton.text.toString()
        Toast.makeText(this, "Оценка $cardNumber: $rating", Toast.LENGTH_SHORT).show()
    }
}