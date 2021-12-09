package ru.marslab.rxjavaeducationapp.domain.model

data class Character(
    val id: Int,
    val name: String,
    val created: String,
    val episode: List<String>,
    val gender: String,
    val image: String,
    val url: String
)
