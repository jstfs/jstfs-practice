package com.jstfs.practice.algorithm.search.binary;

/**
 * 使用二分查找法计算数据开根号的近似值,精确到小数点后6位
 *
 * @createBy 	落叶
 * @createTime 	2019-1-19 下午2:22:00
 */
public class GetRadicalNumber {
	private static int number = 2;		//目标数据,求该数据的pow次方根
	private static int precision = 6;	//要求的精度
	private static int pow = 2;
	
	public static void main(String[] args) {
		System.out.println("Math.sqrt(" + number + ")=" + Math.sqrt(number));
		int integerPart = getItegerPart(0, number);
		if(Math.pow(integerPart, pow) == number) {
			System.out.println(number + "的" + pow + "次方根等于" + integerPart);
			return;
		}
		
		double baseValue = integerPart;
		int i = 1;
		while(true) {
			if(i > precision) {
				System.out.println(number + "的" + pow + "次方根约等于" + baseValue);
				break;
			}
			baseValue = getFloatPart(baseValue, 0, 9, Math.pow(10, -i));
			if(baseValue == number) {
				System.out.println(number + "的" + pow + "次方根等于" + baseValue);
				break;
			}
			i++;
		}
	}
	
	/**
	 * 得到整数部分
	 */
	private static int getItegerPart(int min, int max) {
		if(min >= max) {
			return min;
		}
		
		int middle = min + ((max - min) >> 1);
		if(Math.pow(middle, pow) == number) {
			return middle;
		} else if(Math.pow(middle, pow) > number) {
			return getItegerPart(min, middle);
		} else {
			if(Math.pow(middle + 1, pow) > number) {
				return middle;
			}
			return getItegerPart(middle + 1, max);
		}
	}
	
	/**
	 * 追加小数部分
	 * 
	 * @param baseValue		当前计算前的基数
	 * @param x				小数位的幂
	 */
	private static double getFloatPart(double baseValue, int min, int max, double x) {
		int middle = min + ((max - min) >> 1);
		double temp = baseValue + middle * x;
		if(Math.pow(temp, pow) == number) {
			return temp;
		} else if(Math.pow(temp, pow) > number) {
			return getFloatPart(baseValue, min, middle, x);
		} else {
			if(Math.pow(temp + x, pow) > number) {
				return temp;
			} else {
				return getFloatPart(baseValue, middle + 1, max, x);
			}
		}
	}
}
