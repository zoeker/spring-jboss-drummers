package com.moderndrummer.enums;
/***
 * 
 * @author conpem 2015-08-03
 *
 */

public enum GraphicType {

	BLOG_IMAGE(1,"BlogImage");
	
	private int value;
	private String graphicName;
	
	 public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return graphicName;
	}

	public void setName(String name) {
		this.graphicName = name;
	}

	GraphicType(int val,String name){
		value = val;
		this.graphicName = name;
	}
}
