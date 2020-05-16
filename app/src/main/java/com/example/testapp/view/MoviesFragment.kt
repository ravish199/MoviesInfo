package com.example.testapp.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.testapp.R
import com.example.testapp.callbacks.OnMovieClickListener
import com.example.testapp.databinding.FragmentMoviesBinding
import com.example.testapp.model.Search
import com.example.testapp.utils.AppUtilities
import com.example.testapp.view.adapter.MovieListAdapter
import com.example.testapp.viewmodel.MyViewModel
import kotlinx.android.synthetic.main.fragment_movies.*


class MoviesFragment : Fragment(), OnMovieClickListener {

    enum class CAT {
        movie, series, episode
    }
    var cat = CAT.movie.name
    var page = 1
    lateinit var myViewModel: MyViewModel
    lateinit var movieListAdapter: MovieListAdapter
    var movieList = ArrayList<Search>()
    lateinit var mainActivity: MainActivity
    lateinit var binding:FragmentMoviesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        binding.fragement = this
        init()
        return binding.root
    }

    fun init() {
        binding.movieRView.layoutManager = LinearLayoutManager(context)
        mainActivity = activity as MainActivity
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        myViewModel = mainActivity.myViewModel
        movieListAdapter = MovieListAdapter(movieList, this)
        binding.movieRView.adapter = movieListAdapter
        binding.movieRadio.isChecked = true
        var searchText = ""
        var isLoadMore = false

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
               if(s?.length!! >= 3) {
                   page = 1
                   searchText = s.toString()
                getMoviesList(cat, page, searchText)
               } else {
                   movieList.clear()
                   movieListAdapter.notifyDataSetChanged()
               }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

            myViewModel.movieLiveData.observe(viewLifecycleOwner, Observer {
                mainActivity.hideProgress()
                   if(!isLoadMore)
                       movieList.clear()
                if(it != null) {
                    movieList.addAll(it)
                    movieListAdapter.notifyDataSetChanged()
                } else
                {
                    movieListAdapter.notifyDataSetChanged()
                }
            })

        myViewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            mainActivity.hideProgress()
            mainActivity.showErrorMessage(getString(R.string.error_in_downloading_data))
            movieList.clear()
            movieListAdapter.notifyDataSetChanged()
        })

        binding.movieRView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    isLoadMore = true
                    getMoviesList(cat, ++page, searchText)
                }
            }
        })
    }

    fun getCategory(c:CAT) {
        cat = c.name
    }

    fun getMoviesList(type: String, page: Int, search: String) {
            mainActivity.showProgress()
            myViewModel.getMovieList(cat, page, search)
    }

    override fun onMovieClick(imdbID: String, title:String) {
        myViewModel.imdbID = imdbID
        myViewModel.title = title
        parentFragmentManager.beginTransaction()
            .setCustomAnimations( R.anim.enter_from_right, R.anim.exit_to_left,R.anim.enter_from_left, R.anim.exit_to_right)
            .replace(R.id.fContainer, MoviesDetailsFragment()).addToBackStack(null).commit()
    }

}
