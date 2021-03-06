package ai;

public class Chessboard {

	// 9 邊界
	// 1 玩家棋子
	// 2 目標
	// 3 其他玩家
	final static int[][] map1 = new int[][] { { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
		{ 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8 },
		{ 8, 9, 9, 9, 9, 9, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8 },
		{ 8, 9, 9, 9, 9, 9, 2, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8 },
		{ 8, 9, 9, 9, 9, 9, 2, 2, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8 },
		{ 8, 9, 9, 9, 9, 9, 2, 2, 2, 2, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8 },
		{ 8, 9, 3, 3, 3, 3, 2, 2, 2, 2, 2, 3, 3, 3, 3, 9, 9, 9, 9, 9, 8 },
		{ 8, 9, 9, 3, 3, 3, 0, 0, 0, 0, 0, 0, 3, 3, 3, 9, 9, 9, 9, 9, 8 },
		{ 8, 9, 9, 9, 3, 3, 0, 0, 0, 0, 0, 0, 0, 3, 3, 9, 9, 9, 9, 9, 8 },
		{ 8, 9, 9, 9, 9, 3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 9, 9, 9, 9, 9, 8 },
		{ 8, 9, 9, 9, 9, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9, 9, 9, 9, 8 },
		{ 8, 9, 9, 9, 9, 9, 3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 9, 9, 9, 9, 8 },
		{ 8, 9, 9, 9, 9, 9, 3, 3, 0, 0, 0, 0, 0, 0, 0, 3, 3, 9, 9, 9, 8 },
		{ 8, 9, 9, 9, 9, 9, 3, 3, 3, 0, 0, 0, 0, 0, 0, 3, 3, 3, 9, 9, 8 },
		{ 8, 9, 9, 9, 9, 9, 3, 3, 3, 3, 1, 1, 1, 1, 1, 3, 3, 3, 3, 9, 8 },
		{ 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 1, 1, 1, 9, 9, 9, 9, 9, 8 },
		{ 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 1, 1, 9, 9, 9, 9, 9, 8 },
		{ 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 1, 9, 9, 9, 9, 9, 8 },
		{ 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 9, 9, 9, 9, 9, 8 },
		{ 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8 },
		{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 }, };

	final static int[][] map2 = new int[][] { { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8 },
			{ 8, 9, 9, 9, 9, 9, 3, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8 },
			{ 8, 9, 9, 9, 9, 9, 3, 3, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8 },
			{ 8, 9, 9, 9, 9, 9, 3, 3, 3, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8 },
			{ 8, 9, 9, 9, 9, 9, 3, 3, 3, 3, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8 },
			{ 8, 9, 2, 2, 2, 2, 2, 0, 0, 0, 0, 3, 3, 3, 3, 9, 9, 9, 9, 9, 8 },
			{ 8, 9, 9, 2, 2, 2, 2, 0, 0, 0, 0, 0, 3, 3, 3, 9, 9, 9, 9, 9, 8 },
			{ 8, 9, 9, 9, 2, 2, 2, 0, 0, 0, 0, 0, 0, 3, 3, 9, 9, 9, 9, 9, 8 },
			{ 8, 9, 9, 9, 9, 2, 2, 0, 0, 0, 0, 0, 0, 0, 3, 9, 9, 9, 9, 9, 8 },
			{ 8, 9, 9, 9, 9, 9, 2, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9, 9, 9, 9, 8 },
			{ 8, 9, 9, 9, 9, 9, 3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 9, 9, 9, 9, 8 },
			{ 8, 9, 9, 9, 9, 9, 3, 3, 0, 0, 0, 0, 0, 0, 0, 3, 3, 9, 9, 9, 8 },
			{ 8, 9, 9, 9, 9, 9, 3, 3, 3, 0, 0, 0, 0, 0, 0, 3, 3, 3, 9, 9, 8 },
			{ 8, 9, 9, 9, 9, 9, 3, 3, 3, 3, 1, 1, 1, 1, 1, 3, 3, 3, 3, 9, 8 },
			{ 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 1, 1, 1, 9, 9, 9, 9, 9, 8 },
			{ 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 1, 1, 9, 9, 9, 9, 9, 8 },
			{ 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 1, 9, 9, 9, 9, 9, 8 },
			{ 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1, 9, 9, 9, 9, 9, 8 },
			{ 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 }, };
}
