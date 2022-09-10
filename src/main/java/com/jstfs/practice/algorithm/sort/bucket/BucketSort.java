package com.jstfs.practice.algorithm.sort.bucket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jstfs.common.utils.MyCollecitonUtils;
import com.jstfs.common.utils.MyRandomUtils;
import com.jstfs.practice.algorithm.sort.quick.QuickSort;

/**
 * 桶排序:
 * 		对数据要求比较高
 * 			1, 很容易找到最小值和最大值
 * 			2, 最小值和最大值之间的跨度不大
 * 			3, 数据分布的越均匀越好
 * 		具体操作:
 * 			按最小值和最大值的跨度把数据分为若干个区间,每一个区间称为一个"桶"
 * 			数据经过遍历后,会被依次归属到所属区间对应的桶内,桶和桶之间是有顺序的
 * 			如果数据分布均匀,那么对于每个桶中的数据再使用其他排序算法或者继续使用桶排序,然后依次合并
 * 			如果数据分布不均匀,那么对于数据量比较大的桶来说,可以继续使用桶排序
 * 
 * 特点:
 * 		1, 非原地排序算法(空间复杂度O(N + m))
 * 		2, 可以是稳定的排序算法(取决于对桶内元素采用的排序算法.比如采用快排,那么就是不稳定的)
 * 		3, 时间复杂度:
 * 			最好时间复杂度: O(N)
 * 			平均时间复杂度: O(N)
 * 			最坏时间复杂度: O(N*log₂N)
 * 
 * 桶排序的时间复杂度推导:
 * 		假设: 
 * 			分成了m个桶
 * 		那么:
 * 			每个桶中平均的个数为 N/m
 * 			每个桶中的数据进行快速排序( O(N*log₂N),这里的N就是N/m )
 * 			再加上最后遍历一边数据(O(N))
 * 		得到:
 * 			T(N) = O(m * (N/m) * log₂(N/m) + N) 
 * 				 = O(N * log₂N - N * log₂m + N)
 * 
 * 		当m = N时,即每个数据都使用独立的桶,则时间复杂度就接近 O(N)
 * 		当m = 1时,所有数据都分布在一个桶中,那么就退化成 O(N*log₂N + N),即:O(N*log₂N)
 * 
 * @createBy 	落叶
 * @createTime 	2019-1-6 下午1:25:27
 */
public class BucketSort {
	private static QuickSort qs = new QuickSort();	//桶内使用的排序算法
	private static int size = 40;
	private static int min = size;		//最小值
	private static int max = 4 * size;	//最大值
	private static int spanPerBucket = 20;	//每个桶中最大数据跨度,可根据总数据的跨度来调整
	
	public static void main(String[] args) {
		BucketSort bs = new BucketSort();
		int[] ary = MyRandomUtils.generateIntAry(size, min, max);
		System.out.println("原数组:\t" + Arrays.toString(ary));
		bs.sort(ary);
		System.out.println("排序后:\t" + Arrays.toString(ary));
	}
	
	public void sort(int[] ary) {
		int diff = max - min + 1;
		if(diff <= 1) {
			throw new RuntimeException("max 必须大于 min");
		}
		
		int m = 0; 	//桶的个数
		if(diff % spanPerBucket == 0) {
			m = diff / spanPerBucket;
		} else {
			m = diff / spanPerBucket + 1;
		}
		
		//此处是用List做桶,也可以用二维数组做桶
		Map<Integer, List<Integer>> buckets = initBucket(m);	
		
		//将数据分别放入对应的桶中
		for(int i = 0; i < ary.length; i++) {
			int remainder = (ary[i] - min + 1) % spanPerBucket;		//余数
			int quotient = (ary[i] - min + 1) / spanPerBucket;		//商
			if(remainder == 0) {
				buckets.get(quotient).add(ary[i]);
			} else {
				buckets.get(quotient + 1).add(ary[i]);
			}
		}
		
		//对每个桶进行排序(此处选择的是快速排序)
		for(Map.Entry<Integer, List<Integer>> entry : buckets.entrySet()) {
			List<Integer> bucket = entry.getValue();
			quickSortToBucket(bucket);
		}
		
		//输出到原数组
		for(int bi = 1, i = 0; bi <= m; bi++) {
			List<Integer> bucket = buckets.get(bi);
			for(Integer data : bucket) {
				if(data != null) {
					ary[i++] = data;
				} else {
					break;
				}
			}
		}
	}
	
	/**
	 * 桶的编号从1开始
	 */
	private Map<Integer, List<Integer>> initBucket(int m) {
		Map<Integer, List<Integer>> buckets = new HashMap<Integer, List<Integer>>();
		while(m > 0) {
			buckets.put(m, new ArrayList<Integer>());
			m--;
		}
		return buckets;
	}
	
	/**
	 * 对桶内元素使用快速排序
	 */
	private void quickSortToBucket(List<Integer> bucket) {
		int[] ary = MyCollecitonUtils.intListToAry(bucket);
		qs.sort(ary, 0, ary.length - 1);
		for(int i = 0; i < ary.length; i++) {
			bucket.set(i, ary[i]);
		}
	}
}
