package com.jstfs.practice.algorithm.search.binary;

import java.util.Arrays;

import com.jstfs.common.utils.MyRandomUtils;
import com.jstfs.practice.algorithm.sort.quick.QuickSort;

/**
 * 二分查找变体二: 查找第一个大于等于给定值的元素
 * 
 * @createBy 	落叶
 * @createTime 	2019-1-20 上午12:18:45
 */
public class BinarySearchForFirstGreatThanAndEqual {
	private static QuickSort qs = new QuickSort();
	private static int size = 50;
	private static int searchValue = 0;
	
	public static void main(String[] args) {
		Integer[] ary = MyRandomUtils.generateIntegerAry(size);
		System.out.println(Arrays.toString(ary));
		qs.sort(ary, false);
		System.out.println(Arrays.toString(ary));
		
		System.out.println("第一个大于等于" + searchValue + "的数的下标:[" + search(ary, 0, ary.length - 1) + "]");
	}
	
	public static int search(Integer[] ary, int min, int max) {
		if(min >= max) {
			return -1;
		}
		
		int middle = min + ((max - min) >> 1);
		if(ary[middle] >= searchValue) {
			if(middle == 0 || ary[middle - 1] < searchValue) {
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
