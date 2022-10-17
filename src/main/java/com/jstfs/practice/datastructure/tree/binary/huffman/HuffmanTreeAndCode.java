package com.jstfs.practice.datastructure.tree.binary.huffman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jstfs.common.utils.MyCollectionUtils;
import com.jstfs.common.utils.MyStringUtils;
import com.jstfs.practice.datastructure.tree.binary.BinaryTree;
import com.jstfs.practice.datastructure.tree.binary.TreeNode;

/**
 * 赫夫曼树以及赫夫曼编码
 * 
 * @createBy	落叶
 * @createTime	2022年10月7日 下午5:27:05
 */
public class HuffmanTreeAndCode {
	
	private int lastByteLength = 0;
	
	public static void main(String[] args) {
		HuffmanTreeAndCode huffmanTreeAndCode = new HuffmanTreeAndCode();
		
		String source = "This is a programming exercise about huffman coding!";
		System.out.println("原字符串:" + source);
		Map<Byte, Integer> weightMap = huffmanTreeAndCode.getWeightMap(source);
		System.out.println("各字符权重:" + weightMap);
		
		//构建赫夫曼树
		BinaryTree huffmanTree = huffmanTreeAndCode.createHuffmanTree(weightMap);
		
		//获取赫夫曼编码
		Map<Byte, String> huffmanCodeMap = MyCollectionUtils.newMap(Byte.class, String.class);
		huffmanTreeAndCode.toHuffmanCode(huffmanTree.getRootNode(), huffmanCodeMap, new StringBuilder());
		System.out.println("赫夫曼编码:" + huffmanCodeMap);
		
		//压缩
		byte[] targetBytes = huffmanTreeAndCode.zip(source, huffmanCodeMap);
		
		//解压
		System.out.println("解压后的字符串:" + new String(huffmanTreeAndCode.unzip(targetBytes, huffmanCodeMap)));
	}
	
	/**
	 * 将原文本的每个字符解析成权重map
	 */
	private Map<Byte, Integer> getWeightMap(String source) {
		Map<Byte, Integer> map = new HashMap<>();
		for(Byte b : source.getBytes()) {
			if(map.containsKey(b)) {
				map.put(b, map.get(b) + 1);
			} else {
				map.put(b, 1);
			}
		}
		
		return map;
	}
	
	/**
	 * 根据节点的权重Map构建一棵赫夫曼树
	 */
	private BinaryTree createHuffmanTree(Map<Byte, Integer> weightMap) {
		List<TreeNode> nodes = new ArrayList<>();
		
		for(Map.Entry<Byte, Integer> entry : weightMap.entrySet()) {
			nodes.add(new TreeNode(entry.getKey(), entry.getValue()));
		}
		
		while(nodes.size() > 1) {
			Collections.sort(nodes);
			
			TreeNode left = nodes.get(0);
			TreeNode right = nodes.get(1);
			TreeNode parent = new TreeNode(null, left.getWeight() + right.getWeight());
			parent.setLeftChild(left);
			parent.setRightChild(right);
			
			nodes.remove(left);
			nodes.remove(right);
			nodes.add(parent);
		}
		
		BinaryTree huffmanTree = new BinaryTree(false);
		huffmanTree.setRootNode(nodes.get(0));
		return huffmanTree;
	}
	
	/**
	 * 根据一棵赫夫曼树获得所有叶子节点的编码
	 * 
	 * @param node				赫夫曼树的根节点
	 * @param huffmanCodeMap	存放赫夫曼编码的Map
	 * @param preCode			父节点的赫夫曼编码(前一次递归的后的编码)
	 */
	private void toHuffmanCode(TreeNode node, Map<Byte, String> huffmanCodeMap, StringBuilder preCode) {
		if(node.getLeftChild() != null || node.getRightChild() != null) {
			//非叶子节点
			if(node.getLeftChild() != null) {
				StringBuilder code = new StringBuilder(preCode);
				//左子节点编码为0
				code.append("0");
				toHuffmanCode(node.getLeftChild(), huffmanCodeMap, code);
			}
			if(node.getRightChild() != null) {
				StringBuilder code = new StringBuilder(preCode);
				//右子节点编码为1
				code.append("1");
				toHuffmanCode(node.getRightChild(), huffmanCodeMap, code);
			}
		} else {
			//叶子节点
			huffmanCodeMap.put((Byte)(node.getData()), preCode.toString());
		}
	}
	
