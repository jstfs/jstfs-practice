package com.jstfs.practice.algorithm.search.binary;

import java.util.Arrays;

import com.jstfs.common.utils.MyRandomUtils;
import com.jstfs.practice.algorithm.sort.quick.QuickSort;

/**
 * 二分查找变体四: 查找第一个大于等于给定值的元素
 * 
 * @createBy 	落叶
 * @createTime 	2019-1-20 上午12:18:45
 */
public class BinarySearchForFirstGreatThanAndEqual {
	private static QuickSort qs = new QuickSort();
	private static int size = 20;
	private static int searchValue = 20;
	
	public static void main(String[] args) {
		MyRandomUtils.setSeed(System.currentTimeMillis());
		int[] ary = MyRandomUtils.generateIntAry(size, 1, size);
		qs.sort(ary, 0, ary.length - 1);
		System.out.println("有序数组:\t" + Arrays.toString(ary));
		int index = search(ary, 0, ary.length - 1);
		System.out.println("第一个大于等于" + searchValue + "的元素的下标:[" + index + "]");
	}
	
	public static int search(int[] ary, int min, int max) {
		if(searchValue <= ary[0]) {
			//要找的元素小于等于最小值,直接返回下标0
			return 0;
		}
		if(searchValue > ary[ary.length - 1]) {
			//要找的元素比数组的最大值都大,即没找到
			return -1;
		}
		
		int middle = (max + min) >> 1;
		if(ary[middle] >= searchValue) {
			if(ary[middle - 1] < searchValue) {
				return middle;
			}
			return search(ary, min, middle - 1);
		} else {
			if(ary[middle + 1] >= searchValue) {
				return middle + 1;
			}
			return search(ary, middle + 1, max);
		}
	}
}
