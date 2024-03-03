package dev.anadinema.projects.mapper;

import dev.anadinema.projects.dto.Book;
import dev.anadinema.projects.persistence.BookEntity;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public class BookMapper {

    public static BookEntity transform(Book bookDto) {
        BookEntity entity = new BookEntity();
        entity.setTitle(bookDto.getTitle());
        entity.setAuthor(bookDto.getAuthor());
        entity.setGenre(bookDto.getGenre().value());
        entity.setPageCount(bookDto.getPageCount());
        entity.setIsbn(Optional.ofNullable(bookDto.getIsbn()).orElse(generateIsbn()));
        entity.setPublicationDate(Optional.ofNullable(bookDto.getPublicationDate()).orElse(LocalDate.now()));
        entity.setInStock(bookDto.getStocked());
        return entity;
    }

    public static Book transform(BookEntity entity) {
        Book book = new Book();
        book.setId(entity.id);
        book.setTitle(entity.getTitle());
        book.setAuthor(entity.getAuthor());
        book.setGenre(Book.GenreEnum.fromString(entity.getGenre()));
        book.setPageCount(entity.getPageCount());
        book.setIsbn(entity.getIsbn());
        book.setPublicationDate(entity.getPublicationDate());
        book.setStocked(entity.isInStock());
        return book;
    }

    private static String generateIsbn() {
        return UUID.randomUUID().toString();
    }
}
