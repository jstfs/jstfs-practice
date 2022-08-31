package com.jstfs.practice.datastructure.stack.calcexpression;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.jstfs.practice.datastructure.stack.StackArray;

/**
 * 计算四则运算表达式的值
 * 
 * 使用两个栈,一个保存运算符,一个保存操作数.
 * 遇到操作数则压栈
 * 遇到运算符:
 * 		如果当前运算符比运算符栈的栈顶运算符的优先级高,则压栈
 * 		如果当前运算符比运算符栈的栈顶运算符的优先级低或者相同,则取出栈顶运算符和操作数栈顶的连续两个操作数进行计算
 * 		将计算结果压入操作数栈,然后继续取栈顶运算符做比较...直到当前运算符比栈顶运算符的优先级高或者运算符栈变为空为止
 * 
 * 对于括号:
 * 		碰到左括号直接入运算符栈
 * 		碰到右括号,就取两个操作数和一个运算符计算出结果后,继续取运算符和一个操作数,直到取出左括号为止,就把最后一次的计算结果压入栈
 *
 * 缺少:
 * 		四则运算表达式的合法性校验
 *
 * @createBy	落叶
 * @createTime 	2018-10-29 上午01:01:30
 */
public class CalcExpression {
	public static Map<String, Integer> operators = new HashMap<String, Integer>();	//value为运算符的优先级,0表示优先级最高
	public static StackArray operatorStack = new StackArray();	//运算符栈
	public static StackArray numberStack = new StackArray();	//操作数栈
	public static String expression = "3+2-1+1";	//要计算的表达式
	
	
	public static void main(String[] args) {
		initOperators();
		System.out.println(expression + " = " + calc());
	}
	
	private static void initOperators() {
		operators.put(")", 0);
		operators.put("/", 1);
		operators.put("*", 2);
		operators.put("-", 3);
		operators.put("+", 4);
		operators.put("(", 5);
	}
	
	public static Double calc() {
		String numberStr = new String();
		Character currChar = null;
		for(int i = 0; i < expression.length(); i++) {
			currChar = expression.charAt(i);
			if(currChar == ' ') {
				continue;	//忽略表达式中的空格
			}
			
			if(Character.isDigit(currChar) || ".".equals(currChar.toString())) {
				numberStr += currChar;
			} else if(operators.containsKey(currChar.toString())){
				if(!numberStr.isEmpty()) {
					numberStack.push(Double.parseDouble(numberStr));	//操作数结束了,压栈
					numberStr = new String();	//重置操作数
				}
				handleOperator(currChar.toString());
			} else {
				throw new RuntimeException("表达式中存在不合法字符: " + currChar + "(" + i + ")");
			}
		}
		
		if(!(Character.isDigit(currChar) || operators.get(currChar.toString()) == 0)){
			throw new RuntimeException("表达式只能以数字或者右括号结尾: " + currChar);
		}
		
		if(StringUtils.isNotEmpty(numberStr)) {
			numberStack.push(Double.parseDouble(numberStr));	//表达式遍历完成之后,不要忘记把最后一个操作数压栈
		}
		
		return handleFinal();
	}
	
	
	private static void handleOperator(String newOperator) {
		if(operatorStack.get() == null || operators.get(newOperator) == 5) {	//空栈或者左括号直接入栈
			operatorStack.push(newOperator);
			return;
		}
		
		Double num1, num2, result;
		String topOperator;
		if(operators.get(newOperator) == 0) {	//碰到右括号,循环计算
			do {
				topOperator = operatorStack.pop().toString();
				if(operators.get(topOperator) == 5) {
					//碰到左括号,退出
					break;
				}
				
				num2 = Double.parseDouble(numberStack.pop().toString());
				num1 = Double.parseDouble(numberStack.pop().toString());
				result = calc_(topOperator, num1, num2);
				numberStack.push(result);
			} while(true);
		} else {
			do {
				if(operatorStack.getSize() == 0) {	//操作符栈空了
					operatorStack.push(newOperator);	//新运算符入栈
					break;
				}
				
				topOperator = operatorStack.get().toString();
				if(operators.get(newOperator) < operators.get(topOperator)) {
					operatorStack.push(newOperator);	//新运算符入栈
					break;
				}
				
				operatorStack.pop();	//将栈顶操作符弹出,其实就是 topOperator
				num2 = Double.parseDouble(numberStack.pop().toString());
				num1 = Double.parseDouble(numberStack.pop().toString());
				result = calc_(topOperator, num1, num2);
				numberStack.push(result);
			} while(true);
		}
	}
	
	private static Double handleFinal() {
		Double result = null;
		Double num1 = null;
		Double num2 = null;

		while(true) {
			if(operatorStack.get() != null) {
				String operator = operatorStack.pop().toString();
				num2 = Double.parseDouble(numberStack.pop().toString());
				num1 = Double.parseDouble(numberStack.pop().toString());
				result = calc_(operator, num1, num2);
				numberStack.push(result);
			} else {
				break;
			}
		}
		
		return result;
	}
	
	private static Double calc_(String operator, Double num1, Double num2) {
		Double result = null;
		switch(operator.toCharArray()[0]) {
			case '+' :
				result = num1 + num2;
				break;
			case '-' :
				result = num1 - num2;
				break;
			case '*' :
				result = num1 * num2;
				break;
			case '/' :
				if(num2 == 0) {
					throw new RuntimeException("除数不能为0");
				}
				result = num1 / num2;
				break;
		}
		
		return result;
	}
}