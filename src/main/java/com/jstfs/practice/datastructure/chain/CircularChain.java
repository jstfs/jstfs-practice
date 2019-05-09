package com.jstfs.practice.datastructure.chain;

import org.apache.commons.lang3.StringUtils;

/**
 * 循环链表
 * 
 * @createBy jstfs
 * @createTime 2018-10-25 上午11:14:35
 */
public class CircularChain {
	private Node first; // 头节点
	private Node last; // 尾节点
	private int size = 0; // 链表的大小

	private char firstData = 'a'; // 为了使链表初始化变得简单,内置节点中数据的起始值,后续节点依次增加
	private String chainName = null; // 链表名称(当同一个Node被多个链表使用的情况下,则该属性作为Node的数据前缀来予以区分)

	public CircularChain() {
	}

	public CircularChain(int initSize) {
		this(null, initSize);
	}

	public CircularChain(String chainName, int initSize) {
		if(StringUtils.isNotEmpty(chainName)) {
			this.chainName = chainName;
		}

		for (int i = 0; i < initSize; i++) {
			add(new Node(Character.toString(firstData), chainName), size);
			firstData += 1;
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

		if(first == null) { // 空链表,则插入第一个节点
			first = node;
			last = node;
			size = 1;
			last.setNext(first);
			return;
		} else if(index == 0) { // 插入在表头
			node.setNext(first);
			first = node;
			size++;
			last.setNext(first);
			return;
		} else if(index == size) { // 插入在尾部
			last.setNext(node);
			last = node;
			size++;
			last.setNext(first);
			return;
		}

		// 插入链表中间某个位置
		int i = 1; // 从第二个位置开始遍历
		Node point = first;
		while (true) {
			if(index == i) {
				node.setNext(point.getNext());
				point.setNext(node);
				size++;
				break;
			} else {
				point = point.getNext();
				i++;
			}
		}
	}
	
	public Node get() {
		return get(0);
	}
	
	public Node get(int index) {
		if(index < 0 || index > size) {
			throw new RuntimeException("节点的序号不正确[0 - " + size + "]: " + index);
		}
		
		Node ret = null;
		int i = 0;
		Node point = first;
		while(true) {
			if(index == i) {
				ret = point;
				break;
			} else {
				point = point.getNext();
				i++;
			}
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
	 * @param isCutRel
	 *            是否切断被删除节点和其下一个节点之间的联系
	 */
	public Node remove(int index, boolean isCutRel) {
		if(size == 0) {
			return null;
		}

		if(index < 0 || index > (size - 1)) {
			throw new RuntimeException("删除节点的序号不正确[0 - " + (size - 1) + "]: " + index);
		}

		Node ret = null;
		if(index == 0) { // 删除表头
			ret = first;
			if(first.getNext() == null) { // 只有表头一个节点
				first = null;
				last = null;
				size = 0;
			} else {
				first = first.getNext();
				last.setNext(first);
				size--;
			}
		} else {
			int i = 1;
			Node point = first;
			while (true) {
				if(index == i) {
					ret = point.getNext();
					point.setNext(ret.getNext());
					size--;
					if(index == size) {
						last = point;	//如果删除的时最后一个,则修改last
					}
					break;
				} else {
					point = point.getNext();
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
		return toString(false);
	}

	/**
	 * 打印链表
	 * 
	 * @param isShowIndex	是否打印节点下标
	 */
	public String toString(boolean isShowIndex) {
		String result;
		StringBuilder sb = new StringBuilder();
		Node point = first;
		int index = 0;

		sb.append("{");
		while (true) {
			if(index == size) {
				if(isShowIndex) {
					sb.append(first.toString(0)).append(" -> ");
				} else {
					sb.append(point.toString()).append(" -> ");
				}
				break;
			} else {
				if(isShowIndex) {
					sb.append(point.toString(index)).append(" -> ");
				} else {
					sb.append(point.toString()).append(" -> ");
				}
			}
			point = point.getNext();
			index++;
		}

		if(!sb.toString().equals("{")) {
			result = sb.substring(0, sb.length() - 4);
		} else {
			result = sb.toString();
		}

		return result + "}";
	}
	
	/**
	 * 指定节点向后移动指定步长
	 */
	public Node go(Node node, int step) {
		Node result = node;
		if(node == null) {
			result = first;
			if(step == 1) {
				return result;
			} else {
				step--;
			}
		}

		while (true) {
			if(step <= 0) {
				return result;
			} else {
				if(result.getNext() == null) {
					return null;
				}
				step--;
				result = result.getNext();
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

	public char getNodeData() {
		return firstData;
	}

	public void setNodeData(char nodeData) {
		this.firstData = nodeData;
	}

	public String getChainName() {
		return chainName;
	}

	public void setChainName(String chainName) {
		this.chainName = chainName;
	}
}
