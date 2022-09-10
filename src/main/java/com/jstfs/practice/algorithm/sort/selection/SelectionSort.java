package com.jstfs.practice.algorithm.sort.selection;

import java.util.Arrays;

import com.jstfs.common.utils.MyDateUtils;
import com.jstfs.common.utils.MyRandomUtils;

/**
 * 选择排序: 
 * 		使用[选择-交换]元素来排序
 * 		反向冒泡,即每轮找剩下数据中的最小值,找到后再交换到之前找到的最小值的后面
 * 		不能提前退出
 * 
 * 特点:
 * 		1, 原地排序算法
 * 		2, 不稳定的排序算法
 * 		3, 时间复杂度:
 * 			最好时间复杂度: O(N²)
 * 			平均时间复杂度: O(N²)
 * 			最坏时间复杂度: O(N²)
 * 
 * @createBy 	落叶
 * @createTime 	2018-12-4 上午12:06:39
 */
public class SelectionSort {
	private static int size = 20;
	
	public static void main(String[] args) {
		SelectionSort ss = new SelectionSort();
		MyRandomUtils.setSeed(System.currentTimeMillis());
		int[] ary = MyRandomUtils.generateIntAry(size, 1, 4 * size);
		System.out.println("原数组:\t\t" + Arrays.toString(ary));
		
		System.out.println("开始时间:" + MyDateUtils.getNowStr());
		ss.sort(ary);
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
		
		int sumSelectTimes = 0;		//总选择次数
		int sumSwapTimes = 0;		//总交换次数
		
		for(int i = 0; i < size - 1; i++) {
			int min = ary[i];	//本轮的最小值. 由于小标i之前的都已经排好序了,所以本轮默认i位置的为最小值
			int minIndex = i;	//本轮中最小值的下标
			int currSelectTimes = 0;	//本轮选择次数
			
			for(int j = i; j < size - 1; j++) {
				//找本轮最小值
				if(min > ary[j+1]) {
					//找到更小的则更新最小值和最小值的下标
					min = ary[j+1];
					minIndex = j + 1;
					
					currSelectTimes++;
					sumSelectTimes++;
				}
			}
			
			//找到本轮最小值之后,如果最小值变化了,则将该最小值与本轮开始的位置(也就是位置i)交换
			int temp = 0;		//交换值的临时变量
			if(minIndex != i) {
				temp = ary[i];
				ary[i] = min;
				ary[minIndex] = temp;
				sumSwapTimes++;
			}
			
			System.out.print("第" + (i + 1) + "轮排序后结果:\t" + Arrays.toString(ary));
			System.out.println(" 本轮选择次数:" + currSelectTimes);
		}
		
		System.out.println();
		System.out.println("满有序度:" + (size * (size - 1) / 2));
		System.out.println("选择次数:" + sumSelectTimes);
		System.out.println("交换次数:" + sumSwapTimes);
	}
}
