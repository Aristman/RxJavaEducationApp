package ru.marslab.rxjavaeducationapp

import io.reactivex.Single
import model.CharactersNW
import retrofit2.http.GET

interface RmApiService {
    @GET("character")
    fun getAllCharacters(): Single<CharactersNW>
}