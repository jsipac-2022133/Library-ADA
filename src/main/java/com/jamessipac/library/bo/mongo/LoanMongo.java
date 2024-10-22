package com.jamessipac.library.bo.mongo;

import jakarta.persistence.Embedded;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/**
 * Esta clase representa un documento de préstamo en una colección de MongoDB.
 * Utiliza las anotaciones de Spring Data MongoDB para mapear los datos a una colección específica.
 * La anotación @Data de Lombok genera automáticamente getters, setters, toString,
 * equals, y hashCode.
 */
@Data
@Document(collection = "loans") // Define que esta clase está asociada a la colección "loans" en MongoDB.
public class LoanMongo {

    @Id
    @Embedded // Indica que esta propiedad está incrustada como un objeto en el documento de MongoDB.
    private LoanMongoId idLoan; // Identificador único del préstamo, que puede contener información adicional (como ID de libro y usuario).

    @Field(name = "loan_date") // Mapea el campo "loan_date" en MongoDB a esta propiedad.
    private LocalDateTime loanDate; // Fecha en la que se realizó el préstamo.

    @Field(name = "return_date") // Mapea el campo "return_date" en MongoDB a esta propiedad.
    private LocalDateTime returnDate; // Fecha en la que se espera que se devuelva el préstamo.
}
