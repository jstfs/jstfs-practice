package com.jstfs.practice.algorithm.sort.counting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.jstfs.common.utils.MyRandomUtils;

/**
 * 计数排序:
 * 		是桶排序的一种特殊情况,所以对数据要求就会更高.
 * 			1, 最小值和最大值之间的跨度相对于数据量来说相当小.
 * 			2, 对于数据分布是否均匀没有要求
 *		
 *		具体操作:
 *			1, 由于数据跨度很小,所以每一个桶只保存一"种"数据,这样桶的数量也不会太多,所以遍历数据后,不同的数据会分布在不通的桶中
 *			2, 由于桶内的数据都是相同的,所以不需要排序,按顺序依次输出每一个桶,排序就完成了.
 *
 * 		比如: 
 * 			某省高考有几十万考生的成绩,但最小值0分和最大值900分之间最多就只有901(相当于桶的个数 m)种有限个数的情况.
 * 		
 * 	1, 非原地排序算法(空间复杂度O(N + m))
 * 	2, 稳定的排序算法
 * 	3, 时间复杂度:
 * 		最好时间复杂度: O(N)
 * 		平均时间复杂度: O(N)
 * 		最坏时间复杂度: O(N)
 * 
 * @createBy 	落叶
 * @createTime 	2019-1-18 下午2:09:34
 */
public class CountingSort {
	private static int size = 2000;	//数据量
	private static int min = 1;		//最小值
	private static int max = 50;	//最大值
	
	public static void main(String[] args) {
		CountingSort cs = new CountingSort();
		int[] ary = MyRandomUtils.generateIntAry(size, min, max);
		System.out.println(Arrays.toString(ary));
		cs.sort(ary, min, max);
		System.out.println(Arrays.toString(ary));
	}
	
	public void sort(int[] ary, int min, int max) {
		Map<Integer, List<Integer>> bucketMap = new HashMap<Integer, List<Integer>>();
		for(int i : ary) {
			if(bucketMap.containsKey(i)) {
				bucketMap.get(i).add(i);
			} else {
				List<Integer> bucket = new ArrayList<Integer>();
				bucket.add(i);
				bucketMap.put(i, bucket);
			}
		}
		
		List<Integer> keyList = Arrays.asList(bucketMap.keySet().toArray(new Integer[]{}));
		Collections.sort(keyList);
		
		int index = 0;
		for(Integer key : keyList) {
			List<Integer> bucket = bucketMap.get(key);
			if(CollectionUtils.isNotEmpty(bucket)) {
				for(Integer i : bucket) {
					ary[index++] = i;
				}
			}
		}
	}
	
	/**
	 * 专门定制的对手机号码某个位进行计数排序的方法
	 */
	public void sort(String[] phoneNos, int min, int max, int bit) {
		Map<Integer, List<String>> bucketMap = new HashMap<Integer, List<String>>();
		for(String phoneNo : phoneNos) {
			String c = phoneNo.charAt(bit) + "";
			if(bucketMap.containsKey(Integer.parseInt(c))) {
				bucketMap.get(Integer.parseInt(c)).add(phoneNo);
			} else {
				List<String> bucket = new ArrayList<String>();
				bucket.add(phoneNo);
				bucketMap.put(Integer.parseInt(c), bucket);
			}
		}
		
		List<Integer> keyList = Arrays.asList(bucketMap.keySet().toArray(new Integer[]{}));
		Collections.sort(keyList);
		
		int index = 0;
		for(Integer key : keyList) {
			List<String> bucket = bucketMap.get(key);
			if(CollectionUtils.isNotEmpty(bucket)) {
				for(String phoneNo : bucket) {
					phoneNos[index++] = phoneNo;
				}
			}
		}
	}
}
