package ai;

public class Position {
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
	
	@Override
	public int hashCode() {
		return x * 100 + y;
	}

	@Override
	public boolean equals(Object that) {
		return true;
	}
	
	public boolean equals(Position p) {
		if(this.x == p.x && this.y == p.y) {
			return true;
		}
		return false;
	}
}
