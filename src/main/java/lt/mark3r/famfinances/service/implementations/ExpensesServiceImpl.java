package lt.mark3r.famfinances.service.implementations;

import lt.mark3r.famfinances.models.dto.TransactionDTO;
import lt.mark3r.famfinances.models.entities.Category;
import lt.mark3r.famfinances.models.entities.FinTransactions;
import lt.mark3r.famfinances.repository.TransactionsRepository;
import lt.mark3r.famfinances.service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpensesServiceImpl implements ExpensesService {
	@Autowired
	private TransactionsRepository trRepository;
	@Autowired
	private TransactionServiceImpl transactionService;

	@Override
	public List<FinTransactions> getAllExpenses() {
		return trRepository.findByCategory(Category.EXPENSES);
	}

	@Override
	public List<FinTransactions> getExpensesByMonth(int month, int year) {
		return trRepository.findTransactionsByMonthAndCategory(month, year, Category.EXPENSES);
	}


	@Override
	public List<TransactionDTO> getAllExpensesDTO() {
		return transactionService.getTransactionsDTOByCategory(Category.EXPENSES);
	}

}
