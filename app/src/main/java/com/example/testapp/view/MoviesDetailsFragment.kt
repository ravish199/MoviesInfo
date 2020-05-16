package com.example.testapp.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.testapp.R
import com.example.testapp.callbacks.OnMovieClickListener
import com.example.testapp.databinding.FragmentMovieDetailsBinding
import com.example.testapp.databinding.FragmentMoviesBinding
import com.example.testapp.model.Search
import com.example.testapp.utils.AppUtilities
import com.example.testapp.view.adapter.MovieListAdapter
import com.example.testapp.viewmodel.MyViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movies.view.*
import kotlinx.android.synthetic.main.movie_list_adapter_layout.view.*

class MoviesDetailsFragment : Fragment() {

    lateinit var myViewModel: MyViewModel
    lateinit var mainActivity: MainActivity
lateinit var binding:FragmentMovieDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        init()
        return binding.root
    }

    fun init() {
        mainActivity = (activity as MainActivity)
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        myViewModel = mainActivity.myViewModel
        binding.posterImageView.setImageBitmap(null)
        val plot = "full"

        mainActivity.showProgress()
        myViewModel.getMovieDetails(plot, myViewModel.title, myViewModel.imdbID)

        myViewModel.movieDetailsLiveData.observe(viewLifecycleOwner, Observer {
            mainActivity.hideProgress()
            val sb = StringBuilder()
            it.ratings?.forEach {  sb.append("${it.source} : ${it.value}")}
            it.ratingsValue = sb.toString()
            binding.movieDetails = it
            Picasso.with(context).load(it.poster).into(binding.posterImageView)
        })

        myViewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            mainActivity.hideProgress()
            mainActivity.showErrorMessage(getString(R.string.error_in_downloading_data))
        })
    }
}
