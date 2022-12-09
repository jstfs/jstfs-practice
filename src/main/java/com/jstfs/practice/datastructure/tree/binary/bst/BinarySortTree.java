package com.jstfs.practice.datastructure.tree.binary.bst;

import java.util.Arrays;

import com.jstfs.common.utils.MyArrayUtils;
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
		
		BinarySortTree bst = new BinarySortTree();
		bst.build(MyArrayUtils.toObjectAry(dataAry));
		TreeNode node4 = bst.searchNode(4);
		bst.printTree();
		bst.leftRotate(node4);
		bst.printTree();
	}
	
	/**
	 * 构建一棵BST树
	 */
	@Override
	public void build(Object[] dataAry) {
		for(int i = 0; i < dataAry.length; i++) {
			addNode(new TreeNode(dataAry[i]));
		}
	}
	
	/**
	 * 添加节点
	 * 
	 * 重写父类中的实现
	 */
	@Override
	public void addNode(TreeNode newNode) {
		if(getRootNode() == null) {
			setRootNode(newNode);
		} else {
			addNode(getRootNode(), newNode);
		}
		
		System.out.println("==============增加节点 " + newNode + ",调整前打印树...============================");
		printTree();
		
		System.out.println("===执行增加后动作,尝试调整");
		afterAddNode(newNode);
		System.out.println("==============增加节点 " + newNode + " 完成==================================");
		System.out.println();
	}
	
	/**
	 * 给以指定节点为根节点的子树上添加节点
	 * 
	 * 如果要插入的节点小于指定节点:
	 * 		如果指定节点的左子节点不存在,则设置要插入的节点为指定节点的左子节点,结束
	 * 		如果指定节点的左子节点存在,则要插入的节点继续和指定节点的左子节点比较
	 * 如果要插入的节点大于等于指定节点:
	 * 		如果指定节点的右子节点不存在,则设置要插入的节点为指定节点的右子节点,结束
	 * 		如果指定节点的右子节点存在,则要插入的节点继续和指定节点的右子节点比较
	 * 
	 * @param currNode	指定节点
	 * @param newNode	要插入的节点
	 */
	public void addNode(TreeNode currNode, TreeNode newNode) {
		int newData = (int) newNode.getData();
		int currData = (int) currNode.getData();
		
		if(newData < currData) {
			if(currNode.getLeftChild() == null) {
				currNode.setLeftChild(newNode);
				newNode.setParent(currNode);
			} else {
				addNode(currNode.getLeftChild(), newNode);
			}
		} else {
			if(currNode.getRightChild() == null) {
				currNode.setRightChild(newNode);
				newNode.setParent(currNode);
			} else {
				addNode(currNode.getRightChild(), newNode);
			}
		}
	}
	
	/**
	 * 删除数据
	 * 
	 * 需要将要删掉的节点的子节点继续挂载其父节点上,继续保持二叉排序树的规则
	 * 
	 * @param data	要删除的数据
	 * @return		是否删除成功
	 */
	@Override
	public boolean deleteNode(Object data) {
		TreeNode rootNode = getRootNode();
		if(rootNode == null) {
			System.out.println("空树不能删除");
			return false;
		}
		
		TreeNode delNode = searchNode(data);
		if(delNode == null) {
			System.out.println("要删除的节点[" + data + "]不存在");
			return false;
		}
		
		return deleteNode(delNode);
	}
	
	/**
	 * 删除节点
	 * 
	 * 需要将要删掉的节点的子节点继续挂载其父节点上,继续保持二叉排序树的规则
	 * 
	 * @param delNode	要删除的节点
	 * @return			是否删除成功
	 */
	@Override
	public boolean deleteNode(TreeNode delNode) {
		TreeNode rootNode = getRootNode();
		if(rootNode == null) {
			System.out.println("空树不能删除");
			return false;
		}
		
		if(rootNode == delNode) {
			System.out.println("删除根节点");
			setRootNode(null);
			return true;
		}
		
		TreeNode parentNode = delNode.getParent();
		if(delNode.getLeftChild() == null && delNode.getRightChild() == null) {
			/**
			 * 要删除的节点是叶子节点
			 */
			deleteAndUpdate(parentNode, delNode, null);
		} else if(delNode.getLeftChild() != null && delNode.getRightChild() != null) {
			/**
			 * 要删除的节点有两个子节点
			 * 
			 * 有两种方案:
			 * 		1, 找到要删除的节点按照中序遍历的前驱节点(左子树中最大data的节点),删掉它,并将它的data覆盖到要删除的节点上
			 * 		2, 找到要删除的节点按照中序遍历的后继节点(右子树中最小data的节点),删掉它,并将它的data覆盖到要删除的节点上
			 * 此处用的是第2种方案.
			 */
			TreeNode minNodeInRightChild = deleteMinDataInRightSubTree(delNode.getRightChild());
			delNode.setData(minNodeInRightChild.getData());
		} else {
			/**
			 * 要删除的节点只有一个子节点
			 */
			if(delNode.getLeftChild() != null) {
				//要删除的节点只有左子节点
				deleteAndUpdate(parentNode, delNode, delNode.getLeftChild());
			} else {
				//要删除的节点只有右子节点
				deleteAndUpdate(parentNode, delNode, delNode.getRightChild());
			}
		}
		
		return true;
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
	
	/**
	 * 左旋转
	 * 
	 * 以指定节点以及其右子节点作为支点进行左旋转
	 * 
	 * @param middleNode	指定节点
	 */
	public void leftRotate(TreeNode middleNode) {
		System.out.println("以节点 " + middleNode + "以及其右子节点作为支点进行左旋转...");
		
		if(middleNode == null) {
			return;
		}
		
		TreeNode parent = middleNode.getParent();
		TreeNode oldRight = middleNode.getRightChild();		//6
		TreeNode oldRightLeft = null;
		
		if(oldRight != null) {
			oldRightLeft = oldRight.getLeftChild();		//5
			if(oldRightLeft != null) {
				oldRightLeft.setParent(middleNode);
			}
			middleNode.setRightChild(oldRightLeft);
			
			oldRight.setParent(parent);
			
			if(parent != null) {
				if(parent.getLeftChild() == middleNode) {
					parent.setLeftChild(oldRight);
				} else {
					parent.setRightChild(oldRight);
				}
			}
			
			oldRight.setLeftChild(middleNode);
			middleNode.setParent(oldRight);
			
			if(middleNode == getRootNode()) {
				//如果指定节点是根节点,则旋转后要更换根节点
				setRootNode(oldRight);
			}
		}
	}
	
	/**
	 * 左旋转
	 * 
	 * 把根节点以及其右子节点作为支点进行左旋转
	 */
	public void leftRotate() {
		leftRotate(getRootNode());
	}
	
	/**
	 * 右旋转
	 * 
	 * 把指定节点以及其左子结点作为支点进行右旋转
	 * 
	 * @param middleNode	指定节点
	 */
	public void rightRotate(TreeNode middleNode) {
		System.out.println("以节点 " + middleNode + "以及其左子结点作为支点进行右旋转...");
		
		if(middleNode == null) {
			return;
		}
		
		TreeNode parent = middleNode.getParent();
		TreeNode oldLeft = middleNode.getLeftChild();
		TreeNode oldLeftRight = null;
		
		if(oldLeft != null) {
			oldLeftRight = oldLeft.getRightChild();
			if(oldLeftRight != null) {
				oldLeftRight.setParent(middleNode);
			}
			middleNode.setLeftChild(oldLeftRight);
			
			oldLeft.setParent(parent);
			
			if(parent != null) {
				if(parent.getLeftChild() == middleNode) {
					parent.setLeftChild(oldLeft);
				} else {
					parent.setRightChild(oldLeft);
				}
			}
			
			oldLeft.setRightChild(middleNode);
			middleNode.setParent(oldLeft);
			
			if(middleNode == getRootNode()) {
				//如果指定节点是根节点,则旋转后要更换根节点
				setRootNode(oldLeft);
			}
		}
	}
	
	/**
	 * 右旋转
	 * 
	 * 把根节点以及其左子结点作为支点进行右旋转
	 */
	public void rightRotate() {
		rightRotate(getRootNode());
	}
	
	/**
	 * 删除节点并更新节点间的关系
	 * 
	 * @param parentNode	要删除的节点的父节点
	 * @param deleteNode	要删除的节点
	 * @param newNode		新的子节点
	 */
	public void deleteAndUpdate(TreeNode parentNode, TreeNode deleteNode, TreeNode newNode) {
		if(parentNode != null) {
			if(parentNode.getLeftChild() == deleteNode) {
				parentNode.setLeftChild(newNode);
			} else {
				parentNode.setRightChild(newNode);
			}
			
			if(newNode != null) {
				newNode.setParent(parentNode);
			}
		} else {
			setRootNode(newNode);
		}
		
		deleteNode.setParent(null);
		deleteNode.setLeftChild(null);
		deleteNode.setRightChild(null);
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
	 * 以指定节点为根节点查找其最小data的子节点,删掉该节点,并返回其data
	 * 
	 * 实际上就是一直找左子节点,直到没有左子节点为止
	 * 此时这个最小data的节点有可能存在右子节点的,所以不能直接删除
	 * 那么继续调用deleteNode()方法,递归删除该节点(要么走删除叶子节点的逻辑,要么走删除只有右子节点的逻辑)
	 * 
	 * @param minDataNode	某个节点的右子节点
	 * 
	 * @return 右子树中最小data的节点
	 */
	private TreeNode deleteMinDataInRightSubTree(TreeNode minDataNode) {
		while(true) {
			if(minDataNode.getLeftChild() != null) {
				minDataNode = minDataNode.getLeftChild();
			} else {
				break;
			}
		}
		
		deleteNode(minDataNode);
		return minDataNode;
	}
	
}
