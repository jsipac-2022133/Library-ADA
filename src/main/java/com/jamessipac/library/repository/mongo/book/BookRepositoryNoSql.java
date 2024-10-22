package com.jamessipac.library.repository.mongo.book;

import com.jamessipac.library.bo.mongo.BookMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Interfaz que extiende MongoRepository para operaciones CRUD en la colección de libros en MongoDB.
 *
 * Esta interfaz proporciona métodos predefinidos para realizar operaciones de
 * creación, lectura, actualización y eliminación (CRUD) en documentos de tipo
 * BookMongo, sin necesidad de implementar manualmente los métodos.
 */
public interface BookRepositoryNoSql extends MongoRepository<BookMongo, String> {
    // No se necesitan métodos adicionales ya que MongoRepository
    // proporciona todos los métodos necesarios para interactuar con la base de datos.
}
