package kz.arbuz.kinobuz.di

import kz.arbuz.kinobuz.BuildConfig
import kz.arbuz.kinobuz.data.api.ImdbService
import kz.arbuz.kinobuz.domain.usecase.GetTop250MoviesUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

val module = module {

    factory {
        GetTop250MoviesUseCase(service = get())
    }

    single {
        Retrofit
            .Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .callTimeout(60_000, TimeUnit.MILLISECONDS)
                    .readTimeout(60_000, TimeUnit.MILLISECONDS)
                    .addInterceptor(HttpLoggingInterceptor())
                    .build()
            )
            .build()
    }

    single { get<Retrofit>().create<ImdbService>() }
}