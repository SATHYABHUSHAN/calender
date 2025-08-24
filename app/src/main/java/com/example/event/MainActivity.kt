package com.example.event

import android.os.Bundle
import android.widget.CalendarView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.event.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity() {

    private val eventViewModel: EventViewModel by viewModels()
    private lateinit var eventAdapter: EventAdapter
    private var selectedDate: Long = System.currentTimeMillis()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ✅ Correct R import (uses your app package)
        setContentView(R.layout.activity_main)

        val calendarView: CalendarView = findViewById(R.id.calendarView)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewEvents)
        val fab: FloatingActionButton = findViewById(R.id.fabAddEvent)

        eventAdapter = EventAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = eventAdapter

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val cal = Calendar.getInstance()
            cal.set(year, month, dayOfMonth, 0, 0, 0)
            selectedDate = cal.timeInMillis
            loadEventsForDate(selectedDate)
        }

        fab.setOnClickListener {
            val newEvent = Event(
                title = "Sample Event",
                description = "Description",
                date = selectedDate,
                time = "10:00 AM"
            )
            eventViewModel.insert(newEvent)
        }

        loadEventsForDate(selectedDate)
    }

    private fun loadEventsForDate(date: Long) {
        eventViewModel.getEventsByDate(date).observe(this) { events ->
            eventAdapter.setEvents(events)
        }
    }
}
