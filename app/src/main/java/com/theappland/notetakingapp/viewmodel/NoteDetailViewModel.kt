package com.theappland.notetakingapp.viewmodel

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.NavHostFragment
import com.theappland.notetakingapp.database.NoteDatabase
import com.theappland.notetakingapp.model.NoteModel
import kotlinx.coroutines.launch

class NoteDetailViewModel(application: Application) : BaseViewModel(application) {
    val selectedNoteData = MutableLiveData<NoteModel>()

    fun getNote(uuid: Int) {
        launch {
            val dao = NoteDatabase(getApplication()).noteDao()
            val note = dao.getNote(uuid)
            selectedNoteData.value = note
        }
    }

    fun updateNote(updatedNoteTitle: String, updatedNoteDetails: String, updatedNoteDate:String
                   ,fragment: Fragment,selectedUuid: Int) {
        launch {
            val updatedNote = NoteModel(updatedNoteTitle,updatedNoteDetails,updatedNoteDate)
            updatedNote.uuid = selectedUuid
            val dao = NoteDatabase(getApplication()).noteDao()
            dao.updateNote(updatedNote)
            NavHostFragment.findNavController(fragment).popBackStack()
        }
    }

    fun deleteNote(uuid : Int, fragment : Fragment) {
        launch {
            val dao = NoteDatabase(getApplication()).noteDao()
            val note = dao.getNote(uuid)
            dao.deleteNote(note.uuid)
            NavHostFragment.findNavController(fragment).popBackStack()
        }
    }
}