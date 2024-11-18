package com.taskmanagement.Task.Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmanagement.Task.Management.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
