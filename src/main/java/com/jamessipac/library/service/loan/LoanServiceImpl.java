package com.jamessipac.library.service.loan;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.jamessipac.library.bo.Loan;
import com.jamessipac.library.bo.User;
import com.jamessipac.library.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;

    private static final String LOAN_NOT_FOUND = "Loan not found of the user with the ID %s and the book with ID %s";

    @Value("${spring.profiles.active}")
    private String profile;

    @Override
    public Loan createLoan(String idBook, Loan loan) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String idUser = ((User) userDetails).getId();
        loan.setIdUser(idUser);
        loan.setIdBook(idBook);
        validateIdFormat(idBook);
        return loanRepository.createLoan(loan);
    }

    @Override
    public List<Loan> getLoans() {
        return loanRepository.getLoans();
    }

    @Override
    public Loan findLoanById(String idBook) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String idUser = ((User) userDetails).getId();
        validateIdFormat(idBook);
        return loanRepository.findLoanById(idUser, idBook)
                .orElseThrow(() -> new EntityNotFoundException(String.format(LOAN_NOT_FOUND, idUser, idBook)));
    }

    @Override
    public Loan updateLoan(String idBook, Loan loan) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String idUser = ((User) userDetails).getId();
        validateIdFormat(idBook);
        Loan existingLoan = loanRepository.findLoanById(idUser, idBook)
                .orElseThrow(() -> new EntityNotFoundException(String.format(LOAN_NOT_FOUND, idUser, idBook)));

        // Actualiza los campos necesarios del préstamo
        existingLoan.setLoanDate(loan.getLoanDate());
        existingLoan.setReturnDate(loan.getReturnDate());
        return loanRepository.updateLoan(existingLoan);
    }

    @Override
    public void deleteLoan(String idBook) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String idUser = ((User) userDetails).getId();
        validateIdFormat(idBook);
        loanRepository.findLoanById(idUser, idBook)
                .orElseThrow(() -> new EntityNotFoundException(String.format(LOAN_NOT_FOUND, idUser, idBook)));
        loanRepository.deleteLoan(idUser, idBook);
    }

    /**
     * Valida el formato del ID del libro según el perfil activo.
     *
     * @param idBook ID del libro a validar.
     * @throws IllegalArgumentException si el formato no es válido.
     */
    private void validateIdFormat(String idBook) {
        if (profile.equals("postgres")) {
            try {
                Long.parseLong(idBook);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid idBook format for Postgres: " + idBook);
            }
        }
    }
}
