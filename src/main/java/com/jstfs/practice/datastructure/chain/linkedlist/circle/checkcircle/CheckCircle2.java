package com.jstfs.practice.datastructure.chain.linkedlist.circle.checkcircle;

import com.jstfs.practice.datastructure.chain.Node;
import com.jstfs.practice.datastructure.chain.SingleChain;

/**
 * 检查单链表中是否有环,以及环的大小,环的起始节点
 * 
 * 方法二:
 * 		使用两个指针, fast 和 slow, fast 步长为2, slow 步长为1
 * 		如果 fast 的下一个节点为 null,则没有环
 * 		如果 fast 和 slow 相遇,则有环,并且相遇点一定在环内
 * 		由于第一次相遇点一定在环内,所以 slow 继续转一圈就是环的大小
 * 		
 * 		环的起点:
 * 			假设从链表第一个节点到环的起点的距离是 h,环的起点到第一次相遇点的距离是  m,环的大小为 L,则有:
 * 			2(h + m) = h + m + nL (n>=1), n 表示在第一次相遇前 fast 已经转的圈数,很容易证明 n 最少已经转了1圈
 * 			那么得到: h + m = nL ,此时再有第三个指针从链表头部开始走,步长为1,它和 slow 同时往前走 h 步
 * 			那么 slow 一共走了 (h + m + h) 的距离,由于  h + m + h = nL + h,而 nL 不管等于多少都相当于原地转圈
 * 			那么可以证明此时 slow 肯定停在了环的起始节点,而第三个指针此时也停留在了环的起始节点,
 * 			所以只要判断当这两个指针相等就可以停下来,停下来的节点就是环的起点了
 * 
 * 时间复杂度为: O(N)
 *
 * @createBy jstfs
 * @createTime 2018-10-28 下午11:48:52
 */
public class CheckCircle2 {
	private static SingleChain chain = new SingleChain(8);	//初始化链表
	
	public static void main(String[] args) {
		chain.makeUpCircle(3);	//将链表尾部元素和指定位置的元素相连形成环
		System.out.println(chain);

		check();
	}
	
	public static void check() {
		Node slow = null;
		Node fast = null;
		
		while(true) {
			fast = chain.go(fast, 2);
			slow = chain.go(slow, 1);
			if(fast == null) {
				System.out.println("链表中没有环");
				return;
			}
			
			if(fast == slow) {	//首次相遇
				System.out.println("首次相遇节点: " + slow);
				System.out.println("环的起始节点: " + getCircleStartNode(slow));
				System.out.println("环的长度: " + getCircleLength(slow));
				return;
			}
		}
	}
	
	/**
	 * 获得环的起始节点
	 * 
	 * @param slow 首次相遇节点
	 */
	public static String getCircleStartNode(Node slow) {
		Node third = null;
		while(true) {
			third = chain.go(third, 1);
			slow = chain.go(slow, 1);
			if(third == slow) {
				return third.toString();
			}
		}
	}
	
	/**
	 * 获得环的长度
	 * 该方法必须首次相遇后才可以调用,因为首次相遇才能确保有环
	 * 
	 * @param meetPoint	首次相遇点,该节点一定位于环中
	 */
	public static int getCircleLength(Node meetPoint) {
		Node forwardNode  = meetPoint;
		int circleLength = 1;	//因为相遇点一定在环中,所以环长度需要包含这一个节点
		
		while(true) {
			forwardNode = chain.go(forwardNode, 1);
			if(forwardNode == meetPoint) {
				return circleLength;
			}
			circleLength++;
		}
	}
}