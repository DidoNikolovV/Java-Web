package com.plannerapp.service.impl;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.Task;
import com.plannerapp.model.entity.User;
import com.plannerapp.model.entity.dto.task.TaskAddBindingModel;
import com.plannerapp.model.entity.dto.task.TaskDTO;
import com.plannerapp.model.entity.dto.task.TaskHomeViewModel;
import com.plannerapp.repo.PriorityRepository;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final PriorityRepository priorityRepository;
    private final UserRepository userRepository;



    public TaskService(TaskRepository taskRepository, PriorityRepository priorityRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.priorityRepository = priorityRepository;
        this.userRepository = userRepository;
    }

    public void add(TaskAddBindingModel taskAddBindingModel) {

        Priority priority = priorityRepository.findByName(taskAddBindingModel.getPriority());

        if(priority != null) {
            Task task = new Task();
            task.setDescription(taskAddBindingModel.getDescription());
            task.setDueDate(LocalDate.parse(taskAddBindingModel.getDueDate()));
            task.setPriority(priority);

            taskRepository.save(task);
        }

    }

    public void remove(Long id) {
        this.taskRepository.deleteById(id);
    }

    public void assign(Long id, String username) {
        Optional<Task> optionalTask = this.taskRepository.findById(id);

        if(optionalTask.isPresent()) {
            Task task = optionalTask.get();

            if(username == null) {
                task.setAssignee(null);
            } else {
                User user = userRepository.findByUsername(username);
                task.setAssignee(user);
            }

            taskRepository.save(task);
        }
    }

    public TaskHomeViewModel getHomeViewDate(String username) {
        User user = userRepository.findByUsername(username);

        List<TaskDTO> assignedTasks = taskRepository.findByAssignee(user).stream().map(TaskDTO::createFromTask).toList();
        List<TaskDTO> availableTasks = taskRepository.getAllAvailable().stream().map(TaskDTO::createFromTask).toList();

        return new TaskHomeViewModel(assignedTasks, availableTasks);
    }
}
