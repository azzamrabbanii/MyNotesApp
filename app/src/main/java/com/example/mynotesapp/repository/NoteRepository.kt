package com.example.mynotesapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.mynotesapp.database.Note
import com.example.mynotesapp.database.NoteDao
import com.example.mynotesapp.database.NoteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {

    private val mNoteDao: NoteDao

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = NoteRoomDatabase.getDataBase(application)
        mNoteDao = db.noteDao()
    }

    fun getAllNotes(): LiveData<List<Note>> = mNoteDao.getAllNotes()

    fun insert(note: Note) {
        executorService.execute { mNoteDao.insert(note) } //lambda
    }

    fun delete(note: Note) {
        executorService.execute { mNoteDao.delete(note) }
    }

    fun update(note: Note) {
        executorService.execute { mNoteDao.update(note) }
    }

}