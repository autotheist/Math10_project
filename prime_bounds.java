package prime_bounds;

//This program finds Magi numbers

public class prime_bounds {
	
	public static boolean [] composites=generateArray(1550000000);

	public static void main(String[] args) {		

						
				//numToCheck is the denominator of the fraction where integers greater than an integer n
				//have at most n/numToCheck primes less than n.
				
				//the program is currently set up to check for the Magi number associated with 1/20
				double numToCheck=20;					
				
				
				//primeBound will hold the value of n
				double primeBound=0;
				
				//the lower bound to search for n is set to 2 times the primeBound for the integer m
				//such that integers > m have at most 1/(numToCheck-1) primes less than them.
				
				//the upper bound is set to 3 times m. Based on the first 20 m's, the next m is always
				//between 2 and 3 times the previous m.
				double lowerBound=2*514275641;
				double upperBound=3*514275641;
				
				double boundDifference=upperBound-lowerBound;
				double primeCount;
			    
			    /**********************/
			    /******MAIN LOOP*******/
			    /**********************/
			    
				//This loop is designed to close the gap between the upper bound and lower bound
			    //until they are consecutive integers, or a potential primeBound is found.
			    
				while(boundDifference >=0) {
					
					//divides the difference between the upper and lower bounds by 2 on each
					//iteration of the loop
					boundDifference=Math.floor((upperBound-lowerBound)/2);
					
					primeCount=primeCount(lowerBound+boundDifference);
					
					//error testing
					System.out.println("lower bound: " + lowerBound + "\tupper bound: " + upperBound +"\tboundDifference: " + boundDifference);
					
					//If the number of primes less than the lowerBound plus the bound difference
					//divided by numToCheck
					//is greater than the lower bound plus the bound difference, lower Bound + 
					//bound difference is the new lower bound. If the number of primes is less
					//than the lower bound +bound difference divided by numToCheck, 
					//the lower bound + bound difference is the new upper bound.
					//
					//If the number of primes less than the lowerBound plus bound difference
					//equals the the lowerBound plus the bound difference
					//divided by numToCheck, a potential prime bound is found and the loop is broken.
								
					if(primeCount > (lowerBound+boundDifference)/numToCheck) {
						lowerBound=(lowerBound+boundDifference);
					}
					
					else if (primeCount < (lowerBound+boundDifference)/numToCheck) {
												
						upperBound=lowerBound+boundDifference;
					}
					
							
					else{
						if((lowerBound+boundDifference)%numToCheck==0) {
							primeBound=(lowerBound+boundDifference);
							break;
						}
						else {
							primeBound=(lowerBound+boundDifference) - (lowerBound+boundDifference)%numToCheck;
							break;
						}
					}//end if block
					
					
					if(boundDifference==0) {
						if (lowerBound%numToCheck==0) {
							primeBound=lowerBound;
							
						}//end if
						
						else if (upperBound%numToCheck==0) {
							primeBound=upperBound;
							
						}//end else if
						else {
							System.out.println("Something went wrong.");
							System.out.println("Lower bound: " + lowerBound);
							System.out.println("Upper bound: " + upperBound);
						}//end else
						break;
					}//end if

				}//end while
				
				int k= (int) primeBound;
				int primeLoops=0; //counts how many numbers for which a candidate primeBound or "Magi number" holds
				double oldPrimeBound=primeBound;
			
				
				//This is currently a very expensive loop. This can be optimized further but I don't
				//have time to do it before I have to turn this in. The purpose of this loop is 
				//to make sure the Magi number is correct. I may find a candidate Magi number
				//that has (magi candidate)/numToCheck primes less than it but that doesn't mean
				//there aren't integers n larger than the magi candidate that have more than 
				//n/numToCheck primes less than them. So I check the next 1000 numbers to 
				//make sure the candidate magi number is the actual magi number. If there is a number
				//n that has more than n/numToCheck primes less than it, I increase the magi number
				//to the next multiple of numToCheck after n.
		
				for(double j=k+1; j<=k+1000; j++) {
					
					if(primeCount(j)> j/numToCheck) {
						primeBound=j+(numToCheck-j%numToCheck);
						if(primeBound!=oldPrimeBound) {
							oldPrimeBound=primeBound;
							primeLoops=1;
						}
						
					 
					}
					primeLoops++;
				}
			    
				
			    System.out.println("Prime bound for " + numToCheck+ ": " + (int)primeBound);
			    System.out.println("Prime loops " + primeLoops);
			    
			
			}//end main
	   
		//This function counts the number of primes less than the number i passed as a parameter
		public static double primeCount(double i) {
			int counter = 1;//set to 1 to count 2 as a prime
			for(int j=0; j <=((int)i-3)/2; j++) {
				if(!composites[j])
					counter++;
			}
		
			return (double) (counter);
		}//end primeCount method
		
		
		
		//This function generates an array of primes using a modification of the Sieve of Eratosthenes
		public static boolean[] generateArray(int limit) {
			boolean[] composites = new boolean[limit];
			//int counter=1;
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
				
				for(int i=(n*n-3)/2; i< composites.length; i+=n) {
					composites[i]=true;
					
				}
			}	
			
			
			return composites;
		}//end generateArray method

}
