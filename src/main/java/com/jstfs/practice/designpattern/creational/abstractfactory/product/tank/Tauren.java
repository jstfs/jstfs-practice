package com.jstfs.practice.designpattern.creational.abstractfactory.product.tank;

/**
 * 兽族肉盾(牛头人)
 *
 * @createBy jstfs
 * @createTime 2018-10-25 上午10:36:26
 */
public class Tauren implements ITank {

	@Override
	public int getBloodVolume() {
		return 1300;
	}
}
