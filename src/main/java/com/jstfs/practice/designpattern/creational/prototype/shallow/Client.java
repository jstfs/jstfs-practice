package com.jstfs.practice.designpattern.creational.prototype.shallow;

import com.jstfs.practice.designpattern.creational.prototype.shallow.ConcreteProduct.InnerRef;

/**
 * 原型模式-浅克隆
 * 
 * 对于引用型属性只复制引用,不复制引用指向的对象
 * 
 * @createBy jstfs
 * @createTime 2020年5月24日 下午3:00:05
 */
public class Client implements Cloneable {
	public static void main(String[] args) throws CloneNotSupportedException {
		ConcreteProduct origin = new ConcreteProduct();
		origin.setIntProp(11);
		origin.setStrProp("Hello");
		InnerRef ref = origin.new InnerRef();
		ref.setIntProp(22);
		ref.setStrProp("World");
		origin.setRefProp(ref);
		
		System.out.println("origin.intProp:" + origin.getIntProp());
		System.out.println("origin.strProp:" + origin.getStrProp());
		System.out.println("origin.refProp.intProp:" + origin.getRefProp().getIntProp());
		System.out.println("origin.refProp.strProp:" + origin.getRefProp().getStrProp());
		
		ConcreteProduct clone = (ConcreteProduct) origin.cloneSelf();
		
		System.out.println("----------------------------------------------------");
		System.out.println("clone.intProp:" + clone.getIntProp());
		System.out.println("clone.strProp:" + clone.getStrProp());
		System.out.println("clone.refProp.intProp:" + clone.getRefProp().getIntProp());
		System.out.println("clone.refProp.strProp:" + clone.getRefProp().getStrProp());
		
		origin.getRefProp().setIntProp(99);
		origin.getRefProp().setStrProp("Updated");
		System.out.println("============ updated origin's refProp =============");
		
		System.out.println("clone.intProp:" + clone.getIntProp());
		System.out.println("clone.strProp:" + clone.getStrProp());
		System.out.println("clone.refProp.intProp:" + clone.getRefProp().getIntProp());
		System.out.println("clone.refProp.strProp:" + clone.getRefProp().getStrProp());
    }
}
