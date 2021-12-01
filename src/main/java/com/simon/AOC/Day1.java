package com.simon.AOC;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {
	
	public static int countIncrease() {
		int numIncrease = 0;
		int last = -1;
		try {
			File input = new File("1/input.txt");
			Scanner sc = new Scanner(input);
			
			while (sc.hasNextLine()) {
				String num = sc.nextLine();
				Integer number = Integer.valueOf(num);
				if (last != -1 && number > last) numIncrease++;
				last = number;
			}
			
			sc.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} 
		
		return numIncrease;
	}
	
	public static int countIncrease3Sliding() {
		int numIncrease = 0;
		List<Integer> l = new ArrayList<>();
		try {
			File input = new File("1/input.txt");
			Scanner sc = new Scanner(input);
			
			while (sc.hasNextLine()) {
				String num = sc.nextLine();
				Integer number = Integer.valueOf(num);
				l.add(number);
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} 
		
		for (int i = 0; i < l.size() - 3; i++) {
			if (l.get(i + 3) > l.get(i)) numIncrease++;
		}
		
		return numIncrease;
	}
	
	public static void main(String[] args) {
		System.out.println("Num increase: " + countIncrease());
		System.out.println("Num increase sliding: " + countIncrease3Sliding());
	}
	
}
