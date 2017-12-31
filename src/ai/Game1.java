package ai;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Game1 {

	static Scanner scanner = new Scanner(System.in);
	static Random random = new Random();

	// 步數
	static int maxTotalCount;
	static int totalCount;

	public static void main(String[] args) throws IOException, InterruptedException {

		System.out.printf("遊戲開始\n");

		// 遊戲進行100次
		maxTotalCount = Integer.MAX_VALUE;
		for (int time = 0; time < 1000; time++) {
			totalCount = Integer.MAX_VALUE;
			hillClimbing(Checkers.init(), 0);
			maxTotalCount = Math.min(maxTotalCount, totalCount);
		}

		System.out.printf("遊戲結束\n");
		System.out.printf("最少跳%d步\n", maxTotalCount);
	}

	// 二次方貪婪爬山演算法
	public static void hillClimbing(MySet chessSet, int count) {
		Checkers.print(chessSet);
		System.out.println(count);
		scanner.nextLine();

		if (Checkers.isWin(chessSet)) {
			totalCount = Math.min(totalCount, count);
			System.out.println("完成一個了" + count);
			return;
		}

		// if(count > 1000) {
		// System.out.println("太久了!!!!");
		// return;
		// }

		if (random.nextInt(10) > 2) {
			System.out.println("jump");
			goJump(chessSet, count);
		}
		else {
			System.out.println("move");
			goMove(chessSet, count);
		}
	}

	// 移動
	public static void goMove(MySet chessSet, int count) {
		Chess cc = null;
		Direction dd = null;

		outer: for (Chess c : chessSet.getRandom()) {
			for (Direction d : Checkers.getMovable(chessSet, c, 1)) {
				// 避免走回頭路
				if (chessSet.isRepeat(c.id, d)) {
					continue;
				}
				cc = c;
				dd = d;
				break outer;
			}
		}
		if (cc != null && dd != null) {
			// System.out.println(p + " move " + d.name());
			hillClimbing(Checkers.move(chessSet, cc.id, dd), count + 1);
		} else {
			hillClimbing(chessSet, count);
		}
	}

	// 跳躍
	
	static MyStack tempMaxStep = new MyStack();
	static MyStack tempStep = new MyStack();
	
	public static void goJump(MySet chessSet, int count) {
		List<MyStack> StepList = new ArrayList<MyStack>();

		for (Chess c : chessSet) {
			tempMaxStep.clear();
			tempStep.clear();
			tempStep.id = c.id;
			getMaxJump(chessSet, c.id);
			
			if (tempMaxStep.isEmpty()) {
				return;
			} else if (StepList.isEmpty()) {
				StepList.add(tempMaxStep);
			} else if (Direction.gatProgress(StepList.get(0)) < Direction.gatProgress(tempMaxStep)) {
				StepList.clear();
				StepList.add(tempMaxStep);
			} else if (Direction.gatProgress(StepList.get(0)) == Direction.gatProgress(tempMaxStep)) {
				StepList.add(tempMaxStep);
			}
		}

		if (StepList.isEmpty()) {
			System.out.println("沒有東西可以跳");
			hillClimbing(chessSet, count);
		}
		
		for (MyStack step : StepList) {
			keepJump(chessSet, step, count);
		}
	}
	
	public static void getMaxJump(MySet chessSet, int id) {

		for (Direction d : Checkers.getJumpable(chessSet, getChess(chessSet, id), 1)) {
			if (!tempStep.isEmpty() && tempStep.peek().isRepeat(d)) {
				continue;
			}
			tempStep.add(d);
			getMaxJump(Checkers.jump(chessSet, id, d), id);
		}

		if (Direction.gatProgress(tempMaxStep) < Direction.gatProgress(tempStep)) {
			tempMaxStep = tempStep;
		}
		if (!tempStep.isEmpty()) {
			tempStep.pop();
		}
	}

	private static void keepJump(MySet chessSet, MyStack step, int count) {
		for (Direction d : step) {
			chessSet = Checkers.jump(chessSet, step.id, d);
		}
		hillClimbing(chessSet, count + 1);
	}

	public static Chess getChess(Set<Chess> chessSet, int i) {
		for (Chess p : chessSet) {
			if (p.id == i)
				return p;
		}
		return null;
	}
}
