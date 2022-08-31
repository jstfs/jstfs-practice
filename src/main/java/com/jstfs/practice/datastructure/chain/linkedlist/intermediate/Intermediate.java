package com.jstfs.practice.datastructure.chain.linkedlist.intermediate;

import com.jstfs.practice.datastructure.chain.Node;
import com.jstfs.practice.datastructure.chain.SingleChain;

/**
 * 查找单链表的中间节点
 *
 * 同样利用 low 和 fast 两个指针, fast 步长为2, slow 步长为1
 * 当 fast 下一个节点为 null 时,则该链表的长度为偶数, slow 节点和 slow 的下一个节点都可以作为中间节点
 * 当 fast 的下下一个节点为 null 时,则该链表的长度为奇数, slow 的下一个节点就是该链表的中间节点
 * 
 * @createBy 	落叶
 * @createTime 	2018-10-23 下午7:01:12
 */
public class Intermediate {
	private static SingleChain chain = new SingleChain(11);	//初始化链表
	
	public static void main(String[] args) {
		System.out.println(chain);
		
		check();
	}
	
	public static void check() {
		Node slow = null;
		Node fast = null;
		
		while(true) {
			fast = chain.go(fast, 1);	//为了区分链表长度为奇数和偶数的情况,此处将fast一步一步走
			if(fast == null) {
				System.out.println("链表的中间节点是:" + slow + " 或者 " + slow.getNext());
				return;
			}
			fast = chain.go(fast, 1);
			if(fast == null) {
				System.out.println("链表的中间节点是:" + slow.getNext());
				return;
			}
			slow = chain.go(slow, 1);
		}
	}
}
