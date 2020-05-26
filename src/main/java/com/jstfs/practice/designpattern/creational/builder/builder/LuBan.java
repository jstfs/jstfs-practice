package com.jstfs.practice.designpattern.creational.builder.builder;

import com.jstfs.practice.designpattern.creational.builder.product.QuadrangleDwelling;

/**
 * 鲁班
 *
 * @createBy jstfs
 * @createTime 2020年5月21日 下午1:31:57
 */
public class LuBan extends AbstractBuilder {
	public LuBan() {
		building = new QuadrangleDwelling("北京文煜宅");
		building.setAuthorName(getMyName());
	}

	@Override
	public void drawBluePrint() {
		System.out.println("连夜挑灯绘制草图");
		building.setBluePrint("北京文煜宅设计图.jpg");
	}

	@Override
	public void hireWorkers() {
		System.out.println("去各家各户找一些壮丁");
		building.setWorkerCount(30);
	}

	@Override
	public void prepareMaterials() {
		System.out.println("去后山砍伐树木");
		building.setMaterialQuality("木头");
	}

	@Override
	public void layFoundation() {
		System.out.println("在土壤中加一些烧土制品的碎渣,用木头夯实地面");
		building.setFoundationDepth(2);
	}

	@Override
	public void workOnFloor() {
		System.out.println("中国榫卯,惊艳世界.各种精巧结构互相\"勾搭\"...");
		building.setStoreys(1);
	}
	
	@Override
	public void complete() {
		System.out.println("经过七七四十九天的辛苦劳作,四合院终于落成!");
		((QuadrangleDwelling)building).pasteCouplets("迁居新逢吉祥日", "安宅正遇如意春", "紫微高照");
		System.out.println(building.introduce());
	}

	@Override
	public String getMyName() {
		return "鲁班";
	}
}