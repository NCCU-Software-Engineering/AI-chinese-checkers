package ai;

import java.util.EnumSet;
import java.util.Set;
import java.util.TreeSet;

public class Checkers {

	private static final int SIZE = 21;
	private static final int[][] map = Chessboard.map1;

	// 取得最開始的cset
	public static Set<Position> init() {
		int[][] map = Chessboard.mapInit;
		int id = 0;
		Set<Position> cset = new TreeSet<Position>();

		for (int r = 0; r < SIZE; r++) {
			for (int c = 0; c < SIZE; c++) {
				if (map[r][c] == 1) {
					cset.add(new Position(id++, r, c));
				}
			}
		}
		return cset;
	}

	// 區得遊戲進度 70時完成
	public static int gatProgress(Set<Position> cset) {
		int i = 0;
		for (Position p : cset) {
			i += p.x;
		}
		return i;
	}

	// 判斷遊戲是否結束
	public static boolean isWin(Set<Position> cset) {
		if (gatProgress(cset) == 70) {
			System.out.println("贏了耶");
			return true;
		} else {
			return false;
		}
	}

	// 取得所有可以移動方向
	public static EnumSet<Direction> getMovable(Set<Position> cset, Position p, int i) {
		EnumSet<Direction> ans = EnumSet.noneOf(Direction.class);
		for (Direction d : Direction.values(i)) {
			if (map[p.x + d.x][p.y + d.y] == 0 || map[p.x + d.x][p.y + d.y] == 2) {
				if (!have(cset, p.x + d.x, p.y + d.y)) {
					ans.add(d);
				}
			}
		}
		return ans;
	}

	// 取得所有可以跳躍方向
	public static EnumSet<Direction> getJumpable(Set<Position> cset, Position p, int i) {
		EnumSet<Direction> ans = EnumSet.noneOf(Direction.class);
		for (Direction d : Direction.values(i)) {
			if (map[p.x + d.x * 2][p.y + d.y * 2] == 0 || map[p.x + d.x * 2][p.y + d.y * 2] == 2) {
				if (have(cset, p.x + d.x, p.y + d.y) && !have(cset, p.x + d.x * 2, p.y + d.y * 2)) {
					ans.add(d);
				}
			}
		}
		return ans;
	}

	// 移動棋子
	public static Set<Position> move(Set<Position> cset, Position pos, Direction d) {
		Set<Position> newSet = new TreeSet<Position>();
		for(Position p: cset) {
			if(p == pos) 
				newSet.add(new Position(p.id, p.x + d.x, p.y + d.y));
			else
				newSet.add(new Position(p.id, p.x, p.y));
				
		}
		return newSet;
	}

	// 跳躍棋子
	public static Set<Position> jump(Set<Position> cset, Position pos, Direction d) {
		Set<Position> newSet = new TreeSet<Position>();
		for(Position p: cset) {
			if(p == pos) 
				newSet.add(new Position(p.id, p.x + d.x *2, p.y + d.y*2));
			else
				newSet.add(new Position(p.id, p.x, p.y));
				
		}
		return newSet;
	}

	static void print(Set<Position> cset) {
		for (int r = 0; r < SIZE; r++) {
			for (int s = 0; s < SIZE - r; s++) {
				System.out.print(' ');
			}
			for (int c = 0; c < SIZE; c++) {
				System.out.print(' ');
				if (have(cset, r, c))
					System.out.print(1);
				else
					colorPrint(map[r][c]);
			}
			System.out.println();
		}
	}

	static void colorPrint(int chess) {
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
		case 8:
			System.out.print('h');
			break;
		case 9:
			System.out.print(' ');
			break;
		}
	}

	public static boolean have(Set<Position> cset, int x, int y) {
		for (Position p : cset) {
			if (p.x == x && p.y == y)
				return true;
		}
		return false;
	}

}
