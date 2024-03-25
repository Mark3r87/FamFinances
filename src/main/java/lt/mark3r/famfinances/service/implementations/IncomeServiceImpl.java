package lt.mark3r.famfinances.service.implementations;

import lt.mark3r.famfinances.models.dto.TransactionDTO;
import lt.mark3r.famfinances.models.entities.Category;
import lt.mark3r.famfinances.models.entities.FinTransactions;
import lt.mark3r.famfinances.repository.TransactionsRepository;
import lt.mark3r.famfinances.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class IncomeServiceImpl implements IncomeService {
	@Autowired
	private TransactionsRepository trRepository;
	@Autowired
	private TransactionServiceImpl transactionService;

	@Override
	public List<FinTransactions> getAllIncome() {
		return trRepository.findByCategory(Category.INCOME);
	}

	@Override
	public List<FinTransactions> getIncomeByMonth(int month, int year) {
		List<FinTransactions> transactions = trRepository.findTransactionsByMonthAndCategory(month, year, Category.INCOME);
		return Objects.requireNonNullElse(transactions, Collections.emptyList());
	}

	@Override
	public List<TransactionDTO> getAllIncomeDTO() {
		return transactionService.getTransactionsDTOByCategory(Category.INCOME);
	}


}
