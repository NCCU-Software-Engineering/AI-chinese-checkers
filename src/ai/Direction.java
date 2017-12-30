package ai;

//

public enum Direction {
	LEFTUP(-1, -1), RIGHTUP(-1, 0), LEFT(0, -1), RIGHT(0, 1), RIGHTDOWN(1, 1), LEFTDOWN(1, 0);
	public final int x;
	public final int y;

	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	};

	public static Direction[] values(int i) {
		int random = (int) (Math.random() * 2);

		if (i == 1 && random == 0) {
			return new Direction[] { Direction.LEFTUP, Direction.RIGHTUP };
		} else if (i == 1 && random == 1) {
			return new Direction[] { Direction.RIGHTUP, Direction.LEFTUP };
		} else if (i == 2 && random == 0) {
			return new Direction[] { Direction.LEFT, Direction.RIGHT };
		} else if (i == 2 && random == 1) {
			return new Direction[] { Direction.RIGHT, Direction.LEFT };
		} else {
			return new Direction[] { Direction.LEFTDOWN, Direction.RIGHTDOWN };
		}
	}
	
	public boolean isRepeat(Direction d) {
		if(this.x + d.x == 0 && this.y + d.y == 0)
			return true;
		else
			return false;
	}
}
