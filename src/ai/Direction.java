package ai;

//

public enum Direction {
	LEFTUP(-1, -1), RIGHTUP(-1, 0), LEFT(0, -1), RIGHT(0, 1), RIGHTDOWN(1, -1), LEFTDOWN(0, -1);
	public final int x;
	public final int y;

	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	};

	public Direction[] values(int i) {
		if (i == 1) {
			return new Direction[] { Direction.LEFTUP, Direction.RIGHTUP };
		} else if (i == 2) {
			return new Direction[] { Direction.LEFT, Direction.RIGHT };
		} else {
			return new Direction[] { Direction.LEFTDOWN, Direction.RIGHTDOWN };
		}
	}
}
