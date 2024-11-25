// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) {
		int sum = x1;
		if (x2 >= 0) { 
			for (int i = 0; i < x2; i++) { //sum = x1 + x2
				sum ++; 
			}
		}
		else {
			for (int i = 0; i > x2; i--) { //sum = x1 - x2 , x2 < 0
				sum --;
			}
		}
		return sum;
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		int sum = x1;
		if (x2 >= 0) {
			for (int i = 0; i < x2; i++) { //sum = x1 - x2
				sum --;
			}
		}
		else {
			for (int i = 0; i > x2; i--) { //sum = x1 + x2 , x2 < 0
				sum ++;
			}
		}
		return sum;
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) {
		if (x1 == 0 || x2 == 0) // x1 and or x2 = 0
			return 0;
		int sum = 0;
		if (((x1 < 0) && (x2 < 0)) || ((x1 > 0) && (x2 > 0))) { //sum > 0
			if (x2 > 0) {
				for (int i = 0; i < x2; i++) { //x1 , x2 > 0
					sum = plus(sum, x1); //a function previously made
				}
			}
			else {
				for (int i = 0; i > x2; i--) { //x1, x2 < 0
					sum = minus(sum, x1); //a function previously made
				}
			}
		}
		else { //sum < 0
			if (x2 > 0) {
				for (int i = 0; i < x2; i++) { //x1 < 0 , x2 > 0
					sum = plus(sum, x1); //a function previously made
				}
			}
			else {
				for (int i = 0; i > x2; i--) { //x1 > 0 , x2 < 0
					sum = minus(sum, x1); //a function previously made
				}
			}
		}
		return sum;
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		if (n == 0) // x pow n = 1
			return 1;
		if (n < 0) { //just in case
			System.out.print("this function doesn't deal with n = ");
			return 0;
		}
		int sum = x;
		for (int i = 1; i < n; i++) { //sum = x1 pow x2
			sum = times(sum, x); //a function previously made
		}
		return sum;
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {
        if (x2 == 0) { // x1 / 0 = invalid
			System.out.print("invalid: can't divide by ");
			return 0;
		}
		if (x1 == 0) // 0 / x2 = 0
			return 0;

		int i = 1;
		int sum = x2;
		boolean reach = false;
		while (! reach) {
			if (sum == x1 || sum == -x1) //x1 / x2 is an int
				reach = true;
			else {
				if ((sum > x1) && (x1 > 0) || (sum < x1) && (x1 < 0)) { //x1 / x2 is not an int
					i --;
					reach = true;
				}
				else { //loop continues
					sum = plus(sum, x2); //a function previously made
					i ++;
				}
			}
		}
		if (((x1 < 0) && (x2 < 0)) || ((x1 > 0) && (x2 > 0))) //x1 / x2 > 0
			return i;
		else //x1 / x2 < 0
			return -i;
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		if (x2 == 0) { //x1 % 0 = invalid
			System.out.print("invalid: can't mod by ");
			return 0;
		}
		int divi;
		int multi;
		int sum;
        if (x1 < 0) { //x1 % x2 < 0
			if (x2 < 0) { //x1 , x2 < 0
				divi = div(x1, x2); //a function previously made
				multi = times(divi, -x2); //a function previously made
				sum = minus(-x1, multi); //a function previously made
			}
			else { //x1 < 0, x2 > 0
				divi = div(-x1, x2); //a function previously made
				multi = times(divi, x2); //a function previously made
				sum = minus(-x1, multi); //a function previously made
			}
			return -sum;
		}
		else { //x1 % x2 > 0
			if (x2 < 0) { //x1 > 0, x2 < 0
				divi = div(x1, -x2); //a function previously made
				multi = times(divi, -x2); //a function previously made
				sum = minus(x1, multi); //a function previously made
			}
			else { //x1 , x2 > 0
				divi = div(x1, x2); //a function previously made
				multi = times(divi, x2); //a function previously made
				sum = minus(x1, multi); //a function previously made
			}
			return sum;
		}
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
		if (x < 0) { //the sqrt of x (x < 0) = invalid
			System.out.print("invalid: can't sqrt ");
			return x;
		}
		boolean isFound = false;
		int i = 0;
		int thisSum;
		int nextSum;
		while (!isFound) {
			thisSum = pow(i, 2); //a function previously made
			nextSum = pow(i+1, 2); //a function previously made
			if ((thisSum == x) || (thisSum < x) && (nextSum > x)) //x equals or is between i pow 2
				isFound = true;
			else
				i ++;
		}
		return i;
	}	  	  
}