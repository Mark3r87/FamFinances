package lt.mark3r.famfinances.models.dto;

import lt.mark3r.famfinances.models.entities.Category;


public class TransactionDTO {
	private Long id;
	private String timestamp;
	private Category category;
	private String name;
	private Double amount;
	private String description;
	private String dateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
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

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "TransactionDTO{" +
				"id=" + id +
				", timestamp='" + timestamp + '\'' +
				", category=" + category +
				", name='" + name + '\'' +
				", amount=" + amount +
				", description='" + description + '\'' +
				", dateTime='" + dateTime + '\'' +
				'}';
	}
}
