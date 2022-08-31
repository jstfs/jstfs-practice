package com.jstfs.practice.datastructure.stack;

/**
 * 栈的数组实现
 *
 * @createBy	落叶
 * @createTime 	2018-10-29 上午12:59:11
 */
public class StackArray implements IStack {
	private Object[] datas = new Object[10];	//初始容量,10个
	private int size = 0;	//实际大小
	
	private char firstData = 'a';		//初始化时默认的起始数据
	private final int MAX_SIZE = 500;	//最大容量
	private final double DILATANCY_COEFFICIENT = 1.2;	//扩容系数
	
	public StackArray() {}
	
	public StackArray(int initSize) {
		if(initSize > MAX_SIZE) {
			throw new RuntimeException("容量不能大于500");
		}

		if(initSize > datas.length) {
			datas = new Object[initSize];
		}
		
		for(int i = 0; i < initSize; i++) {
			datas[i] = Character.toString(firstData);
			firstData += 1;
		}
		size = initSize;
	}
	
	@Override
	public void push(Object data) {
		if(datas.length >= MAX_SIZE) {
			throw new RuntimeException("容量不能大于500");
		}
		
		if(size >= datas.length) {
			dilatation();
		}
		datas[size++] = data;
	}
	
	/**
	 * 弹出
	 */
	@Override
	public Object pop() {
		if(size <= 0) {
			return null;
		}
		return datas[--size];
	}
	
	/**
	 * 获得栈顶元素
	 */
	public Object get() {
		if(size <= 0) {
			return null;
		}
		return datas[size - 1];
	}
	
	/**
	 * 扩容
	 * @param times	扩容系数
	 */
	private void dilatation() {
		int newSize = (int)(datas.length * DILATANCY_COEFFICIENT) + 1;
		Object[] bigger = null;
		if(newSize >= MAX_SIZE) {
			bigger = new Object[MAX_SIZE];
		} else {
			bigger = new Object[newSize];
		}
		
		for(int i = 0; i < datas.length; i++) {
			bigger[i] = datas[i];
		}
		datas = bigger;
	}
	
	/**
	 * 打印栈
	 */
	@Override
	public String toString() {
		String result;
		StringBuilder sb = new StringBuilder();
		int index = 0;
		
		sb.append("{");
		while(true) {
			if(index >= size) {
				break;
			}
			sb.append(datas[index]).append(" -> ");
			index++;
		}
		
		if(!sb.toString().equals("{")) {
			result = sb.substring(0, sb.length() - 4);
		} else {
			result = sb.toString();
		}
		
		return result + "}";
	}

	@Override
	public int getSize() {
		return size;
	}
}
