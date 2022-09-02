package com.jstfs.practice.algorithm.maze;

import com.jstfs.common.utils.MyRandomUtils;

/**
 * 迷宫问题:
 * 	打印出从起点找到终点的路线,但这个和探路的策略有关,本例中采用[下-右-上-左]的优先级
 * 
 * @createBy	落叶
 * @createTime	2022年9月1日 下午7:18:57
 */
public class MazePuzzle {
	private int row 	= 10;		//迷宫行数
	private int column 	= 18;		//迷宫列数
	private int totalCell = (row-2) * (column-2);	//迷宫中的总格子数
	private final char obstacleChar	= '障';	//障碍物
	private final char wallChar 	= '墙';	//围墙
	private final char startChar 	= '始';	//开始
	private final char endChar 		= '末';	//结束
	private final char blankChar 	= '十';	//可以走的位置
	private final char badChar 		= '死';	//已经探明的死路
	
	public char[][] map = new char[row][column];	//迷宫地图
	
	public static void main(String[] args) {
		MazePuzzle mp = new MazePuzzle();
		System.out.println("迷宫初始化:");
		mp.initMap();
		mp.show();
		System.out.println("生成围墙:");
		mp.generateWall();
		mp.show();
		System.out.println("设置障碍物:");
		mp.setObstacle(4);
		mp.show();
		System.out.println("生成起点和终点:");
		int[] startPoint = mp.generateStartAndEnd();
		mp.show();
		System.out.println("开始找路:");
		mp.findWay(startPoint[0] + 1, startPoint[1], true);
		mp.show();
	}
	
	/**
	 * 初始化迷宫
	 */
	private void initMap() {
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				map[i][j] = blankChar;
			}
		}
	}
	
	/**
	 * 生成围墙
	 */
	private void generateWall() {
		for(int i = 0; i < column; i++) {
			//把第一行和最后一行设置成围墙
			map[0][i] = wallChar;
			map[row-1][i] = wallChar;
		}
		for(int i = 1; i < row-1; i++) {
			//把第二行到倒数第二行的开始和结尾设置称围墙
			map[i][0] = wallChar;
			map[i][column-1] = wallChar;
		}
	}
	
	/**
	 * 设置障碍物
	 * 
	 * @param count		障碍的数量
	 */
	private void setObstacle(int count) {
		if(count > totalCell/3) {
			throw new RuntimeException("障碍物的数量不能超过" + totalCell/3);
		}
		
		MyRandomUtils.setSeed(System.currentTimeMillis());
		int lastRowNum = 0;
		int lastColumnNum = 0;
		while(count > 0) {
			int rowNum = MyRandomUtils.nextInteger(1, row-2);
			int columnNum = MyRandomUtils.nextInteger(1, column-2);
			
			if(rowNum == lastRowNum && columnNum == lastColumnNum) {
				continue;
			}
			
			map[rowNum][columnNum] = obstacleChar;
			lastRowNum = rowNum;
			lastColumnNum = columnNum;
			count--;
		}
	}
	
	/**
	 * 生成起点和终点
	 */
	private int[] generateStartAndEnd() {
		int[] startPoint = new int[2];
		MyRandomUtils.setSeed(System.currentTimeMillis());
		while(true) {
			int rowNum = MyRandomUtils.nextInteger(1, row-2);
			int columnNum = MyRandomUtils.nextInteger(1, column-2);
			
			if(map[rowNum][columnNum] == obstacleChar) {
				//是障碍就重新生成
				continue;
			} else {
				map[rowNum][columnNum] = startChar;
				startPoint[0] = rowNum;
				startPoint[1] = columnNum;
				break;
			}
		}
		
		MyRandomUtils.setSeed(System.currentTimeMillis());
		while(true) {
			int rowNum = MyRandomUtils.nextInteger(1, row-2);
			int columnNum = MyRandomUtils.nextInteger(1, column-2);
			
			if(map[rowNum][columnNum] == obstacleChar || map[rowNum][columnNum] == startChar) {
				//是障碍或者与起点重合就重新生成
				continue;
			} else {
				map[rowNum][columnNum] = endChar;
				break;
			}
		}
		
		return startPoint;
	}
	
	/**
	 * 找路
	 * 
	 * @param row		当前位置的行号
	 * @param column	当前位置的列号
	 * @param hOrV		上一步是横着走还是竖着走的: true-竖着走,false-横着走
	 * @return			是否走到终点
	 */
	private boolean findWay(int row, int column, boolean verticalFlag) {
		if(map[row][column] == endChar) {
			//到达终点
			return true;
		}
		
		if(map[row][column] == blankChar) {
			//该位置可以走,将该位置改成走过的标识
			if(verticalFlag) {
				map[row][column] = '丨';
			} else {
				map[row][column] = '一';
			}
			
			//然后继续按照策略走下一步: 下-右-上-左
			if(findWay(row + 1, column, true)) {
				return true;
			} else if(findWay(row, column + 1, false)) {
				return true;
			} else if(findWay(row - 1, column, true)) {
				return true;
			} else if(findWay(row, column - 1, false)) {
				return true;
			} else {
				//说明该点走不通,那就需要回退,并将该点标记为死路
				map[row][column] = badChar;
				return false;
			}
		} else {
			//其他情况,该位置可能是'墙'/'障'/'球'/'死',这几种情况也都不能走
			return false;
		}
	}
	
	/**
	 * 输出迷宫
	 */
	private void show() {
		for(char[] row : map) {
			for(char cell : row) {
				System.out.print(cell + " ");
			}
			System.out.println();
		}
	}
}
