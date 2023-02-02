package com.example.planland.contracts;

import androidx.lifecycle.LiveData;

import com.example.planland.entity.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskService {
    LiveData<ArrayList<Task>> GetAllTasks();
    Task GetTaskById(String ID);
    void AddTask(Task task);
    void DeleteTask(String id);
    void UpdateTask(Task task);
}
