package com.jstfs.practice.designpattern.creational.builder.builder;

import com.jstfs.practice.designpattern.creational.builder.product.Skyscraper;

/**
 * 中国建筑一局
 *
 * @createBy	落叶
 * @createTime 	2020年5月23日 上午9:27:59
 */
public class Cscec1b extends AbstractBuilder {
	public Cscec1b() {
		building = new Skyscraper("深圳平安国际金融中心");
		building.setAuthorName(getMyName());
	}

	@Override
	public void drawBluePrint() {
		System.out.println("催促CCDI(悉地国际有限公司)尽快完成设计图纸");
		building.setBluePrint("深圳平安国际金融中心设计图纸.jpg");
	}

	@Override
	public void hireWorkers() {
		System.out.println("部分工程外包给其他建筑公司");
		building.setWorkerCount(200);
	}

	@Override
	public void prepareMaterials() {
		System.out.println("联系长期合作的建材供应商");
		building.setMaterialQuality("纯钢");
	}

	@Override
	public void layFoundation() {
		System.out.println("2009年11月基坑开挖,8根钢骨柱,平均深度30米");
		building.setFoundationDepth(30);
	}

	@Override
	public void workOnFloor() {
		System.out.println("使用上万吨钢材的大型施工现场有条不紊的进行着...");
		building.setStoreys(115);
	}
	
	@Override
	public void complete() {
		System.out.println("经过几百人几年漫长的修建,终于在2016年6月竣工");
		((Skyscraper)building).cutRibbon("尊敬的各位领导,各位嘉宾,今天是个喜庆的日子...(此处省略1万字)");
		System.out.println(building.introduce());
	}

	@Override
	public String getMyName() {
		return "中国建筑一局";
	}
}
