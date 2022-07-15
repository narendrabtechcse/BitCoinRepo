package candidatetask;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

public class Main {
	final static Logger logger = Logger.getLogger(Main.class);
	static ErrorHandler errorHandler = new ErrorHandler();

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		System.out.println(" ########### WELCOME TO THE BITCOIN WORLD ############## ");

		System.out.println("Please enter the Currency from one of them EUR,USD,GBP : ");

		Scanner scannerUserInput = new Scanner(System.in);

		String currency = scannerUserInput.next();

		errorHandler.incorrectCurrencyInput(currency);

		String currentPriceOfBitCoin = BitCoinCur.getCurrentPriceOfBC(currency);
		
		System.out.println();

		System.out.println("Current price of the Bitcoin in the " + currency + " is : " + currentPriceOfBitCoin);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate now = LocalDate.now();

		String currDate = dtf.format(now);

		System.out.println();
		
		System.out.println("Today date : " + dtf.format(now));

		LocalDate date30DaysBack = LocalDate.now().minusDays(30);

		String date30DaysOld = dtf.format(date30DaysBack);

		List<Object> list = BitCoinRates.get30DaysBackPrices(currency, currDate, date30DaysOld);
		
		List<Object> sortedList = list.stream()
		        .sorted()
		        .collect(Collectors.toList());

		//Collections.sort(list);

		Double minValue = (Double) sortedList.get(0);

		Double maxValue = (Double) sortedList.get(sortedList.size() - 1);

		System.out.println();
		System.out.println();

		System.out.println("The Minimum Value in last 30 Days was  :  " + minValue);

		System.out.println();
		
		System.out.println("The Maximum Value in last 30 Days was  :  " + maxValue);
	}
}
