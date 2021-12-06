package com.simon.AOC;

import java.io.File;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day6 {
	
	
	public static BigInteger answer(int days) {
		Map<Integer, BigInteger> fishies = new HashMap<>();
		try {
			File input = new File("6/input.txt");
			Scanner sc = new Scanner(input);
			
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] fishStr = line.trim().split(",");
				for (String f : fishStr) {
					fishies.put(Integer.valueOf(f), fishies.getOrDefault(Integer.valueOf(f), BigInteger.valueOf(0)).add(BigInteger.valueOf(1)));
				}
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		
//		for (Integer f : fishies.keySet()) {
//			System.out.println("f: " + f + ", num: " + fishies.get(f));
//		}
//		
		BigInteger numLanternFish = simulateLanternFishAfterDays(fishies, days);
		
		return numLanternFish;
	}
	
	private static BigInteger simulateLanternFishAfterDays(Map<Integer, BigInteger> fishies, int days) {
		Map<Integer, BigInteger> newFishies = new HashMap<>();
		BigInteger numFishies = BigInteger.valueOf(0);
		for (int i = 0; i < days; i++) {
			newFishies.clear();
			for (Integer fish : fishies.keySet().stream().sorted().collect(Collectors.toList())) {
				if (fish == 0) {
					//Reset to 6 spawn new one at 8
					BigInteger numSpawn = fishies.get(fish);
					newFishies.put(6, numSpawn);
					newFishies.put(8, numSpawn);
					newFishies.put(0, BigInteger.valueOf(0));
				} else {
					//Otherwise, decrease all in current by one, put in newFishies to 
					//accomodate for the 6 and 8
					newFishies.put(fish - 1, newFishies.getOrDefault(fish - 1, BigInteger.valueOf(0)).add(fishies.get(fish)));
				}
			}
			fishies.clear();
			fishies.putAll(newFishies);
		}
		for (int f : fishies.keySet()) {
			numFishies = numFishies.add(fishies.get(f));
		}
		return numFishies;
	}
	
	
	public static void main(String[] args) {
		System.out.println("Answer: " + answer(80));
	}
	
}
