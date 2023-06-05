package bitlab.trello.trello.service;

import bitlab.trello.trello.Models.Tasks;
import bitlab.trello.trello.Repositoriy.TasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TasksRepository tasksRepository;

    public List<Tasks> searchTasks(Long id){
        List<Tasks> tasksList= tasksRepository.findAllByFolder_Id(id);
        return tasksList;
    }
    public Tasks addTasks(Tasks tasks){
        return tasksRepository.save(tasks);
    }
    public Tasks getTask(Long id){
        return tasksRepository.findById(id).orElse(null);
    }
}
