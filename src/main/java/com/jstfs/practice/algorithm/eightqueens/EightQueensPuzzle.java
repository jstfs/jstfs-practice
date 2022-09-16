package com.jstfs.practice.algorithm.eightqueens;

import java.util.Arrays;

/**
 * 八皇后问题:
 * 		在一张8*8的棋盘上,放置8个皇后,要求每一行、每一列、每一斜线上都只能有一个皇后
 * 		那么一共有多少中不同的摆法?
 * 
 * @createBy	落叶
 * @createTime	2022年9月2日 上午1:02:38
 */
public class EightQueensPuzzle {
	private static int row 			= 8;	//棋盘行数(当然列数也相同)
	private static int count 		= 0;	//所有的摆法
	private static int judgeCount 	= 0;	//判断是否冲突的次数
	
	/**
	 * 保存具体解法的数组
	 * 数组的下标表示行号
	 * 该下标中存放的数字,表示皇后放在了该行的哪一列
	 */
	private int[] result = new int[row];
	
	public static void main(String[] args) {
		EightQueensPuzzle eqp = new EightQueensPuzzle();
		eqp.putQueen(0);
		System.out.println("共" + count + "种摆法");			//92种
		System.out.println("共判断了" + judgeCount + "次");	//45686次
	}
	
	/**
	 * 放置皇后
	 * 
	 * @param n		放置第几行的皇后,0表示第一个
	 */
	private void putQueen(int n) {
		if(n == row) {
			//说明8个皇后都放完了
			System.out.println(Arrays.toString(result));
			count++;
			return;
		}
		
		for(int i = 0; i < row; i++) {
			result[n] = i;
			if(judge(n)) {
				//不冲突,就放下一个皇后
				putQueen(n+1);
				
				if(n == (row-1)) {
					/**
					 * 如果放完最后一个皇后,并且不冲突了
					 * 那么直接回退,而不需要在最后一行上尝试其他列
					 * 这样可以提高一点效率,少比较1000多次
					 */
					break;
				}
			}
		}
	}
	
	/**
	 * 判断第n行是否和前面的行冲突
	 * 
	 * @param n		当前刚刚放置了皇后的行的行号
	 * @return		是否可以放 [true-可以, false-冲突]
	 */
	private boolean judge(int n) {
		for(int i = 0; i < n; i++) {
			judgeCount++;
			
			if(result[i] == result[n]) {
				//说明第i行和第n行的皇后放在了同一列
				return false;
			}
			
			if(Math.abs(n-i) == Math.abs(result[n]-result[i])) {
				//说明在同一条斜线上(可能是正斜线'/',也可能是反斜线'\',所以用绝对值比较)
				return false;
			}
		}
		
		return true;
	}
}
