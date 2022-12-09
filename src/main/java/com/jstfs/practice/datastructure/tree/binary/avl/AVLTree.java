package com.jstfs.practice.datastructure.tree.binary.avl;

import java.util.Arrays;

import com.jstfs.common.utils.MyArrayUtils;
import com.jstfs.common.utils.MyRandomUtils;
import com.jstfs.practice.datastructure.tree.binary.TreeNode;
import com.jstfs.practice.datastructure.tree.binary.bst.BinarySortTree;

/**
 * 平衡二叉树
 * 
 * 		是一种优化的二叉排序树
 * 		以任意节点为根节点的子树的左子树和右子树的高度差的绝对值不能大于1
 * 		每加入一个新节点后,如果打破了上述规则,则需要对根节点的左右子树进行相应的"旋转",以达到左右平衡的目的
 * 
 * 左旋转:
 * 		当某个节点的左子树的高度和右子树的高度差大于1,则需要以该节点为支点进行左旋转
 * 
 * 右旋转:
 * 		当某个节点的右子树的高度和左子树的高度差大于1,则需要以该节点为支点进行右旋转
 * 
 * 特殊情况:
 * 		进行相应的旋转后,左右子树还是不平衡,这种情况的规律是:
 * 		当符合右旋条件时:
 * 			同时,根节点的左子节点的右子树的高度大于左子结点的左子树的高度
 * 			那么此时应该优先将根节点的左子树左旋,再将整棵树右旋
 * 		当符合左旋条件时:
 * 			同时,根节点的右子节点的左子树的高度大于右子节点的右子树的高度
 * 			那么此时应该优先将根节点的右子树右旋,再将整棵树左旋
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
		
		AVLTree avlTree = new AVLTree();
		avlTree.build(MyArrayUtils.toObjectAry(dataAry));
		
		avlTree.printTree();
		System.out.println("左子树的高度:" + avlTree.getRootNode().getLeftLever());
		System.out.println("右子树的高度:" + avlTree.getRootNode().getRightLever());
	}
	
	/**
	 * 添加节点成功后的动作
	 * 
	 * AVL的实现是添加完节点后判断是否平衡,如果不平衡则进行旋转使其平衡
	 */
	@Override
	public void afterAddNode(TreeNode newNode) {
		TreeNode rootNode = getRootNode();
		
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
			
			System.out.println("旋转后打印树...");
			printTree();
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
			
			System.out.println("旋转后打印树...");
			printTree();
		}
	}
}
