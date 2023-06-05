package bitlab.trello.trello.service;

import bitlab.trello.trello.Models.Folders;
import bitlab.trello.trello.Models.TaskCategories;
import bitlab.trello.trello.Repositoriy.CommentsRepository;
import bitlab.trello.trello.Repositoriy.FoldersRepository;
import bitlab.trello.trello.Repositoriy.TaskCategoriesRepository;
import bitlab.trello.trello.Repositoriy.TasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public List<TaskCategories>  getFolder(Long id){
        Folders folder =   foldersRepository.findById(id).orElse(null);
        List<TaskCategories> taskCategoriesList = folder.getCategories();
        return taskCategoriesList;
    }
}
