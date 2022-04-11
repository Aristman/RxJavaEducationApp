package ru.marslab.rxjavaeducationapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.marslab.rxjavaeducationapp.data.model.CharacterDB

@Database(entities = [CharacterDB::class], version = 1, exportSchema = false)
abstract class RmDatabase : RoomDatabase() {
    abstract fun rmDao(): RmDao
}