package com.jstfs.practice.algorithm.sort.shell;

import java.util.Arrays;

import com.jstfs.common.utils.MyArrayUtils;
import com.jstfs.common.utils.MyDateUtils;
import com.jstfs.common.utils.MyRandomUtils;

/**
 * 希尔排序:
 * 		平均间隔分组,每轮的组数逐渐减少,每组元素数量逐渐增大
 * 		每组元素之间可以使用[交换]或者[移动-插入]的方式进行排序
 * 		如果使用[移动-插入]的方式,相当于一种更加高效的插入排序版本
 * 
 * 特点:
 * 		1, 原地排序算法
 * 		2, 不稳定的排序算法
 * 		3, 时间复杂度:
 * 			最好时间复杂度: O(N*log₂N)
 * 			平均时间复杂度: O(N^[³/₂])
 * 			最坏时间复杂度: O(N²)
 *
 * @createBy 	落叶
 * @createTime 	2022年9月1日 下午12:11:25
 */
public class ShellSort {
	private static int size = 20;
	
	public static void main(String[] args) {
		ShellSort ss = new ShellSort();
		MyRandomUtils.setSeed(System.currentTimeMillis());
		int[] ary = MyRandomUtils.generateIntAry(size, 1, 4 * size);
		System.out.println("原数组:\t\t" + Arrays.toString(ary));
		
		System.out.println("开始时间:" + MyDateUtils.getNowStr());
		ss.sort_swap(ary);
		//ss.sort_insert(ary);
		System.out.println("结束时间:" + MyDateUtils.getNowStr());
	}
	
	/**
	 * 比较元素后,使用交换来排序
	 */
	public void sort_swap(int[] ary) {
		if(ary == null) {
			return;
		}
		int size = ary.length;
		if(size <= 1) {
			return;
		}
		
		int sumSwapTimes = 0;		//总交换次数

		/**
		 * groupCount是每一轮的组数,默认第一轮分为 (ary.length/2) 足
		 * 随着慢慢的在"宏观"上变得有序,组数也逐渐减少,当然每组中的元素随之增多
		 * 
		 * 最后一轮的时候,groupCount等于1,也就是把整个数组看作一组
		 * 但其实此时前一半元素都是较小的元素,后一半都是较大的元素
		 */
		for(int groupCount = ary.length/2; groupCount > 0; groupCount /= 2) {
			/**
			 * 由于每组中的元素是按步长(刚好也等于groupCount)跳着来分布
			 * 比如:
			 * 		第0个, 第groupCount个,  第2*groupCount个,   ...... 为第一组
			 * 		第1个, 第groupCount+1个,第2*groupCount+1个, ...... 为第二组
			 * 
			 * 这样可以保正每一组中的元素在排完序后的位置尽量趋近于它的正确位置
			 * 
			 * 下面的for循环是从第一组的第二个元素,也就是下标为gourpCount的元素开始,与他同组的前一个元素进行比较
			 */
			
			int currSwqpTimes = 0;	//本轮交换次数
			for(int i = groupCount; i < ary.length; i++) {
				/**
				 * 当i等于[  groupCount, 2*groupCount-1] 之间时,底下的for循环每次比较1次
				 * 当i等于[2*groupCount, 3*groupCount-1] 之间时,底下的for循环每轮比较2次
				 * 当i等于[3*groupCount, 4*groupCount-1] 之间时,底下的for循环每轮比较3次
				 * 
				 * 其实就相当于慢慢将每组的每两个"相邻"的元素都进行过一次比较
				 */
				for(int j = i; j - groupCount >= 0; j -= groupCount) {
					if(ary[j] < ary[j - groupCount]) {
						//交换
						MyArrayUtils.swap(ary, j, j - groupCount);
						
						currSwqpTimes++;
						sumSwapTimes++;
					}
				}
			}
			
			System.out.print("分" + groupCount + "组后排序的结果:\t" + Arrays.toString(ary));
			System.out.println(" 本轮交换次数:" + currSwqpTimes);
		}
		
		System.out.println();
		System.out.println("满有序度:" + (size * (size - 1) / 2));
		System.out.println("交换次数:" + sumSwapTimes);
	}
	
	/**
	 * 比较元素后,使用[移动-插入]来排序
	 */
	public void sort_insert(int[] ary) {
		if(ary == null) {
			return;
		}
		int size = ary.length;
		if(size <= 1) {
			return;
		}
		
		int sumMoveTimes = 0;		//总移动次数
		int sumInsertTimes = 0;		//总插入次数

		/**
		 * groupCount是每一轮的组数,默认第一轮分为 (ary.length/2) 足
		 * 随着慢慢的在"宏观"上变得有序,组数也逐渐减少,当然每组中的元素随之增多
		 * 
		 * 最后一轮的时候,groupCount等于1,也就是把整个数组看作一组
		 * 但其实此时前一半元素都是较小的元素,后一半都是较大的元素
		 */
		for(int groupCount = ary.length/2; groupCount > 0; groupCount /= 2) {
			/**
			 * 由于每组中的元素是按步长(刚好也等于groupCount)跳着来分布
			 * 比如:
			 * 		第0个, 第groupCount个,  第2*groupCount个,   ...... 为第一组
			 * 		第1个, 第groupCount+1个,第2*groupCount+1个, ...... 为第二组
			 * 
			 * 这样可以保正每一组中的元素在排完序后的位置尽量趋近于它的正确位置
			 * 
			 * 下面的for循环是从第一组的第二个元素,也就是下标为gourpCount的元素开始,与他同组的前一个元素进行比较
			 */
			
			int currMoveTimes = 0;	//本轮移动次数
			for(int i = groupCount; i < ary.length; i++) {
				/**
				 * 当i等于[  groupCount, 2*groupCount-1] 之间时,底下的for循环每次比较1次
				 * 当i等于[2*groupCount, 3*groupCount-1] 之间时,底下的for循环每轮比较2次
				 * 当i等于[3*groupCount, 4*groupCount-1] 之间时,底下的for循环每轮比较3次
				 * 
				 * 其实就相当于慢慢将每组的每两个"相邻"的元素都进行过一次比较
				 */
				int temp = ary[i];			//本轮需要插入的元素,缓存起来,顺便把位置"腾"出来
				int j = i;
				boolean moveFlag = false;	//本轮是否移动过的标识
				
				while(j - groupCount >= 0) {
					if(ary[j] < ary[j - groupCount]) {
						//移动
						if(!moveFlag) {
							moveFlag = true;
						}
						
						ary[j] = ary[j - groupCount];
						
						currMoveTimes++;
						sumMoveTimes++;
					}
					
					j -= groupCount;
				}
				
				if(moveFlag) {			//有可能并没有发生移动
					ary[j + groupCount] = temp;	//把之前缓存起来的要插入的数据插入到上一次腾出来的位置
					sumInsertTimes++;
				}
			}
			
			System.out.print("分" + groupCount + "组后排序的结果:\t" + Arrays.toString(ary));
			System.out.println(" 本轮移动次数:" + currMoveTimes);
		}
		
		System.out.println();
		System.out.println("满有序度:" + (size * (size - 1) / 2));
		System.out.println("移动次数:" + sumMoveTimes);
		System.out.println("插入次数:" + sumInsertTimes);
	}
}
