package com.codepath.nationalparks

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray

private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

class TvShowFragment : Fragment(), OnTvShowClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tv_show_list, container, false)
        val progressBar = view.findViewById<ContentLoadingProgressBar>(R.id.progress)
        val recyclerView = view.findViewById<RecyclerView>(R.id.list)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Attach empty adapter initially
        recyclerView.adapter = TvShowRecyclerViewAdapter(emptyList(), this)

        updateAdapter(progressBar, recyclerView)
        return view
    }

    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY

        client[
            "https://api.themoviedb.org/3/tv/popular",
            params,
            object : JsonHttpResponseHandler() {
                override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                    progressBar.hide()
                    val resultsJSON: JSONArray = json.jsonObject.getJSONArray("results")
                    val showsRawJSON = resultsJSON.toString()
                    val gson = Gson()
                    val arrayType = object : TypeToken<List<TvShow>>() {}.type
                    val shows: List<TvShow> = gson.fromJson(showsRawJSON, arrayType)

                    Log.d("TvShowFragment", "Loaded ${shows.size} shows")

                    // Update adapter on main thread
                    activity?.runOnUiThread {
                        recyclerView.adapter = TvShowRecyclerViewAdapter(shows, this@TvShowFragment)
                    }
                }

                override fun onFailure(statusCode: Int, headers: Headers?, errorResponse: String, t: Throwable?) {
                    progressBar.hide()
                    Log.e("TvShowFragment", "API failed: $errorResponse", t)

                    // Show toast on main thread
                    activity?.runOnUiThread {
                        Toast.makeText(requireContext(), "API request failed: $statusCode", Toast.LENGTH_LONG).show()
                    }
                }
            }
        ]
    }

    override fun onTvShowClick(show: TvShow) {
        val intent = Intent(requireContext(), DetailActivity::class.java)
        intent.putExtra("title", show.title)
        intent.putExtra("description", show.description)
        intent.putExtra("posterUrl", show.posterUrl)
        intent.putExtra("firstAirDate", show.firstAirDate)
        intent.putExtra("rating", show.rating?.toString())
        intent.putExtra("language", show.language ?: "N/A")
        startActivity(intent)
    }
}