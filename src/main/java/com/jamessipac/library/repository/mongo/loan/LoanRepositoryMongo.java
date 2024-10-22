package com.jamessipac.library.repository.mongo.loan;

import lombok.RequiredArgsConstructor;
import com.jamessipac.library.bo.Loan;
import com.jamessipac.library.bo.mongo.LoanMongo;
import com.jamessipac.library.bo.mongo.LoanMongoId;
import com.jamessipac.library.repository.LoanRepository;
import com.jamessipac.library.util.caster.LoanCaster;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del repositorio de préstamos utilizando MongoDB.
 * Esta clase se encarga de la persistencia de datos relacionados con
 * los préstamos en una base de datos NoSQL y se activa solo cuando el perfil "mongo" está en uso.
 */
@Profile("mongo")
@RequiredArgsConstructor
@Service
public class LoanRepositoryMongo implements LoanRepository {

    // Repositorio NoSQL para operaciones de base de datos
    private final LoanRepositoryNoSql loanRepositoryNoSql;

    // Utilidad para la conversión entre objetos Loan y LoanMongo
    private final LoanCaster loanCaster;

    /**
     * Crea un nuevo préstamo en la base de datos.
     *
     * @param loan Objeto Loan que representa el préstamo a crear.
     * @return El objeto Loan creado.
     */
    @Override
    public Loan createLoan(Loan loan) {
        // Convierte el objeto Loan a LoanMongo para persistencia
        LoanMongo loanMongo = loanCaster.loanToLoanMongo(loan);
        // Guarda el préstamo en la base de datos y obtiene el nuevo objeto
        LoanMongo newMongo = loanRepositoryNoSql.save(loanMongo);
        // Convierte el objeto LoanMongo guardado de nuevo a Loan y lo retorna
        return loanCaster.loanMongoToLoan(newMongo);
    }

    /**
     * Obtiene una lista de todos los préstamos en la base de datos.
     *
     * @return Lista de objetos Loan.
     */
    @Override
    public List<Loan> getLoans() {
        // Busca todos los préstamos en la base de datos y los convierte a objetos Loan
        return loanRepositoryNoSql.findAll().stream()
                .map(loanCaster::loanMongoToLoan)
                .collect(Collectors.toList());
    }

    /**
     * Busca un préstamo por el ID del usuario y el ID del libro.
     *
     * @param idUser ID del usuario asociado al préstamo.
     * @param idBook ID del libro asociado al préstamo.
     * @return Un Optional que puede contener el objeto Loan si se encuentra, o vacío si no.
     */
    @Override
    public Optional<Loan> findLoanById(String idUser, String idBook) {
        // Crea un nuevo ID de préstamo compuesto por el ID del usuario y el ID del libro
        LoanMongoId id = new LoanMongoId();
        id.setIdUser(idUser);
        id.setIdBook(idBook);
        // Busca el préstamo en la base de datos y convierte el resultado a Loan
        Optional<LoanMongo> loanMongo = loanRepositoryNoSql.findById(id);
        return loanMongo.map(loanCaster::loanMongoToLoan);
    }

    /**
     * Actualiza la información de un préstamo existente.
     *
     * @param loan Objeto Loan que contiene la información actualizada del préstamo.
     * @return El objeto Loan actualizado.
     */
    @Override
    public Loan updateLoan(Loan loan) {
        // Convierte el objeto Loan a LoanMongo para la actualización
        LoanMongo loanMongo = loanCaster.loanToLoanMongo(loan);
        // Guarda el préstamo actualizado en la base de datos y obtiene el nuevo objeto
        LoanMongo newMongo = loanRepositoryNoSql.save(loanMongo);
        // Convierte el objeto LoanMongo guardado de nuevo a Loan y lo retorna
        return loanCaster.loanMongoToLoan(newMongo);
    }

    /**
     * Elimina un préstamo de la base de datos por el ID del usuario y el ID del libro.
     *
     * @param idUser ID del usuario asociado al préstamo.
     * @param idBook ID del libro asociado al préstamo.
     */
    @Override
    public void deleteLoan(String idUser, String idBook) {
        // Crea un nuevo ID de préstamo compuesto por el ID del usuario y el ID del libro
        LoanMongoId id = new LoanMongoId();
        id.setIdUser(idUser);
        id.setIdBook(idBook);
        // Elimina el préstamo de la base de datos utilizando su ID
        loanRepositoryNoSql.deleteById(id);
    }
}
