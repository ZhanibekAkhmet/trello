package bitlab.trello.trello.Repositoriy;

import bitlab.trello.trello.Models.Comments;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface CommentsRepository extends JpaRepository<Comments,Long> {
    List<Comments> findAllByTask_Id(Long id);
}
