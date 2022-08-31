package com.jstfs.practice.designpattern.creational.abstractfactory.product.farmer;

/**
 * 不死族农民(侍僧)
 *
 * @createBy	落叶
 * @createTime 	2018-10-25 上午10:34:58
 */
public class Acolyte implements IFarmer {
	@Override
	public void build() {
		System.out.println("The damned stand ready!");
	}
}