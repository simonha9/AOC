package com.simon.AOC;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day5 {
	
	static class Coordinate {
		private int x;
		private int y;
		
		public Coordinate() {}
		
		public Coordinate(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		
		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
		
		
	}
	
	
	/**
	 * 
	 * @return
	 */
	public static int answer() {
		int res = 0;
		Integer[][] arr = new Integer[1000][1000];
		init(arr);
		try {
			File input = new File("5/input.txt");
			Scanner sc = new Scanner(input);
			
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] coords = line.trim().split("->");
				String[] coord1 = coords[0].trim().split(",");
				String[] coord2 = coords[1].trim().split(",");
				
				Coordinate c1 = new Coordinate(Integer.valueOf(coord1[0]), Integer.valueOf(coord1[1]));
				Coordinate c2 = new Coordinate(Integer.valueOf(coord2[0]), Integer.valueOf(coord2[1]));
				
				if (c1.getY() == c2.getY()) {
					//Horiz line
					for (int i = Math.min(c1.getX(), c2.getX()); i <= Math.max(c1.getX(), c2.getX()); i++) {
						arr[c1.getY()][i]++;
					}
				} else if (c1.getX() == c2.getX()) {
					//Vert line
					for (int i = Math.min(c1.getY(), c2.getY()); i <= Math.max(c1.getY(), c2.getY()); i++) {
						arr[i][c1.getX()]++;
					}
				} else {
					//Diag line
					
					
				}
			}
			
			sc.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} 
//		print(arr);
		
		int add = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				add = arr[i][j] >= 2 ? 1 : 0;
				res += add;
			}
		}
		
		return res;
	}
	
	private static void init(Integer[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = 0;
			}
		}
	}
	
	private static void print(Integer[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static int answer2() {
		int res = 0;
		Integer[][] arr = new Integer[1000][1000];
		init(arr);
		try {
			File input = new File("5/input.txt");
			Scanner sc = new Scanner(input);
			
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] coords = line.trim().split("->");
				String[] coord1 = coords[0].trim().split(",");
				String[] coord2 = coords[1].trim().split(",");
				
				Coordinate c1 = new Coordinate(Integer.valueOf(coord1[0]), Integer.valueOf(coord1[1]));
				Coordinate c2 = new Coordinate(Integer.valueOf(coord2[0]), Integer.valueOf(coord2[1]));
				
				if (c1.getY() == c2.getY()) {
					//Horiz line
					for (int i = Math.min(c1.getX(), c2.getX()); i <= Math.max(c1.getX(), c2.getX()); i++) {
						arr[c1.getY()][i]++;
					}
				} else if (c1.getX() == c2.getX()) {
					//Vert line
					for (int i = Math.min(c1.getY(), c2.getY()); i <= Math.max(c1.getY(), c2.getY()); i++) {
						arr[i][c1.getX()]++;
					}
				} else {
					//Diag line
					//2 Cases, (1,1) -> (3,3) or (3,3) -> (1,1)
					//or (7,9) -> (9,7)
					if (c1.getX() <= c2.getX() && c1.getY() <= c2.getY()) {
						System.out.println("case 1");
						for (int i = 0; i <= c2.getX() - c1.getX(); i++) {
							arr[c1.getY() + i][c1.getX() + i]++;
						}
					} else if (c2.getX() <= c1.getX() && c2.getY() <= c1.getY()) {
						System.out.println("case 2");
						for (int i = 0; i <= c1.getX() - c2.getX(); i++) {
							arr[c2.getY() + i][c2.getX() + i]++;
						}
					} else if (c1.getX() <= c2.getX() && c2.getY() <= c1.getY()) {
						System.out.println("case 3");
						for (int i = 0; i <= c2.getX() - c1.getX(); i++) {
							arr[c1.getY() - i][c1.getX() + i]++;
						}
					} else {
						System.out.println("case 4");
						for (int i = 0; i <= c1.getX() - c2.getX(); i++) {
							arr[c2.getY() - i][c2.getX() + i]++;
						}
					}
					
				}
			}
			
			sc.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} 
//		print(arr);
		
		int add = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				add = arr[i][j] >= 2 ? 1 : 0;
				res += add;
			}
		}
		
		return res;
	}
	
	
	public static void main(String[] args) {
//		System.out.println("Answer: " + answer());
		System.out.println("Answer2: " + answer2());
	}
	
}
