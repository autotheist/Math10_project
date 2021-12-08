package primality_test_mod;

public class primality_test_mod {

	//This algorithm I came up with after looking at the formula that the AKS algorithm is based on.
	//The formula given was the following: n is prime iff (x+a)^n is congruent to (x^n +a)mod n 
	//for some n > 2 and a coprime to n. I chose some examples and found that if n was prime, 
	//a^(n-1)(mod n)=n.
	//
	//I used 2 as a. I didn't care if 2 wasn't coprime to even numbers since they're composite anyway.
	//I thought I had stumbled upon some great secret of the primes heretofore unknown to mathematics.
	//Upon further investigation and trying to understand the theorem the AKS algorithm is based on,
	//I realized my great "discovery" was actually Fermat's little theorem and that 2 could fail as a 
	//useful a at some point. It just didn't fail in this test because my computer can only calculate
	//up to 2^63. This works for all numbers less than 64 though. It's very fast too. This tests all
	//numbers up to 64 because it doesn't take that much more time to do so. It's still in the microsecond
	//range.
	
			public static void main(String[] args)
			{
				long startTime = System.nanoTime();
				for (int i=3; i<=63; i++) {
					System.out.print(i + " is ");
					
					if (Math.pow(2, i-1)%i==1) {
						  
						System.out.println("\tPrime");
					}
					
					else {
						System.out.println("\tNot Prime");
					}
				}
				
				long endTime = System.nanoTime();
			    long duration = (endTime - startTime)/1000;

				
				System.out.println("\nMicroseconds: " + duration );
			}
		


	

}
