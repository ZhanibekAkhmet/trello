package bitlab.trello.trello.Controller;

import bitlab.trello.trello.Models.Comments;
import bitlab.trello.trello.Models.Folders;
import bitlab.trello.trello.Models.TaskCategories;
import bitlab.trello.trello.Models.Tasks;
import bitlab.trello.trello.Repositoriy.CommentsRepository;
import bitlab.trello.trello.Repositoriy.FoldersRepository;
import bitlab.trello.trello.Repositoriy.TaskCategoriesRepository;
import bitlab.trello.trello.Repositoriy.TasksRepository;
import bitlab.trello.trello.service.FolderSrevice;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
   @Autowired
   private FoldersRepository foldersRepository;

   @Autowired
   private TasksRepository tasksRepository;

   @Autowired
   private TaskCategoriesRepository taskCategoriesRepository;

   @Autowired
   private CommentsRepository commentsRepository;

   private final FolderSrevice folderSrevice;

    @GetMapping(value = "/")
    public String indexPage(Model model){
    model.addAttribute("papki",folderSrevice.searchFolders());
    return "index";}
    @PostMapping(value = "/addFolder")
    public String addFolder(Folders folder){
    folderSrevice.addFolder(folder);
    return "redirect:/";
    }
    @GetMapping(value = "/details/{folderId}")
    public String detailsPage(@PathVariable (name="folderId")Long id,Model model){
         List<Tasks> tasksList= tasksRepository.findAllByFolder_Id(id);
         model.addAttribute("zadane", tasksList);

         Folders folder = foldersRepository.findById(id).orElse(null);
         model.addAttribute("folderDetails", folder);

         List<TaskCategories> taskCategoriesList = folder.getCategories();

         model.addAttribute("kategor",taskCategoriesList);

         List<TaskCategories> taskCategoriesList1 = taskCategoriesRepository.findAll();
         model.addAttribute("categorii",taskCategoriesList1);

         return "details";
    }
    @PostMapping(value = "/addTask")
    public String addTasks(Tasks tasks){
        tasksRepository.save(tasks);
        return "redirect:/details/"+tasks.getFolder().getId();
    }
    @GetMapping(value = "/taskMore/{taskId}")
    public String taskMores(@PathVariable (name="taskId")Long id,
                            Model model){
        Tasks task=tasksRepository.findById(id).orElse(null);
        model.addAttribute("task",task);
        List<Comments> comments = commentsRepository.findAllByTask_Id(id);
        model.addAttribute("komenty", comments);
        return "taskMor";
    }
    @PostMapping(value = "/addCategories")
    public String addCat(@RequestParam(value = "folderID") Long folderID,
                         @RequestParam(value = "categoriesID") Long catID){
        Folders folders1 = foldersRepository.findById(folderID).orElseThrow();
        TaskCategories taskCategories1 = taskCategoriesRepository.findById(catID).orElseThrow();

        if (folders1.getCategories()!=null && folders1.getCategories().size()>0){
            folders1.getCategories().add(taskCategories1);
        }else {
            List<TaskCategories> taskCategoriesList= new ArrayList<>();
            taskCategoriesList.add(taskCategories1);
            folders1.setCategories(taskCategoriesList);
        }
        foldersRepository.save(folders1);
        return "redirect:/details/"+folderID;
    }
    @PostMapping(value = "/deleteCategories")
    public String deleteOper(@RequestParam(name = "papkaID") Long papkaID,
                             @RequestParam(name = "katID") Long katID){
        System.out.println(papkaID);
        System.out.println(katID);

        Folders folders1 = foldersRepository.findById(papkaID).orElseThrow();
        TaskCategories taskCategories1 = taskCategoriesRepository.findById(katID).orElseThrow();

        if (folders1.getCategories()!=null && folders1.getCategories().size()>0){
            folders1.getCategories().remove(taskCategories1);
        }
        foldersRepository.save(folders1);
        return "redirect:/details/"+papkaID;
    }
    @PostMapping(value = "/addComment")
    public String addCom(Comments comments){
        commentsRepository.save(comments);
        return "redirect:/taskMore/"+comments.getTask().getId();
    }
    @PostMapping(value = "/saveTask")
    public String saveZad(Tasks tasks){
        tasksRepository.save(tasks);
        return "redirect:/taskMore/"+tasks.getId();
    }
    @GetMapping(value = "/categories")
    public String categories(Model model){
        List<TaskCategories> taskCategoriesList = taskCategoriesRepository.findAll();
        model.addAttribute("categories", taskCategoriesList);
        return "categories";
    }
    @PostMapping(value = "/addcategory")
    public String addCategories(TaskCategories taskCategories){
        taskCategoriesRepository.save(taskCategories);
        return "redirect:/categories";
    }
}
