package com.jstfs.practice.algorithm.sort.quick;

import java.util.Arrays;

import com.jstfs.common.utils.MyRandomUtils;

/**
 *  快速排序:
 * 		使用最后一个元素(该元素称作分区点,)将目标分成两部分
 * 		两部分中:一部分大于分区点,一部分小于等于分区点.然后将分区点放入两个部分之间
 * 		从宏观上看,该数组已经分成了有序的三部分(一堆比较小的,一个中间的分区点,一堆比较大的)
 * 		然后继续上述步骤(使用递归),直到分区后的两部分没有元素或者只有1个元素,则整个数组就已经排好序了
 * 
 * 		与归并排序很像,但区别是:一边分区一边排序
 * 
 * 		分区点的选择: 
 * 			分区点选择的不好,算法的效率就会退化,甚至退化到O(N²). 即:数据本来就是有序的时候,每次都选择最后一个元素.
 * 			所以一般对分区点优化的方法有:
 * 				1, 三数取中法: 
 * 					分别取区间的头尾元素和中间元素,取三个元素的中间值作为分区点
 * 					当数据量非常大的时候,可以适当使用五数取中,甚至十数取中.
 * 				2, 随机法: 
 * 					这样可以一定成都上避免退化
 *
 * 	1, 非原地排序算法(空间复杂度O(N))
 * 	2, 稳定的排序算法
 * 	3, 时间复杂度:
 * 		最好时间复杂度: O(N*log₂N)
 * 		平均时间复杂度: O(N*log₂N)
 * 		最坏时间复杂度: O(N²)
 * 
 * 或者
 * 
 * 	1, 原地排序算法
 * 	2, 不稳定的排序算法
 * 	3, 时间复杂度:
 * 		最好时间复杂度: O(N*log₂N)
 * 		平均时间复杂度: O(N*log₂N)
 * 		最坏时间复杂度: O(N²)
 * 
 * @createBy 	落叶
 * @createTime 	2018-12-6 下午11:35:08
 */
public class QuickSort {
	private static int size = 15;
	
	public static void main(String[] args) {
		QuickSort qs = new QuickSort();
		Integer[] ary = MyRandomUtils.generateIntegerAry(size);
		System.out.println(Arrays.toString(ary));
		qs.sort(ary, false);
		System.out.println(Arrays.toString(ary));
	}
	
	public void sort(Integer[] ary, boolean isInplace) {
		sort(ary, 0, ary.length - 1, isInplace);
	}

	/**
	 * @param isInplace	是否原地排序
	 */
	private void sort(Integer[] ary, int begin, int end, boolean isInplace) {
		if(begin >= end) {
			return ;
		}
		
		int ptnPointIndex;
		if(isInplace) {
			ptnPointIndex = partitionInplace(ary, begin, end);
		} else {
			ptnPointIndex = partitionNotInplace(ary, begin, end);
		}
		 
		sort(ary, begin, ptnPointIndex - 1, isInplace);
		sort(ary, ptnPointIndex + 1, end, isInplace);
	}
	
	/**
	 * 将数组的指定分段分区并返回分区点的下标(原地排序版本,但不是稳定的排序)
	 */
	public int partitionInplace(Integer[] ary, int begin, int end) {
		int biggerBorderLine = begin;	//边界指针: 默认该指针的右边(包括该指针)都是大于分区点值的元素
		int ptnPointValue = ary[end];	//分区点值
		for(int i = begin; i < end; i++) {
			if(ary[i] <= ptnPointValue) {	//将下标为 i 和 biggerBorderLine 的元素交换
				int temp = ary[i];
				ary[i] = ary[biggerBorderLine];
				ary[biggerBorderLine] = temp;
				biggerBorderLine++;	//将边界右移
			}
		}
		
		//将 biggerBorderLine 和 end 指向的元素交换
		int temp = ptnPointValue;
		ary[end] = ary[biggerBorderLine];
		ary[biggerBorderLine] = temp;
		
		return biggerBorderLine;	//下一个分区点的下标
	}
	
	/**
	 * 将数组的指定分段分区并返回分区点的下标(非原地排序版本,但直观、简单并且是稳定的排序)
	 */
	public int partitionNotInplace(Integer[] ary, int begin, int end) {
		int ptnPointValue = ary[end];	//分区点值
		int[] temp1 = new int[end-begin+1];
		int[] temp2 = new int[end-begin+1];
		int temp1Index = 0;
		int temp2Index = 0;
		
		for(int i = begin; i < end;) {
			if(ary[i] <= ptnPointValue) {
				temp1[temp1Index++] = ary[i++];
			} else {
				temp2[temp2Index++] = ary[i++];
			}
		}
		temp1[temp1Index] = -1;	//数组末尾标记
		temp2[temp2Index] = -1;	//数组末尾标记
		
		int ptnPointIndex = begin;
		for(int m = 0; m < temp1.length; m++) {
			if(temp1[m] != -1) {
				ary[begin++] = temp1[m];
				ptnPointIndex++;	//最后一次++之后,就是分区点坐标
			} else {
				break;
			}
		}
		
		ary[begin++] = ptnPointValue;	//分区点放到两个部分的中间
		
		for(int n = 0; n < temp2.length; n++) {
			if(temp2[n] != -1) {
				ary[begin++] = temp2[n];
			} else {
				break;
			}
		}
		
		return ptnPointIndex;
	}
	
	
}