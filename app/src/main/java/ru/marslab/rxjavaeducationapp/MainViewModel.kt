package ru.marslab.rxjavaeducationapp

import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val BASE_URL = "https://inshortsapi.vercel.app/"

class MainViewModel : ViewModel() {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    private val newsApiService = retrofit.create(NewsApiService::class.java)

    fun getAllNews(): Single<String> =
        newsApiService.getNews(category = Category.all.name)
            .subscribeOn(Schedulers.io())
}

enum class Category {
    all,
    national,
    business,
    sports,
    world,
    politics,
    technology,
    startup,
    entertainment,
    miscellaneous,
    hatke,
    science,
    automobile
}
