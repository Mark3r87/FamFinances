package lt.mark3r.famfinances.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class FinTransactions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	LocalDateTime timestamp;
	@Enumerated(EnumType.STRING)
	Category category;
	String name;
	Double amount;
	String description;
	@Column
	LocalDateTime dateTime;

	public FinTransactions() {
	}

	public FinTransactions(Category category, String name, Double amount, String description, LocalDateTime dateTime) {
		this.category = category;
		this.name = name;
		this.amount = amount;
		this.description = description;
		this.dateTime = dateTime;
	}

	@PrePersist
	public void prePersist() {
		if (timestamp == null) {
			timestamp = LocalDateTime.now();
		}
	}

	@PreUpdate
	public void preUpdate() {
		timestamp = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "Transactions{" +
				"id=" + id +
				", timestamp=" + timestamp +
				", category=" + category +
				", name='" + name + '\'' +
				", amount=" + amount +
				", description='" + description + '\'' +
				", dateTime=" + dateTime +
				'}';
	}
}
