package uz.pdp.SimpleLoginProject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "book")
@NoArgsConstructor
public class Book extends BaseEntity {

    private String title;

    private String author;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "count_of_pages")
    private Integer countOfPages;

    @Builder
    public Book(String title, String author, String description, Integer countOfPages) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.countOfPages = countOfPages;
    }
}
