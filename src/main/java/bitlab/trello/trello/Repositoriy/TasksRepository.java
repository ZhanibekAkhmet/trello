package bitlab.trello.trello.Repositoriy;

import bitlab.trello.trello.Models.Tasks;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface TasksRepository extends JpaRepository<Tasks,Long> {
    List<Tasks>findAllByFolder_Id(Long id);

    Tasks findByFolder_Id(Long id);
}
