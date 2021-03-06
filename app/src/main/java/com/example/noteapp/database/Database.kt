package com.example.noteapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp.dao.NotesDao
import com.example.noteapp.model.Notes

@androidx.room.Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase(){

    abstract fun notesDao() : NotesDao

    companion object {
        @Volatile
        var INSTANCE: Database? = null

        fun getDatabaseInstance(context: Context): Database {

            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this)
            {
                val roomDatabaseInstance = Room.databaseBuilder(context,Database::class.java,"Notes").allowMainThreadQueries().build()

                INSTANCE = roomDatabaseInstance

                return roomDatabaseInstance
            }
        }
    }
}