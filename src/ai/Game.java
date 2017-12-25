package ai;

import java.util.Scanner;

import ai.Checkers.Direction;

public class Game {

	public static void main(String[] args) {

		String s;
		int x;
		int y;

		final int playerNum = 3;
		Scanner scanner = new Scanner(System.in);
		Checkers checkers = new Checkers(playerNum);

		System.out.println("Game start");

		while (checkers.isWin() == 0) {

			checkers.print();
			System.out.println("現在輪到玩家" + checkers.getPlayer());

			s = scanner.nextLine();
			x = scanner.nextInt();
			y = scanner.nextInt();
			switch (s) {
			case "LEFT":
				checkers.move(x,y,Direction.LEFT);
				break;
			case "LEFTUP":
				break;
			case "RIGHTUP":
				break;
			case "RIGHT":
				break;
			case "RIGHTDOWN":
				break;
			case "LEFTDOWN":
				break;
			}
		}
	}
}
