package ru.marslab.rxjavaeducationapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterDB(
    @PrimaryKey val id: Int,
    val name: String,
    val created: String,
    val episodes: String,
    val gender: String,
    val image: String,
    val url: String
)
