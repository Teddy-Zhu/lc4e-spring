package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class Calc {

	@Test
	public void main() {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		Integer T = sc.nextInt();
		Integer DayCount[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		List<String> string = new ArrayList<String>();
		for (int i = 0; i < T; i++) {
			String tmp = sc.nextLine();
			string.add(tmp);
		}
		for (int i = 1, len = string.size(); i < len; i++) {
			String[] splitstring = string.get(i).split(":");
			Integer year = Integer.valueOf(splitstring[0]);
			Integer month = Integer.valueOf(splitstring[1]);
			Integer day = Integer.valueOf(splitstring[2]);
			Integer days = day;
			for (int j = 0; j < (month - 1); j++) {
				days += DayCount[j];
			}
			if (month > 2 && ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)) {
				days++;
			}
			System.out.println(days);
		}
	}
}
