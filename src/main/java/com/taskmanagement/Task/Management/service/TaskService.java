package com.taskmanagement.Task.Management.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagement.Task.Management.dto.TaskDTO;
import com.taskmanagement.Task.Management.exception.ResourceNotFoundException;
import com.taskmanagement.Task.Management.model.Event;
import com.taskmanagement.Task.Management.model.Task;
import com.taskmanagement.Task.Management.repository.EventRepository;
import com.taskmanagement.Task.Management.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private EventRepository eventRepository;

	
	public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
	
	public List<TaskDTO> getTasksByEvent(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException("Event not found"));
        List<Task> tasks = taskRepository.findByEvent(event);
        return tasks.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
	
//	public TaskDTO createTask(TaskDTO taskDTO) {
//        Task task = mapToEntity(taskDTO);
//        Task newTask = taskRepository.save(task);
//        return mapToDTO(newTask);
//    }
	
//	public Subtask createSubtask(Long eventId, Subtask subtask) {
//		Event event = eventRepository.findById(eventId)
//				.orElseThrow(() -> new ResourceNotFoundException("Event not exist with id: " + eventId));
//		subtask.setEvent(event);
//		subtask.setCreatedAt(LocalDateTime.now());
//		subtask.setUpdatedAt(LocalDateTime.now());
//		return subtaskRepository.save(subtask);
//	}
	public TaskDTO createTask(Long eventId, TaskDTO taskDTO) {
	    Event event = eventRepository.findById(eventId)
	        .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + eventId));
	    
	    Task task = mapToEntity(taskDTO);
	    task.setEvent(event);
	    Task newTask = taskRepository.save(task);
	    return mapToDTO(newTask);
	}

	
	public TaskDTO updateTask(Long taskId, TaskDTO taskDTO) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setPriority(Task.Priority.valueOf(taskDTO.getPriority()));
        task.setStatus(Task.Status.valueOf(taskDTO.getStatus()));
        task.setExpectedCompletionTime(taskDTO.getExpectedCompletionTime());
        task.setUpdatedAt(LocalDateTime.now());

        taskRepository.save(task);
        return mapToDTO(task);
    }
	
//	public void updateTasksBatch(List<TaskDTO> taskDTOs) {
//        List<Task> tasksToUpdate = new ArrayList<>();
//
//        for (TaskDTO taskDTO : taskDTOs) {
//            Task task = taskRepository.findById(taskDTO.getId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskDTO.getId()));
//
//            task.setName(taskDTO.getName());
//            task.setDescription(taskDTO.getDescription());
//            task.setExpectedCompletionTime(taskDTO.getExpectedCompletionTime());
//            task.setPriority(Task.Priority.valueOf(taskDTO.getPriority()));
//            task.setStatus(Task.Status.valueOf(taskDTO.getStatus()));
//            task.setUpdatedAt(LocalDateTime.now()); // Automatically update `updatedAt`
//
//            tasksToUpdate.add(task);
//        }
//
//        taskRepository.saveAll(tasksToUpdate);
//    }
	public List<TaskDTO> updateTasksBatch(List<TaskDTO> taskDTOs) {
	    List<Task> tasksToUpdate = new ArrayList<>();

	    for (TaskDTO taskDTO : taskDTOs) {
	        Task task = taskRepository.findById(taskDTO.getId())
	                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskDTO.getId()));

	        task.setName(taskDTO.getName());
	        task.setDescription(taskDTO.getDescription());
	        task.setExpectedCompletionTime(taskDTO.getExpectedCompletionTime());
	        task.setPriority(Task.Priority.valueOf(taskDTO.getPriority()));
	        task.setStatus(Task.Status.valueOf(taskDTO.getStatus()));
	        task.setUpdatedAt(LocalDateTime.now());

	        tasksToUpdate.add(task);
	    }

	    List<Task> updatedTasks = taskRepository.saveAll(tasksToUpdate);
	    return updatedTasks.stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	
	private TaskDTO mapToDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
        dto.setPriority(task.getPriority().toString());
        dto.setStatus(task.getStatus().toString());
        dto.setCreatedAt(task.getCreatedAt());
        dto.setUpdatedAt(task.getUpdatedAt());
        dto.setExpectedCompletionTime(task.getExpectedCompletionTime());
        return dto;
    }

    private Task mapToEntity(TaskDTO taskDTO) {
        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setPriority(Task.Priority.valueOf(taskDTO.getPriority()));
        task.setStatus(Task.Status.valueOf(taskDTO.getStatus()));
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        task.setExpectedCompletionTime(taskDTO.getExpectedCompletionTime());
        return task;
    }
	
//	***OLD CODE***
//	public Subtask createSubtask(Long eventId, Subtask subtask) {
//		Event event = eventRepository.findById(eventId)
//				.orElseThrow(() -> new ResourceNotFoundException("Event not exist with id: " + eventId));
//		subtask.setEvent(event);
//		subtask.setCreatedAt(LocalDateTime.now());
//		subtask.setUpdatedAt(LocalDateTime.now());
//		return subtaskRepository.save(subtask);
//	}
//
//	public Subtask updateSubtask(Long subtaskId, Subtask updatedSubtask) {
//		Optional<Subtask> optionalSubtask = subtaskRepository.findById(subtaskId);
//
//		if (optionalSubtask.isPresent()) {
//			Subtask existingSubtask = optionalSubtask.get();
//
//			// Update fields only if provided
//			if (updatedSubtask.getSubtaskName() != null) {
//				existingSubtask.setSubtaskName(updatedSubtask.getSubtaskName());
//			}
//
//			if (updatedSubtask.getDescription() != null) {
//				existingSubtask.setDescription(updatedSubtask.getDescription());
//			}
//
//			if (updatedSubtask.getTaskStatus() != null) {
//				existingSubtask.setTaskStatus(updatedSubtask.getTaskStatus());
//			}
//
//			if (updatedSubtask.getPriority() != null) {
//				existingSubtask.setPriority(updatedSubtask.getPriority());
//			}
//
//			// Ensure the due time is in the future only if it's provided
//			if (updatedSubtask.getExpectedCompletionTime() != null) {
//				LocalDateTime newExpectedCompletionTime = updatedSubtask.getExpectedCompletionTime();
//				if (newExpectedCompletionTime.isAfter(LocalDateTime.now())) {
//					existingSubtask.setExpectedCompletionTime(newExpectedCompletionTime);
//				} else {
//					throw new IllegalArgumentException("Due time must be in the future");
//				}
//			}
//
//			// Update the updatedAt field to the current time
//			existingSubtask.setUpdatedAt(LocalDateTime.now());
//
//			// Save the updated subtask
//			return subtaskRepository.save(existingSubtask);
//		} else {
//			throw new RuntimeException("Subtask not found with id: " + subtaskId);
//		}
//	}

}
