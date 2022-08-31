package com.jstfs.practice.algorithm.sort.selection;

import java.util.Arrays;

import com.jstfs.common.utils.MyRandomUtils;

/**
 * 选择排序:
 * 	反向冒泡,即每轮找剩下数据的最小值,找到后再交换到之前找到的最小值的后面
 * 	类似插入排序,相当于与将数据分为[已排序区]和[未排序区],只不过每轮从未排序区找出最小值将其放到已排序区的末尾
 * 	不能提前退出
 * 	
 * 	1, 原地排序算法
 * 	2, 不稳定的排序算法(对相同的值排序后,位置可能发生变化)
 * 	3, 时间复杂度:
 * 		最好时间复杂度: O(N²)
 * 		平均时间复杂度: O(N²)
 * 		最坏时间复杂度: O(N²)
 * 
 * @createBy jstfs
 * @createTime 2018-12-4 上午12:06:39
 */
public class SelectionSort {
	private static int size = 10;
	
	public static void main(String[] args) {
		SelectionSort ss = new SelectionSort();
		int[] ary = MyRandomUtils.generateIntAry(size, size*5);
		System.out.println("原数组:\t\t" + Arrays.toString(ary));
		ss.sort(ary);
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
			int min = ary[i];	//本轮的最小值. 由于小标i之前的都已经排好序了,所以本轮默认i位置的为最小值
			int minIndex = i;	//本轮中最小值的下标
			for(int j = i; j < size - 1; j++) {
				//找本轮最小值
				if(min > ary[j+1]) {
					//找到更小的则更新最小值和最小值的下标
					min = ary[j+1];
					minIndex = j + 1;
				}
			}
			
			//找到本轮最小值之后,如果最小值变化了,则将该最小值与本轮开始的位置(也就是位置i)交换
			if(minIndex != i) {
				int temp = ary[i];
				ary[i] = min;
				ary[minIndex] = temp;
				sumSwapTimes++;
			}
			
			System.out.println("第" + (i + 1) + "轮排序后结果:\t" + Arrays.toString(ary));
		}
		
		System.out.println();
		System.out.println("满有序度:" + (size * (size - 1) / 2));
		System.out.println("交换次数:" + sumSwapTimes);
	}
}
