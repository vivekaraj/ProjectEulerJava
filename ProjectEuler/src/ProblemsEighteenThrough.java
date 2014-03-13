import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


public class ProblemsEighteenThrough {
	private static ArrayList<String> permutations = new ArrayList<String>();

	private static int helper(int[][] triangle, int x, int y) {
		if(x == 0) {
			return 0;
		}
		int sum = 0;
		for(int j = 0; j <= x; j++) {
			if(j != y)
				sum += triangle[x][j];					
		}
		if(y > 0) {
			sum += Math.min(helper(triangle, x-1, y-1), helper(triangle, x-1, y));
		} else {
			sum += helper(triangle, x-1, y);
		}
		return sum;						
	}
	
	public static int Problem18() {
		String a = "75 95 64 17 47 82 18 35 87 10 20 04 82 47 65 19 01 23 75 03 34 88 02 77 73 07 63 67 99 65 04 28 06 16 70 92 41 41 26 56 83 40 80 70 33 41 48 72 33 47 32 37 16 94 29 53 71 44 65 25 43 91 52 97 51 14 70 11 33 28 77 73 17 78 39 68 17 57 91 71 52 38 17 14 91 43 58 50 27 29 48 63 66 04 68 89 53 67 30 73 16 69 87 40 31 04 62 98 27 23 09 70 98 73 93 38 53 60 04 23";
		int[][] triangle = new int[15][15];
		Scanner scanner = new Scanner(a);
		int triangleSum = 0;
		for(int i = 1; i <= 15; i++) {
			for(int j = 1; j <= i; j++) {
				triangle[i-1][j-1] = Integer.parseInt(scanner.next());
				triangleSum += triangle[i-1][j-1];
			}
			for(int j = i+1; j <= 15; j++) {
				triangle[i-1][j-1] = -1;
			}			
		}
		scanner.close();
		int minSum = (int) Double.POSITIVE_INFINITY;
		for(int i = 0; i < 15; i++) {
			minSum = Math.min(minSum, helper(triangle, 14, i));
		}
		return triangleSum - minSum;
	}
	
	//counting Sundays. How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
	public static int Problem19() {
		Date date = new Date(0, 1, 7, 1900);
		int count = 0;
		while(date.getYear() <= 2000) {
			date.goNextWeek();
			if(date.getYear() > 1900 && date.getDay() == 1)
				count++;
		}
		return count;
	}
	
	private static String strProduct(String a, String b) {
		ArrayList<ArrayList<Integer>> products = new ArrayList<ArrayList<Integer>>();
		int maxSize = -1;
		for(int i = a.length() - 1; i >= 0; i--) {
			ArrayList<Integer> digits = new ArrayList<Integer>();
			for(int j = a.length() - 1; j >= i; j--) {
				digits.add(0);
			}
			int carryover = 0;
			for(int j = b.length() - 1; j >= 0; j--) {
				int num1 = Integer.parseInt("" + b.charAt(j));
				int num2 = Integer.parseInt("" + a.charAt(i));
				int num3 = carryover + num1 * num2;
				digits.add(num3 % 10);
				carryover = num3/10;
			}
			String carry = "" + carryover;
			for(int j = carry.length() - 1; j >= 0; j--) {
				digits.add(Integer.parseInt("" + carry.charAt(j)));
			}
			products.add(digits);
			maxSize = Math.max(maxSize, digits.size());
		}
		String temp = "";
		long num = 0;
		for(int i = 0; i < maxSize; i++) {
			for(int j = 0; j < products.size(); j++) {
				ArrayList<Integer> tempList = products.get(j);
				if(tempList.size() >= i+1) {
					num += tempList.get(i);					
				}
			}
			temp += "" + (num % 10);
			num /= 10;
		}
		String ret = "";
		for(int i = temp.length() - 1; i >= 0; i--) {
			ret += temp.charAt(i);
		}
		return ret;
	}
	
	public static long Problem20() {
		String product = "1";
		for(int i = 1; i <= 100; i++) {
			product = strProduct(product, "" + i);
		}
		long sum = 0;
		for(int i = 0; i < product.length(); i++) {
			sum += Integer.parseInt("" + product.charAt(i));
		}
		return sum;
	}
	
	private static long divisorSum(long a) {
		long[] primeFactors = new long[(int) a + 1];
		Set<Long> nums = new HashSet<Long>();
		long temp = a;
		long num = 2;
		while(temp > 1) {
			if(temp % num == 0) {
				temp /= num;
				primeFactors[(int) num]++;
				nums.add(num);
			} else {
				num++;
			}
		}
		Object[] numsList = nums.toArray();
		long prod = 1;
		for(int i = 0; i < numsList.length; i++) {
			num = (Long) numsList[i];
			long occ = primeFactors[(int) num];
			long sum = 0;
			for(int j = 0; j <= occ; j++) {
				sum += (int) Math.pow(num, j);
			}
			prod *= sum;
		}
		return prod;
	}
	
