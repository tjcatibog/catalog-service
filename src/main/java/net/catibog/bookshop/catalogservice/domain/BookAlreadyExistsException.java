package net.catibog.bookshop.catalogservice.domain;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String isbn) {
        super("A book with ISBN %s already exists.".formatted(isbn));
    }
}
