package naive_primality_test;

import java.math.BigInteger;

public class naive_primality_test {	
	
	
	public static BigInteger zero  = new BigInteger("0");
	public static BigInteger one   = new BigInteger("1");
	public static BigInteger two   = new BigInteger("2");
	public static BigInteger three = new BigInteger("3");

	public static void main(String[] args) {
		
		
			//9181531581341931811 seems to be the biggest prime that can be represented in Java.
			//It takes 2 minutes and 3 seconds to determine if it's prime on my computer.
		
			BigInteger numToCheck=new BigInteger("9181531581341931811");
			BigInteger fctrStub=numToCheck;
			BigInteger sqrtFactor=numToCheck.sqrt();
			BigInteger j=new BigInteger("0");
			
			boolean composite=false;
			
			
			//the condition is j is less than or equal to square root of the number to be factored.
			//It just checks to see if the number is divisible by every number up to its square root.
			//If you 100% want to check to see if a number or a factor of a number is prime, just
			//put the number into the numToCheck variable above.
			
			long startTime = System.nanoTime();//times the start of the main algorithm
			
			if(numToCheck.mod(two).equals(zero)) composite=true; //checks numToCheck is even
			
			if(!composite) {//The loop below will run only if numToCheck is odd
			
				for(j = three; j.compareTo(sqrtFactor)==-1 || j.equals(sqrtFactor); j=j.add(two)) {
				
					if (fctrStub.mod(j).equals(zero)) {					
						composite=true;
						break;
					}//end if block to test for prime factor
				}//end for loop
			}//end if block to test for oddness
			
			long endTime = System.nanoTime();//marks the end time of the loop
			
	        long duration = (endTime - startTime)/1000000;//gives milliseconds
			
			if(composite) {
				System.out.println(numToCheck + " is composite.\n");
			}
			else {
				System.out.println(numToCheck + " is prime.\n");
			}
			
			System.out.println("Main algorithm took " + duration + " milliseconds.");
			System.out.println("Main algorithm took " + duration/1000 + " seconds.");
			System.out.println("Main algorithm took " + duration/60000 + " minutes.");
		}

}