	//Evaluate the sum of all amicable numbers less than 10000
	public static int Problem21() {
		long[] divisorSum = new long[10001];
		int count = 0;
		for(int i = 1; i <= 10000; i++) {
			divisorSum[i] = divisorSum(i);
		}
		for(int i = 1; i <= 10000; i++) {
			try {
				if(i == divisorSum[(int) divisorSum[i]]) {
					count++;
				}
			} catch(Exception e) {
				
			}
		}
		return count / 2;
	}
	
	private static boolean strLessThan(String a, String b) {
		for(int i = 0; i < Math.min(a.length(), b.length()); i++) {
			if(a.charAt(i) < b.charAt(i)) {
				return true;
			} else if(a.charAt(i) > b.charAt(i)) {
				return false;
			}
		}
		if(a.length() > b.length()) {
			return false;
		} else {
			return true;
		}
	}
	
	private static ArrayList<String> merge(ArrayList<String> list1, ArrayList<String> list2) {
		int count1 = 0;
		int count2 = 0;
		ArrayList<String> list = new ArrayList<String>();
		while(count1 < list1.size() || count2 < list2.size()) {
			if(count1 >= list1.size()) {
				list.add(list2.get(count2));
				count2++;
			} else if(count2 >= list2.size()) {
				list.add(list1.get(count1));
				count1++;
			} else {
				if(strLessThan(list1.get(count1), list2.get(count2))) {
					list.add(list1.get(count1));
					count1++;
				} else {
					list.add(list2.get(count2));
					count2++;
				}
			}
		}
		return list;
	}
	
	private static ArrayList<String> mergeSort(ArrayList<String> list) {
		if(list.size() <= 1) {
			return list;
		}
		ArrayList<String> list1 = new ArrayList<String>();
		ArrayList<String> list2 = new ArrayList<String>();
		for(int i = 0; i < list.size()/2; i++) {
			list1.add(list.get(i));
		}
		for(int i = list.size()/2; i < list.size(); i++) {
			list2.add(list.get(i));
		}
		list1 = mergeSort(list1);
		list2 = mergeSort(list2);
		return merge(list1, list2);
	}
	
	private static int Problem22() throws FileNotFoundException {
		File file = new File("names.txt");
		Scanner sc = new Scanner(file);
		String temp = sc.next();
		sc.close();
		String namesStr = "";
		for(int i = 1; i < temp.length() - 1; i++) {
			try {
				if(temp.charAt(i) == '\"' && temp.charAt(i+1) == ',' && temp.charAt(i+2) == '\"') {
					namesStr += " ";
					i += 2;
				} else {
					namesStr += temp.charAt(i);
				}
			} catch(Exception e) {
				i += 2;
			}
		}
		ArrayList<String> names = new ArrayList<String>();
		sc =  new Scanner(namesStr);
		while(sc.hasNext()) {
			names.add(sc.next());
		}
		sc.close();
		names = mergeSort(names);
		int total = 0;
		for(int i = 0; i < names.size(); i++) {
			String name = names.get(i);
			int sum = 0;
			for(int j = 0; j < name.length(); j++) {
				sum += ((int) name.charAt(j)) - 64;
			}
			sum *= (i+1);
			total += sum;
		}
		return total;
	}
	
	//Sum of all positive integers that can not be written as the sum of two abundant numbers
	public static long Problem23() {
		ArrayList<Integer> abundantNumbers = new ArrayList<Integer>();
		for(int i = 12; i <= 28123; i++) {
			if(divisorSum(i) - i > i) {
				abundantNumbers.add(i);
			}
		}		
		Set<Integer> possAbNumbSums = new HashSet<Integer>();
		for(int i = 24; i <= 28123; i++) {
			possAbNumbSums.add(i);
		}
		for(int i = 0; i < abundantNumbers.size(); i++) {
			for(int j = 0; j < abundantNumbers.size(); j++) {
				possAbNumbSums.remove(abundantNumbers.get(i) + abundantNumbers.get(j));
			}
		}
		long sum = 0;
		Iterator<Integer> iter = possAbNumbSums.iterator();
		while(iter.hasNext()) {
			sum += iter.next();
		}
		return sum;
	}
	
	
	private static ArrayList<String> permute(String a) {
		ArrayList<String> ret = new ArrayList<String>(); 
		if(a.length() == 1) {
			ret.add(a);
			return ret;
		} else if(a.length() == 2) {
			int num1 = Integer.parseInt("" + a.charAt(0));
			int num2 = Integer.parseInt("" + a.charAt(1));
			ret.add("" + Math.min(num1,num2) + "" + Math.max(num1,num2));
			ret.add("" + Math.max(num1,num2) + "" + Math.min(num1,num2));
			return ret;
		} else {			
			for(int i = 0; i < a.length(); i++) {
				String temp = "";
				for(int j = 0; j < a.length(); j++) {
					if(j != i) {
						temp += a.charAt(j);
					}
				}
				ArrayList<String> tempList = permute(temp);
				for(int j = 0; j < tempList.size(); j++) {
					ret.add("" + a.charAt(i) + tempList.get(j));
				}
			}
			return ret;
		}
	}
	
