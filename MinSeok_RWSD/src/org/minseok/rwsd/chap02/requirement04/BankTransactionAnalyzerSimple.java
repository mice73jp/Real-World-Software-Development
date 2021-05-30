package org.minseok.rwsd.chap02.requirement04;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankTransactionAnalyzerSimple {
	private static final String RESOURCE = "src/main/resources/"; 
	
	public static void main(String[] args) throws IOException {
		final BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();

		final String filename = args[0];
		final Path path = Paths.get(RESOURCE + filename);
		final List<String> lines = Files.readAllLines(path);

		final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFromCSV(lines);
		final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);
		
		collectSummary(bankStatementProcessor);
	}
	
	private static void collectSummary(final BankStatementProcessor bankStatementProcessor) {
		System.out.println("The total for all transactions is "
				+ bankStatementProcessor.calculateTotalAmount());

		System.out.println("The total for transactions in January is "
				+ bankStatementProcessor.selectInMonth(Month.JANUARY));
		
		System.out.println("The total for transactions in February is "
				+ bankStatementProcessor.selectInMonth(Month.FEBRUARY));

		System.out.println("The total salary received is "
				+ bankStatementProcessor.calculateTotalForCategory("Salary"));

	}
}
