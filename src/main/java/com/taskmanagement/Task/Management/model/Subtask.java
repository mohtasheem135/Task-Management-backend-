package com.taskmanagement.Task.Management.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "subtasks")
public class Subtask {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "subtask_name")
	private String subtaskName;

	@Column(name = "createdAt")
	private LocalDateTime createdAt;

	@Column(name = "updatedAt")
	private LocalDateTime updatedAt;

	@Column(name = "description")
	private String description;

	@Column(name = "expectedCompletionTime")
	private LocalDateTime expectedCompletionTime;

	@Enumerated(EnumType.STRING)
	private Priority priority;

	@Enumerated(EnumType.STRING)
	private Status taskStatus;

	@ManyToOne
	@JoinColumn(name = "event_id")
	@JsonIgnore
	private Event event;

	// Enum for priority levels
	public enum Priority {
		HIGH, MEDIUM, LOW
	}

	// Enum for task status
	public enum Status {
		COMPLETED, NOT_COMPLETED, PENDING
	}

	// empty constructor
	public Subtask() {

	}

	// constructor
	public Subtask(long id, String subtaskName, LocalDateTime createdAt, LocalDateTime updatedAt, String description,
			LocalDateTime expectedCompletionTime, Priority priority, Status taskStatus, Event event) {
		super();
		this.id = id;
		this.subtaskName = subtaskName;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.description = description;
		this.expectedCompletionTime = expectedCompletionTime;
		this.priority = priority;
		this.taskStatus = taskStatus;
		this.event = event;

	}

	// Getters & Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubtaskName() {
		return subtaskName;
	}

	public void setSubtaskName(String subtaskName) {
		this.subtaskName = subtaskName;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getExpectedCompletionTime() {
		return expectedCompletionTime;
	}

	public void setExpectedCompletionTime(LocalDateTime expectedCompletionTime) {
		this.expectedCompletionTime = expectedCompletionTime;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Status getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Status taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public void updateSubtask(String newSubtaskName, String newDescription, Status newStatus, LocalDateTime newDueTime,
			Priority newPriority) {
		this.subtaskName = newSubtaskName != null ? newSubtaskName : this.subtaskName;
		this.description = newDescription != null ? newDescription : this.description;
		this.taskStatus = newStatus != null ? newStatus : this.taskStatus;
		this.expectedCompletionTime = newDueTime.isAfter(LocalDateTime.now()) ? newDueTime
				: this.expectedCompletionTime; // Ensure dueTime is still in the future
		this.priority = newPriority != null ? newPriority : this.priority;
		this.updatedAt = LocalDateTime.now(); // Automatically update the `updatedAt` field
	}

}
