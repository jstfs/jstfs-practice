package com.jstfs.practice.algorithm.knapsack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jstfs.common.utils.MyArrayUtils;
import com.jstfs.common.utils.MyRandomUtils;

/**
 *  背包问题
 *  
 *  	给定N各物品和一个容量为C的背包
 * 
 * @createBy	落叶
 * @createTime	2022年12月11日 下午3:53:37
 */
public class Knapsack {
	private static int size = 6;		//物品的个数
	
	public static void main(String[] args) {
		Knapsack knapsack = new Knapsack();
		int[] weights = MyRandomUtils.generateIntAry(size, 1, 2 * size);
		int targetWeight = 14;
		System.out.println("原数组:\t" + Arrays.toString(weights));
		System.out.println("目标重量:\t" + targetWeight);
		
		List<Integer> result = new ArrayList<>();
		for(int i = 0; i < weights.length; i++) {
			if(knapsack.select(weights, targetWeight, i, result)) {
				System.out.println("找到一组结果:"  + MyArrayUtils.toString(result));
				break;
			} else {
				System.out.println(MyArrayUtils.toString(result) + "...还未找到找到一组结果");
				result.clear();
			}
		}
	}
	
	/**
	 * 
	 */
	private boolean select(int[] weights, int targetWeight, int nowIndex, List<Integer> result) {
		if(nowIndex >= weights.length) {
			return false;
		}
		if(weights[nowIndex] > targetWeight) {
			return select(weights, targetWeight, ++nowIndex, result);
		} else if(weights[nowIndex] == targetWeight) {
			result.add(weights[nowIndex]);
			return true;
		} else {
			result.add(weights[nowIndex]);
			return select(weights, targetWeight - weights[nowIndex], ++nowIndex, result);
		}
	}
}
