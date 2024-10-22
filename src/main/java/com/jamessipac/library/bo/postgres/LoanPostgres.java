package com.jamessipac.library.bo.postgres;

import jakarta.persistence.*; // Importa las anotaciones de JPA.
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Esta clase representa un préstamo de libro en la base de datos PostgreSQL.
 * Utiliza las anotaciones de Jakarta Persistence para mapear los datos a una tabla específica.
 * La anotación @Data de Lombok genera automáticamente getters, setters, toString,
 * equals, y hashCode.
 */
@Data
@Entity // Indica que esta clase es una entidad JPA.
@Table(name = "loans") // Define que esta clase está asociada a la tabla "loans" en la base de datos.
public class LoanPostgres {

    @EmbeddedId // Indica que esta propiedad es una clave primaria compuesta.
    private LoanPostgresId idLoan; // Identificador del préstamo, que incluye múltiples campos.

    @Column(name = "loan_date") // Mapea esta propiedad al campo "loan_date" en la tabla.
    private LocalDateTime loanDate; // Fecha en que se realizó el préstamo.

    @Column(name = "return_date") // Mapea esta propiedad al campo "return_date" en la tabla.
    private LocalDateTime returnDate; // Fecha en que se debe devolver el libro.
}
