package dev.danvega.tasks;


import org.slf4j.Logger;
import org.springframework.ui.Model;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {
    private final Logger log = LoggerFactory.getLogger(TaskController.class);
    private final TaskRepository repository;

    public TaskController(TaskRepository taskRepository) {
        this.repository = taskRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("tasks",repository.findAll());
        return "index";
    }

    @PostMapping("/add-task")
    public String addTask(@RequestParam String description, Model model){
        Task newTask = new Task(description);
        repository.create(newTask);
        model.addAttribute("task",newTask);
        return "task-row";
    }

    @DeleteMapping("/delete-task/{id}")
    public void deleteTask(@PathVariable String id){
        boolean removed = repository.remove(id);
        log.info("Task with id: {} was deleted",id);
    }

}
