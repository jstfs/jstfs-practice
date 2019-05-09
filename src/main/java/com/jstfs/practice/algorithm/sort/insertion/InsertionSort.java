package com.jstfs.practice.algorithm.sort.insertion;

import java.util.Arrays;

import com.jstfs.common.utils.MyRandomUtils;

/**
 * 插入排序: 将数据分为已排序区和未排序区,默认第一个元素是已排序区,之后将每一个元素与已排序区元素进行比较,并插入到已排序区的适当位置
 * 	
 * 	1, 原地排序算法
 * 	2, 稳定的排序算法
 * 	3, 时间复杂度:
 * 		最好时间复杂度: O(N)
 * 		平均时间复杂度: O(N²)
 * 		最坏时间复杂度: O(N²)
 * 
 * @createBy jstfs
 * @createTime 2018-12-3 下午11:33:03
 */
public class InsertionSort {
	private static int size = 15;
	
	public static void main(String[] args) {
		InsertionSort is = new InsertionSort();
		int[] ary = MyRandomUtils.generateIntAry(size);
		System.out.println(Arrays.toString(ary));
		is.sort(ary);
		System.out.println(Arrays.toString(ary));
	}
	
	public void sort(int[] ary) {
		if(ary == null) {
			return;
		}
		int size = ary.length;
		if(size <= 1) {
			return;
		}
		
		int moveTimes = 0;
		for(int i = 1; i < size; i++) {
			int cur = ary[i];	//需要插入的元素,缓存起来,顺便把位置"腾"出来
			int j = i - 1;	//起始比较的元素位置(相当于从已排序区的最后一个元素开始往前比较)
			for(; j >= 0; j--) {
				if(ary[j] > cur) {
					ary[j+1] = ary[j];	//后移一位
					moveTimes++;
				} else {
					break;	//位置已经找到就跳出
				}
			}
			ary[j+1] = cur;	//插入数据
		}
		
		System.out.println("满有序度:" + (size * (size - 1) / 2));
		System.out.println("逆序度(交换次数):" + moveTimes);
	}
}
