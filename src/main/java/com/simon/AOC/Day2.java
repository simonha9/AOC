package com.simon.AOC;

import java.io.File;
import java.util.Scanner;

public class Day2 {

	public static int calculateFinal() {
		int horiz = 0;
		int vert = 0;
		try {
			File input = new File("2/input.txt");
			Scanner sc = new Scanner(input);
			
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] lineArr = line.trim().split(" ");
				Integer number = Integer.valueOf(lineArr[1]);
				if (lineArr[0].toString().equals("forward")) {
					horiz += number;
				} else if (lineArr[0].toString().equals("down")) {
					vert += number;
				} else {
					vert -= number;
				}
			}
			
			sc.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} 
		
		return horiz*vert;
	}
	
	public static int calculateFinal2() {
		int horiz = 0;
		int vert = 0;
		int aim = 0;
		try {
			File input = new File("2/input.txt");
			Scanner sc = new Scanner(input);
			
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] lineArr = line.trim().split(" ");
				Integer number = Integer.valueOf(lineArr[1]);
				if (lineArr[0].toString().equals("forward")) {
					horiz += number;
					vert += aim*number;
				} else if (lineArr[0].toString().equals("down")) {
					aim += number;
				} else {
					aim -= number;
				}
			}
			
			sc.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} 
		
		return horiz*vert;
	}
	
	public static void main(String[] args) {
		System.out.println("Final pos: " + calculateFinal());
		System.out.println("Final pos2: " + calculateFinal2());
	}
}
