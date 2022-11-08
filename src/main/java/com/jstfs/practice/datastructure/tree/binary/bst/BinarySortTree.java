package com.jstfs.practice.datastructure.tree.binary.bst;

import java.util.Arrays;

import com.jstfs.common.utils.MyRandomUtils;
import com.jstfs.practice.datastructure.tree.binary.BinaryTree;
import com.jstfs.practice.datastructure.tree.binary.TreeNode;

/**
 * 二叉排序树
 * 
 * 特点:
 * 		使用中序遍历的方式,节点则已经排好序了(升序)
 * 
 * @createBy	落叶
 * @createTime	2022年10月21日 下午10:05:16
 */
public class BinarySortTree extends BinaryTree {
	private static int size = 10;
	private static int[] dataAry; 
	
	public static void main(String[] args) {
		MyRandomUtils.setSeed(System.currentTimeMillis());
		dataAry = MyRandomUtils.generateIntAry(size, 1, 4 * size);
		System.out.println("原数组:\t\t" + Arrays.toString(dataAry));
		
		BinarySortTree bst = new BinarySortTree(true);
		System.out.println(bst.deleteNode(25));
	}
	
	public BinarySortTree() {
		this(false);
	}
	
	public BinarySortTree(boolean defualtNode) {
		if(defualtNode) {
			build();
		}
	}
	
	/**
	 * 创建二叉排序树
	 */
	@Override
	public void build() {
		build(dataAry);
	}
	
	/**
	 * 用整型数据作为data构建一棵树
	 */
	@Override
	public void build(int[] dataAry) {
		setRootNode(new TreeNode(dataAry[0]));
		for(int i = 1; i < dataAry.length; i++) {
			addNode(new TreeNode(dataAry[i]));
		}
	}
	
	/**
	 * 添加节点
	 */
	@Override
	public void addNode(TreeNode newNode) {
		addNode(getRootNode(), newNode);
	}
	
	/**
	 * 给以指定节点为根节点的子树上添加节点
	 * 
	 * 如果要插入的节点小于当前节点:
	 * 		如果当前节点的左子节点不存在,则设置要插入的节点为当前节点的左子节点,结束
	 * 		如果当前节点的左子节点存在,则要插入的节点继续和当前节点的左子节点比较
	 * 如果要插入的节点大于等于当前节点:
	 * 		如果当前节点的右子节点不存在,则设置要插入的节点为当前节点的右子节点,结束
	 * 		如果当前节点的右子节点存在,则要插入的节点继续和当前节点的右子节点比较
	 * 
	 * @param currNode	当前节点
	 * @param newNode	要插入的节点
	 */
	public void addNode(TreeNode currNode, TreeNode newNode) {
		int newData = (int) newNode.getData();
		int currData = (int) currNode.getData();
		
		if(newData < currData) {
			if(currNode.getLeftChild() == null) {
				currNode.setLeftChild(newNode);
			} else {
				addNode(currNode.getLeftChild(), newNode);
			}
		} else {
			if(currNode.getRightChild() == null) {
				currNode.setRightChild(newNode);
			} else {
				addNode(currNode.getRightChild(), newNode);
			}
		}
	}
	
