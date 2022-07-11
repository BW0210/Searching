package com.saba.searching.core.di


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.saba.searching.core.remote.ApiAll
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getBASE_URL_API(): String {
    return "https://www.filimo.com/api/en/"
}


val ApiServiceModule = module {

    single {
        provideGson()
    }

    single {
        retrofit(get(), get())
    }

    single {
        get<Retrofit>().create(ApiAll::class.java)
    }
}


private fun provideGson(): Gson = GsonBuilder().create()

private fun retrofit(gson: Gson, client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(client)
        .baseUrl(getBASE_URL_API())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}
