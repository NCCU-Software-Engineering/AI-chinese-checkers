package ai;

//, RIGHTDOWN(1, -1), LEFTDOWN(0, -1)

public enum Direction {
	LEFTUP(-1, -1), RIGHTUP(0, -1), LEFT(-1, 0), RIGHT(1, 0);
	public int x;
	public int y;

	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	};
}
