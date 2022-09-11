package com.jstfs.practice.algorithm.sort.counting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jstfs.common.utils.MyCollectionUtils;
import com.jstfs.common.utils.MyRandomUtils;
import com.jstfs.practice.algorithm.sort.quick.QuickSort;

/**
 * 计数排序:
 * 		会用到桶的概念,对数据要求较高
 * 			1, 最小值和最大值之间的跨度相对于数据量来说很小(比如某个省某一年的高考成绩,考生个数(数据量)和成绩(桶)的个数之间)
 * 			2, 但对于数据分布是否均匀没有要求
 *		
 *		具体操作:
 *			1, 由于数据跨度很小,所以每一个桶只保存一"种"数据,所以遍历数据后,不同的数据会分布在不同的桶中
 *			2, 由于桶内的数据都是相同的,所以不需要排序,按顺序依次输出每一个桶,排序就完成了
 *
 * 特点:
 * 		1, 非原地排序算法(空间复杂度O(N + m))
 * 		2, 稳定的排序算法
 * 		3, 时间复杂度:
 * 			最好时间复杂度: O(N)
 * 			平均时间复杂度: O(N)
 * 			最坏时间复杂度: O(N)
 * 
 * @createBy 	落叶
 * @createTime 	2019-1-18 下午2:09:34
 */
public class CountingSort {
	private static QuickSort qs = new QuickSort();	//所有的key使用的排序算法
	private static int size = 2000;
	private static int min = 1;		//最小值
	private static int max = 20;	//最大值
	
	public static void main(String[] args) {
		CountingSort cs = new CountingSort();
		MyRandomUtils.setSeed(System.currentTimeMillis());
		int[] ary = MyRandomUtils.generateIntAry(size, min, max);
		System.out.println("原数组:\t" + Arrays.toString(ary));
		cs.sort(ary);
		System.out.println("排序后:\t" + Arrays.toString(ary));
	}
	
	public void sort(int[] ary) {
		//存放数据的桶,此处是用List做桶,也可以用二维数组做桶
		Map<Integer, List<Integer>> bucketMap = new HashMap<Integer, List<Integer>>();
		//存放bucketMap的key
		List<Integer> keyList = new ArrayList<Integer>();
		
		//将数据分别放入对应的桶中
		for(int i : ary) {
			if(bucketMap.containsKey(i)) {
				bucketMap.get(i).add(i);
			} else {
				List<Integer> bucket = new ArrayList<Integer>();
				bucket.add(i);
				bucketMap.put(i, bucket);
				
				keyList.add(i);
			}
		}
		
		//对key进行排序
		quickSort(keyList);
		
		int index = 0;
		//按照key的大小依次输出各个桶内的元素
		for(Integer key : keyList) {
			List<Integer> bucket = bucketMap.get(key);
			if(MyCollectionUtils.isNotEmpty(bucket)) {
				for(Integer ele : bucket) {
					ary[index++] = ele;
				}
			}
		}
	}
	
	/**
	 * 对所有的key使用快速排序
	 */
	private void quickSort(List<Integer> keyList) {
		int[] ary = MyCollectionUtils.toArray(keyList);
		qs.sort(ary, 0, ary.length - 1);
		for(int i = 0; i < ary.length; i++) {
			keyList.set(i, ary[i]);
		}
	}
}
