package com.jstfs.practice.datastructure.tree.binary.huffman;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * 文件压缩
 * 
 * @createBy	落叶
 * @createTime	2022年10月18日 下午9:06:16
 */
public class FileCompression {

	public static void main(String[] args) {
		compression("d:/1.png", "d:/huffman.zip");
		System.out.println("文件压缩完成!");
	}
	
	/**
	 * 文件压缩
	 * 
	 * @param srcFilePath	原文件路径
	 * @param destFilePath	目标文件路径
	 */
	public static void compression(String srcFilePath, String destFilePath) {
		InputStream is = null;
		OutputStream os = null;
		ObjectOutputStream oos = null;
		
		try {
			is = new FileInputStream(srcFilePath);
			byte[] bytes = new byte[is.available()];
			is.read(bytes);
			
			byte[] huffmanBytes = HuffmanTreeAndCode.compression(bytes);
			
			os = new FileOutputStream(destFilePath);
			oos = new ObjectOutputStream(os);
			
			//写入压缩后的文件内容
			oos.writeObject(huffmanBytes);
			//写入解压时要用到的反向赫夫曼编码表
			oos.writeObject(HuffmanTreeAndCode.reverseHuffmanCodeMap);
			//写入
			oos.writeObject(HuffmanTreeAndCode.lastByteLength);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				os.close();
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
