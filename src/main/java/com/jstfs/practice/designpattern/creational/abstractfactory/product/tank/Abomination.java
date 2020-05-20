package com.jstfs.practice.designpattern.creational.abstractfactory.product.tank;

/**
 * 不死族肉盾(憎恶)
 *
 * @createBy jstfs
 * @createTime 2018-10-25 上午10:36:03
 */
public class Abomination implements ITank {
	@Override
	public int getBloodVolume() {
		return 1175;
	}
}