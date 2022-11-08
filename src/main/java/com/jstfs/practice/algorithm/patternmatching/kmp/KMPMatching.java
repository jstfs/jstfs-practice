package com.jstfs.practice.algorithm.patternmatching.kmp;

/**
 * KMP模式匹配算法
 * 
 * 		由 D.E.Knuth / J.H.Morris / V.R.Pratt 三位提出
 * 		其主要思想是利用匹配失败后的信息,使主串不回溯,并且尽量减少主串与模式串的匹配次数以达到快速匹配的目的.
 * 
 * @createBy	落叶
 * @createTime	2022年11月6日 下午11:06:27
 */
public class KMPMatching {
	
	public static void main(String[] args) {
		String source = "AAD ABAABCDABDAA DDAABDA";
		String dest = "ABCDABD";
		System.out.println(KMPMatching.search(source, dest));
		
	}
	
	public static int search(String source, String dest) {
		//部分匹配表
		int[] parthMatchAry = getPartMatchAry(dest);
		
		for(int i = 0, j = 0; i < source.length(); i++) {
			while(j > 0 && source.charAt(i) != dest.charAt(j)) {
				j = parthMatchAry[j - 1];
			}
			
			if(source.charAt(i) == dest.charAt(j)) {
				j++;
			}
			
			if(j == dest.length()) {
				//找到了
				return i - j + 1;
			}
		}
		
		return -1;
	}
	
	/**
	 * 得到dest的部分匹配表
	 * 
	 * 		要弄懂dest的部分匹配表,先搞清楚"前缀"和"后缀"两个概念:
	 * 			比如一个字符串为"KNUTH",那么它的:
	 * 			前缀有: "K", "KN", "KNU", "KNUT" 这4个,不包含自身
	 * 			后缀有: "NUTH", "UTH", "TH", "H" 这4个,也不包含自身
	 * 		再来解释"部分"的概念,"部分"的意思是所有包含dest串的起始位置的子串,包含自身
	 * 			比如dest="ABABABA",那么它就有如下几种部分:
	 * 			"A", "AB", "ABA", "ABAB", "ABABA", "ABABAB", "ABABABA"
	 * 		而部分匹配表,就是表示每一种"部分"的前缀和后缀中,包含相同值的最长的那个的长度
	 * 			比如上面dest的其中一个部分"ABABABA":
	 * 			前缀有: "A", "AB", "ABA", "ABAB", "ABABA", "ABABAB", 
	 * 			后缀有: "BABABA", "ABABA", "BABA", "ABA", "BA", "A"
	 * 			那么前缀和后缀共有的值有: "A", "ABA", "ABABA" 3个
	 * 			而最长的那个值"ABABA"的长度5就是"ABABABA"这个部分对应的值
	 * 
	 * 		而整个部分匹配表就是每一种部分对应的值的一张表
	 */
	public static int[] getPartMatchAry(String dest) {
		int[] parthMatchAry = new int[dest.length()];
		
		/**
		 * next[0] 表示只包含dest的第1个字符的这个部分的匹配表的值
		 * 因为只有1个字符,所以没有前缀和后缀,那么自然就没有相同的值,所以next[0]固定等于0
		 */
		parthMatchAry[0] = 0;
		
		/**
		 * 底下的算法既简洁又巧妙,但确实还未弄清楚为什么这么写...
		 */
		for(int i = 1, j = 0; i < dest.length(); i++) {
			while(j > 0 && dest.charAt(i) != dest.charAt(j)) {
				j = parthMatchAry[j - 1];
			}
			
			if(dest.charAt(i) == dest.charAt(j)) {
				j++;
			}
			parthMatchAry[i] = j;
		}
		return parthMatchAry;
	}
}
