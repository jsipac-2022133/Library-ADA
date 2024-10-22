package com.jamessipac.library.repository.mongo.loan;

import com.jamessipac.library.bo.mongo.LoanMongo;
import com.jamessipac.library.bo.mongo.LoanMongoId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interfaz que extiende MongoRepository para realizar operaciones CRUD
 * en la colección de préstamos en MongoDB.
 *
 * Esta interfaz permite el acceso a la base de datos MongoDB para realizar
 * operaciones de creación, lectura, actualización y eliminación (CRUD)
 * sobre documentos de tipo LoanMongo, utilizando LoanMongoId como clave compuesta.
 */
public interface LoanRepositoryNoSql extends MongoRepository<LoanMongo, LoanMongoId> {
    // No se necesitan métodos adicionales, ya que MongoRepository
    // proporciona todos los métodos necesarios para interactuar con la base de datos.
}
