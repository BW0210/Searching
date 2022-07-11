package com.saba.searching.core.repository

import com.saba.searching.core.data.Response
import com.saba.searching.core.remote.ApiAll
import com.saba.searching.core.utility.setNetWork
import com.saba.searching.view.main.model.MovieDataResponseModel

class MovieRepository constructor(
    val apiAll: ApiAll
) {

    suspend fun getSearchMovieApi(
        name : String,
        callBack: (Response<MovieDataResponseModel>) -> Unit
    ) {
        setNetWork(callBack = callBack) {
            apiAll.getSearchMovie(name)
        }
    }

}