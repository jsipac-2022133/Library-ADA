package com.jamessipac.library.bo.mongo;

import lombok.Data;

/**
 * Esta clase representa el identificador único para un préstamo en el sistema.
 * Contiene los identificadores del libro y del usuario asociados con el préstamo.
 * La anotación @Data de Lombok genera automáticamente getters, setters, toString,
 * equals, y hashCode.
 */
@Data
public class LoanMongoId {

    private String idBook; // Identificador único del libro asociado al préstamo.
    private String idUser; // Identificador único del usuario que realiza el préstamo.
}
