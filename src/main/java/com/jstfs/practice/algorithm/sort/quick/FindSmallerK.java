package com.jstfs.practice.algorithm.sort.quick;

import java.util.Arrays;

import com.jstfs.common.utils.MyRandomUtils;

/**
 * 查找第K小元素:利用快速排序的分区思想
 * 
 * 最好时间复杂度: O(N)
 * 平均时间复杂度: O(N)
 * 最坏时间复杂度: O(N²)
 *
 * @createBy 	落叶
 * @createTime 	2018-12-10 上午1:45:56
 */
public class FindSmallerK {
	private static QuickSort qs = new QuickSort();
	
	public static void main(String[] args) {
		FindSmallerK fsk = new FindSmallerK();
		Integer[] ary = MyRandomUtils.generateIntegerAry(10);
		System.out.println(Arrays.toString(ary));
		int K = 4;
		System.out.println("第" + K + "小元素为: " + fsk.find(ary, 0, ary.length - 1, K));
		qs.sort(ary, true);
		System.out.println(Arrays.toString(ary));
	}

	public int find(Integer[] ary, int begin, int end, int k) {
		int ptnPointIndex = qs.partitionInplace(ary, begin, end);
		if((ptnPointIndex + 1) == k) {
			return ary[ptnPointIndex];
		} else if((ptnPointIndex + 1) > k) {
			return find(ary, begin, ptnPointIndex - 1, k);
		} else {
			return find(ary, ptnPointIndex + 1, end, k);
		}
	}
}