package com.jstfs.practice.designpattern.creational.prototype.deep.io;

import com.jstfs.practice.designpattern.creational.prototype.deep.io.ConcreteProduct.InnerRef;

/**
 * 原型模式-深克隆(使用串行化)
 * 
 * 使用串行化来深克隆,就需要被克隆的类以及其引用类型的成员变量的类都要实现Serializable接口
 * 对于一些不需要或者不能克隆的类型,比如Thread,Socket类型,可以使用transient关键字进行标识
 * 标识之后就不会序列化到流中,然后从流中读出时该变量的值默认是它所属数据类型的初始值.比如int类型就是0,String类型就是null.
 * 
 * @createBy	落叶
 * @createTime 	2020年5月24日 下午3:00:05
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
		System.out.println();
		System.out.println("============ updated origin's refProp =============");
		System.out.println();
		
		System.out.println("origin.intProp:" + origin.getIntProp());
		System.out.println("origin.strProp:" + origin.getStrProp());
		System.out.println("origin.refProp.intProp:" + origin.getRefProp().getIntProp());
		System.out.println("origin.refProp.strProp:" + origin.getRefProp().getStrProp());
		System.out.println("----------------------------------------------------");
		System.out.println("clone.intProp:" + clone.getIntProp());
		System.out.println("clone.strProp:" + clone.getStrProp());
		System.out.println("clone.refProp.intProp:" + clone.getRefProp().getIntProp());
		System.out.println("clone.refProp.strProp:" + clone.getRefProp().getStrProp());
    }
}
