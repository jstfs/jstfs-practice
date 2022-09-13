package com.jstfs.practice.algorithm.search.binary;

import java.util.Arrays;

import com.jstfs.common.utils.MyRandomUtils;
import com.jstfs.practice.algorithm.sort.quick.QuickSort;

/**
 * 二分查找:
 * 		数据必须是有序的
 * 		数据结构是顺序表结构,如果是链表结构的话,不适合随机存取
 * 
 * 特点:
 *		时间复杂度: O(log₂N)
 * 
 * @createBy 	落叶
 * @createTime 	2019-1-19 上午11:33:26
 */
public class BinarySearch {
	private static QuickSort qs = new QuickSort();
	private static int size = 50;
	private static int searchValue = 5;
	
	public static void main(String[] args) {
		MyRandomUtils.setSeed(System.currentTimeMillis());
		int[] ary = MyRandomUtils.generateIntAry(size, 1, 4 * size);
		qs.sort(ary, 0, ary.length - 1);
		System.out.println("有序数组:\t" + Arrays.toString(ary));
		int index = search(ary, 0, ary.length - 1);
		System.out.println("随机一个等于" + searchValue + "的元素的下标:[" + index + "]");
	}
	
	public static int search(int[] ary, int min, int max) {
		if(ary[min] > searchValue || ary[max] < searchValue) {
			//要找的元素不在段内,这样可以提前结束
			//比如这种情况下{1,2,3,4,6,7,8}找5,在第二轮一开始就结束了
			return -1;
		}
		if(min == max && ary[min] != searchValue) {
			//要找的元素在数组的范围内,但不在数组内
			//这种情况,最终都会回到 min==max
			return -1;
		}
		
		int middle = (max + min) >> 1;
		if(ary[middle] > searchValue) {
			return search(ary, min, middle - 1);
		} else if(ary[middle] < searchValue) {
			return search(ary, middle + 1, max);
		} else {
			return middle;
		}
	}
}
