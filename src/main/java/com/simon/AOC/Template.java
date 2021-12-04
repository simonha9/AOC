package com.simon.AOC;

import java.io.File;
import java.util.Scanner;

public class Template {
	
	public static int answer() {
		int res = 0;
		try {
			File input = new File("4/input.txt");
			Scanner sc = new Scanner(input);
			
			
			sc.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} 
		
		return res;
	}
	
	
	public static void main(String[] args) {
		System.out.println("Answer: " + answer());
	}
	
}
