package lt.mark3r.famfinances.service;

import lt.mark3r.famfinances.models.dto.TransactionDTO;
import lt.mark3r.famfinances.models.entities.FinTransactions;

import java.util.List;

public interface IncomeService {
	List<TransactionDTO> getAllIncomeDTO();

	List<FinTransactions> getAllIncome();

	List<FinTransactions> getIncomeByMonth(int month, int year);
}
