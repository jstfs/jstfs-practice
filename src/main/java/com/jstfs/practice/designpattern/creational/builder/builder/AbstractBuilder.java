package com.jstfs.practice.designpattern.creational.builder.builder;

import com.jstfs.practice.designpattern.creational.builder.product.IBuilding;

/**
 * 建造者接口
 *
 * @createBy jstfs
 * @createTime 2020年5月21日 下午2:00:56
 */
public abstract class AbstractBuilder {
	protected IBuilding building;

	/**
	 * 画建筑图纸
	 */
	public abstract void drawBluePrint();
	
	/**
	 * 雇佣建筑工人
	 */
	public abstract void hireWorkers();
	
	/**
	 * 准备建筑材料
	 */
	public abstract void prepareMaterials();

	/**
	 * 打地基
	 */
	public abstract void layFoundation();
	
	/**
	 * 完成地面工作
	 */
	public abstract void workOnFloor();
	
	/**
	 * 建造结束
	 */
	public abstract void complete();
	
	/**
	 * 建造者的名字
	 */
	public abstract String getMyName();
}
