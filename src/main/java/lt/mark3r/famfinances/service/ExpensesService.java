package lt.mark3r.famfinances.service;

import lt.mark3r.famfinances.models.dto.TransactionDTO;
import lt.mark3r.famfinances.models.entities.FinTransactions;

import java.util.List;

public interface ExpensesService {
	List<FinTransactions> getAllExpenses();

	List<FinTransactions> getExpensesByMonth(int month, int year);

	List<TransactionDTO> getAllExpensesDTO();
}
