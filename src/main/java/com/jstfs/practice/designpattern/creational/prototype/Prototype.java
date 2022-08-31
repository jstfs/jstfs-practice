package com.jstfs.practice.designpattern.creational.prototype;
/**
 * 个人理解:
 * 	1, 实现Cloneable接口,这样需要支持克隆的具体产品类才可以调用父类Object的clone()方法
 * 	2, 接口中声明一个克隆方法,作为需要子类去实现的"提醒",子类的实现完全可以使用Object的super.clone()方法
 * 	3, 不要使用clone()作为方法名,不然可能会和Object类的clone()方法签名冲突
 *	
 * @createBy	落叶
 * @createTime 	2020年5月25日 下午10:33:24
 */
public interface Prototype extends Cloneable {
	public Object cloneSelf() throws CloneNotSupportedException;
}
