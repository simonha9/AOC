package com.simon.AOC;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day8 {
	
	/**
	 * 7 segment search
	 * - In output, only count the number of 1,4,7,8 since unique number of
	 * letters (a-g) are turned on:
	 * 1: 2 (a,c)
	 * 4: 4 (b, c, d, f)
	 * 7: 3 (a, c, f)
	 * 8: 7 (a, b, c, d, e, f, g)
	 * @return
	 */
	public static int answer() {
		int res = 0;
		List<String> outputValues = new ArrayList<>();
		try {
			File input = new File("8/input.txt");
			Scanner sc = new Scanner(input);
			while (sc.hasNextLine()) {
				String line = sc.nextLine().trim();
				String output = line.substring(line.indexOf('|') + 1);

				for (String s : output.trim().split(" ")) {
					outputValues.add(s.trim());
				}
			}
			
			sc.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} 
		
		return findNumDigitsAppear(outputValues);
	}
	
	private static int findNumDigitsAppear(List<String> values) {
		int num = 0;
		Set<Integer> unique = new HashSet<>();
		unique.add(2); //1
		unique.add(4); //4
		unique.add(3); //7
		unique.add(7); //8
		
		for (String s : values) {
			if (unique.contains(s.length())) num++;
		}
		
		return num;
	}
	
	
	/*
	 * PART 2
	 */
	/**
	 * 7 segment search
	 * - In output, only count the number of 1,4,7,8 since unique number of
	 * letters (a-g) are turned on:
	 * 1: 2 (a,c)
	 * 4: 4 (b, c, d, f)
	 * 7: 3 (a, c, f)
	 * 8: 7 (a, b, c, d, e, f, g)
	 * @return
	 */
	public static int answer2() {
		int res = 0;
		List<String> patterns = new ArrayList<>();
		List<String> outputValues = new ArrayList<>();
		try {
			File input = new File("8/input.txt");
			Scanner sc = new Scanner(input);
			while (sc.hasNextLine()) {
				String line = sc.nextLine().trim();
				int index = line.indexOf('|');
				patterns.add(line.substring(0, index).trim());
				outputValues.add(line.substring(index + 1).trim());
			}
			
			sc.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} 
		
		return findNumDigitsAppear2(patterns, outputValues);
	}
	
	private static int findNumDigitsAppear2(List<String> patterns, List<String> values) {
		int res = 0;
		Set<Integer> unique = new HashSet<>();
		unique.add(2); //1
		unique.add(4); //4
		unique.add(3); //7
		unique.add(7); //8
		
		Map<Integer, Integer> uniqueMap = new HashMap<>();
		uniqueMap.put(2, 1);
		uniqueMap.put(3, 7);
		uniqueMap.put(4, 4);
		uniqueMap.put(7, 8);
		
		for (int i = 0; i < patterns.size(); i++) {
			StringBuilder num = new StringBuilder();
			Map<Integer, String> map = new HashMap<>();
			String pattern = patterns.get(i);
			String outputValues = values.get(i);
			
			//Find 1, 4, 7, 8
			for (String s : pattern.trim().split(" ")) {
				if (s.length() == 2) map.put(1, s);
				else if (s.length() == 3) map.put(7, s);
				else if (s.length() == 4) map.put(4, s);
				else if (s.length() == 7) map.put(8, s);
			}
			
			//Find the "L" in the 4 by taking out letters used for "one"
			StringBuilder lBoy = new StringBuilder();
			String one = map.get(1);
			String four = map.get(4);
			for (int j = 0; j < 4; j++) {
				if (one.indexOf(four.charAt(j)) < 0) lBoy.append(four.charAt(j));
			}
			
			//We are guaranteed 1, 4, 7, 8
			//Now look at others
			for (String s : pattern.trim().split(" ")) {
				s = s.trim();
				if (s.length() == 5) {
					//3 cases (2, 3, 5)
					if (containsStr(s, map.get(1))) map.put(3, s);
					else if (containsStr(s, lBoy.toString())) map.put(5, s);
					else map.put(2, s);
				} else if (s.length() == 6) {
					//3 cases (0, 6, 9)
					if (containsStr(s, map.get(4))) map.put(9, s);
					else if (containsStr(s, lBoy.toString())) map.put(6, s);
					else map.put(0, s);
				}
			}
			
			for (String s : outputValues.trim().split(" ")) {
				s = s.trim();
				if (s.length() == 5) {
					if (containsStr(s, map.get(2)) && containsStr(map.get(2), s)) num.append(2);
					else if (containsStr(s, map.get(3)) && containsStr(map.get(3), s)) num.append(3);
					else num.append(5);
				} else if (s.length() == 6) {
					if (containsStr(s, map.get(0)) && containsStr(map.get(0), s)) num.append(0);
					else if (containsStr(s, map.get(6)) && containsStr(map.get(6), s)) num.append(6);
					else num.append(9);
				} else {
					num.append(uniqueMap.get(s.length()));
				}
			}
			res += Integer.valueOf(num.toString());
			map.clear();
		}
		
		return res;
	}
	
	/**
	 * Returns true if str1 contains str2
	 * @param str1
	 * @param str2
	 * @return
	 */
	private static boolean containsStr(String str1, String str2) {
		for (int i = 0; i < str2.length(); i++) {
			if (str1.indexOf(str2.charAt(i)) < 0) return false;
		}
		return true;
	}
	
	
	
	
	public static void main(String[] args) {
		System.out.println("Answer: " + answer());
		System.out.println("Answer2: " + answer2());
	}
	
}
