package com.taskmanagement.Task.Management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmanagement.Task.Management.model.Event;
import com.taskmanagement.Task.Management.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	List<Task> findByEvent(Event event);
}
