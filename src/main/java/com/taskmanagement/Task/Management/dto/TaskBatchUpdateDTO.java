package com.taskmanagement.Task.Management.dto;

import java.util.List;

public class TaskBatchUpdateDTO {
	private List<TaskDTO> tasks;

    // Getters and Setters
    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }
}
