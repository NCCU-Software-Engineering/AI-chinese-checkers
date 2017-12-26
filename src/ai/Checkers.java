package ai;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import javafx.util.Pair;

public class Checkers {

	final int SIZE = 19;
	int[][] map;

	public Set<Position> cset = new HashSet<Position>();

	public Checkers(int i) {
		map = Chessboard.getBoard(i);
	}

	public void start() {
		// 讀取地圖
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

	// 判斷遊戲是否結束
	public boolean isWin() {
		for (Position p : cset) {
			if (map[p.x][p.y] != 2)
				return false;
		}
		return true;
	}

	// 取得目前所有棋子位置
	public Set<Position> getCset() {
		return cset;
	}

	// 得到所有可以移動的棋子
	public Set<Position> getMovableCset() {
		System.out.println("getMovableCset");
		cset.stream().filter(p -> isMovable(p));
		return cset;
	}

	public boolean isMovable(Position p) {
		for (Direction d : Direction.values()) {
			if (map[p.x + d.x][p.x + d.y] == 0 || map[p.x + d.x][p.x + d.y] == 1
					|| map[p.x + d.x * 2][p.x + d.y * 2] == 0) {
				return true;
			}
		}
		return false;
	}

	//public EnumSet<Direction> getMovable(Position p) {

	
	//	for (Direction d : Direction.values()) {
	//	}
	//}

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
