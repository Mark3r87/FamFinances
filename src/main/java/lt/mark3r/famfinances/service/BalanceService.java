package lt.mark3r.famfinances.service;

public interface BalanceService {
	Double getBalanceByMonth(int month, int year);

	Double getAllTimeBalance();

}
