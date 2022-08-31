package com.jstfs.practice.designpattern.creational.builder.product;

/**
 * 四合院
 * Quadrangle:[ˈkwɑːdræŋɡl],四方院子
 * Dwelling:[ˈdwelɪŋ],住宅住所
 * 
 * @createBy	落叶
 * @createTime 	2020年5月21日 下午1:29:54
 */
public class QuadrangleDwelling extends AbstractBuilding {
	public QuadrangleDwelling(String buildingName) {
		setBuildingName(buildingName);
	}
	
	//对联
	private String[] couplets = new String[3];

	/**
	 * 贴对联
	 */
	public void pasteCouplets(String firstLine, String secondLine, String middle) {
		couplets[0] = firstLine;
		couplets[1] = secondLine;
		couplets[2] = middle;
		setOpenning(true);
	}

	@Override
	public String introduce() {
		StringBuilder intr = new StringBuilder();
		if(isOpenning()) {
			intr.append("\r\n").append(getBuildingName()).append("简介:\r\n");
			intr.append("    四合院是一种中国传统高档合院式建筑.其格局为一个院子四面建有房屋,通常由正房、东西厢房和倒座房组成，从四面将庭院合围在中间.\r\n\r\n");
			intr.append("    建筑名:").append(getBuildingName()).append("\r\n");
			intr.append("    地基深度:").append(getFoundationDepth()).append("米\r\n");
			intr.append("    主要用材:").append(getMaterialQuality()).append("\r\n");
			intr.append("    地面层数:").append(getStoreys()).append("层\r\n");
			intr.append("    工人总数:").append(getWorkerCount()).append("人\r\n");
			intr.append("    作者:").append(getAuthorName()).append("\r\n");
			intr.append("    对联:").append("\r\n");
			intr.append("        上联:").append(couplets[0]).append("\r\n");
			intr.append("        下联:").append(couplets[1]).append("\r\n");
			intr.append("        横批:").append(couplets[2]);
		} else {
			intr.append("稍安勿躁...");
		}
		
		return intr.toString();
	}
}