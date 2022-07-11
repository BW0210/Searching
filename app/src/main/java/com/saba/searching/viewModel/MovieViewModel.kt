package com.saba.searching.viewModel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import com.saba.searching.core.data.Response
import com.saba.searching.core.repository.MovieRepository
import com.saba.searching.core.utility.LiveEvent
import com.saba.searching.core.utility.execute
import com.saba.searching.view.main.model.MovieDataResponseModel
import com.saba.searching.view.main.model.MovieDetailsModel


/**
 * Created by Mohammad Gholami on 16/06/2022.
 */

class MovieViewModel constructor(val movieRepository: MovieRepository) : ViewModel() {


    val pagingImageListLiveData: LiveEvent<ArrayList<String>> by lazy {
        LiveEvent()
    }

    fun getSearchMovieApi(name : String) = LiveEvent<Response<MovieDataResponseModel>>().apply {
        execute {
            movieRepository.getSearchMovieApi(name){
                this@apply.postValue(it)
            }
        }
    }


}