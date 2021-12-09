package com.simon.AOC;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Day9 {
	
	static class Coordinate {
		public int i;
		public int j;
		public Coordinate(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		public int getI() {
			return i;
		}
		public void setI(int i) {
			this.i = i;
		}
		public int getJ() {
			return j;
		}
		public void setJ(int j) {
			this.j = j;
		}
		
	}
	
	static int[][] arr = new int[100][100];
	
	public static int answer() {
		int res = 0;
		int row = 0;
		initArr(arr);
		try {
			File input = new File("9/input.txt");
			Scanner sc = new Scanner(input);
			while (sc.hasNextLine()) {
				String line = sc.nextLine().trim();
				for (int j = 0; j < line.length(); j++) {
					arr[row][j] = Integer.valueOf(line.substring(j, j+1));
				}
				row++;
			}
			
			sc.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		System.out.println("row: " + row);
		return getLowPointSum(arr);
	}
	
	private static void initArr(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = -1;
			}
		}
	}
	
	private static void printArr(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private static int getLowPointSum(int[][] arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i][j] >= 0) {
					if (isLowPoint(arr, i, j)) {
						sum += (1 + arr[i][j]);
					}
				}
			}
		}
		return sum;
	}
	
	private static boolean isLowPoint(int[][] arr, int row, int col) {
		int center = arr[row][col];
		
		if (row - 1 >= 0) { //Top row
			if (arr[row - 1][col] >= 0 && arr[row - 1][col] <= center) return false;
		}
		
		if (row >= 0 && row < arr.length) { //Middle row
			if (col - 1 >= 0 && arr[row][col - 1] >= 0 && arr[row][col - 1] <= center) return false;
			if (col + 1 < arr.length && arr[row][col + 1] >= 0 && arr[row][col + 1] <= center) return false;
		}
		
		if (row + 1 < arr.length) { //bot row
			if (arr[row + 1][col] >= 0 && arr[row + 1][col] <= center) return false;
		}
		
		return true;
	}
	
	/*
	 * PART 2
	 */
	
	public static int answer2() {
		int res = 0;
		int row = 0;
		initArr(arr);
		try {
			File input = new File("9/input.txt");
			Scanner sc = new Scanner(input);
			while (sc.hasNextLine()) {
				String line = sc.nextLine().trim();
				for (int j = 0; j < line.length(); j++) {
					arr[row][j] = Integer.valueOf(line.substring(j, j+1));
				}
				row++;
			}
			
			sc.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		System.out.println("row: " + row);
		getLowPointSum2();
		return getBasinSum2();
	}
	
	private static int getLowPointSum2() {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i][j] >= 0) {
					if (isLowPoint2(i, j)) {
						sum += (1 + arr[i][j]);
						//Mark this low point
						arr[i][j] = -2;
					}
				}
			}
		}
		return sum;
	}
	
	private static boolean isLowPoint2(int row, int col) {
		int center = arr[row][col];
		
		if (row - 1 >= 0) { //Top row
			if (arr[row - 1][col] >= 0 && arr[row - 1][col] <= center) return false;
		}
		
		if (row >= 0 && row < arr.length) { //Middle row
			if (col - 1 >= 0 && arr[row][col - 1] >= 0 && arr[row][col - 1] <= center) return false;
			if (col + 1 < arr.length && arr[row][col + 1] >= 0 && arr[row][col + 1] <= center) return false;
		}
		
		if (row + 1 < arr.length) { //bot row
			if (arr[row + 1][col] >= 0 && arr[row + 1][col] <= center) return false;
		}
		
		return true;
	}
	
	//For each, low point, do bfs from that point
	private static int getBasinSum2() {
		int sum = 0;
		
		List<Integer> basinSizes = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i][j] == -2) {
					basinSizes.add(findBasinSize2(i, j));
				}
			}
		}
		
		Collections.sort(basinSizes, Collections.reverseOrder());
		
		sum = basinSizes.get(0) * basinSizes.get(1) * basinSizes.get(2);
		return sum;
	}
	
	//BFS at this point - mark visited by making them -1
	private static int findBasinSize2(int row, int col) {
		int basinSize = 0;
		int curRow = 0;
		int curCol = 0;
		Queue<Coordinate> q = new LinkedList<>();
		q.add(new Coordinate(row-1, col));
		q.add(new Coordinate(row+1, col));
		q.add(new Coordinate(row, col-1));
		q.add(new Coordinate(row, col+1));
		while (!q.isEmpty()) {
			Coordinate current = q.poll();
			curRow = current.getI();
			curCol = current.getJ();
			
			if (curRow < 0 || curCol < 0 || 
					curRow >= arr.length || 
					curCol >= arr.length || 
					arr[curRow][curCol] == -1 || arr[curRow][curCol] == 9) continue;
			arr[curRow][curCol] = -1;
			basinSize++;
			q.add(new Coordinate(curRow-1, curCol));
			q.add(new Coordinate(curRow+1, curCol));
			q.add(new Coordinate(curRow, curCol-1));
			q.add(new Coordinate(curRow, curCol+1));
		}
		
		return basinSize;
	}
	
	public static void main(String[] args) {
//		System.out.println("Answer: " + answer());
		System.out.println("Answer2: " + answer2());
	}
	
}
