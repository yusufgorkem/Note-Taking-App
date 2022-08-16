package com.theappland.notetakingapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.theappland.notetakingapp.R
import com.theappland.notetakingapp.databinding.RecyclerRowBinding
import com.theappland.notetakingapp.model.NoteModel
import com.theappland.notetakingapp.view.NotesFragmentDirections
import kotlinx.android.synthetic.main.recycler_row.view.*

class RecyclerViewAdapter(private val noteList : ArrayList<NoteModel>) : RecyclerView.Adapter<RecyclerViewAdapter.NoteViewHolder>(), NoteClickListener {

    class NoteViewHolder(var view: RecyclerRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.recycler_row,parent,false)
        val view = DataBindingUtil.inflate<RecyclerRowBinding>(inflater,R.layout.recycler_row,parent,false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.view.note = noteList[position]
        holder.view.listener = this

        /*
        holder.view.noteTitleTextView.text = noteList[position].noteTitle
        holder.view.noteDateTextView.text = noteList[position].noteDate

        holder.view.setOnClickListener {
            val action = NotesFragmentDirections.actionNotesFragmentToNoteDetailsFragment(noteList[position])
            Navigation.findNavController(it).navigate(action)
        }
         */
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    fun updateNotes(newNoteListItem : List<NoteModel>) {
        noteList.clear()
        noteList.addAll(newNoteListItem)
        notifyDataSetChanged()
    }

    override fun onNoteClicked(v: View) {
        val uuid = v.noteUuidTextView.text.toString().toInt()
        val action = NotesFragmentDirections.actionNotesFragmentToNoteDetailsFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}