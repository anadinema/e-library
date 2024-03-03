package dev.anadinema.projects.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "book_master", indexes = {
        @Index(name = "idx_book_master_id_isbn", columnList = "id, isbn")
})
@SequenceGenerator(name = "book_master_seq", sequenceName = "book_master_seq",
        initialValue = 100, allocationSize = 1)
public class BookEntity extends PanacheEntity {

    @Column(name = "title", nullable = false, length = 700)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "publication_date", columnDefinition = "date default now()")
    private LocalDate publicationDate;

    @Column(name = "page_count", nullable = false)
    private Integer pageCount;

    @Column(name = "in_stock", nullable = false)
    private boolean inStock;

}