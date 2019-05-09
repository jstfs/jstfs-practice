package com.jstfs.practice.datastructure.chain.linkedlist.circle.intersection;

import com.jstfs.practice.datastructure.chain.Node;
import com.jstfs.practice.datastructure.chain.SingleChain;

/**
 * 判断两个链表是否有相交,如果相交,那么找到相交的第一个节点
 * 
 * 由于一个节点只能有唯一的一个下一节点,所以一旦有相交,则后面的全部相交
 * 将其中一个链表的尾部于另一个链表的头部相连,如果有相交节点,则会形成环,那么只要检查环是否存在就可以判断
 * 如果没有环,则一定没有相交
 * 
 * @createBy jstfs
 * @createTime 2018-10-29 上午12:07:25
 */
public class Intersection {
	private static SingleChain chainA = new SingleChain("A", 3);	//初始化链表
	private static SingleChain chainB = new SingleChain("B", 4);	//初始化链表
	
	public static void main(String[] args) {
		System.out.println(chainA);
		System.out.println(chainB);
		System.out.println("===================================================================================================");
		makeIntersect(2);
		System.out.println(chainA);
		System.out.println(chainB);
		System.out.println("===================================================================================================");
		
		check();
	}
	
	/**
	 * 使两个单链表产生交点
	 * 
	 * @param intersectionCount	交点的数量
	 */
	public static void makeIntersect(int intersectionCount) {
		String intersectionPrefix = chainA.getChainName() + chainB.getChainName() + "-";
		
		Node intersetion = null;
		for(int i = 0; i < intersectionCount; i++) {
			intersetion = new Node(intersectionPrefix + "x" + i);
			chainA.add(intersetion);
			chainB.add(intersetion);
		}
	}
	
	public static void check() {
		link();
		check_();
	}
	
	/**
	 * 将 A 链表的尾节点和 B 链表的头节点相连
	 */
	public static void link() {
		Node point = chainA.getFirst();
		while(true) {
			if(point.getNext() == null) {
				point.setNext(chainB.getFirst());
				break;
			} else {
				point = point.getNext();
			}
		}
	}
	
	public static void check_() {
		Node slow = null;
		Node fast = null;
		
		while(true) {
			slow = chainA.go(slow, 1);
			fast = chainA.go(fast, 2);
			if(fast == null) {
				System.out.println("链表中没有环");
				return;
			}
			
			if(fast == slow) {	//首次相遇
				System.out.println("第一个相交的节点: " + getCircleStartNode(slow));
				return;
			}
		}
	}
	
	/**
	 * 返回环的起始节点
	 * 
	 * @param slow 首次相遇节点
	 */
	public static String getCircleStartNode(Node slow) {
		Node third = null;
		while(true) {
			third = chainA.go(third, 1);
			slow = chainA.go(slow, 1);
			if(third == slow) {
				return third.toString();
			}
		}
	}
}