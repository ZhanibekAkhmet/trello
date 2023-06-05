package bitlab.trello.trello.service;

import bitlab.trello.trello.Models.TaskCategories;
import bitlab.trello.trello.Models.Tasks;
import bitlab.trello.trello.Repositoriy.TaskCategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesService {
    private final TaskCategoriesRepository taskCategoriesRepository;
    public List<TaskCategories> searchCategories(){
        List<TaskCategories> taskCategoriesList1 = taskCategoriesRepository.findAll();
        return taskCategoriesList1;
    }
    public TaskCategories addCategories(TaskCategories taskCategories){
        return taskCategoriesRepository.save(taskCategories);
    }
}
