package dev.anadinema.projects.controller;

import dev.anadinema.projects.api.AdminApi;
import dev.anadinema.projects.dto.Book;
import dev.anadinema.projects.service.BookService;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

public class AdminController implements AdminApi {

    private final BookService service;

    @Inject
    public AdminController(BookService service) {
        this.service = service;
    }

    @Override
    public Response createNewBook(Book book) {
        return service.addBook(book);
    }

    @Override
    public Response removeBook(Long id) {
        return service.removeBook(id);
    }

    @Override
    public Response updateBookDetails(Book book) {
        return service.updateBook(book);
    }
}
