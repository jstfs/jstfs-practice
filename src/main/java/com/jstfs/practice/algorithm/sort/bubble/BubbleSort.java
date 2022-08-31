package com.jstfs.practice.algorithm.sort.bubble;

import java.util.Arrays;

import com.jstfs.common.utils.MyRandomUtils;

/**
 * 冒泡排序:
 * 	找最大值逐步向后交换,可以提前退出
 * 	
 * 
 * 	1, 原地排序算法
 * 	2, 稳定的排序算法
 * 	3, 时间复杂度:
 * 		最好时间复杂度: O(N)
 * 		平均时间复杂度: O(N²)
 * 		最坏时间复杂度: O(N²)
 *
 * @createBy jstfs
 * @createTime 2018-12-3 下午10:59:24
 */
public class BubbleSort {
	private static int size = 12;
	
	public static void main(String[] args) {
		BubbleSort bs = new BubbleSort();
		int[] ary = MyRandomUtils.generateIntAry(size, size*3);
		System.out.println("原数组:\t\t" + Arrays.toString(ary));
		bs.sort(ary);
		System.out.println(Arrays.toString(ary));
	}
	
	public void sort(int[] ary) {
		if(ary == null) {
			return;
		}
		int size = ary.length;
		if(size <= 1) {
			return;
		}
		
		int sumSwapTimes = 0;		//总交换次数
		for(int i = 0; i < size - 1; i++) {	//冒泡的轮次可以少一次
			boolean flag = true;	//提前退出冒泡的标识
			int currSwapTimes = 0;	//本轮交换次数
			for(int j = 0; j < size - i - 1; j++) {
				if(ary[j] > ary[j+1]) {
					//交换
					int tmp = ary[j];
					ary[j] = ary[j+1];
					ary[j+1] = tmp;
					
					sumSwapTimes++;
					currSwapTimes++;
					
					flag = false;
				}
			}
			
			System.out.println("第" + (i + 1) + "轮冒泡后结果:\t" + Arrays.toString(ary) + " 本轮交换次数:" + currSwapTimes);
			
			if(flag && (i+1) < (size-1)) {
				System.out.println("第" + (i + 1) + "轮冒泡后提前完成排序");
				break;
			}
		}
		
		System.out.println();
		System.out.println("满有序度:" + (size * (size - 1) / 2));
		System.out.println("交换次数(逆序度):" + sumSwapTimes);
	}
}
