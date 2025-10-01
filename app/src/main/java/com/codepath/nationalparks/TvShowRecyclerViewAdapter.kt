package com.codepath.nationalparks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class TvShowRecyclerViewAdapter(
    private val shows: List<TvShow>,
    private val mListener: OnTvShowClickListener?
) : RecyclerView.Adapter<TvShowRecyclerViewAdapter.ShowViewHolder>() {

    inner class ShowViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mTitle: TextView = mView.findViewById(R.id.tv_show_title)
        val mDescription: TextView = mView.findViewById(R.id.tv_show_description)
        val mPoster: ImageView = mView.findViewById(R.id.tv_show_poster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tv_show, parent, false)
        return ShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val show = shows[position]
        holder.mTitle.text = show.title
        holder.mDescription.text = show.description
        show.posterUrl?.let {
            Glide.with(holder.mView.context).load(it).into(holder.mPoster)
        }

        // Safe click listener
        holder.mView.setOnClickListener { mListener?.onTvShowClick(show) }
    }

    override fun getItemCount(): Int = shows.size
}