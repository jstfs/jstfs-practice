package com.jstfs.practice.algorithm.sort.merge;

import com.jstfs.common.utils.MyArrayUtils;
import com.jstfs.common.utils.MyDateUtils;
import com.jstfs.common.utils.MyRandomUtils;

/**
 * 归并排序: 
 * 		边分区边排序
 * 		当某个区剩余1个或2个元素的时候就开始排序
 * 		当某个区的两个部分都排好序后就马上合并
 * 		左半边完全排好序后,再开始右半边的分区和排序
 * 		等右半边也排好序后,最后就是将两个有序的半边合并到原始数组
 * 
 * 特点:
 * 		1, 非原地排序算法(空间复杂度O(N))
 * 		2, 稳定的排序算法
 * 		3, 时间复杂度:
 * 			最好时间复杂度: O(N*log₂N)
 * 			平均时间复杂度: O(N*log₂N)
 * 			最坏时间复杂度: O(N*log₂N)
 * 
 * 归并排序的平均时间复杂度推导:
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
 * 			分区的元素只剩1个的时候,即此时k达到最大值.
 * 		那么:
 * 			此时 T(N/2^k) = T(1) = C
 * 			即: N/2^k = 1
 * 		得到:
 * 			2^k = N
 * 			k=log₂N
 * 		所以:
 * 			T(N) = 2^k * T(N/2^k) + k*N
 * 				 = N * C + log₂N*N
 * 				 = N*log₂N
 *
 * @createBy 	落叶
 * @createTime 	2018-12-6 下午3:49:05
 */
public class MergeSort {
	private static int size = 4000000;
	
	public static void main(String[] args) {
		MergeSort ms = new MergeSort();
		int[] ary = MyRandomUtils.generateIntAry(size, 1, 4 * size);
		int[] tempAry = new int[ary.length];
		//System.out.println("原数组:\t" + Arrays.toString(ary));
		
		System.out.println("开始时间:" + MyDateUtils.getNowStr(MyDateUtils.yyyyMMdd_HHmmssSSS_));
		ms.sort(ary, 0, ary.length - 1, tempAry);
		System.out.println("结束时间:" + MyDateUtils.getNowStr(MyDateUtils.yyyyMMdd_HHmmssSSS_));
		//System.out.println("排序后:\t" + Arrays.toString(ary));
	}
	
	public void sort(int[] ary, int left, int right, int[] tempAry) {
		if(left < right) {
			int middle = (left + right) / 2;
			sort(ary, left, middle, tempAry);
			sort(ary, middle + 1, right, tempAry);
			
			merge(ary, left, middle, right, tempAry);
		}
	}
	
	/**
	 * 将原数组的[left, right]段进行归并
	 */
	private void merge(int[] ary, int left, int middle, int right, int[] tempAry) {
		if(right - left == 1) {
			//优化两两比较,如果只有两个值比较,直接比较并交换
			if(ary[left] > ary[right]) {
				MyArrayUtils.swap(ary, left, right);
			}
			return;
		}
		
		int tempIndex = 0;			//指向临时数组中需要存放元素的下标
		int leftIndex = left;		//指向左边需要比较的元素(向右移动)
		int rightIndxe = middle + 1;//指向右边需要比较的元素(向右移动)

		/**
		 * 左半部分和右半部分都是从左到右一次拿出一个元素来比较
		 * 较小元素就插入到临时数组,然后指针右移,直到某一部分没有元素为止
		 */
		while(leftIndex <= middle && rightIndxe <= right) {
			if(ary[leftIndex] < ary[rightIndxe]) {
				tempAry[tempIndex++] = ary[leftIndex++];
			} else {
				tempAry[tempIndex++] = ary[rightIndxe++];
			}
		}
		
		/**
		 * 将另一部分剩余的元素插入到临时数据
		 */
		while(leftIndex <= middle) {
			//如果左边有剩余
			tempAry[tempIndex++] = ary[leftIndex++];
		}
		while(rightIndxe <= right) {
			//如果右边有剩余
			tempAry[tempIndex++] = ary[rightIndxe++];
		}
		
		/**
		 * 将临时数据复制到原数组的[left, right]段
		 */
		tempIndex = 0;
		int copyIndex = left;
		while(copyIndex <= right) {
			ary[copyIndex++] = tempAry[tempIndex++];
		}
	}
}
