package com.jamessipac.library.service.book;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.jamessipac.library.bo.Book;
import com.jamessipac.library.repository.BookRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio para manejar operaciones relacionadas con libros.
 */
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private static final String BOOK_NOT_FOUND = "Book not found with ID: ";

    @Value("${spring.profiles.active}")
    private String profile;

    @Override
    public Book createBook(Book book) {
        // Crea un nuevo libro en el repositorio.
        return bookRepository.createBook(book);
    }

    @Override
    public List<Book> getBooks() {
        // Obtiene la lista de todos los libros del repositorio.
        return bookRepository.getBooks();
    }

    @Override
    public Book findBookById(String idBook) {
        // Verifica el formato del ID del libro si se está usando PostgreSQL.
        validateIdFormat(idBook);

        // Busca el libro por ID y lanza una excepción si no se encuentra.
        return bookRepository.findBookById(idBook)
                .orElseThrow(() -> new EntityNotFoundException(BOOK_NOT_FOUND + idBook));
    }

    @Override
    public Book updateBook(String idBook, Book book) {
        // Verifica el formato del ID del libro si se está usando PostgreSQL.
        validateIdFormat(idBook);

        // Busca el libro existente para actualizarlo.
        Book bookFound = bookRepository.findBookById(idBook)
                .orElseThrow(() -> new EntityNotFoundException(BOOK_NOT_FOUND + idBook));

        // Actualiza los detalles del libro encontrado.
        bookFound.setTitle(book.getTitle());
        bookFound.setAuthor(book.getAuthor());
        bookFound.setIsbn(book.getIsbn());
        return bookRepository.updateBook(bookFound);
    }

    @Override
    public void deleteBook(String idBook) {
        // Verifica el formato del ID del libro si se está usando PostgreSQL.
        validateIdFormat(idBook);

        // Comprueba si el libro existe antes de intentar eliminarlo.
        if(bookRepository.findBookById(idBook).isEmpty()) {
            throw new EntityNotFoundException(BOOK_NOT_FOUND + idBook);
        }
        // Elimina el libro del repositorio.
        bookRepository.deleteBook(idBook);
    }

    /**
     * Valida el formato del ID del libro para PostgreSQL.
     *
     * @param idBook ID del libro a validar.
     */
    private void validateIdFormat(String idBook) {
        if(profile.equals("postgres")) {
            try {
                Long id = Long.parseLong(idBook);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid idBook format for Postgres: " + idBook);
            }
        }
    }
}
