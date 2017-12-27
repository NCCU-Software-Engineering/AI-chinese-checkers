package ai;

import java.io.FileWriter;
import java.io.IOException;

public class Game {

	static int totalCount = 0;
	
	public static void main(String[] args) throws IOException, InterruptedException {

		Checkers checkers;
		FileWriter fw = new FileWriter("D:\\out.txt", false);

		// �U�訫��W��
		System.out.println("question 1");
		checkers = new Checkers(1);
		checkers.start();
		int count = dfs(checkers, 0);
		System.out.println(count);

		/*
		 * // �U�訫�쥪�W�� System.out.println("question 2"); checkers = new Checkers(2);
		 * checkers.start();
		 * 
		 * // �S�w��m���Ѥl System.out.println("question 3"); checkers = new Checkers(3);
		 * checkers.start();
		 * 
		 * // * // ���ʯS�w���Ѥl System.out.println("question 4"); checkers = new Checkers(4);
		 * checkers.start();
		 */
	}

	public static int dfs(Checkers checkers, int count) throws InterruptedException {
		do {
			checkers.print();
			
			//1.�����̻�
			outerloop: for (Position p : checkers.cset) {
			
			
			
			
			}
			
			//2.����
			outerloop: for (Position p : checkers.cset) {
				// if(checkers.goal(p))
				// continue;
				for (Direction d : checkers.getMovable(p)) {
					System.out.println(p + " " + d.name());
					checkers.move(p, d);
					count++;
					break outerloop;
				}
			}
		} while (!checkers.isWin() && totalCount == 0);
		
		return count;
	}
}