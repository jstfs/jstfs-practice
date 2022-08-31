package com.jstfs.practice.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @createBy	落叶
 * @createTime 	2020年7月30日 下午7:36:29
 */
public class RegularTest {
	private static String ENTER = "\n";
	public static void main(String[] args) {
		String html = 
				"<p>这是一个p标签</p>" + ENTER + 
				"<img src=\"001.jpg\"></img>" + ENTER +
				"<a href=\"http://www.baidu.com\">百度一下</a>" + 
				"这是标签之间文本内容" + ENTER + 
				"<abc xxx='123'></abc>" + 
				"<img src='002'>xxx</img>" + "<a>这是个空的超链接</a>"
				;
		String expression = "<img[^>]*>[^<]*</img>|<a[^>]*>[^<]*</a>";
		Pattern p = Pattern.compile(expression, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher m = p.matcher(html);
		while(m.find()) {
			System.out.println(m.group());
			System.out.println((m.start()) + "~" + (m.end()));
		}
	}
}
