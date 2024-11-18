package com.taskmanagement.Task.Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmanagement.Task.Management.model.Subtask;

@Repository
public interface SubtaskRepository extends JpaRepository<Subtask, Long> {

}
