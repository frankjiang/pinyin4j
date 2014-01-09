/*
 * Copyright (c) 2011, 2020, Frank Jiang and/or its affiliates. All rights
 * reserved.
 * PinyinUtils.java is built in 2013-5-8.
 */
package com.frank.pinyin;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * PinyinUtils is a utilities collection for works about pinyin. This is an easy
 * using interface for recalling Pinyin4J framework.
 * <p>
 * </p>
 * 
 * @@author <a href="mailto:jiangfan0576@@gmail.com">Frank Jiang</a>
 * @@version 1.0.0
 */
public class PinyinUtils
{
	/**
	 * Returns the full pinyin string of the specified string.
	 * 
	 * @@param s
	 *            the specified string
	 * @@return pinyin for s
	 */
	public static String getPinYin(String s)
	{
		char[] t1 = null;
		t1 = s.toCharArray();
		String[] t2 = new String[t1.length];
		// 设置汉字拼音输出的格式
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		String t4 = "";
		int t0 = t1.length;
		try
		{
			for (int i = 0; i < t0; i++)
				// check if it is Chinese letters
				if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+"))
				{
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// store
																			// the
																			// pinyins
																			// to
																			// t2
					t4 += t2[0];// combine the first pronunciation to the end of
								// t4
				}
				else
					// if not, combine the letter behind t4
					t4 += Character.toString(t1[i]);
		}
		catch (BadHanyuPinyinOutputFormatCombination e)
		{
			e.printStackTrace();
		}
		return t4;
	}

	/**
	 * Get the first pinyin letters of the specified string.
	 * 
	 * @@param s
	 *            the specified string
	 * @@return the first pinyin letters
	 */
	public static String getPinYinHeadChar(String s)
	{
		String convert = "";
		for (int j = 0; j < s.length(); j++)
		{
			char word = s.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null)
				convert += pinyinArray[0].charAt(0);
			else
				convert += word;
		}
		return convert;
	}
}
