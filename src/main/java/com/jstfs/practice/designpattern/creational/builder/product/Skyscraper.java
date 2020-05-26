package com.jstfs.practice.designpattern.creational.builder.product;

/**
 * 摩天大楼
 * Skyscraper:[ˈskaɪskreɪpər]
 * 
 * @createBy jstfs
 * @createTime 2020年5月21日 下午1:27:47
 */
public class Skyscraper extends AbstractBuilding {
	public Skyscraper(String buildingName) {
		setBuildingName(buildingName);
	}
	
	private String speech;		//剪彩致辞

	public String getSpeech() {
		return speech;
	}

	/**
	 * 剪彩
	 */
	public void cutRibbon(String speech) {
		this.speech = speech;
		setOpenning(true);
	}
	
	@Override
	public String introduce() {
		StringBuilder intr = new StringBuilder();
		if(isOpenning()) {
			intr.append("\r\n").append(getBuildingName()).append("简介:\r\n");
			intr.append("    摩天大楼一般指非常高的高楼大厦,比喻大楼似乎有摩天轮那么高.根据中国相关规定,建筑高度超过100m时,不论住宅及公共建筑均为超高层建筑,而世界超高层建筑的标准是300米以上.\r\n\r\n");
			intr.append("    建筑名:").append(getBuildingName()).append("\r\n");
			intr.append("    地基深度:").append(getFoundationDepth()).append("米\r\n");
			intr.append("    主要用材:").append(getMaterialQuality()).append("\r\n");
			intr.append("    地面层数:").append(getStoreys()).append("层\r\n");
			intr.append("    工人总数:").append(getWorkerCount()).append("人\r\n");
			intr.append("    作者:").append(getAuthorName()).append("\r\n");
			intr.append("    剪彩致辞:").append("\r\n");
			intr.append("        ").append(getSpeech());
		} else {
			intr.append("施工中...");
		}
		return intr.toString();
	}
}
