package com.jstfs.practice.algorithm.patternmatching.bruteforce;

import java.util.Arrays;
import java.util.List;

import com.jstfs.common.utils.MyCollectionUtils;
import com.jstfs.common.utils.MyStringUtils;

/**
 * 暴力模式匹配算法
 * 
 * @createBy	落叶
 * @createTime	2022年11月7日 下午9:56:29
 */
public class BruteForceMatching {

	public static void main(String[] args) {
		String source = "121121312343849121387238761293";
		String dest = "121";
		
		System.out.println(match(source, dest));
		System.out.println(match(source, dest, 4));
		System.out.println(Arrays.toString(matchs(source, dest)));
	}
	
	/**
	 * 从source的起始位置开始匹配(只匹配一次,找到就返回)
	 */
	public static int match(String source, String dest) {
		return match(source, dest, 0);
	}
	
	/**
	 * 从source的指定位置开始匹配(只匹配一次,找到就返回)
	 */
	public static int match(String source, String dest, int fromIndex) {
		if(MyStringUtils.isEmpty(source)) {
			throw new RuntimeException("source must not empty");
		}
		
		if(MyStringUtils.isEmpty(dest)) {
			throw new RuntimeException("dest must not empty");
		}
		
		if(source.length() < dest.length()) {
			throw new RuntimeException("source must longer than dest");
		}
		
		if(fromIndex < 0) {
			throw new RuntimeException("fromIndex must more than 0");
		}
		
		char[] sChars = source.toCharArray();
		char[] dChars = dest.toCharArray();
		
		int sLen = source.length();
		int dLen = dest.length();
		
		int si = fromIndex;
		int di = 0;
		while(si < sLen && di < dLen) {
			if(sChars[si] == dChars[di]) {
				si++;
				di++;
			} else {
				si = si - (di - 1);
				di = 0;
			}
		}
		
		if(di == dest.length()) {
			//找到了
			return si - di;
		}
		
		return -1;
	}
	
	/**
	 * 从source起始位置开始匹配所有的dest
	 */
	public static int[] matchs(String source, String dest) {
		List<Integer> indexs = MyCollectionUtils.newList(Integer.class);
		int index = -1;
		int fromIndex = 0;
		
		while(true) {
			index = match(source, dest, fromIndex);
			if(index != -1) {
				fromIndex = index + dest.length();
				indexs.add(index);
			} else {
				break;
			}
		}
		
		return MyCollectionUtils.toArray(indexs);
	}
}
