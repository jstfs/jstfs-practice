package com.jstfs.practice.algorithm.sort.radix;

import java.util.Arrays;

import com.jstfs.common.utils.MyRandomUtils;
import com.jstfs.practice.algorithm.sort.counting.CountingSort;

/**
 * 基数排序:
 * 		也会使用到桶的概念,对数据要求如下:
 * 			1, 数据必须可以分割出独立的"位"来进行每一位之间的比较
 * 			2, 每一位的数据范围不能太大,这样就可以使用到计数排序.
 * 		
 * 		具体操作:
 * 			从后向前对每一位进行稳定的计数排序,一直到第一位排序结束
 * 
 * 		比如:
 * 			有20万个手机号,由于手机号都是11位,而每一位的范围就是0-9这10个数字.
 * 		
 * 	1, 非原地排序算法(空间复杂度O(N + m))
 * 	2, 稳定的排序算法
 * 	3, 时间复杂度:
 * 		最好时间复杂度: O(N)
 * 		平均时间复杂度: O(N)
 * 		最坏时间复杂度: O(N)
 * 
 * @createBy 	落叶
 * @createTime 	2019-1-18 下午2:11:10
 */
public class RadixSort {
	private static int size = 500;		//数据量
	private static int dataLength = 11;	//数据长度
	private static int min = 0;			//每一位上的最小值
	private static int max = 9;			//每一位上的最大值
	CountingSort cs = new CountingSort();
	
	public static void main(String[] args) {
		RadixSort rs = new RadixSort();
		String[] phoneNos = MyRandomUtils.generatePhoneNo(size, dataLength);;
		System.out.println(Arrays.toString(phoneNos));
		rs.sort(phoneNos, dataLength);
		System.out.println(Arrays.toString(phoneNos));
	}
	
	public void sort(String[] phoneNos, int dataLength) {
		for(int bit = (dataLength - 1); bit >= 0; bit--) {
			cs.sort(phoneNos, min, max, bit);
		}
	}
}
