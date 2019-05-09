package com.jstfs.practice.algorithm.search.binary;

import java.util.Arrays;

import com.jstfs.common.utils.MyRandomUtils;
import com.jstfs.practice.algorithm.sort.quick.QuickSort;

/**
 * 二分查找法:
 * 		1, 数据必须是有序的
 * 		2, 并且数据结构是顺序表结构,简单说就是数组,因为需要根据下标随机访问,如果是链表结构的话,时间复杂度会高很多
 * 		3, 当数据量很大时,对内存的要求就会很高,因为需要用数组存储,所以必须存在一段很大的连续的内存空间
 * 		         有可能总内存足够,但就是没有一段连续的较大的内存空间
 * 
 *	时间复杂度: O(log₂N)
 * 
 * @createBy jstfs
 * @createTime 2019-1-19 上午11:33:26
 */
public class BinarySearch {
	private static QuickSort qs = new QuickSort();
	private static int size = 50;
	private static int searchValue = 1;
	
	public static void main(String[] args) {
		Integer[] ary = MyRandomUtils.generateIntegerAry(size);
		System.out.println(Arrays.toString(ary));
		qs.sort(ary, false);
		System.out.println(Arrays.toString(ary));
		
		System.out.println("随机一个等于" + searchValue + "的数的下标:[" + search(ary, 0, ary.length - 1, searchValue) + "]");
	}
	
	public static int search(Integer[] ary, int min, int max, int value) {
		if(min >= max) {
			return -1;
		}
		
		int middle = min + ((max - min) >> 1);
		if(ary[middle] > value) {
			return search(ary, min, middle - 1, value);
		} else if(ary[middle] < value) {
			return search(ary, middle + 1, max, value);
		} else {
			return middle;
		}
	}
}
