package com.appleframework.message.provider.utils;

import java.lang.Math;

public class ResourceKeyGenerator {
	
	public static String gen(String inputStr) {
		return "" + Math.abs(inputStr.hashCode());
	}
	
	/**
	 * 生成指定位数的随机码
	 * @param count
	 * @return
	 */
	public static String getRandomNum(int count) {
		String[] string = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		String msg = "";
		for (int i = 0; i < count; i++) {
			int temp = (int) (Math.random() * 10);
			String aString = string[temp];
			msg += aString;
		}
		return msg;
	}
	
	public static void main(String[] args) {
		System.out.println(getRandomNum(4));
		//System.out.println(Math.random());
	}

}
