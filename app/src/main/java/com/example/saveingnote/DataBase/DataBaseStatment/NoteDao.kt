package com.example.saveingnote.DataBase.DataBaseStatment

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.saveingnote.DataBase.DataBaseTable.SingleNote

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote( singleNote : SingleNote)

    @Query("SELECT * FROM single_note ORDER BY id ASC")
    fun readAllData(): LiveData<List<SingleNote>>
}