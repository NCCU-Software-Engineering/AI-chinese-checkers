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

		// 下方走到上方
		System.out.println("甲");
		ans.add(Checkers.init());
		
		// 遊戲迴圈
		outerloop: while (!Checkers.isWin(ans.peek())) {
			Checkers.print(ans.peek());
			scanner.nextLine();

			// 1.往前跳
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

			// 2.往前移動
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
			
			// 3.往旁邊跳
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
			
			// 4.往旁邊移動
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
		System.out.printf("完成第一題\n");
		System.out.printf("最少跳了%d步\n", totalCount);
	}
}