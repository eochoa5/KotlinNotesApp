package com.edwin.kotlinnotesapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.edwin.kotlinnotesapp.R
import com.edwin.kotlinnotesapp.models.Note

class NotesAdapter(private val context: Context, val listener: notesItemClickListener): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val notesList = ArrayList<Note>()
    private val fullList = ArrayList<Note>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list_item, parent, false))
    }



    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = notesList[position]
        holder.noteTitle.text = currentNote.title
        holder.noteContent.text = currentNote.note
        holder.noteDate.text = currentNote.date

        holder.noteTitle.isSelected = true
        holder.noteDate.isSelected = true

        val color = currentNote.color

        holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(color!!, null))

        holder.notes_layout.setOnClickListener{
            listener.onItemClicked(notesList[holder.adapterPosition])
        }

        holder.notes_layout.setOnLongClickListener {
            listener.onLongItemClicked(notesList[holder.adapterPosition], holder.notes_layout)
            true
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun updateList(newList: List<Note>){

        fullList.clear()
        fullList.addAll(newList)

        notesList.clear()
        notesList.addAll(fullList)
        notifyDataSetChanged()

    }

    fun filterList(search: String){

        notesList.clear()

        for (item in fullList){

            if(item.title?.lowercase()?.contains(search.lowercase()) == true ||
                item.note?.lowercase()?.contains(search.lowercase()) == true ){

                notesList.add(item)

            }

        }

        notifyDataSetChanged()

    }

    interface  notesItemClickListener{

        fun onItemClicked(note: Note)
        fun onLongItemClicked(note: Note, cardView: CardView)

    }

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val notes_layout = itemView.findViewById<CardView>(R.id.notes_item_layout)
        val noteTitle = itemView.findViewById<TextView>(R.id.noteTitle)
        val noteContent = itemView.findViewById<TextView>(R.id.noteContent)
        val noteDate = itemView.findViewById<TextView>(R.id.noteDate)
    }
}