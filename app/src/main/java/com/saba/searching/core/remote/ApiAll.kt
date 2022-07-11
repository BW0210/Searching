package com.saba.searching.core.remote

import com.saba.searching.view.main.model.MovieDataResponseModel
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiAll {

    @GET("v1/movie/movie/list/tagid/1000300/text/{Query}/sug/on")
    suspend fun getSearchMovie(
        @Path("Query") name: String
    ) : MovieDataResponseModel
}
