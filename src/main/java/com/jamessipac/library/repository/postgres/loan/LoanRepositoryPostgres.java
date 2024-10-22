package com.jamessipac.library.repository.postgres.loan;

import lombok.RequiredArgsConstructor;
import com.jamessipac.library.bo.Loan;
import com.jamessipac.library.bo.postgres.LoanPostgres;
import com.jamessipac.library.bo.postgres.LoanPostgresId;
import com.jamessipac.library.repository.LoanRepository;
import com.jamessipac.library.util.caster.LoanCaster;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Repositorio para gestionar las operaciones de acceso a datos de préstamos en PostgreSQL.
 * Implementa la interfaz LoanRepository y utiliza JpaRepository para realizar operaciones CRUD.
 */
@Profile("postgres")
@RequiredArgsConstructor
@Repository
public class LoanRepositoryPostgres implements LoanRepository {

    private final LoanRepositoryJpa loanRepositoryJpa; // Repositorio JPA para operaciones CRUD
    private final LoanCaster loanCaster; // Utilidad para convertir entre Loan y LoanPostgres

    @Override
    public Loan createLoan(Loan loan) {
        // Convierte el objeto Loan a LoanPostgres y lo guarda en la base de datos
        LoanPostgres loanPostgres = loanCaster.loanToLoanPostgres(loan);
        LoanPostgres newLoan = loanRepositoryJpa.save(loanPostgres);
        // Convierte el LoanPostgres guardado de vuelta a Loan y lo retorna
        return loanCaster.loanPostgresToLoan(newLoan);
    }

    @Override
    public List<Loan> getLoans() {
        // Recupera todos los préstamos de la base de datos y los convierte a la entidad Loan
        return loanRepositoryJpa.findAll().stream()
                .map(loanCaster::loanPostgresToLoan)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Loan> findLoanById(String idUser, String idBook) {
        // Crea un ID de préstamo basado en el ID de usuario y el ID de libro
        LoanPostgresId id = new LoanPostgresId();
        id.setIdUser(Long.parseLong(idUser));
        id.setIdBook(Long.parseLong(idBook));
        // Busca el préstamo en la base de datos y lo convierte a Loan si existe
        Optional<LoanPostgres> loanPostgres = loanRepositoryJpa.findById(id);
        return loanPostgres.map(loanCaster::loanPostgresToLoan);
    }

    @Override
    public Loan updateLoan(Loan loan) {
        // Convierte el objeto Loan a LoanPostgres, lo guarda y lo convierte de nuevo a Loan
        LoanPostgres loanPostgres = loanCaster.loanToLoanPostgres(loan);
        LoanPostgres newLoan = loanRepositoryJpa.save(loanPostgres);
        return loanCaster.loanPostgresToLoan(newLoan);
    }

    @Override
    public void deleteLoan(String idUser, String idBook) {
        // Crea un ID de préstamo y lo elimina de la base de datos
        LoanPostgresId id = new LoanPostgresId();
        id.setIdUser(Long.parseLong(idUser));
        id.setIdBook(Long.parseLong(idBook));
        loanRepositoryJpa.deleteById(id);
    }
}
