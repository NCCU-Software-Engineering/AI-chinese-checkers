package ai;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.util.Pair;

public class Checkers {

	final int SIZE = 19;
	int[][] map;

	public Set<Position> cset = new HashSet<Position>();

	public Checkers(int i) {
		map = Chessboard.getBoard(i);
	}

	public void start() {
		// Ū���a��
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

	// �P�_�C���O�_����
	public boolean isWin() {
		for (Position p : cset) {
			if (map[p.x][p.y] != 2)
				return false;
		}
		return true;
	}

	// ���o�ثe�Ҧ��Ѥl��m
	public Set<Position> getCset() {
		return cset;
	}

	// �o��Ҧ��i�H���ʪ��Ѥl
	public Set<Position> getMovableCset() {
		return cset.stream().filter(p -> isMovable(p)).collect(Collectors.toSet());
	}
	
	// ���լO�_�i�H����
	public boolean isMovable(Position p) {
		for (Direction d : Direction.values()) {
			if (map[p.x + d.x][p.x + d.y] == 0) {
				return true;
			}
		}
		return false;
	}
	
	// ���o�Ҧ��i�H���ʤ�V
	public EnumSet<Direction> getMovable(Position p) {
		EnumSet<Direction> ans = EnumSet.noneOf(Direction.class);
		for (Direction d : Direction.values()) {
			if (map[p.x + d.x][p.x + d.y] == 0) {
				ans.add(d);
			}
		}
		return ans;
	}
	
	// �o��Ҧ��i�H���D���Ѥl
	public Set<Position> getJumpableCset() {
		return cset.stream().filter(p -> isJumpable(p)).collect(Collectors.toSet());
	}
	
	// ���լO�_�i�H���D
	public boolean isJumpable(Position p) {
		for (Direction d : Direction.values()) {
			if (map[p.x + d.x][p.x + d.y] == 1 && map[p.x + d.x * 2][p.x + d.y * 2] == 0) {
				return true;
			}
		}
		return false;
	}

	// ���o�Ҧ��i�H���D��V
	public EnumSet<Direction> getJumpable(Position p) {
		EnumSet<Direction> ans = EnumSet.noneOf(Direction.class);
		for (Direction d : Direction.values()) {
			if (map[p.x + d.x][p.x + d.y] == 1 && map[p.x + d.x * 2][p.x + d.y * 2] == 0) {
				ans.add(d);
			}
		}
		return ans;
	}

	// ���ʴѤl
	public void move(Position p, Direction d) {
		p.x += d.x;
		p.y += d.y;
	}

	//���D�Ѥl
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
