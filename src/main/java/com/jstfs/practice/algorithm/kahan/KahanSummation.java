package com.jstfs.practice.algorithm.kahan;

/**
 * Kahan求和算法
 * 
 * 	由于浮点数进行加减的时候会出现大树吃小数的情况
 * 	尤其是大量的浮点数累加的时候,为了不使精度丢失
 * 	该算法将每次丢失的精度记录下来,在下一次累加时也加上去
 * 
 * @createBy 	落叶
 * @createTime 	2020年7月25日 上午12:13:57
 */
public class KahanSummation {
	public static void main(String[] args) {
		KahanSummation ks = new KahanSummation();
		ks.kahan();
		ks.testFloat();
	}
	
	private void kahan() {
		float kahan_sum = 0.0f;		//kahan求和算法计算的结果
		float normal_sum = 0.0f;	//正常累加计算的结果
		float delta = 0.0f;			//kahan求和算法中每次累加完所丢失的数据
		
		float added = 1.0f;			//每次需要累加的数
		
		for(int i = 1; i <= 16777218; i++) {
			/**
			 * 先将上一次计算后丢失的数据和本次需要累加的数据相加
			 * 由于delta为负数,所以是做相减
			 */
			float newAdded = added - delta;
			
			normal_sum += added;
			
			float temp = kahan_sum + newAdded;	
			delta = temp - kahan_sum - newAdded;	//本次累加后所丢失的数据
			kahan_sum = temp;
		}
		
		if(delta != 0.0f) {
			//累加完成之后,如果delat不是0,则需要累计到结果上
			kahan_sum = kahan_sum - delta;
		}
		
		System.out.printf("delta: 		%,.23f\n", delta);
		System.out.printf("kahan_sum: 	%,.23f\n", kahan_sum);
		System.out.printf("normal_sum: 	%,.23f\n", normal_sum);
	}
	
	/**
	 * 由于16777217.0f = 16777216.0f + 1 = 2^24 + 1
	 * 其二进制为: 1000000000000000000000001,头尾两个1中间共有23个0
	 * 
	 * 根据IEEE754规范的浮点数的表示方法,其如上整数部分的二进制需要向右移动24位
	 * 相当于小数点向左移动24位,而IEEE754规定的单精度浮点数的位数只有23位,
	 * 所以会导致末尾的那个1会被移出去,从而丢失了一部分数据,其最终的32位4字节的二进制表示如下:
	 * 0 10010111 00000000000000000000000
	 * 而这个二进制被读出转化为十进制时却是16777216.0f,所以刚方法最后打印的f1是16777216.0f
	 * 
	 * 所以结论就是:浮点数的表示方法不光有些小数不能精确的表示,就连有些整数也不能表示,比如:
	 * 		16777217.0f,
	 * 		16777219.0f,
	 * 		20000001.0f,
	 * 		20000003.0f,
	 * 		20000007.0f,等等...
	 * 		
	 * 即使是上面的Kahan求和算法也解决不了: (16777216.0f + 1.0f)结果准确的问题
	 */
	private void testFloat() {
		float f = 1.0f;
		float sum = 0.0f;
		for(int i = 1; i <= 16777217; i++) {
			sum += f;
		}
		
		System.out.printf("sum: %,.23f\n", sum);
		
		float f1 = 16777217.0f;
		System.out.printf("f1: %,.23f\n", f1);
	}
}
