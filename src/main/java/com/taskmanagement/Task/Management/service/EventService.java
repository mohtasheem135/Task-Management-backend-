package com.taskmanagement.Task.Management.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagement.Task.Management.model.Event;
import com.taskmanagement.Task.Management.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	public List<Event> getAllEvents() {
		return eventRepository.findAll();
	}

	public Event createEvent(Event event) {
		if (event.getCreatedAt() == null) {
			event.setCreatedAt(LocalDateTime.now()); // Set current date and time if not provided
		}
		return eventRepository.save(event);
	}

	public Event updateEvent(Long eventId, Event updatedEvent) {
		Optional<Event> optionalEvent = eventRepository.findById(eventId);

		if (optionalEvent.isPresent()) {
			Event existingEvent = optionalEvent.get();

			// Update event details
			existingEvent.setEventName(updatedEvent.getEventName());
			existingEvent.setCreatedAt(
					updatedEvent.getCreatedAt() != null ? updatedEvent.getCreatedAt() : existingEvent.getCreatedAt());
			existingEvent.setIsActive(updatedEvent.getIsActive());
			existingEvent.setDescription(updatedEvent.getDescription());

			// Do not modify subtasks if not provided
			if (updatedEvent.getSubtasks() != null && !updatedEvent.getSubtasks().isEmpty()) {
				existingEvent.setSubtasks(updatedEvent.getSubtasks());
			}

			// Set updatedAt to current date and time
			existingEvent.setUpdatedAt(LocalDateTime.now());

			// Save and return updated event
			return eventRepository.save(existingEvent);
		} else {
			throw new RuntimeException("Event not found with id: " + eventId);
		}
	}
}
