package com.jamessipac.library.repository.postgres.book;

import lombok.RequiredArgsConstructor;
import com.jamessipac.library.bo.Book;
import com.jamessipac.library.bo.postgres.BookPostgres;
import com.jamessipac.library.repository.BookRepository;
import com.jamessipac.library.util.caster.BookCaster;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del repositorio de libros para PostgreSQL.
 * Esta clase se encarga de la persistencia de datos relacionados
 * con los libros en una base de datos PostgreSQL.
 */
@Profile("postgres") // Activa esta implementación cuando el perfil 'postgres' está activo.
@RequiredArgsConstructor
@Repository // Indica que esta clase es un repositorio de acceso a datos.
public class BookRepositoryPostgres implements BookRepository {

    private final BookRepositoryJpa bookRepositoryJpa; // Repositorio JPA para operaciones CRUD.
    private final BookCaster bookCaster; // Utilidad para convertir entre entidades.

    /**
     * Crea un nuevo libro en la base de datos.
     *
     * @param book El libro a crear.
     * @return El libro creado.
     */
    @Override
    public Book createBook(Book book) {
        BookPostgres bookPostgres = bookCaster.bookToBookPostgres(book); // Convierte a BookPostgres.
        BookPostgres newBook = bookRepositoryJpa.save(bookPostgres); // Guarda el libro en la base de datos.
        return bookCaster.bookPostgresToBook(newBook); // Convierte y retorna el libro creado.
    }

    /**
     * Recupera todos los libros de la base de datos.
     *
     * @return Una lista de libros.
     */
    @Override
    public List<Book> getBooks() {
        return bookRepositoryJpa.findAll().stream() // Obtiene todos los libros de la base de datos.
                .map(bookCaster::bookPostgresToBook) // Convierte cada libro a Book.
                .collect(Collectors.toList()); // Recoge los libros en una lista.
    }

    /**
     * Encuentra un libro por su ID.
     *
     * @param idBook El ID del libro a buscar.
     * @return Un Optional que contiene el libro si se encuentra, o vacío si no.
     */
    @Override
    public Optional<Book> findBookById(String idBook) {
        Optional<BookPostgres> bookPostgres = bookRepositoryJpa.findById(Long.parseLong(idBook)); // Busca el libro por ID.
        return bookPostgres.map(bookCaster::bookPostgresToBook); // Convierte el resultado a un Optional de Book.
    }

    /**
     * Actualiza un libro existente en la base de datos.
     *
     * @param book El libro a actualizar.
     * @return El libro actualizado.
     */
    @Override
    public Book updateBook(Book book) {
        BookPostgres bookPostgres = bookCaster.bookToBookPostgres(book); // Convierte a BookPostgres.
        BookPostgres newBook = bookRepositoryJpa.save(bookPostgres); // Guarda el libro actualizado.
        return bookCaster.bookPostgresToBook(newBook); // Convierte y retorna el libro actualizado.
    }

    /**
     * Elimina un libro de la base de datos por su ID.
     *
     * @param idBook El ID del libro a eliminar.
     */
    @Override
    public void deleteBook(String idBook) {
        bookRepositoryJpa.deleteById(Long.parseLong(idBook)); // Elimina el libro por ID.
    }
}
