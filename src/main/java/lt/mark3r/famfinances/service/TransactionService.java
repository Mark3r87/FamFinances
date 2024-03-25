package lt.mark3r.famfinances.service;

import lt.mark3r.famfinances.models.entities.FinTransactions;

import java.util.List;

public interface TransactionService {
	FinTransactions saveTransaction(FinTransactions transaction);

	FinTransactions getTransaction(Long id);

	List<FinTransactions> getAllTransactions();

	void deleteTransaction(Long id);

	FinTransactions updateTransaction(Long id, FinTransactions transaction);


}
