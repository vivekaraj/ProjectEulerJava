import java.util.Scanner;


public class ProblemsEighteenThrough {
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
	
	public static void main (String[] args) {
		System.out.println("Problem 18: " + Problem18());
		System.out.println("Problem 19: " + Problem19());
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
