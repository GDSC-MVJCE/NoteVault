package com.example.notevault.data

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

// Create a entity class to store objects
@Entity(tableName = "notes")
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "content")
    val content: String
)

// Create a DOA interface to perform DB operations
@Dao
interface NoteDoa {

    @Query("SELECT * FROM notes")
    fun getAll(): List<NoteModel>

    @Insert
    fun insert(note: NoteModel)
}

@Database(entities = [NoteModel::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDoa
}
