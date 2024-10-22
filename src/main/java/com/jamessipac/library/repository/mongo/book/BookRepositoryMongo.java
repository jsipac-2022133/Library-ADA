package com.jamessipac.library.repository.mongo.book;

import lombok.RequiredArgsConstructor;
import com.jamessipac.library.bo.Book;
import com.jamessipac.library.bo.mongo.BookMongo;
import com.jamessipac.library.repository.BookRepository;
import com.jamessipac.library.util.caster.BookCaster;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del repositorio de libros utilizando MongoDB.
 * Esta clase se encarga de la persistencia de datos en una base de datos NoSQL
 * y se activa solo cuando el perfil "mongo" está en uso.
 */
@Profile("mongo")
@RequiredArgsConstructor
@Repository
public class BookRepositoryMongo implements BookRepository {

    // Repositorio NoSQL para operaciones de base de datos
    private final BookRepositoryNoSql bookRepositoryMongoNoSql;

    // Utilidad para la conversión entre objetos Book y BookMongo
    private final BookCaster bookCaster;

    /**
     * Crea un nuevo libro en la base de datos.
     *
     * @param book Objeto Book que representa el libro a crear.
     * @return El objeto Book creado.
     */
    @Override
    public Book createBook(Book book) {
        // Convierte el objeto Book a BookMongo para persistencia
        BookMongo bookMongo = bookCaster.bookToBookMongo(book);
        // Guarda el libro en la base de datos y obtiene el nuevo objeto
        BookMongo newBook = bookRepositoryMongoNoSql.save(bookMongo);
        // Convierte el objeto BookMongo guardado de nuevo a Book y lo retorna
        return bookCaster.bookMongoToBook(newBook);
    }

    /**
     * Obtiene una lista de todos los libros en la base de datos.
     *
     * @return Lista de objetos Book.
     */
    @Override
    public List<Book> getBooks() {
        // Busca todos los libros en la base de datos y los convierte a objetos Book
        return bookRepositoryMongoNoSql.findAll().stream()
                .map(bookCaster::bookMongoToBook)
                .collect(Collectors.toList());
    }

    /**
     * Busca un libro por su ID.
     *
     * @param idBook ID del libro a buscar.
     * @return Un Optional que puede contener el objeto Book si se encuentra, o vacío si no.
     */
    @Override
    public Optional<Book> findBookById(String idBook) {
        // Busca el libro en la base de datos y convierte el resultado a Book
        Optional<BookMongo> bookMongo = bookRepositoryMongoNoSql.findById(idBook);
        return bookMongo.map(bookCaster::bookMongoToBook);
    }

    /**
     * Actualiza la información de un libro existente.
     *
     * @param book Objeto Book que contiene la información actualizada del libro.
     * @return El objeto Book actualizado.
     */
    @Override
    public Book updateBook(Book book) {
        // Convierte el objeto Book a BookMongo para la actualización
        BookMongo bookMongo = bookCaster.bookToBookMongo(book);
        // Guarda el libro actualizado en la base de datos y obtiene el nuevo objeto
        BookMongo newBook = bookRepositoryMongoNoSql.save(bookMongo);
        // Convierte el objeto BookMongo guardado de nuevo a Book y lo retorna
        return bookCaster.bookMongoToBook(newBook);
    }

    /**
     * Elimina un libro de la base de datos por su ID.
     *
     * @param idBook ID del libro a eliminar.
     */
    @Override
    public void deleteBook(String idBook) {
        // Elimina el libro de la base de datos utilizando su ID
        bookRepositoryMongoNoSql.deleteById(idBook);
    }
}
