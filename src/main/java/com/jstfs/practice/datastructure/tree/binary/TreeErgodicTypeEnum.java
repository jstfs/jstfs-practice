package com.jstfs.practice.datastructure.tree.binary;

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
