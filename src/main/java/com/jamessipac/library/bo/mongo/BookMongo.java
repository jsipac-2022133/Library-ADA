package com.jamessipac.library.bo.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Esta clase representa un documento de libro en una colección de MongoDB.
 * Utiliza las anotaciones de Spring Data MongoDB para mapear los datos a una colección específica.
 * La anotación @Data de Lombok genera automáticamente getters, setters, toString,
 * equals, y hashCode.
 */
@Data
@Document(collection = "books") // Define que esta clase está asociada a la colección "books" en MongoDB.
public class BookMongo {

    @Id
    private String idBook; // Identificador único del libro en la base de datos.

    private String title; // Título del libro.
    private String author; // Autor del libro.
    private String isbn; // Número ISBN del libro.
}
