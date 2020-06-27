package com.jstfs.practice.designpattern.structural.decorator;
/**
 * 人类
 * 
 * @createBy jstfs
 * @createTime 2020年6月20日 下午5:23:56
 */
public class Human implements IAnimal {

	@Override
	public void move() {
		System.out.println(getName() + "的移动方式是直立行走");
	}

	@Override
	public String getName() {
		return "人类";
	}

}
