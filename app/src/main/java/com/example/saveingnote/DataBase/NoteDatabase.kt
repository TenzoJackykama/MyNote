package com.example.saveingnote.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.saveingnote.DataBase.DataBaseTable.SingleNote

@Database(entities = [SingleNote::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {
}