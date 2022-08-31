package com.jstfs.practice.designpattern.creational.prototype.deep;

import com.jstfs.practice.designpattern.creational.prototype.Prototype;

/**
 * 需要克隆自身的具体产品
 * 
 * @createBy	落叶
 * @createTime 	2020年5月24日 下午8:17:48
 */
public class ConcreteProduct implements Prototype {
	private int intProp = -1;
	private String strProp = "NULL";
	private InnerRef refProp = new InnerRef();

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
		ConcreteProduct clone = (ConcreteProduct) super.clone();
		clone.setRefProp((InnerRef)(refProp.cloneSelf()));
		return clone;
	}
	
	class InnerRef implements Prototype {
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

		@Override
		public Object cloneSelf() throws CloneNotSupportedException {
			return super.clone();
		}
	}
}
