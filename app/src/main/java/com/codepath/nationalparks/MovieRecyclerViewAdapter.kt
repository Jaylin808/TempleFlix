package com.codepath.nationalparks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieRecyclerViewAdapter(
    private val movies: List<Movie>,
    private val mListener: OnMovieItemClickListener?
) : RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Movie? = null
        val mMovieTitle: TextView = mView.findViewById(R.id.movie_title)
        val mMovieDescription: TextView = mView.findViewById(R.id.movie_description)
        val mMoviePoster: ImageView = mView.findViewById(R.id.movie_poster)

        override fun toString(): String {
            return mMovieTitle.text.toString() + " '" + mMovieDescription.text + "'"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.mItem = movie
        holder.mMovieTitle.text = movie.title
        holder.mMovieDescription.text = movie.description

        // Load poster with Glide
        movie.posterUrl?.let {
            Glide.with(holder.mView.context)
                .load(it)
                .into(holder.mMoviePoster)
        }

        holder.mView.setOnClickListener {
            holder.mItem?.let { m -> mListener?.onMovieClick(m) }
        }
    }

    override fun getItemCount(): Int = movies.size
}