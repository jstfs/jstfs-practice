package com.jstfs.practice.algorithm.rpn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.jstfs.common.utils.MyStringUtils;

/**
 * 逆波兰表达式:
 * 	将带括号的四则运算表达式转化为另一种表达式并计算其结果的通用算法
 * 	转换后的表达式称作逆波兰表达式,或者后缀表达式
 * 
 * 	转后缀表达式:
 * 		1, 如果遇到运算数,直接输出
 * 		2, 如果遇到四则运算符号则将符号入栈(符号栈)
 * 			2.1, 栈为空或者栈顶是左括号,则当前符号直接入栈
 * 			2.2, 当前符号的优先级不高于栈顶的符号时,则栈顶符号出栈并输出.然后继续和栈顶符号比较
 * 			2.3, 当前符号的优先级高于栈顶的符号时,则当前符号直接入栈
 * 		3, 如果遇到左括号直接入栈
 * 		4, 如果遇到右括号,则栈顶符号依次出栈并输出,直到碰到左括号(出栈但不输出)
 * 
 * 	计算后缀表达式的结果:
 * 		1, 从左到右,遇到运算数直接入栈(数据栈)
 * 		2, 遇到运算符则从栈顶取出两个运算数进行计算,并将计算结果压栈
 * 			2.1, 如果是减法运算,则后取出的是被减数
 * 			2.2, 如果是除法运算,则后取出的是被除数
 * 		3, 最终的得数就是原四则运算的结果
 * 
 * @createBy 	落叶
 * @createTime 	2022-08-31 下午18:56:50
 */
public class RPNExpression {
	private List<Character> validOperatorList = new ArrayList<Character>() {
		private static final long serialVersionUID = -8395662796843390937L;
		{
			add('+');
			add('-');
			add('*');
			add('/');
		}
	};
	
	private Map<Character, Integer> priorityOperatorMap = new HashMap<Character, Integer>() {
		private static final long serialVersionUID = -8395662796843390937L;
		{
			//value的数字越大,优先级越大
			put('+', 1);
			put('-', 1);
			put('*', 2);
			put('/', 2);
		}
	};
	
	public static void main(String[] args) {
		String originExp = "122+((21+3)*4)/17";
		
		RPNExpression rpn = new RPNExpression();
		System.out.println("原表达式:\t" + originExp);
		String rpnExp = rpn.toRPNExp(originExp);
		System.out.println("RPN表达式:" + rpnExp);
		double result = rpn.caculateRPN(rpnExp);
		System.out.println("计算结果:\t" + originExp + " = " + result);
	}
	
	/**
	 * 转RPN表达式
	 * 
	 * @param exp	正常的中缀表达式
	 */
	public String toRPNExp(String exp) {
		StringBuilder rpnExp = new StringBuilder();
		if(MyStringUtils.isEmpty(exp)) {
			throw new RuntimeException("exp不能为空");
		}
		
		Stack<Character> operatorStack = new Stack<Character>();	//符号栈
		
		char[] charAry = exp.toCharArray();
		String tempOperNum = ""; 
		for (int i = 0; i < charAry.length; i++) {
			if(charAry[i] == ' ') {
				continue;
			} else if(Character.isDigit(charAry[i])) {
				//数字,先存起来,后面可能还有数字
				tempOperNum += charAry[i];
			} else if(charAry[i] == '(') {
				//左括号,直接入栈
				operatorStack.push(charAry[i]);
			} else if(charAry[i] == ')') {
				//右括号
				if(MyStringUtils.isNoneEmpty(tempOperNum)) {
					rpnExp.append(" ").append(tempOperNum);	//先将tempOperNum输出
					tempOperNum = "";			//重置为空串
				}
				
				//栈顶运算符出栈并输出,直到左括号出栈(不输出)
				Character topOper = operatorStack.pop();
				while(topOper != '(') {
					rpnExp.append(" ").append(topOper);
					topOper = operatorStack.pop();
				}
			} else if(validOperatorList.contains(charAry[i])){
				//运算符
				if(MyStringUtils.isNoneEmpty(tempOperNum)) {
					rpnExp.append(" ").append(tempOperNum);	//先将tempOperNum输出
					tempOperNum = "";			//重置为空串
				}
				
				Character topOper;
				do {
					//比较栈顶运算符的优先级
					topOper = compareOper(charAry[i], operatorStack, rpnExp);
				} while (topOper != null);
			} else {
				throw new RuntimeException("非法字符:" + charAry[i]);
			}
		}
		
		if(MyStringUtils.isNotEmpty(tempOperNum)) {
			rpnExp.append(" ").append(tempOperNum);
		}
		
		while(operatorStack.size() > 0) {
			rpnExp.append(" ").append(operatorStack.pop());
		}
		
		return rpnExp.toString();
	}
	
	private Character compareOper(char c, Stack<Character> operatorStack, StringBuilder rpnExp) {
		if(operatorStack.isEmpty() || operatorStack.peek() == '(') {
			//如果栈为空或者栈顶是左括号,则直接压栈
			operatorStack.push(c);
			return null;
		} else {
			if(priorityOperatorMap.get(c) > priorityOperatorMap.get(operatorStack.peek())) {
				//优先级高于栈顶符号,则该符号入栈
				operatorStack.push(c);
				return null;
			} else {
				//优先级不高于栈顶符号,则栈顶符号出栈
				Character topOper = operatorStack.pop();
				rpnExp.append(" ").append(topOper);
				return topOper;
			}
		}
	}
	
	private double caculateRPN(String rpnExp) {
		Stack<Double> dataStack = new Stack<Double>();	//数据栈
		
		String[] elmtAry = rpnExp.split(" ");
		Double data1 = null;
		Double data2 = null;
		for (int i = 0; i < elmtAry.length; i++) {
			if(elmtAry[i].matches("([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])")) {
				//是运算数,则入栈
				//这个正则不匹配整数的前导0(比如:0123.45)和小数的后导0(比如:123.450
				dataStack.push(Double.parseDouble(elmtAry[i]));
			} else if(elmtAry[i].equals("+")) {
				data1 = dataStack.pop();
				data2 = dataStack.pop();
				dataStack.push(data1 + data2);
			} else if(elmtAry[i].equals("-")) {
				data1 = dataStack.pop();
				data2 = dataStack.pop();	//被减数
				dataStack.push(data2 - data1);
			} else if(elmtAry[i].equals("*")) {
				data1 = dataStack.pop();
				data2 = dataStack.pop();
				dataStack.push(data1 * data2);
			} else if(elmtAry[i].equals("/")) {
				data1 = dataStack.pop();
				data2 = dataStack.pop();	//被除数
				dataStack.push(data2 / data1);
			}
		}
		return dataStack.pop();
	}
	
	
}
