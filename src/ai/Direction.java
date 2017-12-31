package ai;

public enum Direction {
	LEFTUP(-1, -1), RIGHTUP(-1, 0), LEFT(0, -1), RIGHT(0, 1), RIGHTDOWN(1, 1), LEFTDOWN(1, 0);
	public final int x;
	public final int y;

	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	};

	// 判斷重複移動
	public boolean isRepeat(Direction d) {
		if (this.x + d.x == 0 && this.y + d.y == 0)
			return true;
		else
			return false;
	}

	// 取得特定方向
	public static Direction[] values(int i) {

		if (i == 1) {
			return new Direction[] { Direction.LEFTUP, Direction.RIGHTUP, Direction.RIGHT, Direction.LEFT };
		} else if (i == 2) {
			return new Direction[] {};
		} else {
			return new Direction[] { Direction.LEFTDOWN, Direction.RIGHTDOWN };
		}
	}

	// 看看跳多遠
	public static int gatProgress(MyStack step) {

		int i = 0;
		for (Direction d : step) {
			if (d == LEFTUP || d == RIGHTUP) {
				i += 2;
			}
		}
		return i;
	}
}
