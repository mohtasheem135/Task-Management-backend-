package com.taskmanagement.Task.Management.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagement.Task.Management.dto.EventDTO;
import com.taskmanagement.Task.Management.dto.TaskDTO;
import com.taskmanagement.Task.Management.exception.ResourceNotFoundException;
import com.taskmanagement.Task.Management.model.Event;
import com.taskmanagement.Task.Management.model.Task;
import com.taskmanagement.Task.Management.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	
	public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
	
	public EventDTO createEvent(EventDTO eventDTO) {
        Event event = mapToEntity(eventDTO);
        Event newEvent = eventRepository.save(event);
        return mapToDTO(newEvent);
    }
	
	public EventDTO updateEvent(Long eventId, EventDTO eventDTO) {
        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + eventId));

        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setIsActive(eventDTO.getIsActive());
        event.setUpdatedAt(LocalDateTime.now());
        eventRepository.save(event);

        return mapToDTO(event);
    }
	
	private EventDTO mapToDTO(Event event) {
        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setName(event.getName());
        dto.setDescription(event.getDescription());
        dto.setCreatedAt(event.getCreatedAt());
        dto.setUpdatedAt(event.getUpdatedAt());
        dto.setIsActive(event.getIsActive());
        // Add conversion for subtasks if necessary
        if (event.getTasks() != null) {
            List<TaskDTO> taskDTOs = event.getTasks().stream()
                .map(this::mapTaskToDTO)  // Assuming you have a method to map Task to TaskDTO
                .collect(Collectors.toList());
            dto.setTasks(taskDTOs);  // Add this field to your EventDTO class
        }
        return dto;
    }

    private Event mapToEntity(EventDTO eventDTO) {
        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setIsActive(eventDTO.getIsActive());
        event.setCreatedAt(eventDTO.getCreatedAt() != null ? eventDTO.getCreatedAt() : LocalDateTime.now());
        event.setUpdatedAt(LocalDateTime.now());
        return event;
    }
    
    private TaskDTO mapTaskToDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setPriority(task.getPriority().toString());  // Convert enum to String
        taskDTO.setStatus(task.getStatus().toString());  // Convert enum to String
        taskDTO.setExpectedCompletionTime(task.getExpectedCompletionTime());
        taskDTO.setCreatedAt(task.getCreatedAt());
        taskDTO.setUpdatedAt(task.getUpdatedAt());
        return taskDTO;
    }

    
//    ***OLD CODE***
//	public List<Event> getAllEvents() {
//	return eventRepository.findAll();
//}

//	public Event createEvent(Event event) {
//		if (event.getCreatedAt() == null) {
//			event.setCreatedAt(LocalDateTime.now()); // Set current date and time if not provided
//		}
//		return eventRepository.save(event);
//	}

//	public Event updateEvent(Long eventId, Event updatedEvent) {
//		Optional<Event> optionalEvent = eventRepository.findById(eventId);
//
//		if (optionalEvent.isPresent()) {
//			Event existingEvent = optionalEvent.get();
//
//			// Update event details
//			existingEvent.setEventName(updatedEvent.getEventName());
//			existingEvent.setCreatedAt(
//					updatedEvent.getCreatedAt() != null ? updatedEvent.getCreatedAt() : existingEvent.getCreatedAt());
//			existingEvent.setIsActive(updatedEvent.getIsActive());
//			existingEvent.setDescription(updatedEvent.getDescription());
//
//			// Do not modify subtasks if not provided
//			if (updatedEvent.getSubtasks() != null && !updatedEvent.getSubtasks().isEmpty()) {
//				existingEvent.setSubtasks(updatedEvent.getSubtasks());
//			}
//
//			// Set updatedAt to current date and time
//			existingEvent.setUpdatedAt(LocalDateTime.now());
//
//			// Save and return updated event
//			return eventRepository.save(existingEvent);
//		} else {
//			throw new RuntimeException("Event not found with id: " + eventId);
//		}
//	}

//	private <R> R mapToDTO(Event event1) {
//		return null;
//	}
}
