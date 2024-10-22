package com.jamessipac.library.service.book;

import com.jamessipac.library.bo.Book;

import java.util.List;

/**
 * Servicio para manejar operaciones relacionadas con libros en la biblioteca.
 */
public interface BookService {
    /**
     * Crea un nuevo libro en la biblioteca.
     *
     * @param book El libro a crear.
     * @return El libro creado.
     */
    Book createBook(Book book);

    /**
     * Obtiene la lista de todos los libros en la biblioteca.
     *
     * @return Lista de libros.
     */
    List<Book> getBooks();

    /**
     * Busca un libro por su ID.
     *
     * @param idBook El ID del libro a buscar.
     * @return El libro encontrado, o null si no se encuentra.
     */
    Book findBookById(String idBook);

    /**
     * Actualiza la información de un libro existente.
     *
     * @param idBook El ID del libro a actualizar.
     * @param book   El nuevo objeto libro con la información actualizada.
     * @return El libro actualizado.
     */
    Book updateBook(String idBook, Book book);

    /**
     * Elimina un libro de la biblioteca por su ID.
     *
     * @param idBook El ID del libro a eliminar.
     */
    void deleteBook(String idBook);
}
