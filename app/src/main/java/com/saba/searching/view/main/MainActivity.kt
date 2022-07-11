package com.saba.searching.view.main

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jakewharton.rxbinding.widget.RxTextView
import com.saba.searching.R
import com.saba.searching.core.base.BaseActivity
import com.saba.searching.core.data.Response
import com.saba.searching.core.utility.observe
import com.saba.searching.view.main.adapter.MovieListAdapter
import com.saba.searching.view.main.model.Attributes
import com.saba.searching.view.main.model.MovieDetailsModel
import com.saba.searching.viewModel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class MainActivity : BaseActivity() {

    private val movieViewModel by viewModel<MovieViewModel>()

    lateinit var movieAdapter : MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //getSearchMovieApi("a")

        RxTextView.textChanges(edt_movie_name)
            .debounce(2, TimeUnit.SECONDS)
            .subscribe {
                runOnUiThread {
                    if (!it.isNullOrEmpty()) {
                        getSearchMovieApi(it.toString())
                    }
                }
            }

    }

    private fun getSearchMovieApi(name : String) {
        observe(movieViewModel.getSearchMovieApi(name)){
            when (it!!.status){
                Response.Status.LOADING -> showWaitingDialog()
                Response.Status.ERROR -> dismissWaitingDialog()
                Response.Status.SUCCESS -> {
                    dismissWaitingDialog()
                    updateMovieList(it.data?.data ?: arrayListOf())
                    Timber.tag("MTG").e("-------------------------> List Size : ${it.data?.data?.size}")
                }
            }
        }
    }

    fun updateMovieList(movieList: ArrayList<Attributes>){
        if (!::movieAdapter.isInitialized){
            setInitMovieListList()
        }

        movieAdapter.submitList(movieList)
    }

    private fun setInitMovieListList() {
        movieAdapter = MovieListAdapter {}
        rec_movie.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false) //StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = movieAdapter
        }
    }
}