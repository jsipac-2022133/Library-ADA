package com.jamessipac.library.repository;

import com.jamessipac.library.bo.Loan;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para el repositorio de préstamos (loans).
 * Define las operaciones CRUD que se pueden realizar sobre la entidad Loan.
 */
public interface LoanRepository {

    /**
     * Crea un nuevo préstamo en el repositorio.
     *
     * @param loan El objeto Loan a crear.
     * @return El préstamo creado.
     */
    Loan createLoan(Loan loan);

    /**
     * Obtiene todos los préstamos del repositorio.
     *
     * @return Una lista de objetos Loan.
     */
    List<Loan> getLoans();

    /**
     * Busca un préstamo por su ID (que consiste en el ID del usuario y el ID del préstamo).
     *
     * @param idUser El ID del usuario que realizó el préstamo.
     * @param idLoan El ID del préstamo a buscar.
     * @return Un Optional que contiene el préstamo si se encuentra, o vacío si no.
     */
    Optional<Loan> findLoanById(String idUser, String idLoan);

    /**
     * Actualiza un préstamo existente en el repositorio.
     *
     * @param loan El objeto Loan con los datos actualizados.
     * @return El préstamo actualizado.
     */
    Loan updateLoan(Loan loan);

    /**
     * Elimina un préstamo del repositorio por el ID del usuario y el ID del libro.
     *
     * @param idUser El ID del usuario que realizó el préstamo.
     * @param idBook El ID del libro asociado al préstamo.
     */
    void deleteLoan(String idUser, String idBook);
}
