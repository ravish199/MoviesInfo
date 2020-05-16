package com.example.testapp.view.adapter

import android.content.Context
import android.icu.text.CaseMap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.BR
import com.example.testapp.R
import com.example.testapp.callbacks.OnMovieClickListener
import com.example.testapp.databinding.MovieListAdapterLayoutBinding
import com.example.testapp.model.Search
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_list_adapter_layout.view.*

class MovieListAdapter (private val searchList: List<Search>, private val onMovieClickListener: OnMovieClickListener) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>()
{
    lateinit var context:Context
    lateinit var binding: MovieListAdapterLayoutBinding
    inner class ViewHolder(var binding: MovieListAdapterLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(search:Search) {
            binding.setVariable(BR.adapter, this@MovieListAdapter)
            binding.setVariable(BR.search, search)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {
        context = parent.context
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.movie_list_adapter_layout, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: MovieListAdapter.ViewHolder, position: Int) {
        val search = searchList.get(position)
        Picasso.with(context).load(search.poster).into(viewHolder.itemView.posterImageView)
         viewHolder.bind(search)
    }
    override fun getItemCount(): Int {
        return searchList.size
    }
    fun onItemClick(imdbID: String, title:String) {
        onMovieClickListener.onMovieClick(imdbID, title)
    }
}