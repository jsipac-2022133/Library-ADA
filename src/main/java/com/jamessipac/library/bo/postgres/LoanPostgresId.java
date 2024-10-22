package com.jamessipac.library.bo.postgres;

import jakarta.persistence.Embeddable; // Importa la anotación Embeddable de JPA.
import lombok.Data;

import java.io.Serializable; // Importa Serializable para permitir que esta clase sea serializable.

/**
 * Esta clase representa la clave primaria compuesta para los préstamos en la base de datos PostgreSQL.
 * Se utiliza junto con la clase LoanPostgres para identificar de manera única cada préstamo.
 * La anotación @Data de Lombok genera automáticamente getters, setters, toString,
 * equals, y hashCode.
 */
@Data
@Embeddable // Indica que esta clase puede ser usada como parte de una clave primaria embebida.
public class LoanPostgresId implements Serializable {

    private Long idBook; // Identificador del libro asociado al préstamo.
    private Long idUser; // Identificador del usuario que realiza el préstamo.
}
