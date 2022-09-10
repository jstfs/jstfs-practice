package com.jstfs.practice.algorithm.sort.radix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jstfs.common.utils.MyCollectionUtils;
import com.jstfs.common.utils.MyRandomUtils;
import com.jstfs.practice.algorithm.sort.counting.CountingSort;

/**
 * 基数排序:
 * 		也会使用到桶排序,对数据要求如下:
 * 			1, 数据必须可以分割出独立的"位"来进行每一位之间的比较(有20万个手机号,由于手机号都是11位,而每一位的范围就是0-9这10个数字)
 * 			2, 每一位的数据范围不能太大,这样就可以使用到计数排序
 * 		
 * 		具体操作:
 * 			从后向前对每一位进行分桶,然后再按桶的顺序写回原数组,一直到第一位
 * 			最后一次写回后,原数组就会变为有序的
 * 
 * 特点:
 * 		1, 非原地排序算法 (空间复杂度O(N + m))
 * 		2, 稳定的排序算法
 * 		3, 时间复杂度:
 * 			最好时间复杂度: O(N)
 * 			平均时间复杂度: O(N)
 * 			最坏时间复杂度: O(N)
 * 
 * @createBy 	落叶
 * @createTime 	2019-1-18 下午2:11:10
 */
public class RadixSort {
	private static int size = 20;			//数据量
	private static int dataLength = 11;		//数据长度
	private static int bitMin = 0;			//每一位上的最小值
	private static int bitMax = 9;			//每一位上的最大值
	CountingSort cs = new CountingSort();
	
	public static void main(String[] args) {
		RadixSort rs = new RadixSort();
		MyRandomUtils.setSeed(System.currentTimeMillis());
		String[] phoneNos = MyRandomUtils.generatePhoneNo(size, dataLength);
		System.out.println("原数组:\t" + Arrays.toString(phoneNos));
		rs.sort(phoneNos, dataLength);
		System.out.println("排序后:\t" + Arrays.toString(phoneNos));
	}
	
	public void sort(String[] phoneNos, int dataLength) {
		for(int bit = (dataLength - 1); bit >= 0; bit--) {
			sortBit(phoneNos, bitMin, bitMax, bit);
		}
	}
	
	/**
	 * 专门对手机号码某个位进行计数排序的方法
	 */
	private void sortBit(String[] phoneNos, int min, int max, int bitNo) {
		//存放数据的桶,此处是用List做桶,也可以用二维数组做桶
		Map<Integer, List<String>> bucketMap = new HashMap<Integer, List<String>>();
		
		//存放bucketMap的key,由于已知key最多就是0-9这10个数字,直接初始化
		int[] keyAry = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		
		//根据该位上的数字将数据放入对应的桶中
		for(String phoneNo : phoneNos) {
			int c = Integer.parseInt(phoneNo.charAt(bitNo) + "");
			if(bucketMap.containsKey(c)) {
				bucketMap.get(c).add(phoneNo);
			} else {
				List<String> bucket = new ArrayList<String>();
				bucket.add(phoneNo);
				bucketMap.put(c, bucket);
			}
		}
		
		//遍历所有桶,将数据按顺序放回原数组
		int index = 0;
		for(int key : keyAry) {
			List<String> bucket = bucketMap.get(key);
			if(MyCollectionUtils.isNotEmpty(bucket)) {
				for(String phoneNo : bucket) {
					phoneNos[index++] = phoneNo;
				}
			}
		}
	}
}
