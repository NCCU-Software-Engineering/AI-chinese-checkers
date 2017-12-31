package ai;

import java.util.EnumSet;
import java.util.Set;
import java.util.TreeSet;

public class Checkers {

	private static final int SIZE = 21;
	private static final int[][] map = Chessboard.map1;

	// 取得最開始的chessSet
	public static Set<Chess> init() {
		int[][] map = Chessboard.mapInit;
		int id = 0;
		Set<Chess> chessSet = new TreeSet<Chess>();

		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < SIZE; y++) {
				if (map[x][y] == 1) {
					chessSet.add(new Chess(id++, x, y));
				}
			}
		}
		return chessSet;
	}

	// 區得遊戲進度 70時完成
	public static int gatProgress(Set<Chess> chessSet) {
		int progress = 0;
		for (Chess c : chessSet) {
			progress += c.x;
		}
		return progress;
	}

	// 判斷遊戲是否結束
	public static boolean isWin(Set<Chess> chessSet) {
		if (gatProgress(chessSet) == 70) {
			return true;
		} else {
			return false;
		}
	}

	// 取得所有可以移動方向
	public static EnumSet<Direction> getMovable(Set<Chess> chessSet, Chess c, int i) {
		EnumSet<Direction> ans = EnumSet.noneOf(Direction.class);
		for (Direction d : Direction.values(i)) {
			if (map[c.x + d.x][c.y + d.y] == 0 || map[c.x + d.x][c.y + d.y] == 2) {
				if (!have(chessSet, c.x + d.x, c.y + d.y)) {
					ans.add(d);
				}
			}
		}
		return ans;
	}

	// 取得所有可以跳躍方向
	public static EnumSet<Direction> getJumpable(Set<Chess> chessSet, Chess c, int i) {
		EnumSet<Direction> ans = EnumSet.noneOf(Direction.class);
		for (Direction d : Direction.values(i)) {
			if (map[c.x + d.x * 2][c.y + d.y * 2] == 0 || map[c.x + d.x * 2][c.y + d.y * 2] == 2) {
				if (have(chessSet, c.x + d.x, c.y + d.y) && !have(chessSet, c.x + d.x * 2, c.y + d.y * 2)) {
					ans.add(d);
				}
			}
		}
		return ans;
	}

	// 移動棋子
	public static Set<Chess> move(Set<Chess> chessSet, int id, Direction d) {
		Set<Chess> newSet = new TreeSet<Chess>();
		for(Chess c: chessSet) {
			if(c.id == id) 
				newSet.add(new Chess(c.id, c.x + d.x, c.y + d.y));
			else
				newSet.add(new Chess(c.id, c.x, c.y));
				
		}
		return newSet;
	}

	// 跳躍棋子
	public static Set<Chess> jump(Set<Chess> chessSet, int id, Direction d) {
		Set<Chess> newSet = new TreeSet<Chess>();
		for(Chess c: chessSet) {
			if(c.id == id) 
				newSet.add(new Chess(c.id, c.x + d.x *2, c.y + d.y*2));
			else
				newSet.add(new Chess(c.id, c.x, c.y));
				
		}
		return newSet;
	}
	
	// 複製
	public static Set<Chess> clone(Set<Chess> chessSet) {
		Set<Chess> newSet = new TreeSet<Chess>();
		for(Chess c: chessSet) {
			newSet.add(new Chess(c.id, c.x, c.y));
		}
		return newSet;
	}

	public static boolean have(Set<Chess> chessSet, int x, int y) {
		for (Chess p : chessSet) {
			if (p.x == x && p.y == y)
				return true;
		}
		return false;
	}
	
	static void print(Set<Chess> chessSet) {
		for (int x = 0; x < SIZE; x++) {
			for (int s = 0; s < SIZE - x; s++) {
				System.out.print(' ');
			}
			for (int y = 0; y < SIZE; y++) {
				System.out.print(' ');
				if (have(chessSet, x, y))
					System.out.print(1);
				else
					colorPrint(map[x][y]);
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

}
