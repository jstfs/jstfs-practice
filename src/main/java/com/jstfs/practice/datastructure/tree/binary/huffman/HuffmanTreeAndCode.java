package com.jstfs.practice.datastructure.tree.binary.huffman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.jstfs.common.utils.MyRandomUtils;
import com.jstfs.practice.datastructure.tree.binary.BinaryTree;
import com.jstfs.practice.datastructure.tree.binary.TreeNode;

/**
 * 赫夫曼树以及赫夫曼编码
 * 
 * @createBy	落叶
 * @createTime	2022年10月7日 下午5:27:05
 */
public class HuffmanTreeAndCode {
	private static int size = 10;
	
	public static void main(String[] args) {
		HuffmanTreeAndCode huffman = new HuffmanTreeAndCode();
		int[] ary = MyRandomUtils.generateIntAry(size, 1, 4 * size);
		System.out.println("权重数组:\t" + Arrays.toString(ary));
		
		//构建赫夫曼树
		BinaryTree huffmanTree = huffman.create(ary);
		
		//中序遍历
		huffmanTree.ergodic();
	}
	
	/**
	 * 将一个代表节点权重的数组构建成一棵赫夫曼树
	 * 
	 * @param ary	权重数组
	 * @return		一棵赫夫曼树
	 */
	private BinaryTree create(int[] ary) {
		List<TreeNode> nodes = new ArrayList<>();
		for(int v : ary) {
			nodes.add(new TreeNode(v));
		}
		Collections.sort(nodes);
		
		while(nodes.size() > 1) {
			TreeNode left = nodes.get(0);
			TreeNode right = nodes.get(1);
			TreeNode parent = new TreeNode(left.getWeight() + right.getWeight());
			parent.setLeftChild(left);
			parent.setRightChild(right);
			
			nodes.remove(left);
			nodes.remove(right);
			nodes.add(parent);
			
			Collections.sort(nodes);
		}
		
		BinaryTree huffmanTree = new BinaryTree(false);
		huffmanTree.setRootNode(nodes.get(0));
		return huffmanTree;
	}
}