	//What is the millionth lexicographic permutation of 0,1,2,3,4,5,6,7,8,9?
	public static String Problem24() {
		ArrayList<String> list = permute("0123456789");
		return list.get(999999);
	}
	
	//returns n'th fibonacci number
	/*private static int fibonacci(int n) {
		if(n == 1) {
			return 1;
		} else if(n == 2) {
			return 1;
		} else if (n == 3) {
			return 2;
		} else {
			return fibonacci(n-1) + fibonacci(n-2);
		}
	}*/
	
	private static String stringSum(String a, String b) {
		String str = "";
		int carryover = 0;
		int i = a.length() - 1;
		int j = b.length() - 1;
		while(i >= 0 || j >= 0) {
			if(i >= 0 && j < 0) {
				int num1 = Integer.parseInt("" + a.charAt(i));
				num1 += carryover;
				str += "" + (num1 % 10);
				carryover = num1 / 10;
				i--;
			} else if(j >= 0 && i < 0) {
				int num2 = Integer.parseInt("" + b.charAt(j));
				num2 += carryover;
				str += "" + (num2 % 10);
				carryover = num2 / 10;
				j--;
			} else {
				int num1 = Integer.parseInt("" + a.charAt(i));
				int num2 = Integer.parseInt("" + b.charAt(j));
				int num3 = carryover + num1 + num2;
				str += "" + (num3 % 10);
				carryover = num3 / 10;
				i--;
				j--;
			}
		}
		String ret = "";
		for(int k = str.length() - 1; k >= 0; k--) {
			ret += str.charAt(k);
		}
		if(carryover > 0) {
			ret = "" + carryover + ret;
		}
		return ret;
	}
	
	//first term in Fibonacci sequence to contain 1000 digits
	public static long Problem25() {
		String num1 = "1";
		String num2 = "1";
		String num3 = stringSum(num1, num2);
		long count = 3;
		while(num3.length() < 1000) {
			num1 = num2;
			num2 = num3;
			num3 = stringSum(num1, num2);
			count++;
		}
		return count;
	}
	
	//Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
	public static int Problem26() {
		boolean keepGoing = true;
		int count = 1;
		int d = -1;
		Set<Integer> traversed = new HashSet<Integer>();
		while(keepGoing) {
			keepGoing = false;
			String nines = "";
			for(int i = 1; i <= count; i++) {
				nines += "9";
			}
			long nine = Long.parseLong(nines);
			for(int i = 1; i <= 1000; i++) {
				if(nine % i == 0 && !traversed.contains(i)) {
					d = i;
					traversed.add(i);
					keepGoing = true;
				}
			}
			count++;
		}
		System.out.println(count-1);
		return d;
	}
	
	public static void main (String[] args) throws FileNotFoundException {
		/*System.out.println("Problem 18: " + Problem18());
		System.out.println("Problem 19: " + Problem19());
		System.out.println("Problem 20: " + Problem20());
		System.out.println("Problem 21: " + "NOT FOUND!");
		System.out.println("Problem 22: " + Problem22());
		System.out.println("Problem 23: " + Problem23() + "INCORRECT!");
		System.out.println("Problem 24: " + Problem24());
		System.out.println("Problem 25: " + Problem25());*/
		System.out.println("Problem 26: " + Problem26());
	}
}

class Date {
	int dayOfWeek = 0;
	int month = 0;
	int day = 0;
	int year = 0;
	
	//dayOfWeek = 0 -> Sunday, 1 -> Monday and so on
	Date(int dayOfWeek, int month, int day, int year) {
		this.dayOfWeek = dayOfWeek;
		this.month = month;
		this.day = day;
		this.year = year;
	}
	
	void goNextWeek() {
		day += 7;
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			if(day > 31) {
				day -= 31;
				month++;
			}
		} else if(month == 4 || month == 6 || month == 9 || month == 11) {
			if(day > 30) {
				day -= 30;
				month++;
			}
		} else {
			if(year % 4 == 0 && ((year % 100 != 0) || (year % 400 == 0))) {
				if(day > 29) {
					day -= 29;
					month++;
				}
			} else {
				if(day > 28) {
					day -= 28;
					month++;
				}
			}
		}
		if(month > 12) {
			month -= 12;
			year++;
		}
	}
	
	int getYear() {
		return year;
	}
	
	int getMonth() {
		return month;
	}
	
	int getDay() {
		return day;
	}
}
