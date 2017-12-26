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
		checkers.print();
		//if(checkers.isWin()) {
		//	System.out.println("遊戲結束");
		//	return;
		//}		
/*		for(Position p: checkers.getJumpableCset()) {
			for(Direction d: checkers.getJumpable(p)) {
				checkers.jump(p, d);
				System.out.println("跳");
				dfs(checkers);
			}
		}*/
		for(Position p: checkers.getMovableCset()) {
			for(Direction d: checkers.getMovable(p)) {
				checkers.move(p, d);
				System.out.println("移");
				dfs(checkers);
			}
		}
	}
}
