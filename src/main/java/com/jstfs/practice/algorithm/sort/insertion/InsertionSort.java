package com.jstfs.practice.algorithm.sort.insertion;

import java.util.Arrays;

import com.jstfs.common.utils.MyRandomUtils;

/**
 * 插入排序: 
 * 	将数据分为已排序区和未排序区,默认第一个元素是已排序区,之后将每一个元素与已排序区元素进行比较,并插入到已排序区的适当位置
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
		int[] ary = MyRandomUtils.generateIntAry(size, size*3);
		System.out.println("原数组:\t\t" + Arrays.toString(ary));
		is.sort(ary);
	}
	
	public void sort(int[] ary) {
		if(ary == null) {
			return;
		}
		int size = ary.length;
		if(size <= 1) {
			return;
		}
		
		int sumMoveTimes = 0;	//数据总移动次数
		for(int i = 1; i < size; i++) {	//认为第1个元素已经有序,所以从第2个元素开始
			int cur = ary[i];	//需要插入的元素,缓存起来,顺便把位置"腾"出来
			int j = i - 1;		//起始比较的元素位置(相当于从已排序区的最后一个元素开始往前比较)
			int currMoveTimes = 0;	//本轮数据移动次数
			while(j >= 0) {
				if(ary[j] > cur) {
					//移动
					ary[j+1] = ary[j];	//将大数向后移动一位,那它原本的位置就会腾出来,提前等待curr"入住",但也得和前面的数继续比较后才能决定
					
					sumMoveTimes++;
					currMoveTimes++;
					
					j--;
				} else {
					break;		//本轮结束
				}
			}
			ary[j+1] = cur;	//把之前缓存起来的要插入的数据插入到上一次腾出来的位置[j+1]
			
			System.out.println("第" + (i + 1) + "轮排序后结果:\t" + Arrays.toString(ary) + " 本轮移动次数:" + currMoveTimes);
		}
		
		System.out.println();
		System.out.println("满有序度:" + (size * (size - 1) / 2));
		System.out.println("移动次数:" + sumMoveTimes);
	}
}
