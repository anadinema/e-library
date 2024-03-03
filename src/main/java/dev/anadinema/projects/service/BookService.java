package dev.anadinema.projects.service;

import dev.anadinema.projects.dto.Book;
import jakarta.ws.rs.core.Response;

public interface BookService {

    Response listAllBooks(Boolean onlyInStock);
    Response fetchBook(Long id);
    Response addBook(Book book);
    Response removeBook(Long id);
    Response updateBook(Book book);

}
