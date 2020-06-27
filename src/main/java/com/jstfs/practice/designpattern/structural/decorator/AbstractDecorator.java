package com.jstfs.practice.designpattern.structural.decorator;
/**
 * 抽象的装饰者
 * 
 * @createBy jstfs
 * @createTime 2020年6月20日 下午5:31:35
 */
public abstract class AbstractDecorator implements IAnimal {
	private IAnimal animal;

	public AbstractDecorator(IAnimal animal) {
		this.animal = animal;
	}
	
	public IAnimal getAnimal() {
		return animal;
	}

	public void setAnimal(IAnimal animal) {
		this.animal = animal;
	}
}
