package com.jstfs.practice.datastructure.chain.linkedlist.backwards;

import com.jstfs.practice.datastructure.chain.Node;
import com.jstfs.practice.datastructure.chain.SingleChain;

/**
 * 查找单链表的倒数第 k 个节点
 *
 * 使用两个指针,指针 A 和指针 B
 * A 先向前走 k-1 步,然后两个指针同时向前一步一步走
 * 当 A 的下一个节点为 null 时,此时 B 就是倒数第 k 个节点
 * 
 * @createBy 	落叶
 * @createTime 	2018-10-26 上午12:51:42
 */
public class Backwards {
	private static SingleChain chain = new SingleChain(7);	//初始化链表
	
	public static void main(String[] args) {
		System.out.println(chain);
		
		check(3);
	}
	
	public static void check(int k) {
		if(k > chain.getSize()) {
			throw new RuntimeException("k[" + k + "]不能大于链表大小[" + chain.getSize() + "]");
		}
		
		Node pointA = null;
		Node pointB = null;
		
		pointA = chain.go(pointA, k-1);	//指针A先走 k-1 步
		
		while(true) {
			pointA = chain.go(pointA, 1);
			if(pointA == null) {
				System.out.println("倒数第" + k + "个节点是:" + pointB);
				break;
			}
			pointB = chain.go(pointB, 1);
		}
	}
}
