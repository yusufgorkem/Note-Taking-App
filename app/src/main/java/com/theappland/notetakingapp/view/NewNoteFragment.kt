package com.theappland.notetakingapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.theappland.notetakingapp.R
import com.theappland.notetakingapp.viewmodel.NewNoteViewModel
import kotlinx.android.synthetic.main.fragment_new_note.*
import java.text.SimpleDateFormat
import java.util.*

class NewNoteFragment : Fragment() {
    private lateinit var newNoteViewModel : NewNoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newNoteViewModel = ViewModelProvider(this)[NewNoteViewModel::class.java]

        saveNoteButton.setOnClickListener {
                if (newNoteTitleEditText.text.toString() != "" && newNoteDetailsEditText.text.toString() != "") {
                    val noteTitle = newNoteTitleEditText.text.toString()
                    val noteDetails = newNoteDetailsEditText.text.toString()
                    val noteDate = date()

                    newNoteViewModel.storeInSQLite(noteTitle,noteDetails,noteDate)

                    NavHostFragment.findNavController(this).popBackStack()
                }
        }
    }

    private fun date(): String {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
    }
}