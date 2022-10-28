package com.jstfs.practice.datastructure.tree.binary.huffman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.jstfs.common.utils.MyCollectionUtils;
import com.jstfs.common.utils.MyStringUtils;
import com.jstfs.practice.datastructure.tree.binary.BinaryTree;
import com.jstfs.practice.datastructure.tree.binary.TreeNode;

/**
 * 赫夫曼树以及赫夫曼编码
 * 
 * 使用赫夫曼编码压缩文件的注意事项:
 * 		1, 如果文件本身已经被压缩过,那么压缩效果不会有明显变化
 * 		2, 如果文件内容中重复的数据不多,那么压缩效果也不明显
 * 
 * @createBy	落叶
 * @createTime	2022年10月7日 下午5:27:05
 */
public class HuffmanTreeAndCode {
	//二进制字符串的最后一段的实际长度
	public static int lastByteLength = 0;
	//赫夫曼编码表
	public static Map<Byte, String> huffmanCodeMap = MyCollectionUtils.newMap(Byte.class, String.class);
	//反向赫夫曼编码表
	public static Map<String, Byte> reverseHuffmanCodeMap = MyCollectionUtils.newMap(String.class, Byte.class);
	
	public static void main(String[] args) {
		String source = "This is a programming exercise about huffman coding!";
		
		//压缩
		byte[] huffmanBtyes = compression(source.getBytes());
		//解压
		byte[] sourceBytes = umCompression(huffmanBtyes, reverseHuffmanCodeMap, lastByteLength);
		
		if(source.equals(new String(sourceBytes))) {
			System.out.println("无损还原");
		} else {
			System.out.println("还原不正确,解压后的字符串:" + new String(sourceBytes));
		}
	}
	
	/**
	 * 压缩
	 */
	public static byte[] compression(byte[] sourceBytes) {
		//生成字节权重表
		Map<Byte, Integer> weightMap = createWeightMap(sourceBytes);
		
		//构建赫夫曼树
		BinaryTree huffmanTree = createHuffmanTree(weightMap);
		
		//生成赫夫曼编码表
		huffmanCodeMap.clear();
		fillHuffmanCodeMap(huffmanTree.getRootNode(), new StringBuilder());
		//生成反向赫夫曼编码表
		reverseHuffmanCodeMap();
		
		//压缩
		lastByteLength = 0;
		return zip(sourceBytes);
	}
	
	/**
	 * 解压
	 */
	public static byte[] umCompression(byte[] huffmanBytes, Map<String, Byte> reverseHuffmanCodeMap, int lastByteLength) {
		StringBuilder codeSeq = new StringBuilder();
		for (int i = 0; i < huffmanBytes.length; i++) {
			if(i == huffmanBytes.length - 1) {
				codeSeq.append(byteToBitString(huffmanBytes[i], true, lastByteLength));
			} else {
				codeSeq.append(byteToBitString(huffmanBytes[i], false, lastByteLength));
			}
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
	 * 将原文本的每个字符解析成权重表
	 */
	public static Map<Byte, Integer> createWeightMap(byte[] sourceBytes) {
		Map<Byte, Integer> map = MyCollectionUtils.newMap(Byte.class, Integer.class);
		for(Byte b : sourceBytes) {
			if(map.containsKey(b)) {
				map.put(b, map.get(b) + 1);
			} else {
				map.put(b, 1);
			}
		}
		
		return map;
	}
	
	/**
	 * 根据字节权重表构建一棵赫夫曼树
	 */
	private static BinaryTree createHuffmanTree(Map<Byte, Integer> weightMap) {
		List<TreeNode> nodes = new ArrayList<>();
		
		for(Map.Entry<Byte, Integer> entry : weightMap.entrySet()) {
			TreeNode node = new TreeNode(entry.getKey());
			node.setWeight(entry.getValue());
			nodes.add(node);
		}
		
		while(nodes.size() > 1) {
			Collections.sort(nodes);
			
			TreeNode left = nodes.get(0);
			TreeNode right = nodes.get(1);
			TreeNode parent = new TreeNode();
			parent.setWeight(left.getWeight() + right.getWeight());
			parent.setLeftChild(left);
			parent.setRightChild(right);
			
			nodes.remove(left);
			nodes.remove(right);
			nodes.add(parent);
		}
		
		BinaryTree huffmanTree = new BinaryTree();
		huffmanTree.setRootNode(nodes.get(0));
		return huffmanTree;
	}
	
	/**
	 * 生成赫夫曼编码表
	 * 
	 * @param node		赫夫曼树的根节点
	 * @param preCode	父节点的赫夫曼编码(前一次递归的后的编码)
	 */
	private static void fillHuffmanCodeMap(TreeNode node, StringBuilder preCode) {
		if(node.getLeftChild() != null || node.getRightChild() != null) {
			//非叶子节点
			if(node.getLeftChild() != null) {
				StringBuilder code = new StringBuilder(preCode);
				//左子节点编码为0
				code.append("0");
				fillHuffmanCodeMap(node.getLeftChild(), code);
			}
			if(node.getRightChild() != null) {
				StringBuilder code = new StringBuilder(preCode);
				//右子节点编码为1
				code.append("1");
				fillHuffmanCodeMap(node.getRightChild(), code);
			}
		} else {
			//叶子节点
			huffmanCodeMap.put((Byte)(node.getData()), preCode.toString());
		}
	}
	
	/**
	 * 根据赫夫曼编码表将原字节数组压缩成新的字节数组
	 */
	private static byte[] zip(byte[] sourceBytes) {
		//根据赫夫曼编码表将原字节数组转成由赫夫曼编码组成的01序列
		StringBuilder codeSeq = new StringBuilder();
		for(byte b : sourceBytes) {
			codeSeq.append(huffmanCodeMap.get(b));
		}
		
		int len = (codeSeq.length() + 7) / 8;
		byte[] huffmanBtyes = new byte[len];
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
			huffmanBtyes[index++] = (byte)Integer.parseInt(byteStr, 2);
		}
		
		return huffmanBtyes;
	}
	
	/**
	 * 反向赫夫曼编码表
	 */
	private static void reverseHuffmanCodeMap() {
		for(Map.Entry<Byte, String> entry : huffmanCodeMap.entrySet()) {
			reverseHuffmanCodeMap.put(entry.getValue(), entry.getKey());
		}
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
	private static String byteToBitString(byte b, boolean isLastByte, int lastByteLength) {
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
			//最后一个字节且大于0时的情况
			return MyStringUtils.frontFill(str, lastByteLength, '0');
		}
	}
}
