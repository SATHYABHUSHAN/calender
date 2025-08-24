package com.example.event

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Event)

    @Update
    suspend fun update(event: Event)

    @Delete
    suspend fun delete(event: Event)

    @Query("SELECT * FROM events WHERE date = :date ORDER BY time ASC")
    fun getEventsByDate(date: Long): LiveData<List<Event>>

    @Query("SELECT * FROM events ORDER BY date ASC, time ASC")
    fun getAllEvents(): LiveData<List<Event>>
}
