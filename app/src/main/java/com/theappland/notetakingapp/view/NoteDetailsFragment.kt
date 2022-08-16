package com.theappland.notetakingapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.theappland.notetakingapp.R
import com.theappland.notetakingapp.databinding.FragmentNoteDetailsBinding
import com.theappland.notetakingapp.viewmodel.NoteDetailViewModel
import kotlinx.android.synthetic.main.fragment_note_details.*
import java.text.SimpleDateFormat
import java.util.*

class NoteDetailsFragment : Fragment() {
    private lateinit var noteDetailViewModel : NoteDetailViewModel
    private lateinit var dataBinding : FragmentNoteDetailsBinding
    private var noteUuid = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_note_details,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            noteUuid = NoteDetailsFragmentArgs.fromBundle(it).selectedNoteUuid
        }

        noteDetailViewModel = ViewModelProvider(this)[NoteDetailViewModel::class.java]
        noteDetailViewModel.getNote(noteUuid)

        updateNoteButton.setOnClickListener {
            if (updateNoteTitleEditText.text.toString() != "" && updateNoteDetailsEditText.text.toString() != "") {
                    val updatedNoteTitle = updateNoteTitleEditText.text.toString()
                    val updatedNoteDetails = updateNoteDetailsEditText.text.toString()
                    val updatedNoteDate = date()

                    arguments?.let {
                        if (NoteDetailsFragmentArgs.fromBundle(it).selectedNoteUuid != -1) {
                            val selectedUuid = NoteDetailsFragmentArgs.fromBundle(it).selectedNoteUuid
                            noteDetailViewModel.updateNote(updatedNoteTitle,updatedNoteDetails,updatedNoteDate,this
                            ,selectedUuid)
                        }
                    }
                }
        }

        deleteNoteButton.setOnClickListener {
            arguments?.let {
                if (NoteDetailsFragmentArgs.fromBundle(it).selectedNoteUuid != -1) {
                val selectedUuid = NoteDetailsFragmentArgs.fromBundle(it).selectedNoteUuid
                noteDetailViewModel.deleteNote(selectedUuid,this)
                }
            }
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        noteDetailViewModel.selectedNoteData.observe(viewLifecycleOwner, androidx.lifecycle.Observer { selectedNote ->
            selectedNote?.let {
                dataBinding.selectedNote = selectedNote
            }
        })
    }

    private fun date(): String {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
    }
}