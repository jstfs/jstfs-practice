package com.jstfs.practice.algorithm.sort.bubble;

import java.util.Arrays;

import com.jstfs.common.utils.MyArrayUtils;
import com.jstfs.common.utils.MyDateUtils;
import com.jstfs.common.utils.MyRandomUtils;

/**
 * 冒泡排序: 
 * 		比较后使用[交换]元素来排序
 * 		每轮找最大值逐步向后交换
 * 		可以提前退出
 * 	
 * 
 * 特点:
 * 		1, 原地排序算法
 * 		2, 稳定的排序算法
 * 		3, 时间复杂度:
 * 			最好时间复杂度: O(N)
 * 			平均时间复杂度: O(N²)
 * 			最坏时间复杂度: O(N²)
 *
 * @createBy 	落叶
 * @createTime 	2018-12-3 下午10:59:24
 */
public class BubbleSort {
	private static int size = 20;
	
	public static void main(String[] args) {
		BubbleSort bs = new BubbleSort();
		MyRandomUtils.setSeed(System.currentTimeMillis());
		int[] ary = MyRandomUtils.generateIntAry(size, 1, 4 * size);
		System.out.println("原数组:\t\t" + Arrays.toString(ary));
		
		System.out.println("开始时间:" + MyDateUtils.getNowStr());
		bs.sort(ary);
		System.out.println("结束时间:" + MyDateUtils.getNowStr());
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
		for(int i = 0; i < size - 1; i++) {
			boolean flag = true;	//提前退出冒泡的标识
			int currSwapTimes = 0;	//本轮交换次数
			
			for(int j = 0; j < size - i - 1; j++) {
				if(ary[j] > ary[j+1]) {
					//交换
					MyArrayUtils.swap(ary, j, j+1);
					
					sumSwapTimes++;
					currSwapTimes++;
					
					flag = false;
				}
			}
			
			System.out.print("第" + (i + 1) + "轮排序后结果:\t" + Arrays.toString(ary));
			System.out.println(" 本轮交换次数:" + currSwapTimes);
			
			if(flag && (i+1) < (size - 1)) {
				System.out.println("第" + (i + 1) + "轮排序后提前完成排序");
				break;
			}
		}
		
		System.out.println();
		System.out.println("满有序度:" + (size * (size - 1) / 2));
		System.out.println("交换次数:" + sumSwapTimes);
	}
}
