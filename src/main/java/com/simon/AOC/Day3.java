package com.simon.AOC;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day3 {

	public static int powerConsumption() {
		Map<Integer, Integer> map = new HashMap<>();
		int gamma = 0;
		int epsilon = 0;
		
		try {
			File input = new File("3/input.txt");
			Scanner sc = new Scanner(input);
			
			//Run through line, +1 if 1 -1 if 0, 
			//Then 1 majority = +ve number in map, -ve if 0 majority
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				for (int i = 0; i < line.length(); i++) {
					Integer num = Integer.valueOf(line.substring(i, i+1));
					Integer diff = num == 1 ? 1 : -1;
					map.put(i, map.getOrDefault(i, 0) + diff);
				}
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} 
		
		StringBuilder gammaStr = new StringBuilder();
		StringBuilder epsilonStr = new StringBuilder();
		
		for (Integer i : map.keySet()) {
			String g = map.get(i) > 0 ? "1" : "0";
			String e = map.get(i) > 0 ? "0" : "1";
			gammaStr.append(g);
			epsilonStr.append(e);
		}
		
		gamma = Integer.parseInt(gammaStr.toString(), 2);
		epsilon = Integer.parseInt(epsilonStr.toString(), 2);
		
		return gamma*epsilon;
	}
	
	public static int powerConsumption2() {
		List<String> strings = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		
		try {
			File input = new File("3/input.txt");
			Scanner sc = new Scanner(input);
			
			//Run through line, +1 if 1 -1 if 0, 
			//Then 1 majority = +ve number in map, -ve if 0 majority
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				strings.add(line);
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		
		String oxygen = getNumber(strings, 0, true);
		String c02 = getNumber(strings, 0, false);
		
		int o = Integer.parseInt(oxygen.toString(), 2);
		int c = Integer.parseInt(c02.toString(), 2);
		
		return o * c;
		
	}
	
	/**
	 * List of strings to go through
	 * Find most common, filter out the bad boys that don't fit
	 * o2Flag, true for o2, false for co2
	 * @param binStrings
	 * @param iter
	 * @param o2Flag
	 * @return
	 */
	private static String getNumber(List<String> binStrings, int iter, boolean o2Flag) {
		if (binStrings.size() == 1) return binStrings.get(0);
		int value = 0;
		List<String> nextStrings = new ArrayList<>();
		for (int i = 0; i < binStrings.size(); i++) {
			Integer v = Integer.valueOf(binStrings.get(i).substring(iter, iter+1));
			int diff = v == 1 ? 1 : -1;
			value += diff;
		}
		
		for (int i = 0; i < binStrings.size(); i++) {
			Integer v = Integer.valueOf(binStrings.get(i).substring(iter, iter+1));
			if ((o2Flag && (value >= 0 && v != 1 || value < 0 && v != 0)) || (!o2Flag && (value >= 0 && v != 0 || value < 0 && v != 1))) {
				continue;
			} else {
				nextStrings.add(binStrings.get(i));
			}
		}
		
		iter++;
		return getNumber(nextStrings, iter, o2Flag);
	}
	
	public static void main(String[] args) {
		System.out.println("Final value: " + powerConsumption());
		System.out.println("Final value2: " + powerConsumption2());
	}
}
