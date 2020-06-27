package com.jstfs.practice.designpattern.structural.adapter.clazz.adaptee;

/**
 * 实现了RJ45接口的普通网线
 * 
 * @createBy jstfs
 * @createTime 2020年6月11日 下午3:12:02
 */
public class RJ45Wire implements IRJ45 {
	
	@Override
	public void sendData(String data) {
		System.out.println(data);
	}
}
