package com.taskmanagement.Task.Management.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "events")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "event_name")
	private String eventName;

	@Column(name = "createdAt")
	private LocalDateTime createdAt;

	@Column(name = "isActive")
	private Boolean isActive;
	
	@Column(name = "description")
	private String description;

	@Column(name = "updatedAt")
	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Subtask> subtasks;
	
	//empty constructor
	public Event() {
		
	}

	//constructor
	public Event(String eventName, LocalDateTime createdAt,LocalDateTime updatedAt, Boolean isActive, String description, List<Subtask> subtasks) {
		super();
		this.eventName = eventName;
		this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
		this.isActive = isActive;
		this.description = description;
		this.subtasks = subtasks;
		this.updatedAt = LocalDateTime.now();
	}

	//Getters & Setters
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getEventName() {
		return eventName;
	}
	

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
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
	
	public Boolean getIsActive() {
		return isActive;
	}
		
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public List<Subtask> getSubtasks() {
		return subtasks;
	}
	
	public void setSubtasks(List<Subtask> subtasks) {
		this.subtasks = subtasks;
	}
	
}
