package com.jstfs.practice.datastructure.stack;

/**
 * @createBy	落叶
 * @createTime 	2018-10-29 上午12:58:53
 */
public interface IStack {
	public void push(Object data);
	public Object pop();
	public String toString();
	public int getSize();
}
