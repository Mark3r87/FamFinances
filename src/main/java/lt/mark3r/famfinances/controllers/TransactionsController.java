package lt.mark3r.famfinances.controllers;

import lt.mark3r.famfinances.models.dto.TransactionDTO;
import lt.mark3r.famfinances.models.dto.mapper.TransactionMapper;
import lt.mark3r.famfinances.models.entities.FinTransactions;
import lt.mark3r.famfinances.service.BalanceService;
import lt.mark3r.famfinances.service.ExpensesService;
import lt.mark3r.famfinances.service.IncomeService;
import lt.mark3r.famfinances.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TransactionsController {

	private final TransactionService transactionService;
	private final BalanceService balanceService;
	private final IncomeService incomeService;
	private final ExpensesService expensesService;

	@Autowired
	public TransactionsController(TransactionService transactionService, BalanceService balanceService, IncomeService incomeService, ExpensesService expensesService) {
		this.transactionService = transactionService;
		this.balanceService = balanceService;
		this.incomeService = incomeService;
		this.expensesService = expensesService;
	}

	@GetMapping("/dataEntryForm")
	public String dataEntryForm(Model model) {
		model.addAttribute("transaction", new TransactionDTO());
		return "dataEntryForm";
	}

	@GetMapping("/transactions")
	public String getAllTransactions(Model model) {
		List<FinTransactions> transactions = transactionService.getAllTransactions();
		List<TransactionDTO> transactionDTOs = transactions.stream()
				.map(TransactionMapper::convertToDTO)
				.collect(Collectors.toList());
		model.addAttribute("transactions", transactionDTOs);
		return "transactionList";
	}

	@GetMapping("/transactions/{id}")
	public String getTransaction(@PathVariable Long id, Model model) {
		FinTransactions transaction = transactionService.getTransaction(id);
		TransactionDTO dto = TransactionMapper.convertToDTO(transaction);
		model.addAttribute("transaction", dto);
		return "transactionDetails";
	}

	@PostMapping("/transactions")
	public String createTransaction(@ModelAttribute("transaction") TransactionDTO dto) {
		FinTransactions transaction = TransactionMapper.convertToEntity(dto);
		transactionService.saveTransaction(transaction);
		return "redirect:/transactions";
	}


	@PutMapping("/transactions/{id}")
	public String updateTransaction(@PathVariable Long id, @ModelAttribute TransactionDTO dto) {
		FinTransactions transaction = TransactionMapper.convertToEntity(dto);
		transactionService.updateTransaction(id, transaction);
		return "redirect:/transactions";
	}

	@DeleteMapping("/transactions/{id}")
	public String deleteTransaction(@PathVariable Long id) {
		transactionService.deleteTransaction(id);
		return "redirect:/transactions";
	}

	@GetMapping("/transactions/edit/{id}")
	public String editTransactionForm(@PathVariable Long id, Model model) {
		FinTransactions transaction = transactionService.getTransaction(id);
		if (transaction == null) {
			return "redirect:/transactions"; // Or some error page
		}
		TransactionDTO dto = TransactionMapper.convertToDTO(transaction);
		model.addAttribute("transaction", dto);
		return "transactionEditForm";
	}

	@GetMapping("/expenses")
	public String getAllExpenses(Model model) {
		List<TransactionDTO> dtos = expensesService.getAllExpensesDTO(); // Updated service call
		model.addAttribute("transactions", dtos);
		return "expensesList";
	}


	@GetMapping("/expenses/{month}/{year}")
	public String getExpensesByMonth(@PathVariable int month, @PathVariable int year, Model model) {
		List<FinTransactions> transactions = expensesService.getExpensesByMonth(month, year);
		List<TransactionDTO> dtos = transactions.stream()
				.map(TransactionMapper::convertToDTO)
				.collect(Collectors.toList());
		model.addAttribute("transactions", dtos);
		return "expensesListByMonth";
	}

	@GetMapping("/income")
	public String getAllIncome(Model model) {
		List<TransactionDTO> dtos = incomeService.getAllIncomeDTO(); // This method will need to be implemented in your service layer
		model.addAttribute("transactions", dtos);
		return "incomeList";
	}

	@GetMapping("/income/{month}/{year}")
	public String getIncomeByMonth(@PathVariable int month, @PathVariable int year, Model model) {
		List<FinTransactions> transactions = incomeService.getIncomeByMonth(month, year);
		List<TransactionDTO> dtos = transactions.stream()
				.map(TransactionMapper::convertToDTO)
				.collect(Collectors.toList());
		model.addAttribute("transactions", dtos);
		return "incomeListByMonth";
	}

	@GetMapping("/balance/all-time")
	public String getAllTimeBalance(Model model) {
		Double balance = balanceService.getAllTimeBalance();
		model.addAttribute("balance", balance);
		return "allTimeBalance";
	}

	@GetMapping("/balance/{month}/{year}")
	public String getBalanceByMonth(@PathVariable int month, @PathVariable int year, Model model) {
		Double balance = balanceService.getBalanceByMonth(month, year);
		model.addAttribute("balance", balance);
		return "monthlyBalance";
	}

}


