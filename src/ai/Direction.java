package ai;

public enum Direction {
	LEFT(-1, 0), LEFTUP(-1, 1), RIGHTUP(0, 1), RIGHT(1, 0), RIGHTDOWN(1, -1), LEFTDOWN(0, -1);
	public int x;
	public int y;

	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	};
}
