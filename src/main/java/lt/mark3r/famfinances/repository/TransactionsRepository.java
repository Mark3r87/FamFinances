package lt.mark3r.famfinances.repository;

import lt.mark3r.famfinances.models.entities.Category;
import lt.mark3r.famfinances.models.entities.FinTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<FinTransactions, Long> {

	List<FinTransactions> findByCategory(Category category);

	@Query("SELECT SUM(t.amount) FROM FinTransactions t WHERE t.category = :category")
	Double sumAmountByCategory(@Param("category") Category category);

	@Query("SELECT t FROM FinTransactions t WHERE MONTH(t.dateTime) = :month AND YEAR(t.dateTime) = :year AND t.category = :category")
	List<FinTransactions> findTransactionsByMonthAndCategory(@Param("month") int month,
	                                                         @Param("year") int year,
	                                                         @Param("category") Category category);

	@Query("SELECT SUM(t.amount) FROM FinTransactions t WHERE FUNCTION('MONTH', t.dateTime) = :month AND FUNCTION('YEAR', t.dateTime) = :year AND t.category = :category")
	Double sumAmountByCategoryAndMonth(@Param("category") Category category,
	                                   @Param("month") int month,
	                                   @Param("year") int year);


}

