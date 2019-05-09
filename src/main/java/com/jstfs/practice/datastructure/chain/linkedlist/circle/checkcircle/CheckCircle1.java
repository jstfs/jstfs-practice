package com.jstfs.practice.datastructure.chain.linkedlist.circle.checkcircle;

import com.jstfs.practice.datastructure.chain.Node;
import com.jstfs.practice.datastructure.chain.SingleChain;

/**
 * 检查单链表中是否有环.如果有环,则计算出环的大小和环的起始节点
 * 
 * 方法一: 
 * 		使用两个指针,指针1和指针2,指针1每次往前走一步,指针2都从头开始走
 * 		如果指针1没有下一个节点了,则没有环
 * 		如果指针1的下一个节点等于指针2,则有环,并且环的起点就是指针2
 * 		指针2继续转一圈就是环的大小
 * 
 * 时间复杂度为: O(N²)
 *
 * @createBy jstfs
 * @createTime 2018-10-26 下午4:11:08
 */
public class CheckCircle1 {
	private static SingleChain chain = new SingleChain(12);	//初始化链表
	
	public static void main(String[] args) {
		chain.makeUpCircle(3);	//将链表尾部元素和指定位置的元素相连形成环
		System.out.println(chain);
		
		check();
	}
	
	public static void check() {
		Node point1 = chain.getFirst().getNext();	//指针1从第2个节点开始
		Node point2 = chain.getFirst();
		
		while(point1 != null) {
			while(true) {
				if(point1.getNext() == point2) {
					System.out.println("环的起始节点: " + point2);
					System.out.println("环的长度: " + getCircleLength(point2));
					return;
				}
				
				if(point2.getNext() == point1) {
					point2 = chain.getFirst();	//一旦两个指针相遇,指针2再从头开始
					break;
				} else {
					point2 = point2.getNext();
				}
			}
			point1 = point1.getNext();	//指针1向后移动一位
		}
		
		System.out.println("链表中没有环");
	}
	
	public static int getCircleLength(Node start) {
		Node point = start;
		int length = 1;
		while(true) {
			if(point.getNext() == start) {
				return length;
			}
			point = point.getNext();
			length++;
		}
	}
}