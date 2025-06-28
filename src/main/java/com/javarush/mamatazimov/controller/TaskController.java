package com.javarush.mamatazimov.controller;

import com.javarush.mamatazimov.dto.TaskDTO;
import com.javarush.mamatazimov.entity.Task;
import com.javarush.mamatazimov.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static java.util.Objects.isNull;

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

    @PostMapping("/{id}")
    public void editTask(Model model, @PathVariable("id") Integer id, @RequestBody TaskDTO taskDTO) {
        if (isNull(id) || id <= 0) {
            throw new RuntimeException("Invalid id: " + id);
        }
        taskService.editTask(id, taskDTO);
    }

    @PostMapping("/")
    public void addTask(@RequestBody TaskDTO taskDTO) {
        taskService.addTask(taskDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteTask(Model model, @PathVariable("id") int id) {
        taskService.deleteTask(id);
    }

}
