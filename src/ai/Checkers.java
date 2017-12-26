package ai;

import java.util.HashSet;
import java.util.Set;

public class Checkers {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_WHITE = "\u001B[37m";


	final int SIZE = 19;
	int[][] map;

	public Set<Position> cset = new HashSet<Position>();

	public Checkers(int i) {
		map = Chessboard.getBoard(i);
	}

	public void start() {
		// Åª¨ú¦a¹Ï
		for (int r = 0; r < SIZE; r++) {
			for (int c = 0; c < SIZE; c++) {
				if (map[r][c] == 1) {
					map[r][c] = 0;
					cset.add(new Position(r, c));
				}
			}
		}
		print();
	}

	public boolean move(int r, int c, Direction d) {
		if (map[r + d.x][c + d.y] == 0) {
			map[r][c] = 0;
			map[r + d.x][c + d.y] = 1;
			return true;
		}
		return false;
	}

	public int isWin() {
		return 0;
	}

	public Set<Position> getCset() {
		return cset;
	}

	public Set<Position> getMovableCset() {
		cset.stream().filter(p -> {
			if (map[p.x][p.x] == 0)
				return true;
			else
				return false;
		});
		return cset;
	}

	void print() {

		for (int r = 0; r < SIZE; r++) {
			for (int s = 0; s < SIZE - r; s++) {
				System.out.print(' ');
			}
			for (int c = 0; c < SIZE; c++) {
				System.out.print(' ');
				if (cset.contains(new Position(r, c)))
					colorPrint(1);
				else
					colorPrint(map[r][c]);
			}
			System.out.println();
		}
	}

	void colorPrint(int chess) {

		switch (chess) {
		case 0:
			System.out.print("o");
			break;
		case 1:
			System.out.print("1");
			break;
		case 2:
			System.out.print("2");
			break;
		case 3:
			System.out.print("3");
			break;
		case 9:
			System.out.print(' ');
			break;
		}
	}
}
