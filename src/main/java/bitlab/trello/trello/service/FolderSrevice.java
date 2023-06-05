package bitlab.trello.trello.service;

import bitlab.trello.trello.Models.Folders;
import bitlab.trello.trello.Models.TaskCategories;
import bitlab.trello.trello.Models.Tasks;
import bitlab.trello.trello.Repositoriy.CommentsRepository;
import bitlab.trello.trello.Repositoriy.FoldersRepository;
import bitlab.trello.trello.Repositoriy.TaskCategoriesRepository;
import bitlab.trello.trello.Repositoriy.TasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderSrevice {
    private final FoldersRepository foldersRepository;
    private final CommentsRepository commentsRepository;
    private final TasksRepository tasksRepository;
    private final TaskCategoriesRepository taskCategoriesRepository;

    public List<Folders> searchFolders(){
        List<Folders> foldersList = foldersRepository.findAll();
        return foldersList;
    }
    public Folders addFolder(Folders folder){
        return foldersRepository.save(folder);
    }
    public Folders getFolder(Long id){
        return foldersRepository.findById(id).orElse(null);
    }

    public void addCatInFold(Long folderID,Long catID){
        Folders folders1 = getFolder(folderID);
        TaskCategories taskCategories1 = taskCategoriesRepository.findById(catID).orElseThrow();

        if (folders1.getCategories()!=null && folders1.getCategories().size()>0){
            folders1.getCategories().add(taskCategories1);
        }else {
            List<TaskCategories> taskCategoriesList= new ArrayList<>();
            taskCategoriesList.add(taskCategories1);
            folders1.setCategories(taskCategoriesList);
        }
        foldersRepository.save(folders1);
    }
    public void deleteCatInFold(Long papkaID,Long katID){
        Folders folders1 = foldersRepository.findById(papkaID).orElseThrow();
        TaskCategories taskCategories1 = taskCategoriesRepository.findById(katID).orElseThrow();

        if (folders1.getCategories()!=null && folders1.getCategories().size()>0){
            folders1.getCategories().remove(taskCategories1);
        }
        foldersRepository.save(folders1);
    }

}