	/**
	 * 删除节点
	 * 
	 * 需要将要删掉的节点的子节点继续挂载其父节点上,继续保持二叉排序树的规则
	 */
	@Override
	public TreeNode deleteNode(Object data) {
		if(getRootNode() == null) {
			return null;
		}
		
		TreeNode deleteNode = searchNode(data);
		if(deleteNode == null) {
			return null;
		}
		
		TreeNode parentNode = searchParent(data);
		if(deleteNode.getLeftChild() == null && deleteNode.getRightChild() == null) {
			/**
			 * 要删除的节点是叶子节点
			 */
			if(parentNode != null) {
				//要删除的节点存在父节点
				if(parentNode.getLeftChild() == deleteNode) {
					//要删除的节点是其父节点的左子节点
					parentNode.setLeftChild(null);
				} else {
					//要删除的节点是其父节点的右子节点
					parentNode.setRightChild(null);
				}
			} else {
				//要删除的节点没有父节点,那么它一定就是根节点(说明这棵树就只有这一个节点)
				setRootNode(null);
			}
			return deleteNode;
		} else if(deleteNode.getLeftChild() != null && deleteNode.getRightChild() != null) {
			/**
			 * 要删除的节点有两个子节点
			 * 
			 * 有两种方案:
			 * 		1, 找要删除的节点的左子节点中最大data的节点,删掉它,并将它的data覆盖到要删除的节点上
			 * 		2, 找要删除的节点的右子节点中最小data的节点,删掉它,并将它的data覆盖到要删除的节点上
			 * 此处用的是第2种方案.
			 */
			TreeNode minNodeInRightChild = deleteMinDataInRightChild(deleteNode.getRightChild());
			TreeNode node = new TreeNode(deleteNode.getData());
			deleteNode.setData(minNodeInRightChild.getData());
			
			return node;
		} else {
			/**
			 * 要删除的节点只有一个子节点
			 */
			if(deleteNode.getLeftChild() != null) {
				//要删除的节点只有左子节点
				if(parentNode != null) {
					//要删除的节点存在父节点
					if(parentNode.getLeftChild() == deleteNode) {
						//要删除的节点是父节点的左子节点
						parentNode.setLeftChild(deleteNode.getLeftChild());
					} else {
						//要删除的节点是父节点的右子节点
						parentNode.setRightChild(deleteNode.getLeftChild());
					}
				} else {
					//要删除的节点没有父节点,那么它一定就是根节点
					setRootNode(deleteNode.getLeftChild());
				}
			} else {
				//要删除的节点只有右子节点
				if(parentNode != null) {
					//要删除的节点存在父节点
					if(parentNode.getLeftChild() == deleteNode) {
						//要删除的节点是父节点的左子节点
						parentNode.setLeftChild(deleteNode.getRightChild());
					} else {
						//要删除的节点是父节点的右子节点
						parentNode.setRightChild(deleteNode.getRightChild());
					}
				} else {
					//要删除的节点没有父节点,那么它一定就是根节点
					setRootNode(deleteNode.getRightChild());
				}
			}
			return deleteNode;
		}
	}
	
	/**
	 * 查找指定元素
	 */
	@Override
	public TreeNode searchNode(Object data) {
		if(getRootNode() == null) {
			return null;
		}
		return searchNode(getRootNode(), data);
	}
	
	/**
	 * 查找指定元素的父节点
	 */
	public TreeNode searchParent(Object data) {
		if(getRootNode() == null) {
			return null;
		}
		return searchParent(getRootNode(), data);
	}
	
//======================================= public/private 方法分界线 TODO =========================================================================
	
	/**
	 * 在以指定节点作为根节点的子树中查找
	 * 
	 * @param currNode	指定节点
	 * @param data		要查找的数据
	 */
	private TreeNode searchNode(TreeNode currNode, Object data) {
		int searchData = (int) data;
		int currData = (int) currNode.getData();
		
		if(searchData == currData) {
			return currNode;
		} else if(searchData < currData) {
			if(currNode.getLeftChild() == null) {
				return null;
			} else {
				return searchNode(currNode.getLeftChild(), data);
			}
		} else {
			if(currNode.getRightChild() == null) {
				return null;
			} else {
				return searchNode(currNode.getRightChild(), data);
			}
		}
	}
	
	/**
	 * 在以指定节点作为根节点的子树中查找指定节点的父节点
	 * 
	 * @param currNode	指定节点
	 * @param data		要查找的数据
	 */
	private TreeNode searchParent(TreeNode currNode, Object data) {
		if(currNode.getLeftChild() != null && currNode.getLeftChild().getData().equals(data)
		 ||currNode.getRightChild() != null && currNode.getRightChild().getData().equals(data)) {
			
			return currNode;
		} else {
			int searchData = (int) data;
			int currData = (int) currNode.getData();
			
			if(searchData < currData && currNode.getLeftChild() != null) {
				return searchParent(currNode.getLeftChild(), data);
			} else if(searchData >= currData && currNode.getRightChild() != null) {
				return searchParent(currNode.getRightChild(), data);
			} else {
				return null;
			}
		}
	}
	
	/**
	 * 以指定节点为根节点查找其最小data的子节点,删掉这个最小的子节点并返回其data
	 * 
	 * 实际上就是一直找左子节点,直到没有左子节点为止
	 * 此时这个最小的节点有两种情况:
	 * 		1, 该节点没有左子节点,同时也没有右子节点
	 * 		2, 该节点没有左子节点,但有右子节点之后
	 * 如果是第1种,删除该节点的时候,要把其右子节点挂载父节点上
	 * 如果是第2种,直接删除即可
	 * 
	 * 但不论是哪一种情况,都可以继续使用deleteNode(Object data)方法来删除
	 * 
	 * @param node	某个节点的右子节点
	 * @return		右子节点的最小data子节点
	 */
	private TreeNode deleteMinDataInRightChild(TreeNode node) {
		while(node.getLeftChild() != null) {
			node = node.getLeftChild();
		}
		
		return deleteNode(node.getData());
	}
	
}
