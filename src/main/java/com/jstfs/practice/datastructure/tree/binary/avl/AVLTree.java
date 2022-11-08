package com.jstfs.practice.datastructure.tree.binary.avl;

import java.util.Arrays;

import com.jstfs.common.utils.MyRandomUtils;
import com.jstfs.practice.datastructure.tree.binary.TreeNode;
import com.jstfs.practice.datastructure.tree.binary.bst.BinarySortTree;

/**
 * 平衡二叉树
 * 
 * 		得名于它的两位发明者: G.M.Adelson-Velsky 和 E.M.Landis
 * 		是一种更优的二叉排序树
 * 		以任意节点为根节点的子树的左子树和右子树的高度差的绝对值不能大于1
 * 
 * @createBy	落叶
 * @createTime	2022年10月29日 下午2:37:33
 */
public class AVLTree extends BinarySortTree {
	private static int size = 10;
	private static int[] dataAry; 
	
	public static void main(String[] args) {
		MyRandomUtils.setSeed(System.currentTimeMillis());
		dataAry = MyRandomUtils.generateIntAry(size, 1, 4 * size);
		System.out.println("原数组:\t\t" + Arrays.toString(dataAry));
		
		AVLTree avlTree = new AVLTree(true);
		avlTree.printTree();
		
		System.out.println("左子树的高度:" + avlTree.getRootNode().getLeftLever());
		System.out.println("右子树的高度:" + avlTree.getRootNode().getRightLever());
	}
	
	public AVLTree() {
		this(false);
	}
	
	public AVLTree(boolean defualtNode) {
		if(defualtNode) {
			build();
		}
	}
	
	/**
	 * 创建平衡二叉树
	 */
	@Override
	public void build() {
		build(dataAry);
	}
	
	/**
	 * 添加节点
	 */
	@Override
	public void addNode(TreeNode newNode) {
		TreeNode rootNode = getRootNode();
		addNode(rootNode, newNode);
		//判断是否旋转
		if(rootNode.getLeftLever() - rootNode.getRightLever() > 1) {
			TreeNode leftNode = rootNode.getLeftChild();
			/**
			 * 在满足右旋的条件下,同时还满足根节点的左子节点的右子树的高度大于左子结点的左子树的高度
			 * 那么此时应该优先将根节点的左子树左旋,再将整棵树右旋
			 */
			if(leftNode != null && leftNode.getRightLever() > leftNode.getLeftLever()) {
				leftRotate(leftNode);
			}
			rightRotate();
		} else if(rootNode.getRightLever() - rootNode.getLeftLever() > 1) {
			TreeNode rightNode = rootNode.getRightChild();
			/**
			 * 在满足左旋的条件下,同时还满足根节点的右子节点的左子树的高度大于右子结点的右子树的高度
			 * 那么此时应该优先将根节点的右子树右旋,再将整棵树左旋
			 */
			if(rightNode != null && rightNode.getLeftLever() > rightNode.getRightLever()) {
				rightRotate(rightNode);
			}
			leftRotate();
		}
	}
	
	/**
	 * 左旋转,即将整棵树进行左旋转
	 */
	public void leftRotate() {
		leftRotate(getRootNode());
	}
	
	/**
	 * 把以指定节点作为根节点的子树进行左旋转
	 */
	public void leftRotate(TreeNode middleNode) {
		if(middleNode == null) {
			return;
		}
		
		//创建一个新节点,其节点的数据设置为根节点的数据
		TreeNode newNode = new TreeNode(middleNode.getData());
		//把新节点的左子结点指向根节点的左子结点
		newNode.setLeftChild(middleNode.getLeftChild());
		if(middleNode.getRightChild() != null) {
			//把新节点的右子节点指向根节点的右子节点的左子结点
			newNode.setRightChild(middleNode.getRightChild().getLeftChild());
			//将根节点的数据覆盖为其右子节点的数据
			middleNode.setData(middleNode.getRightChild().getData());
			//把根节点的右子节点指向其右子节点的右子节点
			middleNode.setRightChild(middleNode.getRightChild().getRightChild());
		}
		//将根节点的左子节点指向新节点
		middleNode.setLeftChild(newNode);
	}
	
	/**
	 * 右旋转,即将整棵树进行右旋转
	 */
	public void rightRotate() {
		rightRotate(getRootNode());
	}
	
	/**
	 * 把以指定节点作为根节点的子树进行右旋转
	 */
	public void rightRotate(TreeNode middleNode) {
		if(middleNode == null) {
			return;
		}
		
		//创建一个新节点,其节点的数据设置为根节点的数据
		TreeNode newNode = new TreeNode(middleNode.getData());
		//把新节点的右子结点指向根节点的右子结点
		newNode.setRightChild(middleNode.getRightChild());
		if(middleNode.getLeftChild() != null) {
			//把新节点的左子节点指向根节点的左子节点的右子结点
			newNode.setLeftChild(middleNode.getLeftChild().getRightChild());
			//将根节点的数据覆盖为其左子节点的数据
			middleNode.setData(middleNode.getLeftChild().getData());
			//把根节点的左子节点指向其左子节点的左子节点
			middleNode.setLeftChild(middleNode.getLeftChild().getLeftChild());
		}
		//将根节点的右子节点指向新节点
		middleNode.setRightChild(newNode);
	}
}
