package com.codepath.nationalparks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


/**
 * The MainActivity for the NationalParks app.
 * Launches a [NationalParksFragment].
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.content, TvShowFragment(), null)
            .commit()
    }
}