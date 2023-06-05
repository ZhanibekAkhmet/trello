package bitlab.trello.trello.Controller;

import bitlab.trello.trello.Models.Comments;
import bitlab.trello.trello.Models.Folders;
import bitlab.trello.trello.Models.TaskCategories;
import bitlab.trello.trello.Models.Tasks;
import bitlab.trello.trello.Repositoriy.CommentsRepository;
import bitlab.trello.trello.Repositoriy.FoldersRepository;
import bitlab.trello.trello.Repositoriy.TaskCategoriesRepository;
import bitlab.trello.trello.Repositoriy.TasksRepository;
import bitlab.trello.trello.service.CategoriesService;
import bitlab.trello.trello.service.CommentService;
import bitlab.trello.trello.service.FolderSrevice;
import bitlab.trello.trello.service.TaskService;
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
   private final FolderSrevice folderSrevice;
   private final TaskService taskService;
   private final CategoriesService categoriesService;
   private final CommentService commentService;

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

         model.addAttribute("zadane", taskService.searchTasks(id));

         Folders folder = folderSrevice.getFolder(id);
         model.addAttribute("folderDetails", folder);

         List<TaskCategories> taskCategoriesList = folder.getCategories();

         model.addAttribute("kategor",taskCategoriesList);

         model.addAttribute("categorii",categoriesService.searchCategories());

         return "details";
    }
    @PostMapping(value = "/addTask")
    public String addTasks(Tasks tasks){
        taskService.addTasks(tasks);
        return "redirect:/details/"+tasks.getFolder().getId();
    }
    @GetMapping(value = "/taskMore/{taskId}")
    public String taskMores(@PathVariable (name="taskId")Long id,
                            Model model){
        model.addAttribute("task",taskService.getTask(id));

        model.addAttribute("komenty", commentService.searchComments(id));
        return "taskMor";
    }
    @PostMapping(value = "/addCategories")
    public String addCat(@RequestParam(value = "folderID") Long folderID,
                         @RequestParam(value = "categoriesID") Long catID){
        folderSrevice.addCatInFold(folderID,catID);
        return "redirect:/details/"+folderID;
    }
    @PostMapping(value = "/deleteCategories")
    public String deleteOper(@RequestParam(name = "papkaID") Long papkaID,
                             @RequestParam(name = "katID") Long katID){
       folderSrevice.deleteCatInFold(papkaID,katID);
        return "redirect:/details/"+papkaID;
    }
    @PostMapping(value = "/addComment")
    public String addCom(Comments comments){
        commentService.addComment(comments);
        return "redirect:/taskMore/"+comments.getTask().getId();
    }
    @PostMapping(value = "/saveTask")
    public String saveZad(Tasks tasks){
        taskService.addTasks(tasks);
        return "redirect:/taskMore/"+tasks.getId();
    }
    @GetMapping(value = "/categories")
    public String categories(Model model){
        model.addAttribute("categories", categoriesService.searchCategories());
        return "categories";
    }
    @PostMapping(value = "/addcategory")
    public String addCategories(TaskCategories taskCategories){
        categoriesService.addCategories(taskCategories);
        return "redirect:/categories";
    }
}
