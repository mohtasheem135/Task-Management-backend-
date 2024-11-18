package com.taskmanagement.Task.Management.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagement.Task.Management.exception.ResourceNotFoundException;
import com.taskmanagement.Task.Management.model.Event;
import com.taskmanagement.Task.Management.model.Subtask;
import com.taskmanagement.Task.Management.repository.EventRepository;
import com.taskmanagement.Task.Management.repository.SubtaskRepository;

@Service
public class SubtaskService {

	@Autowired
	private SubtaskRepository subtaskRepository;

	@Autowired
	private EventRepository eventRepository;

	public Subtask createSubtask(Long eventId, Subtask subtask) {
		Event event = eventRepository.findById(eventId)
				.orElseThrow(() -> new ResourceNotFoundException("Event not exist with id: " + eventId));
		subtask.setEvent(event);
		subtask.setCreatedAt(LocalDateTime.now());
		subtask.setUpdatedAt(LocalDateTime.now());
		return subtaskRepository.save(subtask);
	}

	public Subtask updateSubtask(Long subtaskId, Subtask updatedSubtask) {
		Optional<Subtask> optionalSubtask = subtaskRepository.findById(subtaskId);

		if (optionalSubtask.isPresent()) {
			Subtask existingSubtask = optionalSubtask.get();

			// Update fields only if provided
			if (updatedSubtask.getSubtaskName() != null) {
				existingSubtask.setSubtaskName(updatedSubtask.getSubtaskName());
			}

			if (updatedSubtask.getDescription() != null) {
				existingSubtask.setDescription(updatedSubtask.getDescription());
			}

			if (updatedSubtask.getTaskStatus() != null) {
				existingSubtask.setTaskStatus(updatedSubtask.getTaskStatus());
			}

			if (updatedSubtask.getPriority() != null) {
				existingSubtask.setPriority(updatedSubtask.getPriority());
			}

			// Ensure the due time is in the future only if it's provided
			if (updatedSubtask.getExpectedCompletionTime() != null) {
				LocalDateTime newExpectedCompletionTime = updatedSubtask.getExpectedCompletionTime();
				if (newExpectedCompletionTime.isAfter(LocalDateTime.now())) {
					existingSubtask.setExpectedCompletionTime(newExpectedCompletionTime);
				} else {
					throw new IllegalArgumentException("Due time must be in the future");
				}
			}

			// Update the updatedAt field to the current time
			existingSubtask.setUpdatedAt(LocalDateTime.now());

			// Save the updated subtask
			return subtaskRepository.save(existingSubtask);
		} else {
			throw new RuntimeException("Subtask not found with id: " + subtaskId);
		}
	}

}
