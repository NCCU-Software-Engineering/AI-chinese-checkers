package ai;

import java.util.Scanner;

public class Game {

	public static void main(String[] args) {

		final int playerNum = 3;
		Scanner scanner = new Scanner(System.in);
		Checkers checkers = new Checkers(playerNum);

		System.out.println("Game start");
		checkers.start();
		System.out.println("�{�b����" + checkers.getPlayer());
	}
}
