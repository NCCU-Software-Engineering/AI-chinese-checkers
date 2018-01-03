package ai;

import java.io.FileWriter;
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

	static int a;
	static int b;

	static String sMax;
	static StringBuffer s = new StringBuffer();

	static FileWriter fw;

	public static void main(String[] args) throws IOException, InterruptedException {

		System.out.printf("甲\n");
		maxTotalCount = Integer.MAX_VALUE;
		a = 1;
		b = 2;
		for (int time = 0; time < 50000; time++) {
			totalCount = Integer.MAX_VALUE;
			s.delete(0, s.length());
			hillClimbing(Checkers.init(1), 0);
			if(maxTotalCount > totalCount) {
				maxTotalCount = totalCount;
				sMax = s.toString();
			}
		}
		System.out.printf("遊戲結束\n");
		System.out.printf("最少跳%d步\n", maxTotalCount);

		fw = new FileWriter("result1.txt");
		fw.write(sMax);
		fw.write("總計 " + maxTotalCount + " 步\n");
		fw.close();

		System.out.printf("乙\n");
		maxTotalCount = Integer.MAX_VALUE;
		a = 3;
		b = 4;
		for (int time = 0; time < 50000; time++) {
			totalCount = Integer.MAX_VALUE;
			s.delete(0, s.length());
			hillClimbing(Checkers.init(2), 0);
			if(maxTotalCount > totalCount) {
				maxTotalCount = totalCount;
				sMax = s.toString();
			}
		}
		System.out.printf("遊戲結束\n");
		System.out.printf("最少跳%d步\n", maxTotalCount);

		fw = new FileWriter("result2.txt");
		fw.write(sMax);
		fw.write("總計 " + maxTotalCount + " 步\n");
		fw.close();

		//Game3 g3 = new Game3();
		//g3.start();
		//Game4 g4 = new Game4();
		//g4.start();
	}

	// 二次方貪婪爬山演算法
	public static void hillClimbing(MySet chessSet, int count) throws IOException {
		// Checkers.print(chessSet);
		// System.out.println(count);
		// scanner.nextLine();

		if (Checkers.isWin(chessSet)) {
			totalCount = Math.min(totalCount, count);
			// System.out.println("完成一個了" + count);
			return;
		}

		if (count > maxTotalCount) {
			// System.out.println("太久了!!!!");
			return;
		}

		if (random.nextInt(10) > 2) {
			// System.out.println("jump");
			goJump(chessSet, count);
		} else {
			// System.out.println("move");
			goMove(chessSet, count);
		}
	}

	// 移動
	public static void goMove(MySet chessSet, int count) throws IOException {
		Chess cc = null;
		Direction dd = null;

		outer: for (Chess c : chessSet.getRandom()) {
			for (Direction d : Checkers.getMovable(chessSet, c, a)) {
				// 避免走回頭路
				if (chessSet.isRepeat(c.id, d)) {
					continue;
				}
				cc = c;
				dd = d;
				break outer;
			}
			for (Direction d : Checkers.getMovable(chessSet, c, b)) {
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
			hillClimbing(Checkers.move(chessSet, cc.id, dd, s), count + 1);
		} else {
			hillClimbing(chessSet, count);
		}
	}

	// 跳躍
	static MyStack tempMaxStep = new MyStack();
	static MyStack tempStep = new MyStack();

	public static void goJump(MySet chessSet, int count) throws IOException {
		List<MyStack> StepList = new ArrayList<MyStack>();

		for (Chess c : chessSet) {
			tempMaxStep.clear();
			tempStep.clear();
			tempStep.id = c.id;

			getMaxJump(chessSet, c.id);

			if (tempMaxStep.isEmpty()) {
				continue;
			} else if (StepList.isEmpty()) {
				StepList.add((MyStack) tempMaxStep.clone());
			} else if (Direction.gatProgress(StepList.get(0)) < Direction.gatProgress(tempMaxStep)) {
				StepList.clear();
				StepList.add((MyStack) tempMaxStep.clone());
			} else if (Direction.gatProgress(StepList.get(0)) == Direction.gatProgress(tempMaxStep)) {
				StepList.add((MyStack) tempMaxStep.clone());
			}
		}
		// System.out.println(StepList);

		if (StepList.isEmpty()) {
			// System.out.println("沒有東西可以跳");
			hillClimbing(chessSet, count);
		}

		for (MyStack step : StepList) {
			keepJump(chessSet, step, count);
			break;
		}
	}

	public static void getMaxJump(MySet chessSet, int id) throws IOException {

		for (Direction d : Checkers.getJumpable(chessSet, getChess(chessSet, id), a)) {
			if (!tempStep.isEmpty() && tempStep.peek().isRepeat(d)) {
				continue;
			}
			tempStep.add(d);
			getMaxJump(Checkers.jump(chessSet, id, d, new StringBuffer(), true), id);
		}

		for (Direction d : Checkers.getJumpable(chessSet, getChess(chessSet, id), b)) {
			if (!tempStep.isEmpty() && tempStep.peek().isRepeat(d)) {
				continue;
			}
			tempStep.add(d);
			getMaxJump(Checkers.jump(chessSet, id, d, new StringBuffer(), true), id);
		}

		if (Direction.gatProgress(tempMaxStep) < Direction.gatProgress(tempStep)) {
			tempMaxStep = (MyStack) tempStep.clone();
		}
		if (!tempStep.isEmpty()) {
			tempStep.pop();
		}
	}

	private static void keepJump(MySet chessSet, MyStack step, int count) throws IOException {
		boolean b = true;
		for (Direction d : step) {
			chessSet = Checkers.jump(chessSet, step.id, d, s, b);
			b = false;
		}
		s.append("\n");
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
