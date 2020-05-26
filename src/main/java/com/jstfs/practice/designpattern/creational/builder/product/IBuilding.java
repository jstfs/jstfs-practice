package com.jstfs.practice.designpattern.creational.builder.product;

/**
 * 建筑物接口
 *
 * @createBy jstfs
 * @createTime 2020年5月21日 下午1:26:25
 */
public interface IBuilding {
	String introduce();
	
	String getBuildingName();
	void setBuildingName(String buildingName);
	
	String getBluePrint();
	void setBluePrint(String bluePrint);

	int getFoundationDepth();
	void setFoundationDepth(int foundationDepth);

	String getMaterialQuality();
	void setMaterialQuality(String materialQuality);

	int getStoreys();
	void setStoreys(int storeys);

	int getWorkerCount();
	void setWorkerCount(int workerCount);

	String getAuthorName();
	void setAuthorName(String authorName);

	boolean isOpenning();
	void setOpenning(boolean openning);
}