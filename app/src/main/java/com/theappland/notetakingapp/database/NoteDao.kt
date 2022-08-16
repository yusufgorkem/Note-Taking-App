package com.theappland.notetakingapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.theappland.notetakingapp.model.NoteModel

@Dao
interface NoteDao {

    @Insert
    suspend fun insertAll(vararg notes: NoteModel) : List<Long>

    @Query("SELECT * FROM notes ORDER BY date DESC")
    suspend fun getAllNotes() : List<NoteModel>

    @Query("SELECT * FROM notes WHERE uuid = :noteId")
    suspend fun getNote(noteId: Int) : NoteModel

    @Query("DELETE FROM notes WHERE uuid = :noteId")
    suspend fun deleteNote(noteId: Int)

    @Update
    suspend fun updateNote(note: NoteModel)
}