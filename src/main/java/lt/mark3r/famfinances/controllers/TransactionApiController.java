package lt.mark3r.famfinances.controllers;

import lt.mark3r.famfinances.models.dto.TransactionDTO;
import lt.mark3r.famfinances.models.dto.mapper.TransactionMapper;
import lt.mark3r.famfinances.models.entities.FinTransactions;
import lt.mark3r.famfinances.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/transactions")
public class TransactionApiController {

	private final TransactionService transactionService;

	@Autowired
	public TransactionApiController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@GetMapping
	public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
		List<FinTransactions> transactions = transactionService.getAllTransactions();
		List<TransactionDTO> transactionDTOs = transactions.stream()
				.map(TransactionMapper::convertToDTO)
				.collect(Collectors.toList());
		return ResponseEntity.ok(transactionDTOs);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TransactionDTO> getTransaction(@PathVariable Long id) {
		FinTransactions transaction = transactionService.getTransaction(id);
		if (transaction == null) {
			return ResponseEntity.notFound().build();
		}
		TransactionDTO dto = TransactionMapper.convertToDTO(transaction);
		return ResponseEntity.ok(dto);
	}

	@PostMapping
	public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO dto) {
		FinTransactions transaction = TransactionMapper.convertToEntity(dto);
		transaction = transactionService.saveTransaction(transaction);
		dto = TransactionMapper.convertToDTO(transaction);
		return ResponseEntity.ok(dto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TransactionDTO> updateTransaction(@PathVariable Long id, @RequestBody TransactionDTO dto) {
		FinTransactions transaction = TransactionMapper.convertToEntity(dto);
		transaction = transactionService.updateTransaction(id, transaction);
		if (transaction == null) {
			return ResponseEntity.notFound().build();
		}
		dto = TransactionMapper.convertToDTO(transaction);
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
		transactionService.deleteTransaction(id);
		return ResponseEntity.noContent().build();
	}


	@GetMapping("/income")
	public ResponseEntity<List<TransactionDTO>> getAllIncome() {
		List<FinTransactions> transactions = transactionService.getAllIncome();
		List<TransactionDTO> dtos = transactions.stream().map(TransactionMapper::convertToDTO).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/expenses")
	public ResponseEntity<List<TransactionDTO>> getAllExpenses() {
		List<FinTransactions> transactions = transactionService.getAllExpenses();
		List<TransactionDTO> dtos = transactions.stream().map(TransactionMapper::convertToDTO).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/expenses/{month}/{year}")
	public ResponseEntity<List<TransactionDTO>> getExpensesByMonth(@PathVariable int month, @PathVariable int year) {
		List<FinTransactions> transactions = transactionService.getExpensesByMonth(month, year);
		List<TransactionDTO> dtos = transactions.stream().map(TransactionMapper::convertToDTO).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/income/{month}/{year}")
	public ResponseEntity<List<TransactionDTO>> getIncomeByMonth(@PathVariable int month, @PathVariable int year) {
		List<FinTransactions> transactions = transactionService.getIncomeByMonth(month, year);
		List<TransactionDTO> dtos = transactions.stream().map(TransactionMapper::convertToDTO).collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/balance/all-time")
	public ResponseEntity<Double> getAllTimeBalance() {
		Double balance = transactionService.getAllTimeBalance();
		return ResponseEntity.ok(balance);
	}

	@GetMapping("/balance/{month}/{year}")
	public ResponseEntity<Double> getBalanceByMonth(@PathVariable int month, @PathVariable int year) {
		Double balance = transactionService.getBalanceByMonth(month, year);
		return ResponseEntity.ok(balance);
	}

}

