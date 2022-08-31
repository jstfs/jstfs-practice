package com.jstfs.practice.algorithm.sort.bucket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jstfs.common.utils.MyRandomUtils;
import com.jstfs.practice.algorithm.sort.quick.QuickSort;

/**
 * 桶排序:
 * 		对数据要求比较高:
 * 			1, 很容易找到最小值和最大值
 * 			2, 最小值和最大值之间的跨度不大
 * 			3, 数据分布的越均匀越好
 * 		
 * 		具体操作:
 * 			按最小值和最大值的跨度把数据分为若干个区间,每一个区间我们称为"桶"
 * 			桶和桶之间是有顺序的,数据经过遍历后,会被依次归属到所属区间对应的桶内.
 * 			如果数据分布均匀,那么对于每个桶中的数据再使用快排,然后依次合并
 * 			如果数据分布不均匀,那么对于某个数据量比较大的桶来说,可以继续分成若干个更小的桶之后再使用快排.
 * 
 * 	1, 非原地排序算法(空间复杂度O(N + m))
 * 	2, 稳定的排序算法
 * 	3, 时间复杂度:
 * 		最好时间复杂度: O(N)
 * 		平均时间复杂度: O(N)
 * 		最坏时间复杂度: O(N*log₂N)
 * 
 * 		假设: 
 * 			分成了m个桶,那么每个桶中平均的个数为 N/m,每个桶中的数据进行快排,再加上全部遍历一边数据归属到各个桶中的时间复杂度 O(N)
 * 		那么:
 * 			O(N + m*(N/m)*log₂(N/m)) = O(N + N*log₂N - N*log₂m)
 * 
 * 		当 m 接近 N 时,时间复杂度就接近 O(N)
 * 		当所有数据都分布在一个桶中的时候,即 m=1,那么就退化成 O(N + N*log₂N)
 * 		忽略常数 N,则相当于快排的 O(N*log₂N),这就是最坏时间复杂度.
 * 
 * @createBy 	落叶
 * @createTime 	2019-1-6 下午1:25:27
 */
public class BucketSort {
	private static QuickSort qs = new QuickSort();
	private static int size = 200;	//数据量
	private static int min = 31;	//最小值
	private static int max = 130;	//最大值
	private static int countPerBucket = 20;	//每个桶中最大数据跨度,可根据总数据的跨度来调整
	
	public static void main(String[] args) {
		BucketSort bs = new BucketSort();
		int[] ary = MyRandomUtils.generateIntAry(size, min, max);
		System.out.println(Arrays.toString(ary));
		bs.sort(ary, min, max);
		System.out.println(Arrays.toString(ary));
	}
	
	public void sort(int[] ary, int min, int max) {
		int diff = max - min + 1;
		if(diff <= 1) {
			throw new RuntimeException("max 必须大于 min");
		}
		
		int m = 0; 	//桶的个数
		if(diff % countPerBucket == 0) {
			m = diff / countPerBucket;
		} else {
			m = diff / countPerBucket + 1;
		}
		
		Map<Integer, List<Integer>> buckets = initBucket(m);
		//将数据放入桶中
		for(int i = 0; i < ary.length; i++) {
			int remainder = (ary[i] - min + 1) % countPerBucket;
			int quotient = (ary[i] - min + 1) / countPerBucket;
			if(remainder == 0) {
				buckets.get(quotient).add(ary[i]);
			} else {
				buckets.get(quotient + 1).add(ary[i]);
			}
		}
		
		//对每个桶进行快排
		for(Map.Entry<Integer, List<Integer>> entry : buckets.entrySet()) {
			List<Integer> bucket = entry.getValue();
			quickSortToBucket(bucket);
		}
		
		//输出到原数组
		for(int mi = 1, i = 0; mi <= m; mi++) {
			List<Integer> bucket = buckets.get(mi);
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
	
	private void quickSortToBucket(List<Integer> bucket) {
		Integer[] ary = bucket.toArray(new Integer[bucket.size()]);
		qs.sort(ary, true);
		//bucket = Arrays.asList(ary);	//不能用这种方式,Arrays.asList()方法是重新创建了一个 List,和入参 bucket 指向的 List 的没有关系
		for(int i = 0; i < ary.length; i++) {
			bucket.set(i, ary[i]);
		}
	}
}
