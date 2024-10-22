package com.jamessipac.library.bo.postgres;

import jakarta.persistence.*; // Importa las anotaciones de JPA.
import lombok.Data;

/**
 * Esta clase representa un libro en la base de datos PostgreSQL.
 * Utiliza las anotaciones de Jakarta Persistence para mapear los datos a una tabla específica.
 * La anotación @Data de Lombok genera automáticamente getters, setters, toString,
 * equals, y hashCode.
 */
@Data
@Table(name = "books") // Define que esta clase está asociada a la tabla "books" en la base de datos.
@Entity // Indica que esta clase es una entidad JPA.
public class BookPostgres {

    @Id // Marca este campo como el identificador único de la entidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que el valor se generará automáticamente.
    @Column(name = "id_book") // Mapea esta propiedad al campo "id_book" en la tabla.
    private Long idBook; // Identificador único del libro en la base de datos.

    private String title; // Título del libro.
    private String author; // Autor del libro.
    private String isbn; // ISBN del libro.
}
