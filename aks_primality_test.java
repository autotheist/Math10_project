package aks_primality_test;

public class aks_primality_test {
	
	//***Note: Emily didn't write this algorithm. I just got it off the web to time it.
	//It takes slightly longer than the primality test I did where I chose 2 for a but I think
	//it does more. I'm not exactly sure how it works. I know it checks to see if all the 
	//coefficients of an expanded polynomial are divisible by n. I edited it to test all the numbers up to 63.

	// Java code to check if number is prime. This
	// program demonstrates concept behind AKS
	// algorithm and doesn't implement the actual
	// algorithm (This works only till n = 64)

	
		static long c[] = new long[100];

		// function to calculate the coefficients
		// of (x - 1)^n - (x^n - 1) with the help
		// of Pascal's triangle .
		static void coef(int n)
		{
			c[0] = 1;
			for (int i = 0; i < n; c[0] = -c[0], i++) {
				c[1 + i] = 1;

				for (int j = i; j > 0; j--)
					c[j] = c[j - 1] - c[j];
			}
		}

		// function to check whether
		// the number is prime or not
		static boolean isPrime(int n)
		{
			// Calculating all the coefficients by
			// the function coef and storing all
			// the coefficients in c array .
			coef(n);

			// subtracting c[n] and adding c[0] by 1
			// as ( x - 1 )^n - ( x^n - 1), here we
			// are subtracting c[n] by 1 and adding
			// 1 in expression.
			c[0]++;
			c[n]--;

			// checking all the coefficients whether
			// they are divisible by n or not.
			// if n is not prime, then loop breaks
			// and (i > 0).
			int i = n;
			while ((i--) > 0 && c[i] % n == 0)
				;

			// Return true if all coefficients are
			// divisible by n.
			return i < 0;
		}
		// Driver code
		public static void main(String[] args)
		{
			long startTime = System.nanoTime();
			for(int i=3; i < 64; i++) {
				if (isPrime(i)) {
					System.out.println(i + "\tis Prime");
				}
				else {
					System.out.println(i + "\tis Not Prime");
				}
				
			}			  
			
			long endTime = System.nanoTime();
		    	long duration = (endTime - startTime)/1000;
			
			System.out.println("\nMicroseconds: " + duration );
		}//end driver
	

	// This code is contributed by Anant Agarwal.


}
