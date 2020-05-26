package com.jstfs.practice.designpattern.creational.builder;

import com.jstfs.practice.designpattern.creational.builder.builder.AbstractBuilder;
import com.jstfs.practice.designpattern.creational.builder.builder.Cscec1b;
import com.jstfs.practice.designpattern.creational.builder.builder.LuBan;

/**
 * 建造者模式
 * 
 * 使用场景:
 * 	1, 需要建造的对象比较复杂,比如内部的很多属性又都是一个独立的对象
 * 	2, 需要建造的对象的属性之间有依赖,初始化过程中需要有先后顺序
 * 
 * 与抽象工厂模式的区别:
 * 	抽象工厂模式创建的同族产品虽然之间也有关系,甚至客户端也会自行将它们组装在一起,但也可能不会
 * 	所以抽象工厂模式是在更具体的角度建造,而建造者模式是在更宏观的角度建造
 * 
 *
 * @createBy jstfs
 * @createTime 2020年5月19日 下午11:29:47
 */
public class Client {
	public static void main(String[] args) {
		AbstractBuilder luban = new LuBan();
		AbstractBuilder ccec1b = new Cscec1b();
		Director dirct = new Director();

		dirct.setBuilder(luban);
		dirct.construct();
		
		System.out.println("");
		
		dirct.setBuilder(ccec1b);
		dirct.construct();
	}
}