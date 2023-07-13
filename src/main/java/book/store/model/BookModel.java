package book.store.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "t_book")
@Getter
@Setter
public class  BookModel extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "book_name")
    private String name;

    @Column(name = "publicationYear")
    private int publicationYear;

    @Column(name = "price")
    private int price;

    @Column(name = "img_book")
    private String img;

    @Column(name ="description")
    private String description;

    @ManyToMany
    private List<GenreModel> genres;

    @ManyToOne
    private AuthorModel authorModel;
}
