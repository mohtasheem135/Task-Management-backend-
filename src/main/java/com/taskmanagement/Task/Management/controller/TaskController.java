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

import com.taskmanagement.Task.Management.dto.TaskBatchUpdateDTO;
import com.taskmanagement.Task.Management.dto.TaskDTO;
//import com.taskmanagement.Task.Management.model.Subtask;
//import com.taskmanagement.Task.Management.service.SubtaskService;
import com.taskmanagement.Task.Management.service.TaskService;

//@CrossOrigin(origins = "http://localhost:3000/")
 @CrossOrigin(origins = "https://task-management-frontend-y3lr.vercel.app/")
@RestController
@RequestMapping("/api/v1/")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@GetMapping("/tasks")
	public ResponseEntity<List<TaskDTO>> getAllTasks() {
	    return ResponseEntity.ok(taskService.getAllTasks());
	}
	
	@GetMapping("/events/{eventId}/tasks")
	public ResponseEntity<List<TaskDTO>> getTasksByEvent(@PathVariable Long eventId) {
	    return ResponseEntity.ok(taskService.getTasksByEvent(eventId));
	}
	
	@PostMapping("/events/{eventId}/tasks")
	public ResponseEntity<TaskDTO> createTask(@PathVariable Long eventId, @RequestBody TaskDTO taskDTO) {
	    TaskDTO newTask = taskService.createTask(eventId, taskDTO);
	    return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
	}

    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.updateTask(id, taskDTO));
    }
	
    @PutMapping("/tasks/batch")
    public ResponseEntity<List<TaskDTO>> updateTasksBatch(@RequestBody TaskBatchUpdateDTO taskBatchUpdateDTO) {
        List<TaskDTO> updatedTasks = taskService.updateTasksBatch(taskBatchUpdateDTO.getTasks());
        return ResponseEntity.ok(updatedTasks); // Return the updated tasks
    }
    
//	***OLD CODE***
//	@PostMapping("/subtasks/{eventId}")
//	public Subtask createSubtask(@PathVariable Long eventId, @RequestBody Subtask subtask) {
//		return subtaskService.createSubtask(eventId, subtask);
//	}
//
//	@PutMapping("/subtasks/{subtaskId}")
//	public Subtask updateSubtask(@PathVariable Long subtaskId, @RequestBody Subtask updatedSubtask) {
//		return subtaskService.updateSubtask(subtaskId, updatedSubtask);
//	}

}
