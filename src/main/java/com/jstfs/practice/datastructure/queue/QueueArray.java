package com.jstfs.practice.datastructure.queue;

/**
 * 队列的数组实现
 *
 * @createBy 	落叶
 * @createTime 	2018-11-26 上午12:31:06
 */
public class QueueArray implements IQueue {
	private Object[] datas = new Object[10];
	private int size = 0;	//实际大小
	private int headIndex = -1;
	private int tailIndex = -1;
	
	@Override
	public void enqueuue(Object data) {
		if(size == datas.length) {
			throw new RuntimeException("容量不能大于" + datas.length);
		}
		
		if(tailIndex == (datas.length - 1)) {
			move();
		}
		if(headIndex == -1) {
			headIndex++;
		}
		tailIndex++;
		datas[tailIndex] = data;
		size++;
	}

	@Override
	public Object dequeue() {
		if(size == 0) {
			return null;
		}
		headIndex++;
		size--;
		return datas[headIndex];
	}
	
	private void move() {
		int newTailIndex = tailIndex - headIndex;
		Object[] oldDatas = new Object[10];
		for(int i = headIndex, j = 0; i <= tailIndex; i++, j++) {
			oldDatas[j] = datas[i];
		}
		
		datas = oldDatas;
		headIndex = 0;
		tailIndex = newTailIndex;
	}
	
	/**
	 * 打印栈
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		if(size == 0) {
			sb.append("}");
			return sb.toString();
		}
		for(int i = headIndex; i <= tailIndex; i++) {
			sb.append(datas[i]).append(" -> ");
		}

		return sb.substring(0, sb.length() - 4) + "}";
	}

	@Override
	public int getSize() {
		return size;
	}
}
