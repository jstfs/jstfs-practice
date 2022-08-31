package com.jstfs.practice.designpattern.creational.builder.product;
/**
 * @createBy	落叶
 * @createTime 	2020年5月23日 上午9:41:32
 */
public abstract class AbstractBuilding implements IBuilding {
	protected String buildingName;		//名字
	protected String bluePrint;			//图纸
	protected int foundationDepth;		//地基深度
	protected String materialQuality;	//材质
	protected int storeys;				//地面上的层数
	protected int workerCount;			//工人总数
	protected String authorName;		//建造者姓名
	protected boolean openning = false;	//是否投入使用
	
	@Override
	public String getBuildingName() {
		return buildingName;
	}
	@Override
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
	@Override
	public String getBluePrint() {
		return bluePrint;
	}
	@Override
	public void setBluePrint(String bluePrint) {
		this.bluePrint = bluePrint;
	}

	@Override
	public int getFoundationDepth() {
		return foundationDepth;
	}
	@Override
	public void setFoundationDepth(int foundationDepth) {
		this.foundationDepth = foundationDepth;
	}

	@Override
	public String getMaterialQuality() {
		return materialQuality;
	}
	@Override
	public void setMaterialQuality(String materialQuality) {
		this.materialQuality = materialQuality;
	}

	@Override
	public int getStoreys() {
		return storeys;
	}
	@Override
	public void setStoreys(int storeys) {
		this.storeys = storeys;
	}
	
	@Override
	public int getWorkerCount() {
		return workerCount;
	}
	@Override
	public void setWorkerCount(int workerCount) {
		this.workerCount = workerCount;
	}
	
	@Override
	public String getAuthorName() {
		return authorName;
	}
	@Override
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	@Override
	public boolean isOpenning() {
		return openning;
	}
	@Override
	public void setOpenning(boolean openning) {
		this.openning = openning;
	}
}
