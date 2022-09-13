package com.jstfs.practice.algorithm.search.interpolation;

import java.util.Arrays;

import com.jstfs.common.utils.MyRandomUtils;
import com.jstfs.practice.algorithm.sort.quick.QuickSort;

/**
 * 插值查找:
 * 		数据必须是有序的
 * 		数据结构是顺序表结构,如果是链表结构的话,不适合随机存取
 * 		数据的分布越均匀越好
 * 		数据均匀的情况下,越是查找两端的元素越快
 * 
 * @createBy	落叶
 * @createTime	2022年9月12日 下午2:34:55
 */
public class InterpolationSearch {
	private static QuickSort qs = new QuickSort();
	private static int size = 20;
	private static int searchValue = 20;
	
	public static void main(String[] args) {
		MyRandomUtils.setSeed(System.currentTimeMillis());
		int[] ary = MyRandomUtils.generateIntAry(size, 1, size);
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
		
		int middle = min + (max - min) * (searchValue - ary[min]) / (ary[max] - ary[min]);
		if(ary[middle] > searchValue) {
			return search(ary, min, middle - 1);
		} else if(ary[middle] < searchValue) {
			return search(ary, middle + 1, max);
		} else {
			return middle;
		}
	}
}
