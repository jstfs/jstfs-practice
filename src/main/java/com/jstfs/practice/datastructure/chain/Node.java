package com.jstfs.practice.datastructure.chain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @createBy 	落叶
 * @createTime 	2018-10-25 上午11:06:51
 */
public class Node {
	private Object data;	//节点中保存的数据
	private Node next;		//下一个节点
	
	private List<String> namesOfBelongChain = new ArrayList<String>();	//当同一个节点属于不同链表时,记录所属链表的名称
	
	public Node(Object data) {
		this.data = data;
	}
	
	public Node(Object data, String chainName) {
		this.data = data;
		setNameOfBelongChain(chainName);
	}

	public void setNameOfBelongChain(String chainName) {
		if(StringUtils.isNotEmpty(chainName)) {
			namesOfBelongChain.add(chainName);
		}
	}
	
	public String toString() {
		return toString(false);
	}
	
	public String toString(boolean isShowChainName) {
		if(CollectionUtils.isNotEmpty(namesOfBelongChain) && isShowChainName) {
			return "[" + getNamesOfBelongChain() + " - " + data + "]";
		} else {
			return "[" + data + "]";
		}
	}
	
	public String toString(int index) {
		return toString(false, index);
	}
	
	public String toString(boolean isShowChainName, int index) {
		if(CollectionUtils.isNotEmpty(namesOfBelongChain) && isShowChainName) {
			return "[" + getNamesOfBelongChain() + " - ("  + data + ":" + index + ")]";
		} else {
			return "[" + data + ":" + index + "]";
		}
	}
	
	public String getNamesOfBelongChain() {
		if(namesOfBelongChain.size() == 0) {
			return null;
		}
		StringBuilder chainNames = new StringBuilder();;
		for(String chainName : namesOfBelongChain) {
			chainNames.append(chainName).append("|");
		}
		
		return chainNames.substring(0, chainNames.length() - 1);
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
}
