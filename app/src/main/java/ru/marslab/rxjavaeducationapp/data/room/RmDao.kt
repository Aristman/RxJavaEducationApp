package ru.marslab.rxjavaeducationapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.Single
import ru.marslab.rxjavaeducationapp.data.model.CharacterDB

@Dao
interface RmDao {
    @Insert(onConflict = REPLACE)
    fun saveCharacters(characters: List<CharacterDB>)

    @Insert(onConflict = REPLACE)
    fun saveCharacter(characters: CharacterDB)

    @Query("SELECT * FROM characterdb")
    fun getCachedCharacters(): Single<List<CharacterDB>>
}