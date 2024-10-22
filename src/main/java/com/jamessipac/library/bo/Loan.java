package com.jamessipac.library.bo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Esta clase representa un préstamo de un libro en el sistema.
 * La anotación @Data de Lombok genera automáticamente getters, setters, toString,
 * equals, y hashCode.
 */
@Data
public class Loan {

    private String idUser; // Identificador único del usuario que realiza el préstamo.
    private String idBook; // Identificador único del libro que se presta.
    private LocalDateTime loanDate; // Fecha y hora en que se realiza el préstamo.
    private LocalDateTime returnDate; // Fecha y hora en que se debe devolver el libro.
}
