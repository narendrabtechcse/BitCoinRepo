package candidatetask;

public class ErrorHandler {
	
	void incorrectCurrencyInput(String currency) {
		if( !(currency.equals("EUR") || currency.equals("USD") || currency.equals("GBP")) ) {
			System.out.println("Incorrect currency code entered !! ");
			System.out.println();
		    System.out.println("Please re-run the program and enter the correct country code like EUR,USD,GBP !! ");
		    System.exit(0);
		}
	}

}
