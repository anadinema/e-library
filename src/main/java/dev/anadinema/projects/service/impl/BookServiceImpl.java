package dev.anadinema.projects.service.impl;

import dev.anadinema.projects.dto.AdminResponse;
import dev.anadinema.projects.dto.Book;
import dev.anadinema.projects.dto.FaultObject;
import dev.anadinema.projects.mapper.BookMapper;
import dev.anadinema.projects.persistence.BookEntity;
import dev.anadinema.projects.persistence.builder.BookQueryBuilder;
import dev.anadinema.projects.service.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookServiceImpl implements BookService {

    @Override
    public Response listAllBooks(Boolean onlyInStock) {
        List<BookEntity> bookEntityList = BookEntity.listAll();
        if (bookEntityList.isEmpty()) {
            return Response.noContent().build();
        }
        if (onlyInStock) {
            return Response.ok(bookEntityList.stream().filter(BookEntity::isInStock).map(BookMapper::transform).toList()).build();
        }
        return Response.ok(bookEntityList.stream().map(BookMapper::transform).toList()).build();
    }

    @Override
    public Response fetchBook(Long id) {
        Optional<BookEntity> bookEntity = BookEntity.findByIdOptional(id);
        if (bookEntity.isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(BookMapper.transform(bookEntity.get())).build();
    }

    @Override
    @Transactional
    public Response addBook(Book book) {
        BookEntity bookEntity = BookMapper.transform(book);
        AdminResponse responseBody = new AdminResponse();
        responseBody.setId(bookEntity.id);
        try {
            BookEntity.persist(bookEntity);
        } catch (Exception exception) {
            responseBody.addFailureItem(new FaultObject().reason(exception.getMessage()).reasonCode("BOK-0001"));
            responseBody.setResult(AdminResponse.ResultEnum.FAILURE);
            return Response.status(Response.Status.BAD_REQUEST).entity(responseBody).build();
        }
        responseBody.setIsbn(bookEntity.getIsbn());
        responseBody.setResult(AdminResponse.ResultEnum.SUCCESS);
        return Response.ok(responseBody).build();
    }

    @Override
    @Transactional
    public Response removeBook(Long id) {
        Boolean result = BookEntity.deleteById(id);
        if (Optional.ofNullable(result).isEmpty() || !result) {
            return Response.noContent().build();
        }
        return Response.ok(new AdminResponse().result(AdminResponse.ResultEnum.SUCCESS).id(id)).build();
    }

    @Override
    @Transactional
    public Response updateBook(Book book) {
        AdminResponse responseBody = new AdminResponse();

        if (Optional.ofNullable(book.getId()).isEmpty()) {
            responseBody.addFailureItem(new FaultObject().reason("No ID given in request for the update to be done!").reasonCode("BOK-0002"));
            responseBody.setResult(AdminResponse.ResultEnum.FAILURE);
            return Response.status(Response.Status.BAD_REQUEST).entity(responseBody).build();
        }

        try {
            if (BookEntity.update(BookQueryBuilder.build(book)) <= 0) {
                return Response.noContent().build();
            }
        } catch (Exception exception) {
            responseBody.addFailureItem(new FaultObject().reason(exception.getMessage()).reasonCode("BOK-0001"));
            responseBody.setResult(AdminResponse.ResultEnum.FAILURE);
            return Response.status(Response.Status.BAD_REQUEST).entity(responseBody).build();
        }

        responseBody.setResult(AdminResponse.ResultEnum.SUCCESS);
        responseBody.setId(book.getId());
        return Response.ok(responseBody).build();
    }

}
