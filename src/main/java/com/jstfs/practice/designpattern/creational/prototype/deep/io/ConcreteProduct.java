package com.jstfs.practice.designpattern.creational.prototype.deep.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 需要克隆自身的具体产品
 * 
 * @createBy jstfs
 * @createTime 2020年5月24日 下午8:17:48
 */
public class ConcreteProduct implements Serializable {
	private static final long serialVersionUID = -425031115519733129L;

	private transient int intProp = -1;
	private transient String strProp = "NULL";
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
	
	public Object cloneSelf() {
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(this);	//写入流
			
			bis = new ByteArrayInputStream(bos.toByteArray());
			ois = new ObjectInputStream(bis);
			return ois.readObject();	//从流读出
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if(oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					
				}
			}
			if(ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class InnerRef implements Serializable {
		private static final long serialVersionUID = -6224630785659907024L;
		
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
