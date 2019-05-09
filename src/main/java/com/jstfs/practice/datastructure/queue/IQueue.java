package com.jstfs.practice.datastructure.queue;

/**
 * @createBy jstfs
 * @createTime 2018-11-14 下午1:52:31
 */
public interface IQueue {
	public void enqueuue(Object data);
	public Object dequeue();
	public String toString();
	public int getSize();
}
