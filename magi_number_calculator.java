package magi_number_calculator;

import java.util.*;

/* This program computes Magi numbers. Magi numbers are crossover points where the number of
 * primes less than a Magi number is exactly equal to the Magi number divided by some integer.
 * Additionally, a Magi number is the number for which no number greater than it has more
 * than the fraction associated with the Magi number. For example, 8 is the first Magi number.
 * Its associated fraction is 2. For any integer n greater than 8, 1/2 or less of the integers
 * less than n will be prime. The second Magi number is 33 and its associated fraction is 1/3.
 * For any integer n greater than 33, 1/3 or less of the integers less than n will be prime.
 * 
 * The Magi numbers allow for a reasonably tight lower and upper bound for how many primes
 * will be less than a given integer for relatively small integers for which n/ln(n) is
 * a poor estimate for the number of primes less than those integers. 
 * 
 * For example, for any given integer n between 120 and 360, between 1/5 and 1/4 of the
 * integers less than n will be prime. 240 is the midpoint between 120 and 360. 
 * 240/ln(240)~43. The Magi numbers give an estimate of between 48 and 60 primes less
 * than 240 and it will definitely be in that range. These are strict lower and upper
 * bounds. The exact number of primes less than 240 is 52.*/

public class magi_number_calculator {
	
	public static	int	MIN_ALLOWED_VALUE = 3;
	public static	int	MAX_ALLOWED_VALUE = 20;
	public	static	boolean	bDebugMode = true;
	
	public static boolean [] composites;
	
	//array of previously computed Magi numbers used to determine upper and lower bounds
	public final static double[] MAGI_NUMS = {
			0,
			0,
			8, 		// n/2
			33, 		// n/3
			120,  		// n/4
			360,		// n/5
			1134,		// n/6
			3094,		// n/7
			8472,		// n/8
			24300,		// n/9
			64720,		// n/10
			175197,		// n/11
			481452,		// n/12
			1304719,	// n/13
			3524654,	// n/14
			9560100, 	// n/15
			25874784,	// n/16
			70119985,	// n/17
			189964368,	// n/18
			514275641,	// n/19
			1394194680	// n/20
	};
	
