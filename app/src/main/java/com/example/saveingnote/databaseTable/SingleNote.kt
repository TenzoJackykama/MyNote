package com.example.saveingnote.databaseTable

import androidx.room.Entity

@Entity(tableName = "single_note")
data class SingleNote(val id: Int,
                      var title: String,
                      var content: String
)
