package ai;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import ai.Game4.Node;

import java.util.ArrayList;
import java.util.HashMap;

public class Game4 {
	public int[][] map = new int[21][21];
	public List<int[]> chess = new ArrayList<int[]>();
	public List<List<int[]>> fail = new ArrayList<List<int[]>>();
	
	//輸入棋子位置
	public Game4() {
		chess.add(new int[]{2,3});
		chess.add(new int[]{2,4});
		chess.add(new int[]{3,2});
		chess.add(new int[]{3,3});
		chess.add(new int[]{3,4});
		chess.add(new int[]{4,2});
		chess.add(new int[]{4,3});
	}
	//棋盤範圍
	public enum position{
		P1(-4,8),P2(-4,7),P3(-3,7),P4(-4,6),P5(-3,6),P6(-2,6),P7(-4,5),P8(-3,5),P9(-2,5),P10(-1,5),
		P11(-8,4),P12(-7,4),P13(-6,4),P14(-5,4),P15(-4,4),P16(-3,4),P17(-2,4),P18(-1,4),P19(2,4),P20(3,4),
		P21(4,4),P22(-7,3),P23(-6,3),P24(-5,3),P25(-4,3),P26(-3,3),P27(-2,3),P28(-1,3),P29(0,3),P30(1,3),
		P31(0,4),P32(2,3),P33(3,3),P34(4,3),P35(-6,2),P36(-5,2),P37(-4,2),P38(-3,2),P39(-2,2),P40(-1,2),
		P41(0,2),P42(1,2),P43(2,2),P44(3,2),P45(4,2),P46(-5,1),P47(-4,1),P48(-3,1),P49(-2,1),P50(-1,1),
		P51(0,1),P52(1,1),P53(2,1),P54(3,1),P55(4,1),P56(-4,0),P57(-3,0),P58(-2,0),P59(-1,0),P60(0,0),
		P61(1,0),P62(2,0),P63(3,0),P64(4,0),P65(-4,-1),P66(-3,-1),P67(-2,-1),P68(-1,-1),P69(0,-1),P70(1,-1),
		P71(2,-1),P72(3,-1),P73(4,-1),P74(5,-1),P75(-4,-2),P76(-3,-2),P77(-2,-2),P78(-1,-2),P79(0,-2),P80(1,-2),
		P81(2,-2),P82(3,-2),P83(4,-2),P84(5,-2),P85(6,-2),P86(-4,-3),P87(-3,-3),P88(-2,-3),P89(-1,-3),P90(0,-3),
		P91(1,-3),P92(2,-3),P93(3,-3),P94(4,-3),P95(5,-3),P96(6,-3),P97(7,-3),P98(-4,-4),P99(-3,-4),P100(-2,-4),
		P101(-1,-4),P102(0,-4),P103(1,-4),P104(2,-4),P105(3,-4),P106(4,-4),P107(5,-4),P108(6,-4),P109(7,-4),P110(8,-4),
		P111(1,-5),P112(2,-5),P113(3,-5),P114(4,-5),P115(2,-6),P116(3,-6),P117(4,-6),P118(3,-7),P119(4,-7),P120(4,-8),P121(1,4);
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
		LEFT(-1,0),LEFTUP(-1,1)
		,LEFTDOMN(0,-1),RIGHT(1,0),RIGHTUP(0,1)
		,RIGHTDOWN(1,-1);
		private int x,y;
		private direction(int x , int y) {
			this.x = x;
			this.y = y;
		};
	}
	//跳躍方向
	public enum Jdirection{
		LEFT(-2,0),LEFTUP(-2,2)
		,LEFTDOMN(0,-2),RIGHT(2,0),RIGHTUP(0,2)
		,RIGHTDOWN(2,-2);
		private int x,y;
		private Jdirection(int x , int y) {
			this.x = x;
			this.y = y;
		};
	}
	//下載地圖
	public void loadmap() {
		for(position p : position.values()) {
			map[p.x+10][p.y+10] = 1;
		}
	}
	//下載目標位置
	public void loadtarget() {
		for(target t : target.values()) {
			map[t.x+10][t.y+10] = 1;
		}
	}
	//下載棋子位置
	public void loadchess(List<int[]> c, int[][] m) {
		for(int[] cc : c) {
			m[cc[0]+10][cc[1]+10]+=2;
		}
	}
	//開始
	public void start() {
		loadmap();
		loadtarget();
		bfs(chess);
	}
	//Node Class
	public class Node {
		List<int[]> c = new ArrayList<int[]>();
		List<List<int[]>> r = new ArrayList<List<int[]>>();
		int g;
		int h;
		public Node(List<int[]> c,int g,int h) {
			c.sort((a1,a2)->(a1[1] - a2[1]));
			c.sort((a1,a2)->(a1[0] - a2[0]));
			this.c=c;
			this.g=g;
			this.h=h;
		}
	}
	//Search
	public void bfs(List<int[]> c) {
		
		Queue<Node> queue = new PriorityQueue<Node>(1000,(n1,n2)->n1.h - n2.h);
		queue.add(new Node(c,0,0));
		int[][] m = new int[21][21];
		
		while(!queue.isEmpty()) {
			for(int i = 0; i < 21; i++){ //複製地圖
				m[i] = map[i].clone();
			}
			Node node = queue.remove(); //移除第一個node
			loadchess(node.c,m); //下載棋子
			
			if(finish(m) == chess.size()) {
				printRoad(node.r);
				printGraph(m);
				break;
			}
			fail.add(node.c);
			
			Map<int[],List<int[]>> d = new HashMap<int[],List<int[]>>();
			Map<int[],List<int[]>> jd = new HashMap<int[],List<int[]>>();
			
			for(int[] cc : node.c) {
				d.put(cc, isMovable(cc,m));
				jd.put(cc, isJumpable(cc,m));
			}
			jd.forEach((k,v)->v.forEach(e -> queue.add( getNewNode(node,k,e,m) )));
			d.forEach((k,v)->v.forEach(e -> queue.add( getNewNode(node,k,e,m) )));
		}
	}
	//可移動方向
	public List<int[]> isMovable(int[] p,int[][] m) {
		List<int[]> dl = new ArrayList<int[]>();
		for (direction d : direction.values()) {
			if(m[p[0]+d.x+10][p[1]+d.y+10] == 1) {
				dl.add(new int[]{p[0]+d.x,p[1]+d.y});
			}
		}
		return dl;
	}
	//可跳躍方向
	public List<int[]> isJumpable(int[] p, int[][] m) {
		List<int[]> jdl = new ArrayList<int[]>();
		for (Jdirection jd : Jdirection.values()) {
			if(m[p[0]+jd.x+10][p[1]+jd.y+10] == 1 && m[p[0]+jd.x/2+10][p[1]+jd.y/2+10] == 3) {
				jdl.add(new int[]{p[0]+jd.x,p[1]+jd.y});
			}
		}
		return jdl;
	}
	//可移動方向
	public Node getNewNode(Node oldn, int[] oldp , int[] newp , int[][] m) {
		List<int[]> r = new ArrayList<int[]>();
		List<int[]> cl = new ArrayList<int[]>(oldn.c);
		cl.set(cl.indexOf(oldp), newp);
		Node n = new Node(cl,oldn.g+1,h(cl, m));
		r.add(oldp);
		r.add(newp);
		n.r.addAll(oldn.r);
		n.r.add(r);
		return n;
	}
	//h()
	public int h(List<int[]> c, int[][] m) {
		int h = 0;
		for(int[] cc : c) {
			h += (cc[0]+cc[1]+4 > 0 ? cc[0]+cc[1]+4 //: 0); //58步
					: (cc[0]+cc[1]+5 > 0 ? cc[0]+cc[1]+5 //: 0)); //75步
							: (cc[0]+cc[1]+6 > 0 ? cc[0]+cc[1]+6 : 0))); //59步
									//: (cc[0]+cc[1]+7 > 0 ? cc[0]+cc[1]+7 //: 0)))); //69步
											//: (cc[0]+cc[1]+8 > 0 ? cc[0]+cc[1]+8 //: 0))))); //64步
			h -= finish(m);
		}
		return h;
	}
	//結束
	public int finish(int[][] m) {
		int count = 0;
		for(target t : target.values()) {
			if(m[t.x+10][t.y+10] == 3) {
				count += 1;
			}
		}
		return count;
	}
	//輸出路線&計算步數
	public void printRoad(List<List<int[]>> r) {
		System.out.print("========================");
		int[] x = null;
		int[] y = null;
		boolean jump = false;
		int count = 0;
		for(List<int[]> l : r) {
			count += 1;
			x = l.get(0);
			if((l.get(0)[0] - l.get(1)[0]) / 2 != 0 || (l.get(0)[1] - l.get(1)[1]) / 2 != 0) {
				if(x == y && jump) {
					System.out.print("("+l.get(1)[0]+","+l.get(1)[1]+")");
					count -= 1;
				}
				else {
					System.out.println();
					for(int[] i : l) {
						System.out.print("("+i[0]+","+i[1]+")");
					}
				}
				jump = true;
			}
			else {
				System.out.println();
				for(int[] i : l) {
					System.out.print("("+i[0]+","+i[1]+")");
				}
				jump = false;
			}
			y = l.get(1);
		}
		System.out.println("\n總計 "+count+" 步");
	}
	//輸出地圖
	public void printGraph(int[][] m) {
		for(int j = 20 ; j > 0 ; j--) {
			for(int i = 0 ; i <= 20 ; i++) {
				System.out.print(m[i][j]);
			}
			System.out.println();
		}
	}
}