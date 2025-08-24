package com.example.event

class EventRepository(private val eventDao: EventDao) {

    fun getEventsByDate(date: Long) = eventDao.getEventsByDate(date)

    fun getAllEvents() = eventDao.getAllEvents()

    suspend fun insert(event: Event) = eventDao.insert(event)

    suspend fun update(event: Event) = eventDao.update(event)

    suspend fun delete(event: Event) = eventDao.delete(event)
}
