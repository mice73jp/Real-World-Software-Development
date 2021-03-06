package org.minseok.rwsd.chap02.requirement03;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionAnalyzerSimple {
	private static final String RESOURCE = "src/main/resources/"; 
	
	public static void main(String[] args) throws IOException {
		final BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();

		final String filename = args[0];
		final Path path = Paths.get(RESOURCE + filename);
		final List<String> lines = Files.readAllLines(path);

		final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFromCSV(lines);
		
		System.out.println("The total for all transaction is " + calculateTotalAmount(bankTransactions));
		System.out.println("Transaction in January " + selectInMonth(bankTransactions, Month.JANUARY));
	}
	
	public static double calculateTotalAmount(List<BankTransaction> bankTransactions) {
		double total = 0d;
		for(final BankTransaction bankTransaction: bankTransactions ) {
			total += bankTransaction.getAmount();
		}
		
		return total;
	}
	
	public static List<BankTransaction> selectInMonth(final List<BankTransaction> bankTransactions, final Month month) {
		final List<BankTransaction> bankTransactionsInMonth = new ArrayList<>();
		for(final BankTransaction bankTransaction: bankTransactions ) {
			if (bankTransaction.getDate().getMonth() == month) {
				bankTransactionsInMonth.add(bankTransaction);
			}
		}
		
		return bankTransactionsInMonth;
	}
}
