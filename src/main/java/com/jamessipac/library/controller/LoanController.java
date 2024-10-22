package com.jamessipac.library.controller;

import lombok.RequiredArgsConstructor;
import com.jamessipac.library.bo.Loan;
import com.jamessipac.library.service.loan.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador para la gestión de préstamos de libros.
 * Este controlador maneja las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los préstamos de libros.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/loan")
public class LoanController {

    private final LoanService loanService; // Servicio para la gestión de préstamos de libros.

    /**
     * Maneja la creación de un nuevo préstamo de libro.
     *
     * @param idBook El ID del libro que se va a prestar.
     * @param loan El objeto Loan que contiene la información del préstamo.
     * @return Un objeto ResponseEntity que contiene el préstamo creado y un estado HTTP 201 (CREATED).
     */
    @PostMapping("/{idBook}")
    public ResponseEntity<Loan> createLoan(@PathVariable String idBook, @RequestBody Loan loan) {
        Loan newLoan = loanService.createLoan(idBook, loan); // Crea el préstamo utilizando el servicio de préstamos.
        return new ResponseEntity<>(newLoan, HttpStatus.CREATED); // Devuelve el préstamo creado con estado HTTP 201.
    }

    /**
     * Maneja la solicitud para obtener todos los préstamos.
     *
     * @return Un objeto ResponseEntity que contiene una lista de préstamos y un estado HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loanPostgres = loanService.getLoans(); // Obtiene todos los préstamos utilizando el servicio de préstamos.
        return new ResponseEntity<>(loanPostgres, HttpStatus.OK); // Devuelve la lista de préstamos con estado HTTP 200.
    }

    /**
     * Maneja la solicitud para obtener un préstamo específico por su ID.
     *
     * @param idBook El ID del préstamo que se desea obtener.
     * @return Un objeto ResponseEntity que contiene el préstamo encontrado y un estado HTTP 200 (OK).
     */
    @GetMapping("/{idBook}")
    public ResponseEntity<Loan> getLoanById(@PathVariable String idBook) {
        Loan loan = loanService.findLoanById(idBook); // Busca el préstamo utilizando el servicio de préstamos.
        return new ResponseEntity<>(loan, HttpStatus.OK); // Devuelve el préstamo encontrado con estado HTTP 200.
    }

    /**
     * Maneja la actualización de un préstamo existente.
     *
     * @param idBook El ID del préstamo que se va a actualizar.
     * @param loan Los datos actualizados del préstamo.
     * @return Un objeto ResponseEntity que contiene el préstamo actualizado y un estado HTTP 200 (OK).
     */
    @PutMapping("/{idBook}")
    public ResponseEntity<Loan> updateLoan(@PathVariable String idBook, @RequestBody Loan loan) {
        Loan newLoan = loanService.updateLoan(idBook, loan); // Actualiza el préstamo utilizando el servicio de préstamos.
        return new ResponseEntity<>(newLoan, HttpStatus.OK); // Devuelve el préstamo actualizado con estado HTTP 200.
    }

    /**
     * Maneja la eliminación de un préstamo existente.
     *
     * @param idBook El ID del préstamo que se va a eliminar.
     * @return Un objeto ResponseEntity que contiene un mensaje de éxito y un estado HTTP 200 (OK).
     */
    @DeleteMapping("/{idBook}")
    public ResponseEntity<Map<String, String>> deleteLoan(@PathVariable String idBook) {
        loanService.deleteLoan(idBook); // Elimina el préstamo utilizando el servicio de préstamos.
        Map<String, String> response = new HashMap<>();
        response.put("message", "Loan deleted successfully"); // Mensaje de éxito.
        return new ResponseEntity<>(response, HttpStatus.OK); // Devuelve el mensaje con estado HTTP 200.
    }
}
