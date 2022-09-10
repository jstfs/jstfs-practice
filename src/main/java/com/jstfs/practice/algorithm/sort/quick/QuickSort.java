package com.jstfs.practice.algorithm.sort.quick;

import com.jstfs.common.utils.MyArrayUtils;
import com.jstfs.common.utils.MyDateUtils;
import com.jstfs.common.utils.MyRandomUtils;

/**
 * 快速排序:
 * 		使用某一个元素(该元素称作分区点)将目标分成两部分
 * 		两部分中:一部分小于分区点,一部分大于等于分区点
 * 		此时会得到两部分的边界,然后再对这两部分分别使用同样的分区排序,所以会用到递归
 * 		直到分区只有两个元素时,直接比较大小即可
 * 
 * 		分区点的选择: 
 * 			分区点选择的不好,算法的效率就会退化,甚至退化到O(N²)
 * 			所以一般对分区点的选择有如下方法:
 * 				1, 三数取中: 
 * 					分别取区间的头元素、尾元素和中间元素中的中间值作为分区点
 * 					当数据量非常大的时候,可以适当使用五数取中,甚至十数取中
 * 				2, 随机: 
 * 					这样可以一定成都上避免退化
 *
 * 特点:
 * 		1, 原地排序算法
 * 		2, 不稳定的排序算法
 * 		3, 时间复杂度:
 * 			最好时间复杂度: O(N*log₂N)
 * 			平均时间复杂度: O(N*log₂N)
 * 			最坏时间复杂度: O(N²)
 * 
 * @createBy 	落叶
 * @createTime 	2018-12-6 下午11:35:08
 */
public class QuickSort {
	private static int size = 4000000;
	private int sumSwapTimes = 0;	//总交换次数
	
	public static void main(String[] args) {
		QuickSort qs = new QuickSort();
		MyRandomUtils.setSeed(System.currentTimeMillis());
		int[] ary = MyRandomUtils.generateIntAry(size, 1, 4 * size);
		//System.out.println("原数组:\t" + Arrays.toString(ary));
		
		System.out.println("开始时间:" + MyDateUtils.getNowStr(MyDateUtils.yyyyMMdd_HHmmssSSS_));
		qs.sort(ary, 0, ary.length - 1);
		System.out.println("结束时间:" + MyDateUtils.getNowStr(MyDateUtils.yyyyMMdd_HHmmssSSS_));
		//System.out.println("排序后:\t" + Arrays.toString(ary));
		System.out.println("交换次数:" + qs.sumSwapTimes);
	}
	
	/**
	 * 固定使用分段的begin位置的元素作为pivot
	 */
	public void sort(int[] ary, int begin, int end) {
		if(begin >= end) {
			return;
		}
		
		int pivotIndex = getPivotIndex(ary, begin, end);
		
		sort(ary, begin, pivotIndex - 1);
		sort(ary, pivotIndex + 1, end);
	}
	
	/**
	 * 对分段进行大小分区,并把分区元素的下标返回
	 */
	private int getPivotIndex(int[] ary, int begin, int end) {
		if(end - begin == 1) {
			//优化两两比较,如果只有两个值比较,直接比较并交换
			if(ary[begin] > ary[end]) {
				MyArrayUtils.swap(ary, begin, end);
				sumSwapTimes++;
			}
			return begin;
		}
		
		int left = begin;
		int right = end;

		int pivot = ary[begin];
		
		while(left < right) {
			//从右向左找小于等于pivot的元素
			while(left < right && ary[right] > pivot) {
				right--;
			}
			//从左向右找大于pivot的元素
			while(left < right && ary[left] <= pivot) {
				left++;
			}
			
			//如果没有过界,则交换
			if(left < right) {
				MyArrayUtils.swap(ary, left, right);
				//交换后的元素直接跳过,为下一次比较作准备
				sumSwapTimes++;
			}
		}
		
		ary[begin] = ary[right];
		ary[right] = pivot;
		sumSwapTimes++;
		return right;
	}
	
	/**
	 * 自己实现的快速排序
	 * 固定使用分段的中间位置的元素作为pivot
	 */
//	private void sort(int[] ary, int begin, int end) {
//		int left = begin;
//		int right = end;
//		
//		if(end - begin == 1) {
//			//优化两两比较,如果只有两个值比较,直接比较并交换
//			if(ary[begin] > ary[end]) {
//				swap(ary, begin, end);
//				sumSwapTimes++;
//			}
//		} else {
//			int pivotIndex = (begin + end) / 2;
//			int pivot = ary[pivotIndex];
//			
//			while(left < right) {
//				//这里的while没有等号,那么和pivot相等的元素会视为大元素,向右边交换
//				while(left < right && ary[left] < pivot) {
//					left++;
//				}
//				//和pivot相等的元素视为大元素,留在右边
//				while(left < right && ary[right] >= pivot) {
//					right--;
//				}
//				
//				if(left < right) {
//					//交换
//					swap(ary, left, right);
//					//经过如下两句代码之后,right可能和left相等,即指向同一个位置
//					//那么该位置就一定在begin和end之间
//					right--;
//					left++;
//					
//					sumSwapTimes++;
//				}
//			}
//			
//			//退出循环只有一个条件,就是现在(left >= right)了
//			if(left > right) {
//				//如果left大于right,那么两个肯定也是相差1,那么都退1之后,两者还是相邻的
//				left--;
//				right++;
//			} else {
//				/**
//				 * 如果(left == right),此时如下三种情况只有两种存在可能:
//				 * 	1, left和right都指向边界begin
//				 * 	2, left和right都指向非边界(也就是begin和end之间的某个位置)
//				 * 	3, left和right不可能都指向边界end
//				 * 
//				 * 此时只能退一个指针,因为如果都退1,则两个下标中间会间隔一个元素,那这个元素就不会参与后续的排序,导致结果有误
//				 * 那具体退哪一个呢? 就看left和right指向的位置在哪里,以及该位置上的元素和pivot之间的关系
//				 */
//				if(left == begin) {
//					//都指向begin
//					if(ary[left] > pivot) {
//						//如果begin位置的元素大于pivot,则将其和 pivotIndex下标上的pivot交换后,begin位置也就变成有序的了
//						swap(ary, begin, pivotIndex);
//						sumSwapTimes++;
//					}
//					right++;
//				} else {
//					//都指向非边界(也就是begin和end之间的某个位置)
//					if(ary[left] >= pivot) {
//						//该元素大于等于pivot,则归为大元素,则left后退
//						left--;
//					} else {
//						//该元素小于pivot,则归位小元素,则right后退
//						right++;
//					}
//				}
//			}
//		}
//		
//		if(begin < left) {
//			sort(ary, begin, left);
//		}
//		if(right < end) {
//			sort(ary, right, end);
//		}
//	}

}