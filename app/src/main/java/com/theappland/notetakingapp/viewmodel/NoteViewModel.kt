package com.theappland.notetakingapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.theappland.notetakingapp.database.NoteDatabase
import com.theappland.notetakingapp.model.NoteModel
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : BaseViewModel(application) {
    val notes = MutableLiveData<List<NoteModel>>()

    fun refreshData() {
        getDataFromSQLite()
    }

    private fun getDataFromSQLite() {
        launch {
            val notes = NoteDatabase(getApplication()).noteDao().getAllNotes()
            showNotes(notes)
        }
    }

    fun showNotes(noteList : List<NoteModel>) {
        notes.value = noteList
    }
}