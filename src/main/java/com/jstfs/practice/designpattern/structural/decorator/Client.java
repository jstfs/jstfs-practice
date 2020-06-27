package com.jstfs.practice.designpattern.structural.decorator;
/**
 * 装饰模式
 * 
 * 	使用场景:
 * 		替代继承,增强或者扩展一个类的某些功能
 * 	
 * 	优点:
 * 		由于继承是静态的,编译完成后类的功能就固定了
 * 		如果已有的继承树比较深,想在树的中间部分的类中来扩展功能,就会影响其所有的子类
 * 		而装饰模式是在运行时动态的给对象添加功能,在不需要的时候也可以动态的撤销功能
 * 
 * @createBy jstfs
 * @createTime 2020年6月20日 下午7:09:17
 */
public class Client {

	public static void main(String[] args) {
		System.out.println("=====没有被装饰的对象的行为方式===============");
		IAnimal human = new Human();
		human.move();
		
		System.out.println();
		
		//超人
		System.out.println("=====被装饰的对象的行为方式===============");
		IAnimal superman = new AnimalCanFlyDecorator(human);
		superman.move();
		
		System.out.println();
		
		//海王
		System.out.println("=====被二次装饰的对象的行为方式===============");
		IAnimal aquaman = new AnimalCanSwimDecorator(superman);
		aquaman.move();
	}
}
