package com.jstfs.practice.designpattern.structural.adapter.obj;

import com.jstfs.practice.designpattern.structural.adapter.obj.adaptee.RJ45Wire;
import com.jstfs.practice.designpattern.structural.adapter.obj.adapter.TypeCToRJ45Adaptor;
import com.jstfs.practice.designpattern.structural.adapter.obj.target.ITypeC;

/**
 * 对象的适配器模式
 * 
 * 与装饰模式相比,适配器更倾向于接口的替换
 * 
 * JDK中:
 * 		InputStreamReader
 * 
 * @createBy jstfs
 * @createTime 2020年6月13日 下午1:42:03
 */
public class Client {
	public static void main(String[] args) {
		System.out.println("==============对象的适配器模式-start===============");
		ITypeC typec = new NormalTypeC();
		typec.sendData("这是从Type-C接口发送的数据");
		
		System.out.println("-----------------------------------------------");
		
		typec = new TypeCToRJ45Adaptor(new RJ45Wire());
		typec.sendData("这是从Type-C接口发送的数据");
		System.out.println("==============对象的适配器模式-end===============");
	}
}
