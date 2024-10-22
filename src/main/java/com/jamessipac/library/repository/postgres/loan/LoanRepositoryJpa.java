package com.jamessipac.library.repository.postgres.loan;

import com.jamessipac.library.bo.postgres.LoanPostgres;
import com.jamessipac.library.bo.postgres.LoanPostgresId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para gestionar las operaciones de acceso a datos de préstamos en PostgreSQL.
 * Esta interfaz extiende JpaRepository, lo que proporciona métodos CRUD para la entidad LoanPostgres.
 */
public interface LoanRepositoryJpa extends JpaRepository<LoanPostgres, LoanPostgresId> {
    // JpaRepository proporciona métodos para operaciones básicas de persistencia
    // como save, findAll, findById, delete, etc., sin necesidad de implementarlos manualmente.
}
