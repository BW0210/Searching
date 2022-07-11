package com.saba.searching.core.di

import com.saba.searching.viewModel.MovieViewModel
import org.koin.dsl.module


val ViewModelModule = module {
    single {
        MovieViewModel(get())
    }

}