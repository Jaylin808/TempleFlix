package com.codepath.nationalparks

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        findViewById<TextView>(R.id.detail_title).text =
            intent.getStringExtra("title")
        findViewById<TextView>(R.id.detail_description).text =
            intent.getStringExtra("description")
        findViewById<TextView>(R.id.detail_air_date).text =
            "First air date: ${intent.getStringExtra("firstAirDate")}"
        findViewById<TextView>(R.id.detail_rating).text =
            "Rating: ${intent.getStringExtra("rating")}"
        findViewById<TextView>(R.id.detail_language).text =
            "Language: ${intent.getStringExtra("language") ?: "N/A"}"

        val poster = findViewById<ImageView>(R.id.detail_poster)
        Glide.with(this).load(intent.getStringExtra("posterUrl")).into(poster)
    }
}