package com.jstfs.practice.designpattern.structural.decorator;
/**
 * 具体的装饰者(超人),将动物的移动功能增强成使用飞行的方式来移动
 * 
 * @createBy jstfs
 * @createTime 2020年6月20日 下午5:50:21
 */
public class AnimalCanFlyDecorator extends AbstractDecorator {
	public AnimalCanFlyDecorator(IAnimal animal) {
		super(animal);
	}
	
	private void fly() {
		System.out.println(getName() + "可以在天空飞翔");
	}

	@Override
	public void move() {
		getAnimal().move();
		fly();
	}

	@Override
	public String getName() {
		return "超人";
	}
}
