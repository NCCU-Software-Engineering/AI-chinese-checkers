package ai;

import java.util.Set;

public class Game {

	public static void main(String[] args) {

		Checkers checkers;

		// 下方走到上方
		System.out.println("question 1");
		checkers = new Checkers(1);
		checkers.start();
		int count = dfs(checkers, 0);
		System.out.println(count);

		/*
		 * // 下方走到左上方 System.out.println("question 2"); checkers = new Checkers(2);
		 * checkers.start();
		 * 
		 * // 特定位置有棋子 System.out.println("question 3"); checkers = new Checkers(3);
		 * checkers.start();
		 * 
		 * // * // 移動特定紅棋子 System.out.println("question 4"); checkers = new Checkers(4);
		 * checkers.start();
		 */
	}

	public static int dfs(Checkers checkers, int count) {
		clearConsole();
		checkers.print();
		while (!checkers.isWin()) {
			checkers.print();
			outerloop:
			for (Position p : checkers.cset) {
				//if(checkers.goal(p))
					//continue;
				for (Direction d : checkers.getMovable(p)) {
					System.out.println(p + " " + d.name());
					checkers.move(p, d);
					count ++;
					break outerloop;
				}
			}
		}
		return count;
	}

	public final static void clearConsole() {
		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (final Exception e) {
			// Handle any exceptions.
		}
	}
}
