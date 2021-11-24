package ru.marslab.rxjavaeducationapp.data.model

import model.CharactersNW
import ru.marslab.rxjavaeducationapp.domain.model.Character

fun CharactersNW.Character.toDomain(): Character =
    Character(
        id = id,
        name = name,
        created = created,
        episode = episode,
        gender = gender,
        image = image,
        url = url
    )