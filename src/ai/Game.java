package ai;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Game {

	static Scanner scanner = new Scanner(System.in);
	static int totalCount = 0;
	static int maxTotalCount = Integer.MAX_VALUE;
	static Stack<Set<Chess>> ans = new Stack<>();
	static int random;

	// �T��
	static Set<Chess> temp;
	static int tempid = -1;
	static Direction tempD = Direction.LEFTUP;
	static MyStack maxStep = new MyStack();
	
	static List<MyStack> tempMaxStepList = new ArrayList<MyStack>();
	static MyStack tempMaxStep = new MyStack();
	static MyStack tempStep = new MyStack();

	public static void main(String[] args) throws IOException, InterruptedException {

		// �U�訫��W��
		System.out.println("��");
		// �C���j��
		for (int time = 0; time < 100; time++) {
			totalCount = 0;
			ans.clear();
			ans.add(Checkers.init());

			outerloop: while (!Checkers.isWin(ans.peek())) {
				// Checkers.print(ans.peek());
				// scanner.nextLine();

				random = (int) (Math.random() * 2);

				// ���a �Ѥl
				if (random == 0) {
					for (Chess p : ans.peek()) {
						// ���մѤl
						maxStep.clear();
						tempMaxStep.clear();
						tempStep.clear();
						tempStep.ID = p.id;
						getMaxJump(ans.peek());

						// �ݷs�Ѥl�i���i�H��̻�
						if (Direction.gatProgress(maxStep) < Direction.gatProgress(tempMaxStep)) {
							maxStep = (MyStack) tempMaxStep.clone();
						}

						if (!maxStep.isEmpty()) {
							for (Direction d : maxStep) {
								ans.push(Checkers.jump(ans.peek(), maxStep.ID, d));
							}
							totalCount++;
							continue outerloop;
						}
					}
				}
				// ���ʧa �Ѥl
				else {
					for (Chess p : ans.peek()) {
						for (Direction d : Checkers.getMovable(ans.peek(), p.id, 1)) {
							if (tempid == p.id && tempD.isRepeat(d)) {
								continue;
							}

							// System.out.println(p + " move " + d.name());
							temp = Checkers.move(ans.peek(), p, d);
							tempid = p.id;
							tempD = d;
							ans.push(temp);
							totalCount++;
							continue outerloop;
						}
					}
				}
			}
			System.out.println(totalCount);
			if (maxTotalCount > totalCount)
				maxTotalCount = totalCount;
		}

		// Checkers.print(ans.peek());
		System.out.printf("�����Ĥ@�D\n");
		System.out.printf("�ָ̤��F%d�B\n", maxTotalCount);
	}

	public static void getMaxJump(Set<Chess> chessSet) {

		Chess p = getChess(chessSet, tempStep.ID);
		// Checkers.print(chessSet);
		// scanner.nextLine();

		for (Direction d : Checkers.getJumpable(chessSet, p, 1)) {
			if (!tempStep.isEmpty() && tempStep.peek().isRepeat(d)) {
				continue;
			}
			tempStep.add(d);
			getMaxJump(Checkers.jump(chessSet, p, d));
		}

		if (Direction.gatProgress(tempMaxStepList.get(0)) < Direction.gatProgress(tempStep)) {
			tempMaxStepList.clear();
			tempMaxStepList.add((MyStack) tempStep.clone());
		}
		if (!tempStep.isEmpty()) {
			tempStep.pop();
		}
	}

	public static Chess getChess(Set<Chess> chessSet, int i) {
		for (Chess p : chessSet) {
			if (p.id == i)
				return p;
		}
		return null;
	}
}