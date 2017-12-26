package ai;

public class Game {

	public static void main(String[] args) {

		Checkers checkers;

		// 下方走到上方
		System.out.println("question 1");
		checkers = new Checkers(1);
		checkers.start();
		dfs(checkers);

		/*
		 * // 下方走到左上方 System.out.println("question 2"); checkers = new Checkers(2);
		 * checkers.start();
		 * 
		 * // 特定位置有棋子 System.out.println("question 3"); checkers = new Checkers(3);
		 * checkers.start();
		 * 
//		 * // 移動特定紅棋子 System.out.println("question 4"); checkers = new Checkers(4);
		 * checkers.start();
		 */
	}
	
	public static void dfs(Checkers checkers) {
		for(Position p: checkers.getMovableCset()) {
			if(checkers.isMovable(p)) {
				
			}
		}
	}
}
