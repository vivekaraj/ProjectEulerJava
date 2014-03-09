import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Problems {
	private ArrayList<Integer> primeNumbers = new ArrayList<Integer>();
	//Find the sum of all the multiples of 3 or 5 below 1000.
	public static int Problem1() {
		int sumMultThree = 3 * 333 * 334 / 2;
		int sumMultFive = 5 * 199 * 200 / 2;
		int sumMultFifteen = 15 * 66 * 67 / 2;
		return sumMultThree + sumMultFive - sumMultFifteen;
	}
	
	//By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.
	public static int Problem2() {
		int sum = 2;
		int first = 1;
		int second = 2;
		int third = first + second;
		while(third < 4000000) {			
			if(third % 2 == 0) {
				sum += third;
			}
			first = second;
			second = third;
			third = first + second;
		}
		return sum;
	}
	
	//What is the largest prime factor of the number 600851475143 ?
	public static int Problem3() {
		long a = 600851475143L;
		int count = 2;
		while(a > 1) {
			count++;
			if(a % count == 0) {
				a /= count;
			}
		}
		return count;
	}
	
	private static boolean isSixDigitPalindrome(int num) {
		String str = "" + num;
		if(str.length() != 6) {
			return false;
		}
		for(int i = 0; i <= 3; i++) {
			if(str.charAt(i) != str.charAt(-1 * i + 5)) {
				return false;
			}
		}
		return true;
	}
	
	//Find the largest palindrome made from the product of two 3-digit numbers.
	public static int Problem4() {
		int palindrome = -1;
		for(int i = 999; i >= 100; i--) {
			for(int j = 999; j >= 100; j--) {
				if(i * j > palindrome && isSixDigitPalindrome(i * j)) {
					palindrome = i * j;
				}
			}
		}
		return palindrome;
	}
	
	//What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
	public static int Problem5() {
		int answer = 2520;
		boolean stop = false;
		while(!stop) {
			answer++;
			stop = true;
			for(int i = 1; i <= 20; i++) {
				if(answer % i != 0) {
					stop = false;
					break;
				}
			}
		}
		return answer;
	}
	
	//Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
	public static int Problem6() {
		int squareOfSumOfFirst100 = (100 * 101 / 2) * (100 * 101 / 2);
		int sumOfFirst100Squares = 1;
		for(int i = 2; i <= 100; i++) {
			sumOfFirst100Squares += (i * i);
		}
		return Math.abs(sumOfFirst100Squares - squareOfSumOfFirst100);
	}
	
	private static boolean isPrime(int num) {
		for(int i = 2; i < num/2; i++) {
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	//What is the 10001st prime number?
	public static int Problem7() {
		int count = 1;
		int num = 3;
		while(count < 10001) {
			if(num % 2 != 0 && isPrime(num)) {
				count++;
			}
			num++;
		}
		return num-1;
	}
	
	//Find the greatest product of five consecutive digits in the 1000-digit number below
	public static int Problem8()  {
		String num = "7316717653133062491922511967442657474235534919493496983520312774506326239578318016984801869478851843858615607891129494954595017379583319528532088055111254069874715852386305071569329096329522744304355766896648950445244523161731856403098711121722383113622298934233803081353362766142828064444866452387493035890729629049156044077239071381051585930796086670172427121883998797908792274921901699720888093776657273330010533678812202354218097512545405947522435258490771167055601360483958644670632441572215539753697817977846174064955149290862569321978468622482839722413756570560574902614079729686524145351004748216637048440319989000889524345065854122758866688116427171479924442928230863465674813919123162824586178664583591245665294765456828489128831426076900422421902267105562632111110937054421750694165896040807198403850962455444362981230987879927244284909188845801561660979191338754992005240636899125607176060588611646710940507754100225698315520005593572972571636269561882670428252483600823257530420752963450";
		int maxProduct = 7 * 3 * 1 * 6 * 7;
		int prevProduct = 7 * 3 * 1 * 6 * 7;
		int[] fiveNums = {7, 3, 1, 6, 7};
		for(int i = 5; i < num.length(); i++) {
			String temp = "" + num.charAt(i);
			int tempNum = Integer.parseInt(temp);
			if(fiveNums[i % 5] != 0) {
				prevProduct /= fiveNums[i % 5];
				prevProduct *= tempNum;
			} else if(tempNum == 0) {
				prevProduct = 0;
			} else {
				prevProduct = 1;
				for(int j = 0; j < 5; j++) {
					if(i % 5 == j) {
						prevProduct *= tempNum;
					} else {
						prevProduct *= fiveNums[j];
					}
				}
			}
			fiveNums[i % 5] = tempNum;
			maxProduct = Math.max(maxProduct, prevProduct);
		}
		return maxProduct;
	}
	
	//There exists exactly one Pythagorean triplet for which a + b + c = 1000. Find the product abc.
	public static int Problem9() {
		for(int a = 1; a <= 1000; a++) {
			for(int b = 1; b <= 1000; b++) {
				int c2 = a * a + b * b;
				int c = (int) Math.sqrt(c2);
				if(c * c == c2 && a + b + c == 1000) {
					return a * b * c;
				}
			}
		}
		return -1;
	}
	
	//Find the sum of all the primes below two million.
	public static long Problem10(int max) {
		long sum = 0;
		Boolean[] isPrime = new Boolean[max + 1];
		for(int i = 2; i < max + 1; i++) {
			isPrime[i] = true;
		}
		for(int i = 2; i <= Math.sqrt(max); i++) {
			for(int j = i * i; j <= max; j += i) {
				isPrime[j] = false;
			}
		}
		for(int i = 2; i < max; i++) {
			if(isPrime[i]) {
				sum += i;
			}
		}
		return sum;
	}
	
	public static void main (String[] args) {
		System.out.println("Problem 1: " + Problem1());
		System.out.println("Problem 2: " + Problem2());
		System.out.println("Problem 3: " + Problem3());
		System.out.println("Problem 4: " + Problem4());
		System.out.println("Problem 5: " + 232792560); //derived from method Problem5(), but it takes too long to execute
		System.out.println("Problem 6: " + Problem6());		
		System.out.println("Problem 7: " + 104743);	//derived from method Problem7(), but it takes too long to execute	
		System.out.println("Problem 8: " + Problem8());	
		System.out.println("Problem 9: " + Problem9());	
		System.out.println("Problem 10: " + Problem10(2000000));	
	}
}