	/**
	 * 将原文本根据赫夫曼编码压缩成成新的字节数组
	 */
	private byte[] zip(String source, Map<Byte, String> huffmanCodeMap) {
		byte[] sourceBytes = source.getBytes();
		System.out.println("原字节数组(" + sourceBytes.length + "):\t" + Arrays.toString(sourceBytes));
		
		//将元文本根据赫夫曼编码转成由0和1组成的序列
		StringBuilder codeSeq = new StringBuilder();
		for(byte b : sourceBytes) {
			codeSeq.append(huffmanCodeMap.get(b));
		}
		System.out.println("二进制序列(" + codeSeq.length() + "):" + codeSeq);
		
		int len = (codeSeq.length() + 7) / 8;
		byte[] targetBtyes = new byte[len];
		int index = 0;
		for(int i = 0; i < codeSeq.length(); i += 8) {
			String byteStr = null;
			if(i + 8 > codeSeq.length()) {
				byteStr = codeSeq.substring(i);
				//最后一段需要记录有几位,在解压的时候将长度补齐
				lastByteLength = byteStr.length();
			} else {
				byteStr = codeSeq.substring(i, i + 8);
			}
			
			/**
			 * 注意:
			 * 		由于计算机是用补码来存放负数的,所以Java的相关API也是会将一些与二进制的操作数视作补码
			 * 		比如:
			 * 			Integer.parseInt(byteStr, 2)这个方法会将byteStr这个字符串视作二进制补码
			 * 			所以在转为int时,会反向(补码->反码->原码)转成原码,得到一个int数据,然后再强转为byte
			 * 		例如,"11101111"这个二进制字符串,转为byte就是-17, 步骤如下:
			 * 			补码: 11101111 
			 * 			反码: 11101110
			 * 			原码: 10010001
			 * 			byte: -17 (符号位是1为负数,0010001是17)
			 * 
			 * 		如果最后一个byteStr不足8位,会补前导0到至少一个字节的长度
			 * 		当然最后一个byteStr也可能刚好8位并且是1开头,
			 */
			targetBtyes[index++] = (byte)Integer.parseInt(byteStr, 2);
		}
		System.out.println("压缩后字节数组(" + targetBtyes.length + "):" + Arrays.toString(targetBtyes));
		
		return targetBtyes;
	}
	
	/**
	 * 将压缩后的字节数组根据赫夫曼编码表解码
	 */
	private byte[] unzip(byte[] targetBytes, Map<Byte, String> huffmanCodeMap) {
		StringBuilder codeSeq = new StringBuilder();
		for (int i = 0; i < targetBytes.length; i++) {
			if(i == targetBytes.length - 1) {
				codeSeq.append(byteToBitString(targetBytes[i], true));
			} else {
				codeSeq.append(byteToBitString(targetBytes[i], false));
			}
		}
		
		System.out.println("解压后的二进制序列(" + codeSeq.length() + "):" + codeSeq);
		
		Map<String, Byte> reverseHuffmanCodeMap = MyCollectionUtils.newMap(String.class, Byte.class);
		for(Map.Entry<Byte, String> entry : huffmanCodeMap.entrySet()) {
			reverseHuffmanCodeMap.put(entry.getValue(), entry.getKey());
		}
		
		List<Byte> byteList = MyCollectionUtils.newList(Byte.class);
		for(int first = 0; first < codeSeq.length(); ) {
			int length = 1;
			Byte b = null;
			while(true) {
				String code = codeSeq.substring(first, first + length);
				b = reverseHuffmanCodeMap.get(code);
				if(b == null) {
					length++;
				} else {
					break;
				}
			}
			byteList.add(b);
			first = first + length;
		}
		
		byte[] sourceBytes = new byte[byteList.size()];
		for (int i = 0; i < byteList.size(); i++) {
			sourceBytes[i] = byteList.get(i);
		}
		
		return sourceBytes;
	}
	
	/**
	 * 将byte数据转成二进制字符串
	 * 最后一个字节要特殊处理,其他字节只需要对正数保留前导0即可
	 * 
	 * @param b		targetBytes中的一个字节
	 * @param flag	是否是最后一个字节
	 * 
	 * @return		二进制字符串
	 */
	private String byteToBitString(byte b, boolean isLastByte) {
		int temp = b;
		/**
		 * 注意:
		 * 		Integer.toBinaryString()方法如果遇到入参byte是正数的话(即第1位是0)
		 * 		那么转成字符串之后,前导0会被丢掉,所以结果str可能不满8位
		 * 		所以底下计算(temp|256)就是为了让第9位的1保留,这样1后面的0就不会被丢掉了
		 */
		if(!isLastByte) {
			/**
			 * 256的二进制: 1 0000 0000
			 */
			temp = temp | 256;
		}
		
		String str = Integer.toBinaryString(temp);
		
		if(!isLastByte || temp < 0) {
			//当最后一个字节小于0时,str前面就会有24个1,也需要丢弃
			return str.substring(str.length() - 8);
		} else {
			//最后一个字节且大于0的时的情况
			return MyStringUtils.frontFill(str, lastByteLength, '0');
		}
	}
}
