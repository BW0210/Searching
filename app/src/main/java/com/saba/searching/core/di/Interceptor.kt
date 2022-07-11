package com.saba.searching.core.di

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class Interceptor : Interceptor {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()

        val newUrl = original.url.newBuilder()
            .host("filimo.com")
            .build()

        original = original.newBuilder()
            .addHeader("jsonType", "simple")
            .url(newUrl)
            .build()

        return chain.proceed(original)
    }

}

