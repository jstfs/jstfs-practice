package com.jstfs.practice.datastructure.tree.binary.huffman;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * 文件解压
 * 
 * @createBy	落叶
 * @createTime	2022年10月18日 下午11:41:00
 */
public class FileUnCompression {

	public static void main(String[] args) {
		umCompression("d:/huffman.zip", "d:/source.png");
		System.out.println("文件解压完成!");
	}
	
	/**
	 * 文件解压
	 * 
	 * @param srcFilePath	原文件路径
	 * @param destFilePath	目标文件路径
	 */
	@SuppressWarnings("unchecked")
	public static void umCompression(String srcFilePath, String destFilePath) {
		InputStream is = null;
		ObjectInputStream ois = null;
		OutputStream os = null;
		
		try {
			is = new FileInputStream(srcFilePath);
			ois = new ObjectInputStream(is);
			os = new FileOutputStream(destFilePath);
			
			byte[] huffmanBytes = (byte[]) ois.readObject();
			Map<String, Byte> reverseHuffmanCodeMap = (Map<String, Byte>) ois.readObject();
			Integer lastByteLength = (Integer) ois.readObject();
			
			byte[] sourceBytes = HuffmanTreeAndCode.umCompression(huffmanBytes, reverseHuffmanCodeMap, lastByteLength);
			os.write(sourceBytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				ois.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
