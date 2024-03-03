package dev.anadinema.projects.controller;

import dev.anadinema.projects.api.FetchApi;
import dev.anadinema.projects.service.BookService;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

public class FetchController implements FetchApi {

    @Inject
    BookService service;

    @Override
    public Response getAllBooks(Boolean inStock) {
        return service.listAllBooks(inStock);
    }

    @Override
    public Response getBookFromId(Long id) {
        return service.fetchBook(id);
    }

}
