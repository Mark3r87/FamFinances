package lt.mark3r.famfinances.service;

import lt.mark3r.famfinances.models.dto.TransactionDTO;
import lt.mark3r.famfinances.models.entities.FinTransactions;

import java.util.List;

public interface TransactionService {
	FinTransactions saveTransaction(FinTransactions transaction);

	FinTransactions getTransaction(Long id);

	List<FinTransactions> getAllTransactions();

	void deleteTransaction(Long id);

	FinTransactions updateTransaction(Long id, FinTransactions transaction);


	List<FinTransactions> getAllExpenses();

	List<FinTransactions> getExpensesByMonth(int month, int year);

	List<TransactionDTO> getAllExpensesDTO();


	List<TransactionDTO> getAllIncomeDTO();

	List<FinTransactions> getAllIncome();

	List<FinTransactions> getIncomeByMonth(int month, int year);


	Double getAllTimeBalance();

	Double getBalanceByMonth(int month, int year);


}
