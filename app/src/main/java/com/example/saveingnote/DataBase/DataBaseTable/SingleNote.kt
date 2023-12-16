package com.example.saveingnote.DataBase.DataBaseTable

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "single_note")
data class SingleNote(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var title: String,
    var content: String
)
