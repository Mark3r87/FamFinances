package lt.mark3r.famfinances.service.implementations;

import lt.mark3r.famfinances.models.dto.TransactionDTO;
import lt.mark3r.famfinances.models.entities.Category;
import lt.mark3r.famfinances.models.entities.FinTransactions;
import lt.mark3r.famfinances.repository.TransactionsRepository;
import lt.mark3r.famfinances.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionsRepository trRepository;

	@Override
	public FinTransactions saveTransaction(FinTransactions transaction) {
		return trRepository.save(transaction);
	}

	@Override
	public FinTransactions getTransaction(Long id) {
		Optional<FinTransactions> optionalTransaction = trRepository.findById(id);
		return optionalTransaction.orElse(null);
	}

	@Override
	public List<FinTransactions> getAllTransactions() {
		return trRepository.findAll();
	}

	@Override
	public void deleteTransaction(Long id) {
		trRepository.deleteById(id);
	}


	@Override
	public FinTransactions updateTransaction(Long id, FinTransactions transaction) {
		Optional<FinTransactions> optionalTransaction = trRepository.findById(id);
		if (optionalTransaction.isPresent()) {
			FinTransactions existingTransaction = optionalTransaction.get();
			if (transaction.getTimestamp() == null) {
				transaction.setTimestamp(existingTransaction.getTimestamp());
			}
			existingTransaction.setCategory(transaction.getCategory());
			existingTransaction.setName(transaction.getName());
			existingTransaction.setAmount(transaction.getAmount());
			existingTransaction.setDescription(transaction.getDescription());
			existingTransaction.setDateTime(transaction.getDateTime());
			return trRepository.save(existingTransaction);
		} else {
			return null;
		}
	}

	List<TransactionDTO> getTransactionsDTOByCategory(Category category) {
		List<FinTransactions> transactions = trRepository.findByCategory(category);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return transactions.stream()
				.map(transaction -> {
					TransactionDTO dto = new TransactionDTO();
					dto.setId(transaction.getId());
					dto.setName(transaction.getName());
					dto.setAmount(transaction.getAmount());
					dto.setCategory(transaction.getCategory());
					dto.setDescription(transaction.getDescription());
					if (transaction.getDateTime() != null) {
						dto.setDateTime(transaction.getDateTime().format(formatter));
					}
					return dto;
				})
				.collect(Collectors.toList());
	}


}
