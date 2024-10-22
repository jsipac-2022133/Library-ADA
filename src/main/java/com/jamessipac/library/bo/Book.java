package com.jamessipac.library.bo;

import lombok.Data;

/**
 * Esta clase representa un libro en el sistema.
 * La anotación @Data de Lombok genera automáticamente getters, setters, toString,
 * equals, y hashCode.
 */
@Data
public class Book {

    private String idBook; // Identificador único del libro.
    private String title; // Título del libro.
    private String author; // Autor del libro.
    private String isbn; // Número ISBN del libro.
}
