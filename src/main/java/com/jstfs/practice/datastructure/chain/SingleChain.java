package com.jstfs.practice.datastructure.chain;

import org.apache.commons.lang3.StringUtils;

import com.jstfs.common.utils.MyRandomUtils;

/**
 * 单链表
 * 
 * @createBy jstfs
 * @createTime 2018-10-25 上午11:14:35
 */
public class SingleChain {
	private Node first;		//头节点
	private Node last;		//尾节点(用来辅助完成一些操作,对外部不提供任何访问方式)
	private int size = 0;	//链表的大小
	
	private Integer defaultDataWithMax = 30;	//默认链表节点数据的最大值
	private String chainName = "DCN";	//链表名称,默认"DCN"(Default Chain Name)
	private int circleStartIndex = -1;	//如果存在环,环的起始节点下标
	
	public static void main(String[] args) {
		SingleChain sc = new SingleChain(10);
		sc.makeUpCircle(8);
		System.out.println(sc.toString());
	}
	
	public SingleChain() {}
	
	public SingleChain(int initSize) {
		this(null, initSize);
	}
	
	public SingleChain(String chainName, int initSize) {
		if(StringUtils.isNotEmpty(chainName)) {
			this.chainName = chainName;
		}
		
		for(int i = 0; i < initSize; i++) {
			add(new Node(MyRandomUtils.nextInteger(defaultDataWithMax), this.chainName), size);
		}
	}
	
	/**
	 * 尾部增加节点
	 */
	public void add(Node node) {
		add(node, size);
	}
	
	/**
	 * 指定位置增加节点
	 */
	public void add(Node node, int index) {
		if(index < 0 || index > size) {
			throw new RuntimeException("增加节点的序号不正确[0 - " + size + "]: " + index);
		}
		
		if(first == null) {	//空链表,则只能插入到第一个节点
			if(index != 0) {
				throw new RuntimeException("空链表不能插入到指定位置[" + index + "]");
			}
			first = last = node;
			size = 1;
			node.setNext(null);	//防止插入的节点自带下一个节点,所以必须清除
			return;
		}
		
		if(index == 0) {	//插入在表头
			node.setNext(first);
			first = node;
			size++;
			return;
		}
		
		if(index == size) {	//插入在尾部
			last.setNext(node);
			last = node;
			size++;
			node.setNext(null);	//防止插入的节点自带下一个节点,所以必须清除
			return;
		}
		
		//插入链表中间某个位置
		int i = 1;	//从第二个节点开始遍历
		Node preNode = first;	//要插入位置的上一个节点
		while(true) {
			if(index == i) {
				node.setNext(preNode.getNext());
				preNode.setNext(node);
				size++;
				break;
			} else {
				preNode = preNode.getNext();
				i++;
			}
		} 
	}
	
	public Node get() {
		return get(0);
	}
	
	public Node get(int index) {
		if(index < 0 || index >= size) {
			throw new RuntimeException("节点的序号不正确[0 - " + (size-1) + "]: " + index);
		} else if(index == 0) {
			return first;
		}
		
		Node ret = first;
		for (int i = 0; i < index; i++) {
			ret = ret.getNext();
		}
		
		return ret;
	}
	
	/**
	 * 删除并返回表尾节点
	 */
	public Node remove() {
		return remove(size - 1);
	}

	/**
	 * 删除并返回指定节点,并切断被删除节点和其下一个节点之间的联系
	 */
	public Node remove(int index) {
		return remove(index, true);
	}
	
	/**
	 * 删除并返回指定节点,并根据要求是否切断被删除节点和其下一个节点之间的联系
	 * 
	 * @param isCutRel	是否切断被删除节点和其下一个节点之间的联系
	 */
	public Node remove(int index, boolean isCutRel) {
		if(size == 0) {
			return null;
		}
		
		if(index < 0 || index >= size) {
			throw new RuntimeException("删除节点的序号不正确[0 - " + (size-1) + "]: " + index);
		}
		
		Node ret = null;
		if(index == 0) {	//删除表头
			ret = first;
			if(first.getNext() == null) {	//只有表头一个节点
				first = last = null;
			} else {
				first = first.getNext();
			}
			size--;
		} else {
			int i = 1;	//从第二个节点开始遍历
			Node preNode = first;	//要删除位置的上一个节点
			while(true) {
				if(index == i) {
					ret = preNode.getNext();
					preNode.setNext(ret.getNext());
					if(index == (size-1)) {
						last = preNode;	//如果删除的时最后一个,则修改last
					}
					size--;
					break;
				} else {
					preNode = preNode.getNext();
					i++;
				}
			} 
		}
		
		if(isCutRel) {
			ret.setNext(null);
		}
		
		return ret;
	}
	
	@Override
	public String toString() {
		return toString(false, false);
	}
	
	/**
	 * 打印链表
	 * 
	 * @param isShowChainName	是否打印节点所属的链表名称
	 * @param isShowIndex		是否打印节点下标
	 */
	public String toString(boolean isShowChainName, boolean isShowIndex) {
		String result;
		StringBuilder sb = new StringBuilder();
		Node node = first;
		int index = 0;	//节点下标
		
		sb.append("{");
		while(true) {
			if(node != null) {
				if(index == size) {	//有环
					if(isShowIndex) {
						sb.append(node.toString(isShowChainName, circleStartIndex)).append(" -> ");
					} else {
						sb.append(node.toString(isShowChainName)).append(" -> ");
					}
					break;
				} else {
					if(isShowIndex) {
						sb.append(node.toString(isShowChainName, index)).append(" -> ");
					} else {
						sb.append(node.toString(isShowChainName)).append(" -> ");
					}
				}
				node = node.getNext();
				index++;
			} else {
				break;	//无环
			}
		}
		
		if(!sb.toString().equals("{")) {
			result = sb.substring(0, sb.length() - 4);
		} else {
			result = sb.toString();
		}
		
		return result + "}";
	}
	
	/**
	 * 使链表中存在环,默认将尾节点连到头节点
	 */
	public void makeUpCircle() {
		makeUpCircle(0);
	}
	
	/**
	 * 使链表中存在环
	 * @param circleStartIndex	环的起始下标(从1开始),即将尾部连接到哪个下标的节点
	 */
	public void makeUpCircle(int circleStartIndex) {
		if(size < 2) {
			throw new RuntimeException("链表中最少需要2个元素才可以连成环");
		}
		if(circleStartIndex < 0 || circleStartIndex > (size-2)) {
			throw new RuntimeException("环的起始下标有误[0," + (size-2) + "]: " + circleStartIndex);
		}
		
		this.circleStartIndex = circleStartIndex;

		Node node = first;
		int index = 0;
		while(true) {
			if(index != circleStartIndex) {
				index++;
				node = node.getNext();
			} else {
				last.setNext(node);
				break;
			}
		}
	}
	
	/**
	 * 返回指定节点向后移动指定步长后的节点
	 */
	public Node go(Node node, int step) {
		Node ret = node;
		if(node == null) {
			ret = first;
			if(step == 1) {
				return ret;
			} else {
				step--;
			}
		}
		
		while(true) {
			if(step <= 0) {
				return ret; 
			} else {
				if(ret.getNext() == null) {
					return null;
				}
				step--;
				ret = ret.getNext();
			}
		}
	}

	public Node getFirst() {
		return first;
	}

	public void setFirst(Node first) {
		this.first = first;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getChainName() {
		return chainName;
	}

	public void setChainName(String chainName) {
		this.chainName = chainName;
	}
}
