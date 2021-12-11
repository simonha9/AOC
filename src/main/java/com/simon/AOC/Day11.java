package com.simon.AOC;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import com.simon.AOC.Day9.Coordinate;

public class Day11 {

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

	static class Octo {
		private int energy;
		private boolean hasFlashed;
		private boolean isVisited;

		public Octo(int energy) {
			super();
			this.energy = energy;
			this.hasFlashed = false;
			this.isVisited = false;
		}

		public int getEnergy() {
			return energy;
		}

		public void setEnergy(int energy) {
			this.energy = energy;
		}

		@Override
		public String toString() {
			return "" + energy;
		}

		public void increaseEnergy() {
			this.energy++;
			if (this.energy > 9)
				setHasFlashed(true);
		}

		public boolean hasFlashed() {
			return hasFlashed;
		}

		public void setHasFlashed(boolean hasFlashed) {
			this.hasFlashed = hasFlashed;
		}

		public boolean isVisited() {
			return isVisited;
		}

		public void setVisited(boolean isVisited) {
			this.isVisited = isVisited;
		}
		
		public void reset() {
			this.energy = 0;
			this.hasFlashed = false;
			this.isVisited = false;
		}
		
		

	}

	static Octo[][] arr = new Octo[10][10];

	public static int answer(int steps) {
		int res = 0;
		int row = 0;
		try {
			File input = new File("11/input.txt");
			Scanner sc = new Scanner(input);

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				for (int i = 0; i < line.trim().length(); i++) {
					arr[row][i] = new Octo(Integer.valueOf(line.substring(i, i + 1)));
				}
				row++;
			}

			sc.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}

//		printArr();
		int numFlashes = getNumFlashes(steps);
		return numFlashes;
	}

	private static void printArr() {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	private static int getNumFlashes(int steps) {
		int numFlashes = 0;
		int curFlashes = 0;
		for (int i = 0; i < steps; i++) {
			curFlashes = getNumFlashesOneStep();
			if (curFlashes == 100) System.out.println("First step all synch: " + i);
			numFlashes += curFlashes;
		}

		return numFlashes;
	}

	/**
	 * Note that a octo can flash as long as the energy is over 9
	 * 
	 * @return
	 */
	private static int getNumFlashesOneStep() {
		int numFlashes = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				increaseOctoEnergy(i, j);
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i][j].hasFlashed()) {
					numFlashes++;
					arr[i][j].reset();
				}
			}
		}
		
//		printArr();
//		System.out.println(numFlashes);
//		System.out.println();
		return numFlashes;
	}

	private static void increaseOctoEnergy(int row, int col) {
		arr[row][col].increaseEnergy();

		if (arr[row][col].hasFlashed()) {
			Queue<Coordinate> q = new LinkedList<>();
			q.add(new Coordinate(row, col));
			
			while (!q.isEmpty()) {
				Coordinate curOcto = q.poll();
				
				if (curOcto.getI() < 0 || curOcto.getI() >= arr.length
						|| curOcto.getJ() < 0 || curOcto.getJ() >= arr.length) continue;
				
				Octo o = arr[curOcto.getI()][curOcto.getJ()];
				o.increaseEnergy();
				
				//If visited, we've actually seen this guy before, skip
				if (o.hasFlashed() && !o.isVisited()) {
					o.setVisited(true);
					
					//Add neighbors
					int curRow = curOcto.getI();
					int curCol = curOcto.getJ();
					q.add(new Coordinate(curRow - 1, curCol - 1));
					q.add(new Coordinate(curRow - 1, curCol));
					q.add(new Coordinate(curRow - 1, curCol + 1));
					q.add(new Coordinate(curRow, curCol - 1));
					q.add(new Coordinate(curRow, curCol + 1));
					q.add(new Coordinate(curRow + 1, curCol - 1));
					q.add(new Coordinate(curRow + 1, curCol));
					q.add(new Coordinate(curRow + 1, curCol + 1));
				}
			}
		}

	}

	public static void main(String[] args) {
		System.out.println("Answer: " + answer(300));
	}

}
