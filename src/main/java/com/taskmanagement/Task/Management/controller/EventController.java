package com.taskmanagement.Task.Management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagement.Task.Management.dto.EventDTO;
import com.taskmanagement.Task.Management.model.Event;
import com.taskmanagement.Task.Management.service.EventService;

 @CrossOrigin(origins = "https://task-management-frontend-y3lr.vercel.app/")
//@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@GetMapping("/events")
	public ResponseEntity<List<EventDTO>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }
	
	@PostMapping("/events")
	public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO) {
        return ResponseEntity.ok(eventService.createEvent(eventDTO));
    }
	
	@PutMapping("/events/{eventId}")
//    public ResponseEntity<Event> updateEvent(@PathVariable Long eventId, @RequestBody Event event) {
//        Event updatedEvent = eventService.updateEvent(eventId, event);
//        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
//    }
	public ResponseEntity<EventDTO> updateEvent(@PathVariable Long eventId, @RequestBody EventDTO eventDTO) {
        return ResponseEntity.ok(eventService.updateEvent(eventId, eventDTO));
    }
	
}
