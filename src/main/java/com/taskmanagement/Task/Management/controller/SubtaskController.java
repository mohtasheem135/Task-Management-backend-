package com.taskmanagement.Task.Management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagement.Task.Management.model.Subtask;
import com.taskmanagement.Task.Management.service.SubtaskService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/")
public class SubtaskController {

	@Autowired
	private SubtaskService subtaskService;

	@PostMapping("/subtasks/{eventId}")
	public Subtask createSubtask(@PathVariable Long eventId, @RequestBody Subtask subtask) {
		return subtaskService.createSubtask(eventId, subtask);
	}

	@PutMapping("/subtasks/{subtaskId}")
	public Subtask updateSubtask(@PathVariable Long subtaskId, @RequestBody Subtask updatedSubtask) {
		return subtaskService.updateSubtask(subtaskId, updatedSubtask);
	}

}
