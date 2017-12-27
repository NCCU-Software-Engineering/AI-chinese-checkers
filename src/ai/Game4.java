package ai;

import java.util.List;
import java.util.ArrayList;

public class Game4 {
	public int [][] map = new int[17][17];
	public List<Integer> chess = new ArrayList<Integer>();
	//輸入棋子位置
	public Game4() {
		//23 24 32 33 34 42 43
		chess.add(2);chess.add(3);
		chess.add(2);chess.add(4);
		chess.add(3);chess.add(2);
		chess.add(3);chess.add(3);
		chess.add(3);chess.add(4);
		chess.add(4);chess.add(2);
		chess.add(4);chess.add(3);
	}
	//棋盤範圍
	public enum position{
		P1(-4,8),P2(-4,7),P3(-3,7),P4(-4,6),P5(-3,6),P6(-2,6),P7(-4,5),P8(-3,5),P9(-2,5),P10(-1,5),
		P11(-8,4),P12(-7,4),P13(-6,4),P14(-5,4),P15(-4,4),P16(-3,4),P17(-2,4),P18(-1,4),P121(0,4),P122(1,4),P19(2,4),P20(3,4),
		P21(4,4),P22(-7,3),P23(-6,3),P24(-5,3),P25(-4,3),P26(-3,3),P27(-2,3),P28(-1,3),P29(0,3),P30(1,3),
		P32(2,3),P33(3,3),P34(4,3),P35(-6,2),P36(-5,2),P37(-4,2),P38(-3,2),P39(-2,2),P40(-1,2),
		P41(0,2),P42(1,2),P43(2,2),P44(3,2),P45(4,2),P46(-5,1),P47(-4,1),P48(-3,1),P49(-2,1),P50(-1,1),
		P51(0,1),P52(1,1),P53(2,1),P54(3,1),P55(4,1),P56(-4,0),P57(-3,0),P58(-2,0),P59(-1,0),P60(0,0),
		P61(1,0),P62(2,0),P63(3,0),P64(4,0),P65(-4,-1),P66(-3,-1),P67(-2,-1),P68(-1,-1),P69(0,-1),P70(1,-1),
		P71(2,-1),P72(3,-1),P73(4,-1),P74(5,-1),P75(-4,-2),P76(-3,-2),P77(-2,-2),P78(-1,-2),P79(0,-2),P80(1,-2),
		P81(2,-2),P82(3,-2),P83(4,-2),P84(5,-2),P85(6,-2),P86(-4,-3),P87(-3,-3),P88(-2,-3),P89(-1,-3),P90(0,-3),
		P91(1,-3),P92(2,-3),P93(3,-3),P94(4,-3),P95(5,-3),P96(6,-3),P97(7,-3),P98(-4,-4),P99(-3,-4),P100(-2,-4),
		P101(-1,-4),P102(0,-4),P103(1,-4),P104(2,-4),P105(3,-4),P106(4,-4),P107(5,-4),P108(6,-4),P109(7,-4),P110(8,-4),
		P111(1,-5),P112(2,-5),P113(3,-5),P114(4,-5),P115(2,-6),P116(3,-6),P117(4,-6),P118(3,-7),P119(4,-7),P120(4,-8);
		private int x, y;
		private position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	//目標位置
	public enum target{
		T1(-4,0),T2(-4,-1),T3(-3,-1),T4(-4,-2),T5(-3,-2),T6(-2,-2),T7(-4,-3),T8(-3,-3),T9(-2,-3),T10(-1,-3),
		T11(-4,-4),T12(-3,-4),T13(-2,-4),T14(-1,-4),T15(0,-4);
		private int x, y;
		private target(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	//移動方向
	public enum direction{
		LEFT(-1,0),LEFTUP(-1,1),LEFTDOMN(0,-1),RIGHT(1,0),RIGHTUP(0,1),RIGHTDOWN(1,-1);
		private int x,y;
		private direction(int x , int y) {
			this.x = x;
			this.y = y;
		};
	}
	//跳躍方向
	public enum Jdirection{
		LEFT(-2,0),LEFTUP(-2,2),LEFTDOMN(0,-2),RIGHT(2,0),RIGHTUP(0,2),RIGHTDOWN(2,-2);
		private int x,y;
		private Jdirection(int x , int y) {
			this.x = x;
			this.y = y;
		};
	}
	//開始
	public void start() {
		loadmap();
		bfs(map,0);
	}
	//下載地圖
	public void loadmap() {
		for(position p : position.values()) {
			map[p.y+8][p.x+8] = 1;
		}
		for(target t : target.values()) {
			map[t.y+8][t.x+8] = 7;
		}
		for(int i = 0; i < chess.size()/2 ; i++) {
			map[chess.get(i*2+1) + 8][chess.get(i*2) + 8] = 3;
		}
	}
	//Search
	public void bfs(int[][] m,int count) {
		if(finish(m)) {
			System.out.print(m);
		}
		int [][] m2 = m.clone();
		for(int i = 16 ; i >= 0 ; i--) {
			for(int j = 0 ; j <= 16 ; j++) {
				if(m2[j][i] == 3) {
					for(direction d : isMovable(j,i,m2)) {
						bfs(move(j,i,d,m2),count+1);
					}
					for(Jdirection jd : isJumpable(j,i,m2)) {
						bfs(jump(j,i,jd,m2),count+1);
					}
				}
			}
		}
	}
	//輸出地圖
	public void printGraph(int[][] m) {
		for(int i = 16 ; i >= 0 ; i--) {
			for(int j = 0 ; j <= 16 ; j++) {
				System.out.print(m[j][i]);
			}
			System.out.println();
		}
	}
	//可移動方向
	public List<direction> isMovable(int x,int y,int[][] m) {
		List<direction> dl = new ArrayList<direction>();
		for (direction d : direction.values()) {
			if(m[y+d.y][x+d.x] == 1) {
				dl.add(d);
			}
		}
		return dl;
	}
	//移動
	public int[][] move(int x, int y, direction d,int[][] m) {
		int[][] m2 = m.clone();
		m2[y][x] -= 2;
		m2[y+d.y][x+d.x] += 2;
		return m2;
	}
	//可跳躍方向
	public List<Jdirection> isJumpable(int x,int y,int[][] m) {
		List<Jdirection> jdl = new ArrayList<Jdirection>();
		for (Jdirection jd : Jdirection.values()) {
			if(m[y+jd.y][x+jd.x] == 1 && m[y+jd.y/2][x+jd.x/2] == 3) {
				jdl.add(jd);
			}
		}
		return jdl;
	}
	//跳誒
	public int[][] jump(int x, int y, Jdirection jd,int[][] m) {
		int[][] m2 = m.clone();
		m[y][x] -= 2;
		m[y+jd.y][x+jd.x] += 2;
		return m2;
	}
	//結束
	public boolean finish(int[][] m) {
		int count = 0;
		for(target t : target.values()) {
			if(m[t.y+8][t.x+8] == 9) {
				count += 1;
			}
		}
		if(count == chess.size()/2) {
			return true;
		}
		return false;
	}
}
