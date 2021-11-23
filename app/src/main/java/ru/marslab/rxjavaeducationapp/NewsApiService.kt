package ru.marslab.rxjavaeducationapp

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("news")
    fun getNews(@Query("category") category: String): Single<String>
}
