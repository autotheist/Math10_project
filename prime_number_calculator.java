package prime_number_calculator;

//This program generates a sieve of prime numbers up to num_To_check,
//counts all the primes less than num_to_check, and prints them to the console.
//This program can be used for primality testing. If the last number printed is
//num_to_Check, num_to_Check is prime.

public class prime_number_calculator {
	public static void main(String[] args) {
		
		int num_to_check=1394194680;
		int compositeArraySize;
		int counter=1;//counts prime numbers- starts at 1 to count 2
		int n;//index variable for outer nested for loop
		
		//The array size only needs to be half the size of num_to_Check because
		//it only stores odd numbers
		if(num_to_check%2==0)compositeArraySize=num_to_check/2;
		else compositeArraySize=(num_to_check+1)/2;
		
		//this array gives the option of checking many numbers at a time for primality
		//even though I'm currently only using it to check one number.
		int [] nums_to_check= {num_to_check};
		boolean [] composites = new boolean[(compositeArraySize)];
			
		System.out.print(2 +"\t");
		
		//This loop will only run up to the square root of num_To_Check even
		//though it says it stops when it reaches the end of the composite array
		
		for(n=3; n  < composites.length; n+=2) {
			
			//A false value found while iterating through the loop means the number associated with the
			//index is prime. (n-3)/2 is the formula to get to the index associated with n. If n is 5,
			//for example, it will be stored in index (5-3)/2=1. The array only stores odd values greater
			//than 1.
			if(!composites[(n-3)/2]) {	
				counter++;//a prime has been found so it is counted.
				if((counter%10)==0)System.out.println();//This prints a new line every 10 primes
				System.out.print(n + "\t");
				
			}
			
			//This if block executes if n has already been marked composite. It will continue the outer for loop.
			if(composites[(n-3)/2]) {
				continue;
			}
			
			//This is the integer square root of the largest integer that can be represented by Java.
			//If n reaches this number, it's because num_To_Check is close to the largest
			//integer that can be represented by Java. The loop is broken at this point since no
			//number greater than the largest integer represented by Java can be tested by this program.
			if(n==46349) {
				n+=2;
				break;
			}
			
			//This breaks the loop once the integer square root of num_to_Check has been reached
			if(n*n>(nums_to_check[nums_to_check.length-1])) {
				n+=2;
				break;
			}
			
			//This loop executes if n is prime. It marks all the multiples of a prime starting at its square.
			//There is no need to cross out multiples less than the square of the prime because they will
			//have been marked composite by smaller primes.
			for(int i=(n*n-3)/2; i< composites.length; i+=n) {
				composites[i]=true;
				
			}
		}
		
		//This loop runs from the square root of num_to_Check to the end of the array. It prints
		//the remaining primes in the sieve and counts them.
		for(int i=n; i <= composites.length*2; i+=2) {
			if(!composites[(i-3)/2]) { 
				counter++;
				System.out.print(i +"\t");
				if((counter%10)==0)System.out.println();
				
			}
			
		}
		System.out.print("\n" +"Number of primes less than or equal to "+ (nums_to_check[nums_to_check.length -1])+": "+(counter));
		
		
		//******Commented code below allows for this program to tell me other things like if 
		//******num_to_Check is a twin prime. 
		
		/*if(num_to_check%2!=0)System.out.println("\n" + num_to_check + " is prime: "+!composites[(num_to_check-3)/2]);
		if(num_to_check%2!=0 && !composites[(num_to_check-3)/2] && !composites[(num_to_check-3)/2-1]) {
			System.out.println("\n" + num_to_check + " is a twin prime. Its twin is: "+(num_to_check-2));
			
		}
		if(num_to_check%2!=0 && !composites[(num_to_check-3)/2] &&!composites[(num_to_check-3)/2+1]) {
			System.out.println("\n" + num_to_check + " is a twin prime. Its twin is: "+(num_to_check+2));
		}*/
	}
	
}
