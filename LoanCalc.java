// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the ending balance of the loan, given a periodical payment
		double payment = 10000;
		double endBalance = endBalance(loan, rate, n, payment);
		//System.out.println("If your periodical payment is " + payment + ", your ending balance is: " + (int) endBalance);
	
		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter); 
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {	
		double balance = loan;
		double fixedRate = 1 + (rate * 0.01); //changes the display of rate
		for (int i = 0; i < n; i++) {
			balance = (balance - payment) * fixedRate; //does this function n times
		}
		return Math.round(balance); //rounds the final balance
	}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {  
    	iterationCounter = 0;
		if (n == 0) {
			System.out.println("enter different n");
			return loan;
		}
		double g = loan / n;
		double fixedPayment = endBalance(loan, rate, n, g);
		while (Math.abs(fixedPayment) >= epsilon) {
			if (fixedPayment > 0)
				g = g + epsilon;
			else
				g = g - epsilon;
			fixedPayment = endBalance(loan, rate, n, g);
			iterationCounter ++;
		}
		return g;
    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
        iterationCounter = 0;
		double H = loan;
		if (n == 0) {
			System.out.println("enter different n");
			return loan;
		}
		double L = loan / n;
		double g = (L + H) / 2;
		double fixedPayment = endBalance(loan, rate, n, g);
		while (Math.abs(H - L) > epsilon) {
			if (fixedPayment * endBalance(loan, rate, n, L) > 0)
				L = g;
			else
				H = g;
			iterationCounter ++;
			g = (L + H) / 2;
			fixedPayment = endBalance(loan, rate, n, g);
		}
		return g;
    }
}