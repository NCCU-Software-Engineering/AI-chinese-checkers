package ai;

public class Game {

	public static void main(String[] args) {

		Checkers checkers;

		// �U�訫��W��
		System.out.println("question 1");
		checkers = new Checkers(1);
		checkers.start();

		// �U�訫��W��
		while (checkers.isWin() == 0) {

		}

		// �U�訫�쥪�W��
		System.out.println("question 2");
		checkers = new Checkers(2);
		checkers.start();

		// �S�w��m���Ѥl
		System.out.println("question 3");
		checkers = new Checkers(3);
		checkers.start();

		// ���ʯS�w���Ѥl
		System.out.println("question 4");
		checkers = new Checkers(4);
		checkers.start();
	}
}
