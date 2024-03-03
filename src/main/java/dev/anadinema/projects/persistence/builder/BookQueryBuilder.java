package dev.anadinema.projects.persistence.builder;

import dev.anadinema.projects.dto.Book;

import java.util.Optional;

public class BookQueryBuilder {

    private static final String END_QUOTE = "'";

    public static String build(Book book) {
        StringBuilder builder = new StringBuilder().append("title = '").append(book.getTitle()).append(END_QUOTE);

        if (Optional.ofNullable(book.getAuthor()).isPresent()) {
            builder.append(", author = '").append(book.getAuthor()).append(END_QUOTE);
        }
        if (Optional.ofNullable(book.getGenre()).isPresent()) {
            builder.append(", genre = '").append(book.getGenre().value()).append(END_QUOTE);
        }
        if (Optional.ofNullable(book.getIsbn()).isPresent()) {
            builder.append(", isbn = '").append(book.getIsbn()).append(END_QUOTE);
        }
        if (Optional.ofNullable(book.getPublicationDate()).isPresent()) {
            builder.append(", publication_date = '").append(book.getPublicationDate()).append(END_QUOTE);
        }
        if (Optional.ofNullable(book.getPageCount()).isPresent()) {
            builder.append(", author = '").append(book.getPageCount()).append(END_QUOTE);
        }
        if (Optional.ofNullable(book.getStocked()).isPresent()) {
            builder.append(", author = '").append(book.getStocked()).append(END_QUOTE);
        }
        return builder.append("where id = '").append(book.getId()).append(END_QUOTE).toString();
    }
}
