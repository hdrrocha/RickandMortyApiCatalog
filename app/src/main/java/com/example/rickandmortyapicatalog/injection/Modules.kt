package com.example.rickandmortyapicatalog.injection

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.example.rickandmortyapicatalog.BuildConfig
import com.example.rickandmortyapicatalog.data.api.RickAndMortyApi
import com.example.rickandmortyapicatalog.data.model.CharacterInfo
import com.example.rickandmortyapicatalog.data.repository.CharacterListRepositoryImp
import com.example.rickandmortyapicatalog.data.source.CharacterListPagingSource
import com.example.rickandmortyapicatalog.domain.mapper.CharacterListMapperImp
import com.example.rickandmortyapicatalog.domain.mapper.abs.CharacterListMapper
import com.example.rickandmortyapicatalog.domain.repository.CharacterListRepository
import com.example.rickandmortyapicatalog.domain.usercase.CharacterListUseCaseImp
import com.example.rickandmortyapicatalog.domain.usercase.abs.CharacterListUseCase
import com.example.rickandmortyapicatalog.domain.viewmodel.CharacterListViewModel
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


object Modules {
    private val network = module {

        single {
            Moshi.Builder()
                .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                .build()
        }

        single {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(OkHttpProfilerInterceptor())
                        .build()
                )
                .build()
        }
    }

    private val api = module {
        single {
            val retrofit: Retrofit = get()
            retrofit.create(RickAndMortyApi::class.java)
        }
    }
    private val paging = module {
        single<PagingSource<Int, CharacterInfo>> { CharacterListPagingSource(api = get()) }
        single { PagingConfig(CharacterListRepositoryImp.DEFAULT_LIST_SIZE) }
        single { Pager(get()) { get<PagingSource<Int, CharacterInfo>>() } }
    }

    private val repository = module {
        single<CharacterListRepository> {
            CharacterListRepositoryImp(
                pager = get()
            )
        }
    }

    private val mapper = module {
        single<CharacterListMapper> {
            CharacterListMapperImp()
        }
    }

    private val useCase = module {
        single<CharacterListUseCase> {
            CharacterListUseCaseImp(
                mapper = get(),
                characterListRepository = get()
            )
        }
    }

    private val viewModel = module {
        viewModel {
            CharacterListViewModel(
                useCase = get()
            )
        }

    }

    private val database = module {
        //TODO
    }

    private val dao = module {
        //TODO
    }


    private val router = module {
        //TODO
    }

    var all = listOf(
        network,
        api,
        paging,
        repository,
        mapper,
        useCase,
        viewModel,
        router

    )
}

