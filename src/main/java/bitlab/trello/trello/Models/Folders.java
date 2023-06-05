package bitlab.trello.trello.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "folders")
@Getter
@Setter
public class Folders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "folder_name")
    private String name;
    @ManyToMany
    private List <TaskCategories> categories;
}
