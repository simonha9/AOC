package com.simon.AOC;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

public class Day10 {
	
	
	static Map<String, String> bmap = new HashMap<>();
	static Map<String, Integer> pointsMap = new HashMap<>();
	static String openingBrackets = "({[<";
	static String endingBrackets = ")}]>";
	
	/**
	 * First find corrupted lines (im guessing any even lines first)
	 * @return
	 */
	public static long answer() {
		int res = 0;
		List<String> allLines = new ArrayList<>();
		try {
			File input = new File("10/input.txt");
			Scanner sc = new Scanner(input);
			while (sc.hasNextLine()) {
				allLines.add(sc.nextLine().trim());
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		initBMap();
		long score = getSyntaxScore(allLines);
		return score;
	}
	
	private static void initBMap() {
		bmap.put("(", ")");
		bmap.put("[", "]");
		bmap.put("{", "}");
		bmap.put("<", ">");
		pointsMap.put(")", 3);
		pointsMap.put("]", 57);
		pointsMap.put("}", 1197);
		pointsMap.put(">", 25137);
	}
	
	private static long getSyntaxScore(List<String> list) {
		long res = 0;
		
		for (String s : list) {
			res += pointsMap.getOrDefault(syntaxScoreForLine(s), 0);
		}
		
		return res;
	}
	
	/**
	 * Return the first offending string, empty otherwise
	 * @param s
	 * @param i
	 * @return
	 */
	private static String syntaxScoreForLine(String s) {
		Stack<String> stack = new Stack<>();
		String curCh = "";
		for (int i = 0; i < s.length() - 1; i++) {
			curCh = s.substring(i, i+1);
			if (openingBrackets.indexOf(curCh) >= 0) {
				stack.push(curCh);
			} else if (endingBrackets.indexOf(curCh) >= 0) {
				//No pair to go along with this ending bracket, its a stack (S) offender
				if (!curCh.equals(bmap.get(stack.pop()))) {
					return curCh;
				}
			}
		}
		return "";
	}
	
	/*
	 * PART 2
	 */
	
	/**
	 * 
	 * @return
	 */
	public static long answer2() {
		List<String> allLines = new ArrayList<>();
		try {
			File input = new File("10/input.txt");
			Scanner sc = new Scanner(input);
			while (sc.hasNextLine()) {
				allLines.add(sc.nextLine().trim());
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		initBMap2();
		long score = getSyntaxScore2(allLines);
		return score;
	}
	
	private static void initBMap2() {
		bmap.put("(", ")");
		bmap.put("[", "]");
		bmap.put("{", "}");
		bmap.put("<", ">");
		pointsMap.put(")", 3);
		pointsMap.put("]", 57);
		pointsMap.put("}", 1197);
		pointsMap.put(">", 25137);
	}
	
	private static long getSyntaxScore2(List<String> list) {
		long res = 0;
		List<String> corruptedLines = new ArrayList<>();
		List<Long> scores = new ArrayList<>();
		for (String s : list) {
			if (pointsMap.getOrDefault(syntaxScoreForLine(s), 0) > 0) corruptedLines.add(s);
		}
		
		list.removeAll(corruptedLines);
		for (String s : list ) {
			String addedItems = syntaxScoreForLine2(s);
			scores.add(calculateCompletionScore(addedItems));
		}
		
		Collections.sort(scores);
		return scores.get((int) Math.floor(scores.size()/2));
		
	}
	
	private static long calculateCompletionScore(String added) {
		long res = 0;
		String curCh = "";
		
		Map<String, Integer> completionPointsMap = new HashMap<>();
		completionPointsMap.put(")", 1);
		completionPointsMap.put("]", 2);
		completionPointsMap.put("}", 3);
		completionPointsMap.put(">", 4);
		
		
		for (int i = 0; i < added.length(); i++) {
			curCh = added.substring(i, i+1);
			res = (res*5) + completionPointsMap.get(curCh);
		}
		return res;
	}
	
	/**
	 * Return the first offending string, empty otherwise
	 * @param s
	 * @param i
	 * @return
	 */
	private static String syntaxScoreForLine2(String s) {
		StringBuilder added = new StringBuilder("");
		Stack<String> stack = new Stack<>();
		String curCh = "";
		for (int i = 0; i < s.length(); i++) {
			curCh = s.substring(i, i+1);
			if (openingBrackets.indexOf(curCh) >= 0) {
				stack.push(curCh);
			} else if (!stack.isEmpty()) {
				stack.pop();
			}
		}
		
		while (!stack.isEmpty()) {
			added.append(bmap.get(stack.pop()));
		}
		return added.toString();
	}
	
	public static void main(String[] args) {
//		System.out.println("Answer: " + answer());
		System.out.println("Answer2: " + answer2());
	}
	
}
