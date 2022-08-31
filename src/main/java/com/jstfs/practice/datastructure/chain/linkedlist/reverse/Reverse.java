package com.jstfs.practice.datastructure.chain.linkedlist.reverse;

import com.jstfs.practice.datastructure.chain.Node;
import com.jstfs.practice.datastructure.chain.SingleChain;

/**
 * 单链表反转
 *
 * @createBy 	落叶
 * @createTime 	2018-10-26 上午12:51:23
 */
public class Reverse {
	private static SingleChain chain1 = new SingleChain(10);	//初始化链表
	private static SingleChain chain2 = new SingleChain();
	
	public static void main(String[] args) {
		System.out.println(chain1);
		
		Node point;
		while(true) {
			point = chain1.remove(0);
			if(point == null) {
				break;
			}
			
			chain2.add(point, 0);
		}
		
		System.out.println(chain2);
	}
}
