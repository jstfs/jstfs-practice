package com.jstfs.practice.algorithm.sort.merge;

import java.util.Arrays;

import com.jstfs.common.utils.MyArrayUtils;
import com.jstfs.common.utils.MyRandomUtils;

/**
 * 归并排序: 
 * 		先分区,一直分区,直到分区只有1个或者2个元素时就直接比较大小
 * 		然后逐层两两分区合并(使用递归),合并的过程才开始排序,当合并回最初的大数组时,也就是有序的了
 * 
 * 		与快速排序很像,但区别是:先分区,再集中排序(合并时)
 * 
 * 	1, 非原地排序算法(空间复杂度O(N))
 * 	2, 稳定的排序算法
 * 	3, 时间复杂度:
 * 		最好时间复杂度: O(N*log₂N)
 * 		平均时间复杂度: O(N*log₂N)
 * 		最坏时间复杂度: O(N*log₂N)
 * 		
 * 		假设: 
 * 			T(1) = C, 表示N=1时,只需要常量级的执行时间C
 * 		那么:
 * 			T(N)  = 2 * T(N/2)  + 1*N	= 2 * (2 * T(N/4)  + N/2) + 1*N
 * 			 	  = 4 * T(N/4)  + 2*N 	= 4 * (2 * T(N/8)  + N/4) + 2*N
 * 			 	  = 8 * T(N/8)  + 3*N	= 8 * (2 * T(N/16) + N/8) + 3*N
 * 			 	  = 16* T(N/16) + 4*N
 * 			 	  ......
 * 			 	  = 2^k * T(N/2^k) + k*N
 * 		
 * 		当:	
 * 			N/2^k = 1 时
 * 		得到:
 * 			k=log₂N
 * 		所以:
 * 			T(N) = C*N + N*log₂N
 *
 * @createBy jstfs
 * @createTime 2018-12-6 下午3:49:05
 */
public class MergeSort {
	private static int size = 15;
	
	public static void main(String[] args) {
		MergeSort ms = new MergeSort();
		int[] ary = MyRandomUtils.generateIntAry(size);
		System.out.println(Arrays.toString(ary));
		ary = ms.sort(ary);
		System.out.println(Arrays.toString(ary));
	}
	
	public int[] sort(int[] ary) {
		//递归结束条件
		if(MyArrayUtils.isEmpty(ary) || ary.length == 1) {
			return ary;
		} else if(ary.length == 2) {
			if(ary[0] > ary[1]) {
				int tmp = ary[0];
				ary[0] = ary[1];
				ary[1] = tmp;
			}
			return ary;
		}
		
		//数组元素大于2个,就继续分成更小的数组
		int middleIndex = ary.length >> 1;
		int[] temp1 = sort(MyArrayUtils.subIntArray(ary, 0, middleIndex - 1));
		int[] temp2 = sort(MyArrayUtils.subIntArray(ary, middleIndex, ary.length - 1));
		return merge(temp1, temp2);
	}
	
	/**
	 * 将两个有序的数组合并成一个有序的大数组
	 */
	private int[] merge(int[] aryA, int[] aryB) {
		int[] temp = new int[aryA.length + aryB.length];
		int tempIndex = 0;
		int aryAIndex = 0;
		int aryBIndex = 0;
		for(; aryAIndex < aryA.length && aryBIndex < aryB.length;) {
			if(aryA[aryAIndex] <= aryB[aryBIndex]) {
				temp[tempIndex] = aryA[aryAIndex++];
			} else {
				temp[tempIndex] = aryB[aryBIndex++];
			}
			tempIndex++;
		}
		
		//将数组A中剩下的元素放入大数组中
		if(aryAIndex < aryA.length) {
			for(; aryAIndex < aryA.length; aryAIndex++) {
				temp[tempIndex++] = aryA[aryAIndex];
			}
		}
		
		//将数组B中剩下的元素放入大数组中
		if(aryBIndex < aryB.length) {
			for(; aryBIndex < aryB.length; aryBIndex++) {
				temp[tempIndex++] = aryB[aryBIndex];
			}
		}
		
		return temp;
	}
}
