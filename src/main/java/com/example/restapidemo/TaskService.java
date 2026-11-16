package com.example.restapidemo;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() ->
            new TaskNotFoundException(id));
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = taskRepository.findById(id).orElseThrow(() ->
            new TaskNotFoundException(id));
        existingTask.setTitle(updatedTask.getTitle());
        return taskRepository.save(existingTask);
    }

    public void deleteTask(Task existingTask) {
        taskRepository.delete(existingTask);
    }

    public Task createTask(Task task) {
        task.setId(null);
        return taskRepository.save(task);
    }
}
