package prime_number_counter;

/* This program counts the number of primes up to a given integer greater than 7 and less than or equal 
 * to 2147483647 which is the largest primitive integer that can be represented by Java. 
 * It also prints the primes to the console. If all that is needed is the count, the print statements can 
 * be commented out or deleted. The prime count by itself runs significantly faster than printing 
 * the primes to the console.*/

public class prime_number_counter {

	public static void main(String[] args) {		
	
		int integerLimit=2147483647;//We want to see how many primes are less than this number
		int arraySize=(integerLimit-3)/2 + 1;//This formula puts the last odd value up to integerLimit in 
		//the last index of the array. The array does not hold 1. It starts at 3.
		
		boolean[] composite = new boolean [arraySize];//Stores primality values in an array of Booleans.
		//False values are prime. True values are composite.
		
		int integerSqrt=(int)(Math.sqrt(integerLimit));//Square root of integerLimit. Terminating condition
		//of first outer for loop. Only multiples of primes less than or equal to the square root of
		//integer limit need to be marked composite.
		
		int primeCount = 1; //adds 2 to the prime count
		int numTest;//iterator of outer for loops - keeps track of which number is being tested for primality
		int multipleOfPrime;//iterator of inner for loop - keeps track of multiples of prime
		
		System.out.print(2 + "\t");//outputs 2 as the first prime
		
		//This loop counts primes less than or equal to the square root of integerLimit and marks
		//all of their multiples in the boolean array true.	
		long startTime = System.nanoTime();//times the start of the main algorithm
		for (numTest = 3; numTest <= integerSqrt || numTest < 9; numTest += 2) {
			
			//This block will only execute if numTest is prime.
			if (!composite[(numTest - 3)/2]) {
				
		         primeCount++;//A prime has been found and added to the count
		         
		         //Begin printing block - comment out this block if only the prime count is needed
		         if(primeCount%10==0)//ensures only 10 primes print to the console per line
		        	 System.out.println(numTest);
		         else
		        	 System.out.print(numTest + "\t");
		         //End printing block.
		         
		         //This loop marks all multiples of primes in the array "true"
		         //Second terminating condition for loop is for 
		         //when multiples cross the limit of the size for a primitive int type
		         //and wrap around to become negative numbers.
		         for(multipleOfPrime = numTest*numTest; multipleOfPrime <= integerLimit && multipleOfPrime > 0; multipleOfPrime+=2*numTest) {
		        	
		        	 composite[(multipleOfPrime - 3)/2] = true;
			     }//end inner for loop
			}//end if
			
		}//end outer for loop
		
		//This loop counts primes greater than the square root of integerLimit.
		for (; numTest <= integerLimit; numTest+=2) {
			
			if (!composite[(numTest - 3)/2]) { 
				
				primeCount++; //A prime has been found and added to the count
				
				//Begin printing block - comment out this block if only the prime count is needed
				if(primeCount%10==0)
		        	 System.out.println(numTest);
		         else
		        	 System.out.print(numTest + "\t");
				//End printing block.
			}
			
			if(numTest==2147483647) break; //This is the largest value that can be
			//represented by a primitive integer in Java. If we try to add 2 to it to check the
			//terminating loop condition, it will result in a runtime error. So the loop is broken
			//before numTest is incremented.
			
		}
		long endTime = System.nanoTime();//marks the end time of the loop
		
        long duration = (endTime - startTime)/1000000;//gives milliseconds
        
		String outputCount="Number of primes less than or equal to " + integerLimit+ ": " + primeCount;
		
		if(primeCount%10==0)System.out.println("\n"+ outputCount);
		else System.out.println("\n\n"+ outputCount);
		
		System.out.println("Time: " + duration + " milliseconds");
	}

}
