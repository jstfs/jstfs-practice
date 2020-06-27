package com.jstfs.practice.designpattern.structural.adapter.obj.adaptee;

/**
 * RJ45类型的接口,将需要适配的类适配到的"目的地"
 * 
 * @createBy jstfs
 * @createTime 2020年6月14日 下午1:33:38
 */
public interface IRJ45 {
	/**
	 * 发送数据包到互联网
	 */
	public void sendData(String data);
}
