package ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class MySet extends TreeSet<Chess> {
	private static final long serialVersionUID = 1L;
	int id = -1;
	Direction d = null;

	public void setLast(int id, Direction d) {
		this.id = id;
		this.d = d;
	}

	public boolean isRepeat(int id, Direction d) {
		if (this.id == id && this.d.isRepeat(d))
			return true;
		else
			return false;
	}

	public List<Chess> getRandom() {
		List<Chess> list = new ArrayList<Chess>(this);
		Collections.shuffle(list);
		return list;
	}
}
