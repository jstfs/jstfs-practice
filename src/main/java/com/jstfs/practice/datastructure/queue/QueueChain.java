package com.jstfs.practice.datastructure.queue;

import com.jstfs.practice.datastructure.chain.Node;
import com.jstfs.practice.datastructure.chain.SingleChain;

/**
 * 队列的数组实现
 *
 * @createBy 	落叶
 * @createTime 	2018-11-27 上午12:21:11
 */
public class QueueChain implements IQueue {
	private SingleChain datas = new SingleChain();
	
	@Override
	public void enqueuue(Object data) {
		datas.add(new Node(data));
	}

	@Override
	public Object dequeue() {
		return datas.remove(0);
	}
	
	/**
	 * 打印栈
	 */
	@Override
	public String toString() {
		return datas.toString();
	}

	@Override
	public int getSize() {
		return datas.getSize();
	}

	public static void main(String[] args) {
		QueueChain qa = new QueueChain();
		qa.enqueuue("1");
		qa.enqueuue("2");
		qa.enqueuue("3");
		qa.enqueuue("4");
		qa.enqueuue("5");
		qa.enqueuue("6");
		qa.enqueuue("7");
		qa.enqueuue("8");
		qa.enqueuue("9");
		qa.enqueuue("10");
		System.out.println(qa);
		qa.dequeue();
		qa.dequeue();
		qa.dequeue();
		System.out.println(qa);
		qa.enqueuue("11");
		System.out.println(qa);
	}
}
