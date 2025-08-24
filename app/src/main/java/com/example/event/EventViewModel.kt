package com.example.event
import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class EventViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: EventRepository
    val allEvents: LiveData<List<Event>>

    init {
        val eventDao = EventDatabase.getDatabase(application).eventDao()
        repository = EventRepository(eventDao)
        allEvents = repository.getAllEvents()
    }

    fun getEventsByDate(date: Long): LiveData<List<Event>> {
        return repository.getEventsByDate(date)
    }

    fun insert(event: Event) = viewModelScope.launch {
        repository.insert(event)
    }

    fun update(event: Event) = viewModelScope.launch {
        repository.update(event)
    }

    fun delete(event: Event) = viewModelScope.launch {
        repository.delete(event)
    }
}
