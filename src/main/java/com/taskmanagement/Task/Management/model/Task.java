package com.taskmanagement.Task.Management.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "description")
	private String description;

	@Column(name = "expected_completion_time")
	private LocalDateTime expectedCompletionTime;

	@Enumerated(EnumType.STRING)
	@Column(name = "priority", nullable = false)
	private Priority priority;

	@Enumerated(EnumType.STRING)
	@Column(name = "task_status", nullable = false)
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id", nullable = false)
//	@JsonIgnore
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
	public Task() {

	}

	// constructor
	public Task(long id, String name, LocalDateTime createdAt, LocalDateTime updatedAt, String description,
			LocalDateTime expectedCompletionTime, Priority priority, Status status, Event event) {
		super();
		this.id = id;
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.description = description;
		this.expectedCompletionTime = expectedCompletionTime;
		this.priority = priority;
		this.status = status;
		this.event = event;
	}

	// Getters & Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

//	public void updateSubtask(String newName, String newDescription, Status newStatus, LocalDateTime newDueTime,
//			Priority newPriority) {
//		this.name = newName != null ? newName : this.name;
//		this.description = newDescription != null ? newDescription : this.description;
//		this.taskStatus = newStatus != null ? newStatus : this.taskStatus;
//		this.expectedCompletionTime = newDueTime.isAfter(LocalDateTime.now()) ? newDueTime
//				: this.expectedCompletionTime; // Ensure dueTime is still in the future
//		this.priority = newPriority != null ? newPriority : this.priority;
//		this.updatedAt = LocalDateTime.now(); // Automatically update the `updatedAt` field
//	}

}
