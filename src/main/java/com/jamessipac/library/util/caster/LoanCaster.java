package com.jamessipac.library.util.caster;

import com.jamessipac.library.bo.Loan;
import com.jamessipac.library.bo.mongo.LoanMongo;
import com.jamessipac.library.bo.mongo.LoanMongoId;
import com.jamessipac.library.bo.postgres.LoanPostgres;
import com.jamessipac.library.bo.postgres.LoanPostgresId;
import org.springframework.stereotype.Component;

/**
 * Clase utilitaria para realizar conversiones entre diferentes representaciones
 * de un préstamo, específicamente entre las entidades Loan, LoanPostgres y LoanMongo.
 */
@Component
public class LoanCaster {

    /**
     * Convierte un objeto Loan a un objeto LoanPostgres.
     *
     * @param loan El objeto Loan que se va a convertir.
     * @return Un objeto LoanPostgres que representa el préstamo.
     */
    public LoanPostgres loanToLoanPostgres(Loan loan) {
        LoanPostgres loanPostgres = new LoanPostgres();
        LoanPostgresId loanPostgresId = new LoanPostgresId();
        loanPostgresId.setIdUser((loan.getIdUser() != null && !loan.getIdUser().isEmpty())
                ? Long.parseLong(loan.getIdUser()) : null);
        loanPostgresId.setIdBook((loan.getIdBook() != null && !loan.getIdBook().isEmpty())
                ? Long.parseLong(loan.getIdBook()) : null);
        loanPostgres.setIdLoan(loanPostgresId);
        loanPostgres.setLoanDate(loan.getLoanDate());
        loanPostgres.setReturnDate(loan.getReturnDate());
        return loanPostgres;
    }

    /**
     * Convierte un objeto LoanPostgres a un objeto Loan.
     *
     * @param loanPostgres El objeto LoanPostgres que se va a convertir.
     * @return Un objeto Loan que representa el préstamo.
     */
    public Loan loanPostgresToLoan(LoanPostgres loanPostgres) {
        Loan loan = new Loan();
        loan.setIdUser(String.valueOf(loanPostgres.getIdLoan().getIdUser()));
        loan.setIdBook(String.valueOf(loanPostgres.getIdLoan().getIdBook()));
        loan.setLoanDate(loanPostgres.getLoanDate());
        loan.setReturnDate(loanPostgres.getReturnDate());
        return loan;
    }

    /**
     * Convierte un objeto Loan a un objeto LoanMongo.
     *
     * @param loan El objeto Loan que se va a convertir.
     * @return Un objeto LoanMongo que representa el préstamo.
     */
    public LoanMongo loanToLoanMongo(Loan loan) {
        LoanMongo loanMongo = new LoanMongo();
        LoanMongoId loanMongoId = new LoanMongoId();
        loanMongoId.setIdBook(loan.getIdBook());
        loanMongoId.setIdUser(loan.getIdUser());
        loanMongo.setIdLoan(loanMongoId);
        loanMongo.setLoanDate(loan.getLoanDate());
        loanMongo.setReturnDate(loan.getReturnDate());
        return loanMongo;
    }

    /**
     * Convierte un objeto LoanMongo a un objeto Loan.
     *
     * @param loanMongo El objeto LoanMongo que se va a convertir.
     * @return Un objeto Loan que representa el préstamo.
     */
    public Loan loanMongoToLoan(LoanMongo loanMongo) {
        Loan loan = new Loan();
        loan.setIdUser(loanMongo.getIdLoan().getIdUser());
        loan.setIdBook(loanMongo.getIdLoan().getIdBook());
        loan.setLoanDate(loanMongo.getLoanDate());
        loan.setReturnDate(loanMongo.getReturnDate());
        return loan;
    }
}
