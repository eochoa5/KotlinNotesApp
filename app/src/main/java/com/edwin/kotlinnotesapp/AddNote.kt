package com.edwin.kotlinnotesapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.edwin.kotlinnotesapp.databinding.ActivityAddNoteBinding
import com.edwin.kotlinnotesapp.models.Note
import com.edwin.kotlinnotesapp.utils.Utils
import java.text.SimpleDateFormat
import java.util.*

class AddNote : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private  lateinit var note: Note;
    private  lateinit var old_note: Note;
    var isUpdate = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try{
            old_note = intent.getSerializableExtra("current_note") as Note
            binding.editTextNoteTitle.setText(old_note.title)
            binding.editTextNoteContent.setText(old_note.note)
            isUpdate = true
        }catch (e: Exception){
            e.printStackTrace()
        }

        binding.doneBtn.setOnClickListener{

            val title = binding.editTextNoteTitle.text.toString()
            val note_cnt = binding.editTextNoteContent.text.toString()

            if(title.isNotEmpty() || note_cnt.isNotEmpty()){

                val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm a")

                note = if(isUpdate){

                    Note(old_note.id, title, note_cnt, old_note.color, old_note.date)

                }else{
                    Note(null, title, note_cnt, Utils.getRandomColor(), formatter.format(Date()))
                }

                val intent = Intent()
                intent.putExtra("note", note)
                setResult(Activity.RESULT_OK, intent)
                finish()

            }else{
                Toast.makeText(this@AddNote, "Please ad some text", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
        }

        binding.backBtn.setOnClickListener{
            onBackPressed()
        }
    }
}