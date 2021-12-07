package com.simon.AOC;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day7 {
	
	/**
	 * Crabs, it's greedy? - need to find min cost to
	 * make all the numbers the same
	 * @return
	 */
	public static int answer() {
		int res = 0;
		List<Integer> positions = new ArrayList<>();
		try {
			File input = new File("7/input.txt");
			Scanner sc = new Scanner(input);
			String line = sc.nextLine();
			for (String s : line.trim().split(",")) {
				positions.add(Integer.valueOf(s));
			}
			
			sc.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		
		int target = getTarget(positions);
		res = calculateFuel(positions, target);
		
		return res;
	}
	
	private static int getTarget(List<Integer> positions) {
		Collections.sort(positions);
		return positions.get((int) Math.floor(positions.size() / 2));
	}
	
	private static int calculateFuel(List<Integer> positions, int target) {
		int total = 0;
		for (int i : positions) {
			total += Math.abs(i - target);
		}
		return total;
	}
	
	/*
	 * PART 2
	 */
	
	/**
	 * Crabs, it's greedy? - need to find min cost to
	 * make all the numbers the same
	 * 
	 * Find k such that it minimizes the equation (total cost equation)
	 * Simply find derivative of equation, notice that it is absolute and derivative is
	 * sgn(x).  Then rearrange and find that (where x = mean of values) x - 1/2 <= k <= x + 1/2
	 * Then, take ceiling of lower and floor of upper and compare
	 * @return
	 */
	public static int answer2() {
		int res = 0;
		List<Integer> positions = new ArrayList<>();
		try {
			File input = new File("7/input.txt");
			Scanner sc = new Scanner(input);
			String line = sc.nextLine();
			for (String s : line.trim().split(",")) {
				positions.add(Integer.valueOf(s));
			}
			
			sc.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		
		Double mean = getTarget2(positions);
		int target1 = (int) Math.ceil(mean - 1/2);
		int target2 = (int) Math.floor(mean + 1/2);
		res = calculateFuel2(positions, target1);
		res = Math.min(res, calculateFuel2(positions, target2));
		
		return res;
	}
	
	private static Double getTarget2(List<Integer> positions) {
		Double sum = (double) positions.stream().mapToInt(Integer::intValue).sum();
		return sum / positions.size();
	}
	
	private static int calculateFuel2(List<Integer> positions, int target) {
		int total = 0;
		for (int i : positions) {
			int n = Math.abs(i - target);
			total += ((n * (n+1)) / 2);
		}
		return total;
	}
	
	
	public static void main(String[] args) {
		System.out.println("Answer: " + answer());
		System.out.println("Answer2: " + answer2());
	}
	
}
