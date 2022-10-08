package com.jstfs.practice.algorithm.search.binarytree;

import com.jstfs.practice.datastructure.tree.binary.BinaryTree;
import com.jstfs.practice.datastructure.tree.binary.TreeErgodicTypeEnum;
import com.jstfs.practice.datastructure.tree.binary.TreeNode;

/**
 * 二叉树查找
 * 
 * @createBy	落叶
 * @createTime	2022年9月18日 下午9:49:01
 */
public class BinaryTreeSearch {

	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();
		TreeNode node = bt.serach(TreeErgodicTypeEnum.ERGODIC_LDR, 5);
		System.out.println(node);
	}
}
