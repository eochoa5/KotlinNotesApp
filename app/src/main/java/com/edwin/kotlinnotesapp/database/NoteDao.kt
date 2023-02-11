package com.edwin.kotlinnotesapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.edwin.kotlinnotesapp.models.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("select * from notes_table order by id ASC")
     fun getAllNotes(): LiveData<List<Note>>

    @Query("update notes_table set title = :title, note = :note where id = :id")
    suspend fun update(id: Int?, title:String?, note: String?)


}