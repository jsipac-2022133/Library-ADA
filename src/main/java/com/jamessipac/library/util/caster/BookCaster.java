package com.jamessipac.library.util.caster;

import com.jamessipac.library.bo.Book;
import com.jamessipac.library.bo.mongo.BookMongo;
import com.jamessipac.library.bo.postgres.BookPostgres;
import org.springframework.stereotype.Component;

/**
 * Clase utilitaria para realizar conversiones entre diferentes representaciones
 * de un libro, específicamente entre las entidades Book, BookPostgres y BookMongo.
 */
@Component
public class BookCaster {

    /**
     * Convierte un objeto Book a un objeto BookPostgres.
     *
     * @param book El objeto Book que se va a convertir.
     * @return Un objeto BookPostgres que representa el libro.
     */
    public BookPostgres bookToBookPostgres(Book book) {
        BookPostgres bookPostgres = new BookPostgres();
        bookPostgres.setIdBook((book.getIdBook() != null && !book.getIdBook().isEmpty())
                ? Long.parseLong(book.getIdBook()) : null);
        bookPostgres.setTitle(book.getTitle());
        bookPostgres.setAuthor(book.getAuthor());
        bookPostgres.setIsbn(book.getIsbn());
        return bookPostgres;
    }

    /**
     * Convierte un objeto BookPostgres a un objeto Book.
     *
     * @param bookPostgres El objeto BookPostgres que se va a convertir.
     * @return Un objeto Book que representa el libro.
     */
    public Book bookPostgresToBook(BookPostgres bookPostgres) {
        Book book = new Book();
        book.setIdBook(String.valueOf(bookPostgres.getIdBook()));
        book.setTitle(bookPostgres.getTitle());
        book.setAuthor(bookPostgres.getAuthor());
        book.setIsbn(bookPostgres.getIsbn());
        return book;
    }

    /**
     * Convierte un objeto Book a un objeto BookMongo.
     *
     * @param book El objeto Book que se va a convertir.
     * @return Un objeto BookMongo que representa el libro.
     */
    public BookMongo bookToBookMongo(Book book) {
        BookMongo bookMongo = new BookMongo();
        bookMongo.setIdBook(book.getIdBook());
        bookMongo.setTitle(book.getTitle());
        bookMongo.setAuthor(book.getAuthor());
        bookMongo.setIsbn(book.getIsbn());
        return bookMongo;
    }

    /**
     * Convierte un objeto BookMongo a un objeto Book.
     *
     * @param bookMongo El objeto BookMongo que se va a convertir.
     * @return Un objeto Book que representa el libro.
     */
    public Book bookMongoToBook(BookMongo bookMongo) {
        Book book = new Book();
        book.setIdBook(bookMongo.getIdBook());
        book.setTitle(bookMongo.getTitle());
        book.setAuthor(bookMongo.getAuthor());
        book.setIsbn(bookMongo.getIsbn());
        return book;
    }
}
