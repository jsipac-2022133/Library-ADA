package com.jamessipac.library.repository;

import com.jamessipac.library.bo.Book;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para el repositorio de libros.
 * Define las operaciones CRUD que se pueden realizar sobre la entidad Book.
 */
public interface BookRepository {

    /**
     * Crea un nuevo libro en el repositorio.
     *
     * @param book El objeto Book a crear.
     * @return El libro creado.
     */
    Book createBook(Book book);

    /**
     * Obtiene todos los libros del repositorio.
     *
     * @return Una lista de objetos Book.
     */
    List<Book> getBooks();

    /**
     * Busca un libro por su ID.
     *
     * @param idBook El ID del libro a buscar.
     * @return Un Optional que contiene el libro si se encuentra, o vacío si no.
     */
    Optional<Book> findBookById(String idBook);

    /**
     * Actualiza un libro existente en el repositorio.
     *
     * @param book El objeto Book con los datos actualizados.
     * @return El libro actualizado.
     */
    Book updateBook(Book book);

    /**
     * Elimina un libro del repositorio por su ID.
     *
     * @param idBook El ID del libro a eliminar.
     */
    void deleteBook(String idBook);
}
