package com.jstfs.practice.algorithm.sort.selection;

import java.util.Arrays;

import com.jstfs.common.utils.MyRandomUtils;

/**
 * 选择排序: 类似插入排序,也是将数据分为已排序区和未排序区,只不过每次从未排序区找出最小值将其放到已排序区的末尾
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
	private static int size = 15;
	
	public static void main(String[] args) {
		SelectionSort ss = new SelectionSort();
		int[] ary = MyRandomUtils.generateIntAry(size);
		System.out.println(Arrays.toString(ary));
		ss.sort(ary);
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
		
		for(int i = 0; i < size; i++) {
			int min = ary[i];
			int minIndex = i;
			for(int j = i; j < size - 1; j++) {
				if(min > ary[j+1]) {
					min = ary[j+1];
					minIndex = j + 1;
				}
			}
			//交换
			int temp = ary[i];
			ary[i] = min;
			ary[minIndex] = temp;
		}
		
		System.out.println("满有序度:" + (size * (size - 1) / 2));
	}
}
