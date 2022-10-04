package com.jstfs.practice.datastructure.tree.binary;

import java.util.ArrayList;
import java.util.List;

import com.jstfs.common.utils.MyArrayUtils;

/**
 * 二叉树的遍历方式枚举
 * 
 * @createBy	落叶
 * @createTime	2022年9月17日 上午12:04:10
 */
public enum TreeErgodicTypeEnum {

	ERGODIC_DLR("DLR", "前序遍历"),
	ERGODIC_LDR("LDR", "中序遍历"),
	ERGODIC_LRD("LRD", "后序遍历");
	
	TreeErgodicTypeEnum(String type, String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	private String type;
	private String desc;
	
	/**
	 * 获取所有type
	 */
	public List<String> getAllTypes() {
		TreeErgodicTypeEnum[] enumAry = TreeErgodicTypeEnum.values();
		List<String> types = new ArrayList<>();
		for(TreeErgodicTypeEnum ele : enumAry) {
			types.add(ele.getType());
		}
		return types;
	}
	
	/**
	 * 获取所有枚举元素的List
	 */
	public List<TreeErgodicTypeEnum> getEnumList() {
		return MyArrayUtils.toList(TreeErgodicTypeEnum.values());
	}
	
	/**
	 * 根据type获取枚举元素
	 */
	public TreeErgodicTypeEnum getByType(String type) {
		TreeErgodicTypeEnum[] enumAry = TreeErgodicTypeEnum.values();
		for(TreeErgodicTypeEnum ele : enumAry) {
			if(ele.getType().equals(type)) {
				return ele;
			}
		}
		return null;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
