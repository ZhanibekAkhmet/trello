package bitlab.trello.trello.Repositoriy;

import bitlab.trello.trello.Models.Folders;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface FoldersRepository extends JpaRepository<Folders,Long> {
}
