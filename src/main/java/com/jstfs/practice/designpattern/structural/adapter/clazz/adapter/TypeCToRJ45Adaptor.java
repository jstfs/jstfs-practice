package com.jstfs.practice.designpattern.structural.adapter.clazz.adapter;

import com.jstfs.practice.designpattern.structural.adapter.clazz.adaptee.RJ45Wire;
import com.jstfs.practice.designpattern.structural.adapter.clazz.target.ITypeC;

/**
 * TypeC接口转RJ45接口的适配器
 * 
 * 类的适配器
 *
 * @createBy jstfs
 * @createTime 2020年6月11日 下午3:14:15
 */
public class TypeCToRJ45Adaptor extends RJ45Wire implements ITypeC {
	
	@Override
	public void sendData(String data) {
		System.out.println(data);
		String _data = process(data);
		super.sendData(_data);
	}
	
	private String process(String originData) {
		System.out.println("接口转换中...");
		return "这是从RJ45接口发送的数据";
	}
}
