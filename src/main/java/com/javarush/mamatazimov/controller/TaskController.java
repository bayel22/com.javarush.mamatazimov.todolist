package com.javarush.mamatazimov.controller;

import com.javarush.mamatazimov.dto.TaskDTO;
import com.javarush.mamatazimov.entity.Task;
import com.javarush.mamatazimov.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String getTasks(Model model,
                               @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "15") int pageSize) {
        model.addAttribute("tasks", taskService.getTasksPaged(page, pageSize));
        return "index";
    }

    @PutMapping("/{id}")
    public void editTask(@PathVariable int id, @RequestBody  TaskDTO taskDTO) {
        Task task = taskService.getTask(id);
        if (task != null) {
            task.setDescription(taskDTO.getDescription());
            task.setStatus(taskDTO.getStatus());
            taskService.editTask(task);
        }
    }

    @PostMapping("/")
    public void addTask(@RequestBody TaskDTO taskDTO) {
        Task task = new Task();
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        taskService.addTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable int id) {
        if (taskService.getTask(id) != null) {
            taskService.deleteTask(id);
        }
    }

}
