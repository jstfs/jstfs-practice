package com.jstfs.practice.designpattern.creational.factorymethod.product;

/**
 * @createBy jstfs
 * @createTime 2018-10-25 上午10:55:38
 */
public class Apple implements IFruit {
	private int treeAge;	//树龄
	
	@Override
	public void grow() {
		System.out.println("Apple is growing...");
	}

	@Override
	public void harvest() {
		System.out.println("Apple has been harvested.");
	}

	@Override
	public void plant() {
		System.out.println("Apple has been planted.");
	}

	public int getTreeAge() {
		return treeAge;
	}

	public void setTreeAge(int treeAge) {
		this.treeAge = treeAge;
	}
}