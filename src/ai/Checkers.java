package ai;

public class Checkers {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public enum Direction {
		LEFT(-1, 0), LEFTUP(-1, 1), RIGHTUP(0, 1), RIGHT(1, 0), RIGHTDOWN(1, -1), LEFTDOWN(0, -1);
		public int x;
		public int y;

		private Direction(int x, int y) {
			this.x = x;
			this.y = y;
		};
	}

	// 玩家人數
	int playerNum;
	// 目前玩家
	int player;

	final int SIZE = 19;
	int[][] map = new int[][] { { 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 },
			{ 9, 8, 8, 8, 8, 1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9 },
			{ 9, 8, 8, 8, 8, 1, 1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9 },
			{ 9, 8, 8, 8, 8, 1, 1, 1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9 },
			{ 9, 8, 8, 8, 8, 1, 1, 1, 1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9 },
			{ 9, 5, 5, 5, 5, 0, 0, 0, 0, 0, 6, 6, 6, 6, 8, 8, 8, 8, 9 },
			{ 9, 8, 5, 5, 5, 0, 0, 0, 0, 0, 0, 6, 6, 6, 8, 8, 8, 8, 9 },
			{ 9, 8, 8, 4, 5, 0, 0, 0, 0, 0, 0, 0, 6, 6, 8, 8, 8, 8, 9 },
			{ 9, 8, 8, 8, 5, 0, 0, 0, 0, 0, 0, 0, 0, 6, 8, 8, 8, 8, 9 },
			{ 9, 8, 8, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8, 8, 8, 9 },
			{ 9, 8, 8, 8, 8, 3, 0, 0, 0, 0, 0, 0, 0, 0, 2, 8, 8, 8, 9 },
			{ 9, 8, 8, 8, 8, 3, 3, 0, 0, 0, 0, 0, 0, 0, 2, 2, 8, 8, 9 },
			{ 9, 8, 8, 8, 8, 3, 3, 3, 0, 0, 0, 0, 0, 0, 2, 2, 2, 8, 9 },
			{ 9, 8, 8, 8, 8, 3, 3, 3, 3, 0, 0, 0, 0, 0, 2, 2, 2, 2, 9 },
			{ 9, 8, 8, 8, 8, 8, 8, 8, 8, 8, 4, 4, 4, 4, 8, 8, 8, 8, 9 },
			{ 9, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 4, 4, 4, 8, 8, 8, 8, 9 },
			{ 9, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 4, 4, 8, 8, 8, 8, 9 },
			{ 9, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 4, 8, 8, 8, 8, 9 },
			{ 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 }, };

	public Checkers(int playerNum) {
		this.playerNum = playerNum;
		this.player = 1;
	}

	public void start() {
		print();
	}

	public boolean move(int r, int c, Direction d) {
		if (map[r][c] == player && map[r + d.x][c + d.y] == 0) {
			map[r][c] = 0;
			map[r + d.x][c + d.y] = (player + 1);
			player = (player % (playerNum+1) + 1);
			return true;
		}
		return false;
	}

	public int getPlayer() {
		return player;
	}

	void print() {

		for (int r = 0; r < SIZE; r++) {
			for (int s = 0; s < SIZE - r; s++) {
				System.out.print(' ');
			}
			for (int c = 0; c < SIZE; c++) {
				System.out.print(' ');
				colorPrint(map[r][c]);
			}
			System.out.println();
		}
	}

	void colorPrint(int chess) {
		switch (chess) {
		case 0:
			System.out.print("0");
			break;
		case 1:
		case 2:
			System.out.print("1");
			break;
		case 3:
		case 4:
			System.out.print("3");
			break;
		case 5:
		case 6:
			System.out.print("5");
			break;
		case 8:
			System.out.print(' ');
			break;
		case 9:
			System.out.print('o');
			break;

		}
	}
}
