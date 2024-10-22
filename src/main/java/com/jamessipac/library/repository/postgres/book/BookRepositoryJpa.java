package com.jamessipac.library.repository.postgres.book;

import com.jamessipac.library.bo.postgres.BookPostgres;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz del repositorio de libros para PostgreSQL.
 * Extiende JpaRepository para proporcionar operaciones CRUD
 * para la entidad BookPostgres.
 */
public interface BookRepositoryJpa extends JpaRepository<BookPostgres, Long> {
    // Este repositorio hereda todos los métodos CRUD de JpaRepository.
    // No se requieren métodos adicionales a menos que se necesiten consultas personalizadas.
}
