package com.theappland.notetakingapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.theappland.notetakingapp.R
import com.theappland.notetakingapp.adapter.RecyclerViewAdapter
import com.theappland.notetakingapp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_notes.*

class NotesFragment : Fragment() {
    private lateinit var noteViewModel: NoteViewModel
    private var noteAdapter = RecyclerViewAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        noteViewModel.refreshData()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = noteAdapter

        addNewNoteButton.setOnClickListener {
            val action = NotesFragmentDirections.actionNotesFragmentToNewNoteFragment()
            Navigation.findNavController(it).navigate(action)
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        noteViewModel.notes.observe(viewLifecycleOwner, Observer { notes ->
            notes?.let {
                noteAdapter.updateNotes(notes)
            }
        })
    }
}