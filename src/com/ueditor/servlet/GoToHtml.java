package com.ueditor.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;

import com.ueditor.servlet.GoToHtml;

public class GoToHtml {

	/**
	 * 
	 * @param page
	 *            存放静态页面的本地文件路径（c,d,e,f,g）
	 * @param url_addr
	 *            所要生成的静态页的URL地址(http://)
	 * @return
	 */
	public boolean PrintPage(String page, String url_addr) {
		System.out.println("page" + page);
		String newPage = "";
		// 判断输入的本地路径是否是以。jsp结尾的
		if (page.endsWith(".html")) {
			System.out.println("this is end with xxx.html");
			int bias = page.lastIndexOf("/");// bias(斜线的意思)获取最后一个斜线的位置
			System.out.println("the last / at :" + bias);
			newPage = page.substring(0, bias);
			System.out.println("newPage:" + newPage);
		}
		// 如果文件夹不存在就创建一个
		File ff = new File(newPage);
		ff.mkdirs();
		URL url;
		String rLine = null;
		PrintWriter fileOut = null;
		InputStream ins = null;
		try {
			url = new URL(url_addr);
			System.out.println(url + ".......");
			ins = url.openStream();
			BufferedReader bReader = new BufferedReader(new InputStreamReader(
					ins, "utf-8"));// 获取编码为gb2312的文件
			FileOutputStream out = new FileOutputStream(page);
			OutputStreamWriter writer = new OutputStreamWriter(out, "utf-8");
			fileOut = new PrintWriter(writer);
			// 循环取取数据,并写入目标文件中
			while ((rLine = bReader.readLine()) != null) {
				String tmp_rLine = rLine;
				System.out.println(tmp_rLine);
				int str_len = tmp_rLine.length();
				if (str_len > 0) {
					fileOut.println(tmp_rLine);
					fileOut.flush();
				}
				tmp_rLine = null;
			}
			url = null;
			return true;
		} catch (IOException e) {
			System.out.println("error: " + e.getMessage());
			e.printStackTrace();
			return false;
		} catch (Exception es) {
			System.out.println(es.getMessage());
			return false;
		} finally {// 关闭资源
			fileOut.close();
			try {
				ins.close();
			} catch (IOException ex) {
				// 关闭输入流出错
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		GoToHtml gth = new GoToHtml();
		// 有参数的怎么解决？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
		String urlString = "http://localhost:8080/testXheditor/index.jsp";
		String path = System.getProperty("user.dir");
		System.out.println("--------"+path+"---------");
		// 取服务器时间作为html的文件名
		gth.PrintPage(path+"/WebRoot/index1.html", urlString);
		// 生成的文件信息要放入数据库中，数据库的表如何设计？？？？？？？？？？？？？
	}

}
