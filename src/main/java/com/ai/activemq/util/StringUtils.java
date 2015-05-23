package com.ai.activemq.util;

import java.util.Random;

public class StringUtils extends org.apache.commons.lang.StringUtils {
	
	
	public static final char[] BASE_CHARS = {'0','1','2','3','4','5','6','7','8','9',
		'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q',
		'r','s','t','u','v','w','x','y','z',
		'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q',
		'R','S','T','U','V','W','X','Y','Z'};
	
	private static Random random = new Random();
	
	/**
	 * 生成制定位数的随机 数字/字母 码
	 * @param length
	 * @return
	 */
	public static String genRandamString(int length) {
		
		int charLength = BASE_CHARS.length;
		StringBuffer buffer = new StringBuffer();
		
        int charIndex; 
		for (int i=0; i<length; i++ ) {
			charIndex = random.nextInt(charLength); 
			buffer.append(BASE_CHARS[charIndex]);
		}
		
		return String.valueOf(buffer);
	}
	
	public static void main(String[] args) {
		System.out.println(genRandamString(5));
		System.out.println(genRandamString(5));
		System.out.println(genRandamString(5));
		
		System.out.println(genRandamString(15));
		System.out.println(genRandamString(15));
		System.out.println(genRandamString(15));
	}
}
