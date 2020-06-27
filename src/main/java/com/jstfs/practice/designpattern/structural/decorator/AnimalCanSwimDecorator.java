package com.jstfs.practice.designpattern.structural.decorator;
/**
 * 具体的装饰者(海王),将动物的移动功能增强成使用游泳的方式来移动
 * 
 * @createBy jstfs
 * @createTime 2020年6月20日 下午8:15:49
 */
public class AnimalCanSwimDecorator extends AbstractDecorator {
	public AnimalCanSwimDecorator(IAnimal animal) {
		super(animal);
	}

	private void swim() {
		System.out.println(getName() + "可以在水里自由战斗");
	}
	
	@Override
	public void move() {
		getAnimal().move();
		swim();
	}

	@Override
	public String getName() {
		return "海王";
	}
}
