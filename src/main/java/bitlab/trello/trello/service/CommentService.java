package bitlab.trello.trello.service;

import bitlab.trello.trello.Models.Comments;
import bitlab.trello.trello.Models.Tasks;
import bitlab.trello.trello.Repositoriy.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentsRepository commentsRepository;
    public List<Comments> searchComments(Long id){
        List<Comments> comments = commentsRepository.findAllByTask_Id(id);
        return comments;
    }
    public Comments addComment(Comments comments){
       return commentsRepository.save(comments);
    }
}
