package com.jstfs.practice.designpattern.structural.adapter.obj.adapter;

import com.jstfs.practice.designpattern.structural.adapter.obj.adaptee.IRJ45;
import com.jstfs.practice.designpattern.structural.adapter.obj.target.ITypeC;

/**
 * TypeC接口转RJ45接口的适配器
 * 
 * 对象的适配器
 *
 * @createBy jstfs
 * @createTime 2020年6月11日 下午3:14:15
 */
public class TypeCToRJ45Adaptor implements ITypeC {
	private IRJ45 rj45;
	
	public TypeCToRJ45Adaptor(IRJ45 rj45) {
		this.rj45 = rj45;
	}
	
	@Override
	public void sendData(String data) {
		System.out.println(data);
		String _data = process(data);
		rj45.sendData(_data);
	}
	
	private String process(String originData) {
		System.out.println("接口转换中...");
		return "这是从RJ45接口发送的数据";
	}

	public IRJ45 getRj45() {
		return rj45;
	}

	public void setRj45(IRJ45 rj45) {
		this.rj45 = rj45;
	}
}
