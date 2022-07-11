package com.saba.searching.core.di


import com.saba.searching.core.repository.MovieRepository
import org.koin.dsl.module

val RepositoryModule = module {
    single {
        MovieRepository(get())
    }

}