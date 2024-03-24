package lt.mark3r.famfinances.models.dto.mapper;

import lt.mark3r.famfinances.models.dto.TransactionDTO;
import lt.mark3r.famfinances.models.entities.FinTransactions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionMapper {

	public static TransactionDTO convertToDTO(FinTransactions transaction) {
		TransactionDTO dto = new TransactionDTO();
		dto.setId(transaction.getId());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd : HH:mm:ss");
		if (transaction.getTimestamp() != null) {
			dto.setTimestamp(transaction.getTimestamp().format(formatter));
		} else {
			dto.setTimestamp(null);
		}
		dto.setCategory(transaction.getCategory());
		dto.setName(transaction.getName());
		dto.setAmount(transaction.getAmount());
		dto.setDescription(transaction.getDescription());
		if (transaction.getDateTime() != null) {
			dto.setDateTime(transaction.getDateTime().format(formatter));
		} else {
			dto.setDateTime(null);
		}
		return dto;
	}


	public static FinTransactions convertToEntity(TransactionDTO dto) {
		FinTransactions transaction = new FinTransactions();
		transaction.setId(dto.getId());
		transaction.setName(dto.getName());
		transaction.setAmount(dto.getAmount());
		transaction.setCategory(dto.getCategory());
		transaction.setDescription(dto.getDescription());
		transaction.setDateTime(LocalDateTime.parse(dto.getDateTime()));
		return transaction;
	}
}
