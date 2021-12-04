package com.simon.AOC;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day4 {
	
	/*
	 * PART ONE - FIRST TO WIN
	 * 
	 */
	
	/**
	 * BINGO
	 * Pseudo code - read the first line into list of numbers
	 * For each number
	 * 	Go through board and mark number
	 * 	Check that number diag - horiz - vert if match stop and sum and return
	 * 
	 * @return
	 */
	public static int calculateSum() {
		int res = 0;
		List<Integer> numbersDrawn = new ArrayList<>();
		List<Integer[][]> boards = new ArrayList<>();
		try {
			File input = new File("4/input.txt");
			Scanner sc = new Scanner(input);
			
			numbersDrawn = Arrays.asList(sc.nextLine().split(",")).stream().map(e -> Integer.valueOf(e)).collect(Collectors.toList());
			int counter = 0;
			Integer[][] board = new Integer[5][5];
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (line.trim().isEmpty()) continue;
				String[] numbers = line.trim().split("\\s+");
				for (int col = 0; col < 5; col++) {
					board[counter][col] = Integer.valueOf(numbers[col]);
				}
				if (counter == 4) {
					Integer[][] copy = Arrays.stream(board).map(Integer[]::clone).toArray(Integer[][]::new);
					boards.add(copy);
					counter = 0;
					continue;
				}
				counter++;
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} 
		
		return getAnswer(numbersDrawn, boards);
	}
	
	private static int getAnswer(List<Integer> numbersDrawn, List<Integer[][]> boards) {
		int res = 0;
		int winningNum = 0;
		for (int num : numbersDrawn) {
			for (int i = 0; i < boards.size(); i++) {
				Integer[][] currentBoard = boards.get(i);
				if (markBoard(num, currentBoard) && checkWinner(currentBoard)) {
					for (int j = 0; j < 5; j++) {
						for (int k = 0; k < 5; k++) {
							res += currentBoard[j][k] != -1 ? currentBoard[j][k] : 0;
						}
					}
					winningNum = num;
					return res*winningNum;
				}
			}
		}
		
		return -1;
	}
	
	private static boolean markBoard(int number, Integer[][] board) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (board[i][j] == number) {
					board[i][j] = -1;
					return true;
				}
			}
		}
		return false;
	}
	
	private static boolean checkWinner(Integer[][] board) {
		//Check rows
		for (int i = 0; i < 5; i++) {
			boolean possibleRow = true;
			boolean possibleColumn = true;
			for (int j = 0; j < 5; j++) {
				possibleRow = board[i][j] == -1 && possibleRow;
				possibleColumn = board[j][i] == -1 && possibleColumn;
			}
			if (possibleRow || possibleColumn) return true;
		}
		
		//Check diagonals
		boolean possibleDiagLeft = true;
		boolean possibleDiagRight = true;
		for (int i = 0; i < 5; i++) {
			possibleDiagLeft = board[i][i] == -1 && possibleDiagLeft;
			possibleDiagRight = board[4 - i][4 - i] == -1 && possibleDiagRight;
		}
		if (possibleDiagLeft || possibleDiagRight) return true;
		
		return false;
	}
	
	
	/*
	 * PART 2 LAST TO WIN
	 * 
	 */
	
	/**
	 * BINGO
	 * Pseudo code - read the first line into list of numbers
	 * For each number
	 * 	Go through board and mark number
	 * 	Check that number diag - horiz - vert if match stop and sum and return
	 * 
	 * @return
	 */
	public static int calculateSum2() {
		int res = 0;
		List<Integer> numbersDrawn = new ArrayList<>();
		List<Integer[][]> boards = new ArrayList<>();
		try {
			File input = new File("4/input.txt");
			Scanner sc = new Scanner(input);
			
			numbersDrawn = Arrays.asList(sc.nextLine().split(",")).stream().map(e -> Integer.valueOf(e)).collect(Collectors.toList());
			int counter = 0;
			Integer[][] board = new Integer[5][5];
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (line.trim().isEmpty()) continue;
				String[] numbers = line.trim().split("\\s+");
				for (int col = 0; col < 5; col++) {
					board[counter][col] = Integer.valueOf(numbers[col]);
				}
				if (counter == 4) {
					Integer[][] copy = Arrays.stream(board).map(Integer[]::clone).toArray(Integer[][]::new);
					boards.add(copy);
					counter = 0;
					continue;
				}
				counter++;
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		} 
		
		return getAnswer2(numbersDrawn, boards);
	}
	
	private static int getAnswer2(List<Integer> numbersDrawn, List<Integer[][]> boards) {
		int res = 0;
		int winningNum = 0;
		List<Integer> winningBoards = new ArrayList<>();
		for (int num : numbersDrawn) {
			for (int i = 0; i < boards.size(); i++) {
				if (winningBoards.contains(i)) continue;
				Integer[][] currentBoard = boards.get(i);
				//If there is a winner
				if (markBoard2(num, currentBoard) && checkWinner2(currentBoard)) {
					if (winningBoards.size() == boards.size() - 1) {
						for (int j = 0; j < 5; j++) {
							for (int k = 0; k < 5; k++) {
								res += currentBoard[j][k] != -1 ? currentBoard[j][k] : 0;
							}
						}
						winningNum = num;
						return winningNum * res;
					} else {
						winningBoards.add(i);
					}
				}
			}
		}
		return -1;
	}
	
	private static boolean markBoard2(int number, Integer[][] board) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (board[i][j] == number) {
					board[i][j] = -1;
					return true;
				}
			}
		}
		return false;
	}
	
	private static boolean checkWinner2(Integer[][] board) {
		//Check rows
		for (int i = 0; i < 5; i++) {
			boolean possibleRow = true;
			boolean possibleColumn = true;
			for (int j = 0; j < 5; j++) {
				possibleRow = board[i][j] == -1 && possibleRow;
				possibleColumn = board[j][i] == -1 && possibleColumn;
			}
			if (possibleRow || possibleColumn) return true;
		}
		
		//Check diagonals
		boolean possibleDiagLeft = true;
		boolean possibleDiagRight = true;
		for (int i = 0; i < 5; i++) {
			possibleDiagLeft = board[i][i] == -1 && possibleDiagLeft;
			possibleDiagRight = board[4 - i][4 - i] == -1 && possibleDiagRight;
		}
		if (possibleDiagLeft || possibleDiagRight) return true;
		
		return false;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println("Answer: " + calculateSum());
		System.out.println("Answer2: " + calculateSum2());
	}
	
}
