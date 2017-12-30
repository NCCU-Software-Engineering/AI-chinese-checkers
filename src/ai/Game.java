package ai;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Game {

	static int totalCount = 0;
	static Scanner scanner = new Scanner(System.in);
	static Stack<Set<Position>> ans = new Stack<>();
	static Set<Position> temp;
	static int tempid = -1;
	static Direction tempD = Direction.LEFTUP;

	public static void main(String[] args) throws IOException, InterruptedException {

		// �U�訫��W��
		System.out.println("��");
		ans.add(Checkers.init());
		
		// �C���j��
		outerloop: while (!Checkers.isWin(ans.peek())) {
			Checkers.print(ans.peek());
			scanner.nextLine();

			// 1.���e��
			for (Position p : ans.peek()) {
				for (Direction d : Checkers.getJumpable(ans.peek(), p, 1)) {
					if(tempid == p.id && tempD.isRepeat(d)) {
						System.out.println("continue");
						continue;
					}
					
					System.out.println(p + " jump " + d.name());
					temp = Checkers.jump(ans.peek(), p, d);
					tempid = p.id;
					tempD = d;
					ans.push(temp);
					totalCount++;
					continue outerloop;
				}
			}

			// 2.���e����
			for (Position p : ans.peek()) {
				for (Direction d : Checkers.getMovable(ans.peek(), p, 1)) {
					if(tempid == p.id && tempD.isRepeat(d)) {
						System.out.println("continue");
						continue;
					}
					
					System.out.println(p + " move " + d.name());
					temp = Checkers.move(ans.peek(), p, d);
					tempid = p.id;
					tempD = d;
					ans.push(temp);
					totalCount++;
					continue outerloop;
				}
			}
			
			// 3.�������
			for (Position p : ans.peek()) {
				for (Direction d : Checkers.getJumpable(ans.peek(), p, 2)) {
					if(tempid == p.id && tempD.isRepeat(d)) {
						System.out.println("continue");
						continue;
					}
					
					System.out.println(p + " jump " + d.name());
					temp = Checkers.jump(ans.peek(), p, d);
					tempid = p.id;
					tempD = d;
					ans.push(temp);
					totalCount++;
					continue outerloop;
				}
			}
			
			// 4.�����䲾��
			for (Position p : ans.peek()) {
				for (Direction d : Checkers.getMovable(ans.peek(), p, 2)) {
					if(tempid == p.id && tempD.isRepeat(d)) {
						System.out.println("continue");
						continue;
					}
					
					System.out.println(p + " move " + d.name());
					temp = Checkers.move(ans.peek(), p, d);
					tempid = p.id;
					tempD = d;
					ans.push(temp);
					totalCount++;
					continue outerloop;
				}
			}
		}

		Checkers.print(ans.peek());
		System.out.printf("�����Ĥ@�D\n");
		System.out.printf("�ָ̤��F%d�B\n", totalCount);
	}
}