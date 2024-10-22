package com.jamessipac.library.controller;

import lombok.RequiredArgsConstructor;
import com.jamessipac.library.bo.Book;
import com.jamessipac.library.service.book.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador para la gestión de libros.
 * Este controlador maneja las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los libros.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService; // Servicio para la gestión de libros.

    /**
     * Maneja la creación de un nuevo libro.
     *
     * @param book El libro que se va a crear.
     * @return Un objeto ResponseEntity que contiene el libro creado y un estado HTTP 201 (CREATED).
     */
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book bookCreated = bookService.createBook(book); // Crea el libro utilizando el servicio de libros.
        return new ResponseEntity<>(bookCreated, HttpStatus.CREATED); // Devuelve el libro creado con estado HTTP 201.
    }

    /**
     * Maneja la solicitud para obtener todos los libros.
     *
     * @return Un objeto ResponseEntity que contiene una lista de libros y un estado HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getBooks(); // Obtiene todos los libros utilizando el servicio de libros.
        return new ResponseEntity<>(books, HttpStatus.OK); // Devuelve la lista de libros con estado HTTP 200.
    }

    /**
     * Maneja la solicitud para obtener un libro específico por su ID.
     *
     * @param idBook El ID del libro que se desea obtener.
     * @return Un objeto ResponseEntity que contiene el libro encontrado y un estado HTTP 200 (OK).
     */
    @GetMapping("/{idBook}")
    public ResponseEntity<Book> getBookById(@PathVariable String idBook) {
        Book book = bookService.findBookById(idBook); // Busca el libro utilizando el servicio de libros.
        return new ResponseEntity<>(book, HttpStatus.OK); // Devuelve el libro encontrado con estado HTTP 200.
    }

    /**
     * Maneja la actualización de un libro existente.
     *
     * @param idBook El ID del libro que se va a actualizar.
     * @param book Los datos actualizados del libro.
     * @return Un objeto ResponseEntity que contiene el libro actualizado y un estado HTTP 200 (OK).
     */
    @PutMapping("/{idBook}")
    public ResponseEntity<Book> updateBook(@PathVariable String idBook, @RequestBody Book book) {
        Book bookUpdated = bookService.updateBook(idBook, book); // Actualiza el libro utilizando el servicio de libros.
        return new ResponseEntity<>(bookUpdated, HttpStatus.OK); // Devuelve el libro actualizado con estado HTTP 200.
    }

    /**
     * Maneja la eliminación de un libro existente.
     *
     * @param idBook El ID del libro que se va a eliminar.
     * @return Un objeto ResponseEntity que contiene un mensaje de éxito y un estado HTTP 200 (OK).
     */
    @DeleteMapping("/{idBook}")
    public ResponseEntity<Map<String, String>> deleteBook(@PathVariable String idBook) {
        bookService.deleteBook(idBook); // Elimina el libro utilizando el servicio de libros.
        Map<String, String> response = new HashMap<>();
        response.put("message", "Book deleted successfully"); // Mensaje de éxito.
        return new ResponseEntity<>(response, HttpStatus.OK); // Devuelve el mensaje con estado HTTP 200.
    }
}
