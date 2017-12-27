package ai;

//, RIGHTDOWN(1, -1), LEFTDOWN(0, -1)

public enum Direction {
	LEFTUP(-1, -1), RIGHTUP(-1, 0);// LEFT(0, -1), RIGHT(0, 1);
	public final int x;
	public final int y;

	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	};
}
