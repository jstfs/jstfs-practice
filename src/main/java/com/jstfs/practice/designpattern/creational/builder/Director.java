package com.jstfs.practice.designpattern.creational.builder;
/**
 * 建造者模式的指挥者
 * 
 * @createBy jstfs
 * @createTime 2020年5月23日 下午5:51:18
 */
import com.jstfs.practice.designpattern.creational.builder.builder.AbstractBuilder;

public class Director {
	private AbstractBuilder builder;
	
	public AbstractBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(AbstractBuilder builder) {
		this.builder = builder;
	}

	/**
	 * 建造动作
	 */
	public void construct() {
		System.out.println("============开始建造...===============");
		System.out.print("第1步:");
		builder.drawBluePrint();
		System.out.print("第2步:");
		builder.hireWorkers();
		System.out.print("第3步:");
		builder.prepareMaterials();
		System.out.print("第4步:");
		builder.layFoundation();
		System.out.print("第5步:");
		builder.workOnFloor();
		System.out.print("第6步:");
		builder.complete();
	}
}
