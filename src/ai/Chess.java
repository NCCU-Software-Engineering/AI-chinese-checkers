package ai;

public class Chess implements Comparable<Chess> {
	public int x;
	public int y;
	public int id;

	Chess(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	};

	@Override
	public String toString() {
		return String.format("(%d, %d)", x, y);
	}

	public boolean equals(Chess p) {
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
	public int compareTo(Chess p) {
		return (p.x * 100 + p.y) - (this.x * 100 + this.y);
	}
	
}
