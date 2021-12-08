import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

/* This program is designed to factor Integers. It uses trial division to both generate the primes
 * less than the square root of the integer to factor and then factorizes integers if they can be
 * factored. This program also serves as a primality testing algorithm. The lower bound for this
 * algorithm is approximately log_3(n). 
 * 
 * The upper bound is O(1/2 sqrt N) or O(sqrt N). It does not test even values for divisibility. It divides
 * out all factors of 2 on the first pass if there are any. 1/2 is a constant but it does literally
 * cut the time in half which is a lot when dealing with large integers. This program is designed to
 * factorize integers up to 9.2 quintillion. */

public class Big_Integer_Factorizer3 {

	
		public static BigInteger zero  = new BigInteger("0");
		public static BigInteger one   = new BigInteger("1");
		public static BigInteger two   = new BigInteger("2");
		public static BigInteger three = new BigInteger("3");

		public static void main(String[] args) {
			
							 
			    //String numValue="30378687785843359";     	//138980701 * 218582059 - takes 6 seconds
			    //String numValue="10000231782547849";     	//149 * 67115649547301 - takes 1/2 second
			    //String numValue="100000231782547849";    	//2477 * 56843 * 710228359 - takes 40 milliseconds
			    //String numValue="1000000231782547849";   	//7^2 * 139 * 21599 *	6797599541 - takes 61 milliseconds
			    //String numValue="1000000231782547847";   	//20043223 * 49892187089 - takes 1.2 seconds
			    //String numValue="899";			//29 * 31 - takes 0 milliseconds
			    //String numValue="127";			//prime - takes 0 milliseconds
			    //String numValue="1024";			//2^10 - takes 0 milliseconds
			    //String numValue="121330189";		//101 * 103 * 107 * 109 - takes 0 milliseconds
			    //String numValue="67115649547301";		//prime - takes 1/2 second
			    //String numValue = "8512677386048191063"; 	//prime takes 2.5 minutes
			    //String numValue = "8512677386048199600";  //2^4 * 3^2 * 5 * 788210869078537 - takes 1 second
			    //String numValue="9181531581341931811"; 	//prime takes 2 minutes 50 seconds using this algorithm
			    String numValue ="9223372036854775807"; 	//largest Big Integer in Java 7^2 * 73 * 127 * 337 * 92737 * 649657 - takes 50 milliseconds
			
			    //9,181,531,581,341,931,811 seems to be the biggest prime that can be represented in Java.
			    //It takes 2 minutes and 3 seconds to determine if it's prime on my computer with a 
			    //pared-down algorithm that only checks for primality.			    
			    
			
				BigInteger numToCheck=new BigInteger(numValue);
				
				 //The following if block will terminate the program early if the number to be factored
				 //is less than or equal to 3. There is no sense in allocating memory and running through the
				 //whole program since none of it will be used for numbers <= 3.
				
				 //This algorithm is designed to factor numbers larger than 3. It will factor numbers up
				 //to 9.1 quintillion. 9,223,372,036,854,775,807
				
				if(numToCheck.compareTo(three)==-1) {
					System.out.println("You input a number that is too small for to factor."
							+ "\n" + "Numbers less than 1 are not valid to be factored."
							+ "\n" + "1 is only divisible by itself and isn't prime."
							+ "\n" + "2 and 3 are both prime.");
					System.exit(0);
				 }
				
				BigInteger numStub=numToCheck;//this number will be divided down if the number is composite.
				BigInteger sqrtNum=numToCheck.sqrt();
				BigInteger i=new BigInteger("0");
				//i is index of main loop - starts at 3 and increments by 2 so that i is always an 
				//odd number >= 3. i will serve as the next number less than the  square root of the 
				//number to be factored to be tested. i will be tested for primality and added to the list of 
				//factors if it is prime and be generated as a factor of numToFactor if it is a factor. 
				//If i is not prime, it will be passed over.	
				
				BigInteger loopCount=new BigInteger("0");
				
				ArrayList<BigInteger> factors=new ArrayList<BigInteger>();
				
				int factorCounter=0;//This counts total number of factors of numToFactor including repeat factors.
				int repeatFactor=1;//This counts how many times a given factor repeats.
				int printFactor=0;//This keeps track of how many factors have been printed to the console.
				
				
				//the condition is j is less than or equal to square root of the number to be factored.
				//It just checks to see if the number is divisible by every number up to its square root.
				//If you 100% want to check to see if a number or a factor of a number is prime, just
				//put the number into the numToCheck variable above.
				
				long startTime = System.nanoTime();//times the start of the loop
				
				//This while loop will remove all factors of 2 from numStub which is
	 	  		//initialized to numToFactor so only odd numbers can be tested for divisibility.     	         	 
	        		
				while(numStub.mod(two).equals(zero)) {
	        		 	numStub=numStub.divide(two);
	        		 	factors.add(two);
	        		 	
	        		 	if(!numStub.mod(two).equals(zero)) { //This block only executes once all factors of 2 have been found.
	        		 		       		 		
	        		 	sqrtNum=numStub.sqrt();//factors have been divided out so new square root is set.
						
	        		 	}//end if block
	        		 	
	        	 	}//end while loop to find all factors of 2
	        	
	        	/***************/
		        /***MAIN LOOP***/
		        /***************/
				
				for(i = three; i.compareTo(sqrtNum)==-1 || i.equals(sqrtNum) && !numStub.equals(one); i=i.add(two)) {
					
					loopCount=loopCount.add(one);
										 	        	   
		        	   	//This while loop executes if i is prime and a factor of numToFactor. It will
		        	   	//stop when numStub is no longer divisible by i. 
		        	  	while(numStub.mod(i).equals(zero)) {
		        		   
		        			 factors.add(i);
		        			 
		        			 numStub=numStub.divide(i);//divides numStub by i
		        			 
		        			 if(!numStub.mod(i).equals(zero)) {
		        			 	
		        			 	sqrtNum=numStub.sqrt();//factors have been divided out of 
		        			 	//numStub so rtNum is set to the square root of numStub since that
		        			 	//is the new number to be factored.
		        			 	
		        			 }//end if block
		        			 	
		        		}//end while loop to find all factors of i       				            				 
		        			 		        	   
		        	   if(numStub.equals(one)) break; //if numStub equals 1, all factors of numToCheck have been found.
					
				}//end for loop
				
				long endTime = System.nanoTime();//marks the end time of the loop
				
		       		long duration = (endTime - startTime)/1000000;//gives milliseconds
			
		        
		        	//end main algorithm. The code below just prints the findings to the console.
		        
		        	if(numStub.compareTo(numToCheck)==-1 && numStub.compareTo(one)==1) factors.add(numStub);
		         
		        	Collections.sort(factors);
				
				if(factors.size()>0) {
					
					System.out.println("Factors of " + numToCheck + ":\n");
					for(int k=0; k< factors.size(); k++) {
			        	 
			        	 factorCounter++;
			        	 
			        	 for(int j=k; j+1<factors.size() &&factors.get(j)==factors.get(j+1); j++) {
			        		 //System.out.println("here");
			        		 repeatFactor++;
			        		 if(repeatFactor > 1) factorCounter++;
			        		 k++;
			        	 }//end inner for loop
			        	 
			        	 if(repeatFactor > 1) {
			        		 System.out.print(factors.get(k) +"^"+repeatFactor + "\t");
			        		 printFactor++;
			        	 }
			        	 else {
			        		// System.out.println("here 2");
			        		 System.out.print(factors.get(k) + "\t");
			        		 printFactor++;
			        	 }//end if block
			        	 
			        	 //System.out.println("i: " +i);
			        	 if(printFactor%3==0)System.out.println();
			        	 
			        	 repeatFactor=0;
			         }
				}
				else {
					System.out.println(numToCheck + " is prime.");
				}
				
				if(factors.size() > 0){//executes if all prime factors are less than the square root of numToFactor
		        	 System.out.println("\n");
		        	 System.out.println(numToCheck + " has " +(factorCounter) + " prime factors.");
		        	 System.out.println(numToCheck + " has " +(printFactor) + " unique prime factor(s).");
		         }
				
				System.out.println("\nMain algorithm took " + duration + " milliseconds.");
				System.out.println("Main algorithm took " + duration/1000 + " seconds.");
				System.out.println("Main algorithm took " + duration/60000 + " minutes.");
				System.out.println("\nLoop count: " + loopCount);
			}	

	

}
