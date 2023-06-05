package bitlab.trello.trello.Repositoriy;

import bitlab.trello.trello.Models.TaskCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TaskCategoriesRepository extends JpaRepository<TaskCategories,Long> {
}