	public static void main(String[] args) {
		
		
		//I created a way to input values because there were too many values I had to change
		//every time I ran the program for a different number
		
		/****INPUT BLOCK******/
		Scanner input = new Scanner(System.in); 

		int enteredValue = -1;
		
		while(enteredValue == -1)
		{
			
			try
			{
					System.out.print("Please enter an integer between "+ MIN_ALLOWED_VALUE +" and "+ MAX_ALLOWED_VALUE +" : ");
					enteredValue=input.nextInt();
					input.nextLine();
			

					if( !((enteredValue >= MIN_ALLOWED_VALUE) && (enteredValue <= MAX_ALLOWED_VALUE )) )
					{
						System.out.println(enteredValue + " is out of range.\n");
						enteredValue = -1;
					}
			}//end try block
			
			catch(Exception e)
			{
				System.out.println("That wasn't an integer.\n");
				input.nextLine();
				enteredValue = -1;

			
			}//end catch block

		} // end of while
		
		System.out.println();
		input.close();
		
		/*****END INPUT BLOCK*****/
		
		double numToCheck = enteredValue; //numToCheck is the denominator associated with a Magi number.
		//For example, the Magi number associated with a denominator of 3 is 33. There are no integers greater
		//than 33 for which more than 1/3 of the integers less than them are prime.
		
		// look up the lower and upper bounds in the array of MAGI_NUMS
		double boundsValueForThisNumber = MAGI_NUMS[(int)(numToCheck-1)];


		//The lower bound to search for a Magi number is set to 2 times the previous Magi number
		//and the upper bound is set to 3 times the previous Magi number.
		//Based on the first 20 Magi numbers, the next Magi number is always between 2 and 3 times 
		//the previous Magi number. However, to make sure no numbers are missed for the smaller Magi
		//numbers, the upper bound is set to 4 times the previous Magi number.
		
		double lowerBoundMultiplier = 2;
		double upperBoundMultiplier = 3;
		
		// *** Check and adjust multipliers
		// ********************************** CHECK THIS ************************************
		if(numToCheck < 6)
		{
			lowerBoundMultiplier = 2;
			upperBoundMultiplier = 5;
			
		}
		// ******************************* END CHECK THIS ************************************
		

		double lowerBound = lowerBoundMultiplier*boundsValueForThisNumber;
		double initialLowerBound = lowerBoundMultiplier*boundsValueForThisNumber;
		double upperBound = upperBoundMultiplier*boundsValueForThisNumber;

		
		System.out.println("Lower bound set to -> "+lowerBoundMultiplier+" X " + boundsValueForThisNumber);
		System.out.println("Upper bound set to -> "+upperBoundMultiplier+" X " + boundsValueForThisNumber);
		
		System.out.print("\nGenerating array...");
		// *************************************************
		
		long startTime = System.nanoTime();//start timing the algorithm
		composites=generateArray((int) upperBound+2000);
		
		// *************************************************
		System.out.println("Done.\n");				
		
		//magiNumberCandidate will hold the value of a Magi number candidate and the 
		//eventual Magi number when it is determined to be a Magi number
		double magiNumberCandidate=0;
		
		int lowerBoundPrimeCount=(int)primeCount(lowerBound);
		
		double boundDifference=upperBound-lowerBound;
		double primeCount;
		
		/**********************/
		/******MAIN LOOP*******/
		/**********************/
		
		//This loop is designed to close the gap between the upper bound and lower bound
		//until they are consecutive integers, or a potential magiNumberCandidate is found.
		//It is essentially a binary search technique.
		
		while(boundDifference >=0) {
			
			//divides the difference between the upper and lower bounds by 2 on each
			//iteration of the loop
			boundDifference=Math.floor((upperBound-lowerBound)/2);
			
			primeCount=primeCount(lowerBoundPrimeCount, initialLowerBound, (lowerBound+boundDifference));
			//primeCount=primeCount(lowerBound+boundDifference);
			if(bDebugMode)
			{
				//error testing
				String strToPrint = new String("lower bound: " + lowerBound + "  \tupper bound: " + upperBound +"  \tboundDifference: " + boundDifference);
				System.out.println(strToPrint);
			}
			//If the number of primes less than the lowerBound plus the bound difference
			//divided by numToCheck is greater than the lower bound plus the bound difference, 
			//lower Bound + bound difference is the new lower bound. If the number of primes is less
			//than the lower bound +bound difference divided by numToCheck, 
			//the lower bound + bound difference is the new upper bound.
		
						
			if(primeCount <= (lowerBound+boundDifference)/numToCheck) {
				upperBound=(lowerBound+boundDifference);
			}
			
			else {
				
				lowerBound=lowerBound+boundDifference;
			}
			
				
			//loop finishes when boundDifference is 0 and a Magi number candidate is found
			if(boundDifference==0) {
				if (lowerBound%numToCheck==0) {
					magiNumberCandidate=lowerBound;
					
				}//end if
				
				else if (upperBound%numToCheck==0) {
					magiNumberCandidate=upperBound;
					
				}//end else if
				else {

					//String toPrint = new String("Something went wrong.\n" + "Lower bound: " + lowerBound+"\nUpper bound: " + upperBound+"\n" );
					System.out.println("Something went wrong.");
					System.out.println("Lower bound: " + lowerBound);
					System.out.println("Upper bound: " + upperBound);
				}//end else
				break;
			}//end if
		
		}//end while
		
		
		double magiNumberCandidatePrimeCount=primeCount(lowerBoundPrimeCount, initialLowerBound, magiNumberCandidate);
		
		int k= (int) magiNumberCandidate;
		
		if(numToCheck >10) k+=2000;
		else k+=1000;
		
		int primeLoops=0; //counts how many numbers for which a candidate magiNumberCandidate or "Magi number" holds
		double oldMagiNumberCandidate=magiNumberCandidate;
		
		
		//This loop checks the next 1000-2000 numbers after a Magi number candidate is found to see 
		//if it holds. The Magi number is raised until there are no more integers greater than it
		//that have more than the fraction associated with the Magi number primes less than it.
		//For example, 1/3 of the integers less than 30 are prime but slightly more than 1/3 of the
		//integers less than or equal to 31 are prime. But after 33, no integers have more than
		//1/3 of their value in primes less than them.
		for(double j=magiNumberCandidate+1; j<=k; j++) {
			
			if(j%2==0) { //prime count will not change if j is even so we skip it.						
				primeLoops++;
				continue;
			}
			
			if(!composites[((int)j-3)/2]) magiNumberCandidatePrimeCount++;
			
			if(magiNumberCandidatePrimeCount > j/numToCheck) {
				
				//magiNumberCandidate needs to be increased
				magiNumberCandidate=j+(numToCheck-j%numToCheck);
				
				if(magiNumberCandidate!=oldMagiNumberCandidate) {
					oldMagiNumberCandidate=magiNumberCandidate;
					primeLoops=1;
				}
				
			 
			}
						
			primeLoops++;
		}
		long endTime = System.nanoTime();//end timing the algorithm
		long duration = (endTime-startTime)/1000000;
		
		String strOutput = new String("\nPrime bound for " + (int)numToCheck+ ": " + (int)magiNumberCandidate);
		
		System.out.println(strOutput);

		String strOutput2 = new String("Prime loops " + primeLoops);
		System.out.println(strOutput2);
	
		System.out.println("\nAlgorithm running time: " + duration + " milliseconds");
		
		}//end main
	
	//This will count the number of primes up to a given number
	public static double primeCount(double i) {
		
		int counter = 1;//set to 1 to count 2 as a prime
		
		for(int j=0; j <=((int)i-3)/2; j++) {
		if(!composites[j])
			counter++;
		}
	
		return (double) (counter);
	}//end primeCount method
	
	//This will count the number of primes between the initial lowerBound and a value i.
	public static double primeCount(int lowerBoundPrimes, double lowerBound, double i) {
		
		int counter = lowerBoundPrimes;
		
		for(int j=((int)lowerBound-3)/2 + 1; j <=((int)i-3)/2; j++) {
		if(!composites[j])
			counter++;
		}
	
		return (double) (counter);
	}//end primeCount method
	
	
	
	//Generate array method will generate an array of primes up to the upper bound + 1000 which
	//will enable us to determine the number of primes less than a given integer.
	public static boolean[] generateArray(int limit) {
		
		boolean[] composites = new boolean[limit];
		
		int n;
		for(n=3; n  < composites.length; n+=2) {
		
					
			if(composites[(n-3)/2]) {
				continue;
			}
			if(n==46349) {
				n+=2;
				break;
			}
			if(n*n > (limit)) {
				n+=2;
				break;
			}
		
			for(int i=(n*n-3)/2; i < composites.length; i+=n) {
				composites[i]=true;
				
			}
		}
	
	
		return composites;
	}//end generateArray method


}
