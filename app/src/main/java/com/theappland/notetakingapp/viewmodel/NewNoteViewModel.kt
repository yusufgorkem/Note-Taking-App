package com.theappland.notetakingapp.viewmodel

import android.app.Application
import com.theappland.notetakingapp.database.NoteDatabase
import com.theappland.notetakingapp.model.NoteModel
import kotlinx.coroutines.launch

class NewNoteViewModel(application : Application) : BaseViewModel(application) {
    private val noteViewModel = NoteViewModel(application)

    fun storeInSQLite(noteTitle: String, noteDetails: String, noteDate:String) {
        launch {
            val note = NoteModel(noteTitle,noteDetails,noteDate)
            val list = listOf(note)
            val dao = NoteDatabase(getApplication()).noteDao()
            val listLong = dao.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list[i].uuid = listLong[i].toInt()
                i += 1
            }
            noteViewModel.showNotes(list)
        }
    }
}