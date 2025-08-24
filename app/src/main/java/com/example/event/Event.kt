package com.example.event
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class Event(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val date: Long,   // store date as timestamp (millis)
    val time: String  // store time as string (e.g. "10:30 AM")
)
