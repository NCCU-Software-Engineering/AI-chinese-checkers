package ai;

public class Position implements Comparable<Position> {
	public int x;
	public int y;

	Position(int x, int y) {
		this.x = x;
		this.y = y;
	};
	
	@Override
	public String toString() {
		return String.format("(%d, %d)", x, y);
	}
	
	public boolean equals(Position p) {
		if(this.x == p.x && this.y == p.y) {
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Position p) {
		return this.x - p.x;
	}
}
