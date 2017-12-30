package ai;

public class Position implements Comparable<Position> {
	public int x;
	public int y;
	public int id;

	Position(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	};

	@Override
	public String toString() {
		return String.format("(%d, %d)", x, y);
	}

	public boolean equals(Position p) {
		if (this.x == p.x && this.y == p.y) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
	     return this.toString().hashCode();
	}

	@Override
	public int compareTo(Position p) {
		return (p.x * 100 + p.y) - (this.x * 100 + this.y);
	}
}
