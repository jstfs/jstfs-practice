package com.jstfs.practice.datastructure.stack;

import com.jstfs.practice.datastructure.chain.Node;
import com.jstfs.practice.datastructure.chain.SingleChain;

/**
 * 栈的链表实现
 *
 * @createBy	落叶
 * @createTime 	2018-10-29 上午12:59:27
 */
public class StackChain implements IStack {
	private SingleChain datas = new SingleChain();
	private final int MAX_SIZE = 500;	//最大容量
	
	public StackChain() {}
	
	public StackChain(int initSize) {
		if(initSize >= MAX_SIZE) {
			throw new RuntimeException("容量不能大于500");
		}
		
		datas = new SingleChain(initSize);
	}
	
	@Override
	public void push(Object data) {
		if(datas.getSize() >= MAX_SIZE) {
			throw new RuntimeException("容量不能大于500");
		}
		
		datas.add(new Node(data));
	}
	
	/**
	 * 弹出
	 */
	@Override
	public Object pop() {
		return datas.remove().getData();
	}
	
	/**
	 * 获得栈顶元素
	 */
	public Object get() {
		return datas.get().getData();
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
			if(index >= datas.getSize()) {
				break;
			}
			sb.append(datas.get(index)).append(" -> ");
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
		return datas.getSize();
	}
}
