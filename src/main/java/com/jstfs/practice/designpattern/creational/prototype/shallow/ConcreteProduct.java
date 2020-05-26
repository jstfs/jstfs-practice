package com.jstfs.practice.designpattern.creational.prototype.shallow;

import com.jstfs.practice.designpattern.creational.prototype.Prototype;

/**
 * 需要支持克隆自身的具体产品
 * 
 * @createBy jstfs
 * @createTime 2020年5月24日 下午8:17:48
 */
public class ConcreteProduct implements Prototype {
	private int intProp = -1;
	private String strProp = "NULL";
	InnerRef refProp = new InnerRef();
	
	public int getIntProp() {
		return intProp;
	}
	public void setIntProp(int intProp) {
		this.intProp = intProp;
	}

	public String getStrProp() {
		return strProp;
	}
	public void setStrProp(String strProp) {
		this.strProp = strProp;
	}

	public InnerRef getRefProp() {
		return refProp;
	}
	public void setRefProp(InnerRef refProp) {
		this.refProp = refProp;
	}
	
	@Override
	public Object cloneSelf() throws CloneNotSupportedException {
		return super.clone();
	}
	
	class InnerRef {
		public int intProp = -1;
		public String strProp = "NULL";
		
		public int getIntProp() {
			return intProp;
		}
		public void setIntProp(int intProp) {
			this.intProp = intProp;
		}
		
		public String getStrProp() {
			return strProp;
		}
		public void setStrProp(String strProp) {
			this.strProp = strProp;
		}
	}
}
