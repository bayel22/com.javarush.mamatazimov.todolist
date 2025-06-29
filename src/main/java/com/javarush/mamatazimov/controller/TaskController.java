package com.javarush.mamatazimov.controller;

import com.javarush.mamatazimov.dto.TaskDTO;
import com.javarush.mamatazimov.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        model.addAttribute("current_page", page);
        int totalPages = (int) Math.ceil(1.0 * taskService.getTaskCount() / pageSize);
        if (totalPages > 1) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("page_numbers", pageNumbers);
        }
        return "index";
    }

    @PostMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> editTask(@PathVariable("id") Integer id, @RequestBody TaskDTO taskDTO) {
        if (isNull(id) || id <= 0) {
            return ResponseEntity.badRequest().body("Invalid id: " + id);
        }
        taskService.editTask(id, taskDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> addTask(@RequestBody TaskDTO taskDTO) {
        taskService.addTask(taskDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteTask(@PathVariable("id") int id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}
