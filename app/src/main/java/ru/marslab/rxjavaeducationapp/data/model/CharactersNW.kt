package model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharactersNW(
    @SerialName("info")
    val info: Info,
    @SerialName("results")
    val results: List<Character>
) {
    @Serializable
    data class Info(
        @SerialName("count")
        val count: Int,
        @SerialName("pages")
        val pages: Int,
        @SerialName("next")
        val next: String,
        @SerialName("prev")
        val prev: String?
    )

    @Serializable
    data class Character(
        @SerialName("created")
        val created: String,
        @SerialName("episode")
        val episode: List<String>,
        @SerialName("gender")
        val gender: String,
        @SerialName("id")
        val id: Int,
        @SerialName("image")
        val image: String,
        @SerialName("location")
        val location: Location,
        @SerialName("name")
        val name: String,
        @SerialName("origin")
        val origin: Origin,
        @SerialName("species")
        val species: String,
        @SerialName("status")
        val status: String,
        @SerialName("type")
        val type: String,
        @SerialName("url")
        val url: String
    ) {
        @Serializable
        data class Location(
            @SerialName("name")
            val name: String,
            @SerialName("url")
            val url: String
        )

        @Serializable
        data class Origin(
            @SerialName("name")
            val name: String,
            @SerialName("url")
            val url: String
        )
    }
}