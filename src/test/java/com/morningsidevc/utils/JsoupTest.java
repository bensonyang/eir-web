package com.morningsidevc.utils;

import com.alibaba.fastjson.JSON;
import com.morningsidevc.crawler.HTMLBean;
import com.morningsidevc.crawler.HTMLCrawlerUtils;

public class JsoupTest {

	public static void main(String ...s){
		try{
			//HtmlToPlainText.main(new String[]{"http://jvmplus.duapp.com/blog/view/B143644913"});
			HTMLBean html = HTMLCrawlerUtils.get("jvmplus.duapp.com/blog/view/B143644913");
			System.out.println(JSON.toJSON(html));
			System.out.println("done!");
		}catch(Exception e){
			
		}
		
	}
}
