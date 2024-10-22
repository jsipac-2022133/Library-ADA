package com.jamessipac.library.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * Controlador de manejo de excepciones global en la aplicación.
 * Esta clase captura y maneja excepciones específicas que pueden ocurrir
 * durante la ejecución de la aplicación y proporciona respuestas adecuadas
 * con información relevante.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja la excepción cuando una entidad no se encuentra.
     *
     * @param ex La excepción que se produjo.
     * @return Una respuesta con estado 404 Not Found y detalles del error.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleEntityNotFoundException(EntityNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setProperty("description", "The requested entity was not found");
        return new ResponseEntity<>(problemDetail, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja excepciones de credenciales incorrectas durante el inicio de sesión.
     *
     * @param ex La excepción que se produjo.
     * @return Una respuesta con estado 400 Bad Request y detalles del error.
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ProblemDetail> handleBadCredentialsException(BadCredentialsException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setProperty("description", "The email or password is incorrect");
        return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepciones relacionadas con el estado de la cuenta.
     *
     * @param ex La excepción que se produjo.
     * @return Una respuesta con estado 403 Forbidden y detalles del error.
     */
    @ExceptionHandler(AccountStatusException.class)
    public ResponseEntity<ProblemDetail> handleAccountStatusException(AccountStatusException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
        problemDetail.setProperty("description", "The account is locked");
        return new ResponseEntity<>(problemDetail, HttpStatus.FORBIDDEN);
    }

    /**
     * Maneja excepciones de acceso denegado.
     *
     * @param ex La excepción que se produjo.
     * @return Una respuesta con estado 403 Forbidden y detalles del error.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ProblemDetail> handleAccessDeniedException(AccessDeniedException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
        problemDetail.setProperty("description", "You are not authorized to access this resource");
        return new ResponseEntity<>(problemDetail, HttpStatus.FORBIDDEN);
    }

    /**
     * Maneja excepciones de firma de token JWT inválida.
     *
     * @param ex La excepción que se produjo.
     * @return Una respuesta con estado 403 Forbidden y detalles del error.
     */
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ProblemDetail> handleSignatureException(SignatureException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
        problemDetail.setProperty("description", "The request token signature is invalid");
        return new ResponseEntity<>(problemDetail, HttpStatus.FORBIDDEN);
    }

    /**
     * Maneja excepciones de token JWT expirado.
     *
     * @param ex La excepción que se produjo.
     * @return Una respuesta con estado 403 Forbidden y detalles del error.
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ProblemDetail> handleExpiredJwtException(ExpiredJwtException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
        problemDetail.setProperty("description", "The request token is expired");
        return new ResponseEntity<>(problemDetail, HttpStatus.FORBIDDEN);
    }

    /**
     * Maneja excepciones de argumento ilegal.
     *
     * @param ex La excepción que se produjo.
     * @return Una respuesta con estado 400 Bad Request y detalles del error.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProblemDetail> handleIllegalArgumentException(IllegalArgumentException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setProperty("description", "The request is invalid");
        return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepciones de violación de integridad de datos.
     *
     * @param ex La excepción que se produjo.
     * @return Una respuesta con estado 400 Bad Request y detalles del error.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ProblemDetail> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setProperty("description data", "Invalid data generates a conflict");
        return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepciones generales no manejadas.
     *
     * @param ex La excepción que se produjo.
     * @return Una respuesta con estado 500 Internal Server Error y detalles del error.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleException(Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        problemDetail.setProperty("info", "The unhandled exception is of type: " + ex.getClass().getSimpleName());
        problemDetail.setProperty("type", ex.getClass().getSimpleName());
        problemDetail.setProperty("description", ex.getMessage() != null ? ex.getMessage() : "Unknown error");
        problemDetail.setProperty("class", ex.getStackTrace()[0].getClassName());
        problemDetail.setProperty("line", ex.getStackTrace()[0].getLineNumber());
        problemDetail.setProperty("timestamp", LocalDateTime.now().toString());
        return new ResponseEntity<>(problemDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
