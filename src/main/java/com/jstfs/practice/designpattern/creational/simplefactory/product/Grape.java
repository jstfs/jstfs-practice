package com.jstfs.practice.designpattern.creational.simplefactory.product;

/**
 * @createBy jstfs
 * @createTime 2018-10-25 上午10:54:38
 */
public class Grape implements IFruit {
	private boolean seedless;	//是否无籽

	@Override
	public void plant() {
		System.out.println("Grape has been planted.");
	}

	@Override
	public void grow() {
		System.out.println("Grape is growing...");
	}

	@Override
	public void harvest() {
		System.out.println("Grape has been harvested.");
	}
	
	public boolean getSeedless() {
		return seedless;
	}

	public void setSeedless(boolean seedless) {
		this.seedless = seedless;
	}
}