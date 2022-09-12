package com.jstfs.practice.algorithm.search.binary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jstfs.common.utils.MyCollectionUtils;
import com.jstfs.common.utils.MyRandomUtils;
import com.jstfs.practice.algorithm.sort.quick.QuickSort;

/**
 * 二分查找变体三: 查找所有给定的元素
 * 
 * @createBy	落叶
 * @createTime	2022年9月12日 上午12:39:05
 */
public class BinarySearchForAll {
	private static QuickSort qs = new QuickSort();
	private static int size = 50;
	private static int searchValue = 3;
	
	public static void main(String[] args) {
		MyRandomUtils.setSeed(System.currentTimeMillis());
		int[] ary = MyRandomUtils.generateIntAry(size, 1, size);
		qs.sort(ary, 0, ary.length - 1);
		System.out.println("有序数组:\t" + Arrays.toString(ary));
		int[] indexAry = MyCollectionUtils.toArray(search(ary, 0, ary.length - 1));
		qs.sort(indexAry, 0, indexAry.length - 1);
		System.out.println("所有等于" + searchValue + "的元素的下标:" + Arrays.toString(indexAry));
	}
	
	public static List<Integer> search(int[] ary, int min, int max) {
		if(ary[0] > searchValue || ary[ary.length - 1] < searchValue) {
			//要找的元素不在数组的范围内
			return new ArrayList<Integer>();
		}
		if(min == max && ary[min] != searchValue) {
			//要找的元素在数组的范围内,但不在数组内
			//这种情况,最终都会回到 min==max
			return new ArrayList<Integer>();
		}
		
		int middle = (max + min) >> 1;
		if(ary[middle] > searchValue) {
			return search(ary, min, middle - 1);
		} else if(ary[middle] < searchValue) {
			return search(ary, middle + 1, max);
		} else {
			List<Integer> indexList = new ArrayList<Integer>();
			indexList.add(middle);
			
			int i = middle - 1;
			while(true) {
				if(i < 0 || ary[i] != searchValue) {
					break;
				} else {
					indexList.add(i--);
				}
			}
			
			i = middle + 1;
			while(true) {
				if(i > ary.length - 1 || ary[i] != searchValue) {
					break;
				} else {
					indexList.add(i++);
				}
			}
			return indexList;
		}
	}
}
