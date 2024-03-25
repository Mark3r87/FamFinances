package lt.mark3r.famfinances.service.implementations;

import lt.mark3r.famfinances.models.entities.Category;
import lt.mark3r.famfinances.repository.TransactionsRepository;
import lt.mark3r.famfinances.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImpl implements BalanceService {
	@Autowired
	private TransactionsRepository trRepository;

	@Override
	public Double getAllTimeBalance() {
		Double income = trRepository.sumAmountByCategory(Category.INCOME);
		Double expenses = trRepository.sumAmountByCategory(Category.EXPENSES);
		return (income != null ? income : 0) - (expenses != null ? expenses : 0);
	}

	@Override
	public Double getBalanceByMonth(int month, int year) {
		Double income = trRepository.sumAmountByCategoryAndMonth(Category.INCOME, month, year);
		Double expenses = trRepository.sumAmountByCategoryAndMonth(Category.EXPENSES, month, year);
		return (income != null ? income : 0) - (expenses != null ? expenses : 0);
	}
}
