package ai;

import java.util.EnumSet;
import java.util.Set;
import java.util.TreeSet;

public class Checkers {

	final int SIZE = 21;
	int[][] map;

	public Set<Position> cset = new TreeSet<Position>();

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
	}

	// 判斷遊戲是否結束
	public boolean isWin() {
		for (Position p : cset) {
			if (map[p.x][p.y] != 2)
				return false;
		}
		return true;
	}

	// 取得所有可以移動方向
	public EnumSet<Direction> getMovable(Position p) {
		EnumSet<Direction> ans = EnumSet.noneOf(Direction.class);
		for (Direction d : Direction.values()) {
			if (map[p.x + d.x][p.y + d.y] == 0 || map[p.x + d.x][p.y + d.y] == 2) {
				if (!have(p.x + d.x, p.y + d.y)) {
					ans.add(d);
				}
			}
		}
		return ans;
	}

	// 取得所有可以跳躍方向
	public EnumSet<Direction> getJumpable(Position p) {
		EnumSet<Direction> ans = EnumSet.noneOf(Direction.class);
		for (Direction d : Direction.values()) {
			if (cset.contains(new Position(p.x + d.x, p.y + d.y)) && map[p.x + d.x * 2][p.y + d.y * 2] == 0
					&& !cset.contains(new Position(p.x + d.x * 2, p.y + d.y * 2))) {
				ans.add(d);
			}
		}
		return ans;
	}

	// 移動棋子
	public void move(Position p, Direction d) {
		p.x += d.x;
		p.y += d.y;
	}

	// 跳躍棋子
	public void jump(Position p, Direction d) {
		p.x += d.x * 2;
		p.y += d.y * 2;
	}

	void print() {
		for (int r = 0; r < SIZE; r++) {
			for (int s = 0; s < SIZE - r; s++) {
				System.out.print(' ');
			}
			for (int c = 0; c < SIZE; c++) {
				System.out.print(' ');
				if (have(r, c))
					System.out.print(1);
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
		case 8:
			System.out.print('h');
			break;
		case 9:
			System.out.print(' ');
			break;
		}
	}

	public boolean have(int x, int y) {
		for (Position p : cset) {
			if (p.x == x && p.y == y)
				return true;
		}
		return false;
	}
	
	public boolean goal(Position p) {
		if(map[p.x][p.y] == 2)
				return true;
		return false;
	}

}
