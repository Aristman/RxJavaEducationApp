package ru.marslab.rxjavaeducationapp

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import ru.marslab.rxjavaeducationapp.data.model.toDomain
import ru.marslab.rxjavaeducationapp.domain.model.Character

private const val NEWS_BASE_URL = "https://inshortsapi.vercel.app/"
private const val RM_BASE_URL = "https://rickandmortyapi.com/api/"

class MainViewModel : ViewModel() {

    private val newsRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(NEWS_BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    private val rmRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(RM_BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val newsApiService = newsRetrofit.create(NewsApiService::class.java)

    private val rmApiService = rmRetrofit.create(RmApiService::class.java)

    fun getAllNews(): Single<String> =
        newsApiService.getNews(category = Category.all.name)
            .subscribeOn(Schedulers.io())

    fun getAllCharacters(): Observable<Character> =
        rmApiService.getAllCharacters()
            .map { result ->
                result.results.map { it.toDomain() }
            }
            .flattenAsObservable { it }
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
