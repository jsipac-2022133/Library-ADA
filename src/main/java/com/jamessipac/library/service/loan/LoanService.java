package com.jamessipac.library.service.loan;

import com.jamessipac.library.bo.Loan;

import java.util.List;

/**
 * Interfaz que define las operaciones para manejar los préstamos de libros.
 */
public interface LoanService {
    /**
     * Crea un nuevo préstamo para un libro específico.
     *
     * @param idBook ID del libro que se va a prestar.
     * @param loan Objeto Loan que contiene la información del préstamo.
     * @return El préstamo creado.
     */
    Loan createLoan(String idBook, Loan loan);

    /**
     * Obtiene la lista de todos los préstamos.
     *
     * @return Lista de préstamos.
     */
    List<Loan> getLoans();

    /**
     * Encuentra un préstamo por su ID.
     *
     * @param idBook ID del libro cuyo préstamo se desea encontrar.
     * @return El préstamo encontrado.
     */
    Loan findLoanById(String idBook);

    /**
     * Actualiza la información de un préstamo existente.
     *
     * @param idBook ID del libro cuyo préstamo se va a actualizar.
     * @param loan Objeto Loan que contiene la nueva información del préstamo.
     * @return El préstamo actualizado.
     */
    Loan updateLoan(String idBook, Loan loan);

    /**
     * Elimina un préstamo por su ID.
     *
     * @param idBook ID del libro cuyo préstamo se va a eliminar.
     */
    void deleteLoan(String idBook);
}
