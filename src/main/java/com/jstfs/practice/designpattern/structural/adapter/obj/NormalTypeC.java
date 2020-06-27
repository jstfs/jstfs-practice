package com.jstfs.practice.designpattern.structural.adapter.obj;

import com.jstfs.practice.designpattern.structural.adapter.obj.target.ITypeC;

/**
 * 正常的实现了Type-C接口的类
 * 
 * 假如有一台拥有Type-C接口的集线器或者路由器,
 * 那么我们只需要找一根具有Type-C接口的网线,就可以将只具有Type-C网口的电脑接入互联网.
 * 而该类,就是这根网线,但是实际情况中是没有这种集线器或路由器的,所以这种网线也就不存在
 * 此处只是为了模拟不需要适配的情况下,而增加该类.
 * 
 * @createBy jstfs
 * @createTime 2020年6月14日 下午2:10:08
 */
public class NormalTypeC implements ITypeC {

	@Override
	public void sendData(String data) {
		System.out.println(data);
	}
}
