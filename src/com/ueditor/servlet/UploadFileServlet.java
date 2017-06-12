package com.ueditor.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;

public class UploadFileServlet extends HttpServlet {
	
	private static String baseDir = "/upload/";//上传文件存储目录
	//private static String fileExt = "jpg,jpeg,bmp,gif,png";
	private static Long maxSize = 0l;

	// 0:不建目录 1:按天存入目录 2:按月存入目录 3:按扩展名存目录 建议使用按天存
	private static String dirType = "1";
	

	

	/**
	 * 上传文件数据处理过程
	 */
	@SuppressWarnings({"unchecked" })
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				response.setContentType("text/html; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");

				String err = "";
				String newFileName = "";

				DiskFileUpload upload = new DiskFileUpload();
				try {
					List<FileItem> items = upload.parseRequest(request);
					Map<String, Serializable> fields = new HashMap<String, Serializable>();
					Iterator<FileItem> iter = items.iterator();
					
					while (iter.hasNext()) {
						FileItem item = (FileItem) iter.next();
						if (item.isFormField())
							fields.put(item.getFieldName(), item.getString());
						else
							fields.put(item.getFieldName(), item);
					}
					
					/*获取表单的上传文件*/
					FileItem uploadFile = (FileItem)fields.get("filedata");
					
					/*获取文件上传路径名称*/
					String fileNameLong = uploadFile.getName();
								
					/*获取文件扩展名*/			
					String extensionName = fileNameLong.substring(fileNameLong.lastIndexOf(".") + 1);
					
					
					//  string aLastName = filename.Substring(filename.LastIndexOf(".") + 1, (filename.Length - filename.LastIndexOf(".") - 1));   //扩展名
		              int address = "zip,rar,txt,doc,docx,xls,jpg,bmp,gif".indexOf(extensionName);              
		              if (address < 0) throw (new Exception("请注意文件格式！"));  // 后台判断文件扩展名，弥补前台js漏洞
					
					/*获取文件名，不含路径*/
					String fileNameOnly = new File(fileNameLong).getName();
					
					/*将utf-8文件名改为gbk，以防乱码*/
					fileNameOnly = new String( fileNameOnly.getBytes( "gbk" ), "utf-8");
					
					/*检查文件类型，由前台xheditor检查，这里不用检查*/
//					if (("," + fileExt.toLowerCase() + ",").indexOf("," + extensionName.toLowerCase() + ",") < 0){
//						printInfo(response, "不允许上传此类型的文件", "");
//						return;
//					}
					
					/*文件是否为空*/
					if (uploadFile.getSize() == 0){
						printInfo(response, "上传文件不能为空", "");
						return;
					}
					/*检查文件大小*/
					if (maxSize > 0 && uploadFile.getSize() > maxSize){
						printInfo(response, "上传文件的大小超出限制", "");
						return;
					}
					
					//0:不建目录, 1:按天存入目录, 2:按月存入目录, 3:按扩展名存目录.建议使用按天存.
					String fileFolder = "";
					if (dirType.equalsIgnoreCase("1"))
						fileFolder = new SimpleDateFormat("yyyyMMdd").format(new Date());;
					if (dirType.equalsIgnoreCase("2"))
						fileFolder = new SimpleDateFormat("yyyyMM").format(new Date());
					if (dirType.equalsIgnoreCase("3"))
						fileFolder = extensionName.toLowerCase();
					
					/*文件存储的相对路径*/
					String saveDirPath = baseDir + fileFolder + "/";
					
					/*文件存储在容器中的绝对路径*/
					String saveFilePath = this.getServletConfig().getServletContext().getRealPath("") + saveDirPath;
				
					/*构建文件目录以及目录文件*/
					File fileDir = new File(saveFilePath);
					if (!fileDir.exists()) {fileDir.mkdirs();}
					
					/*文件名用用户上传的文件名，注意文件名中不要有空格*/
					File savefile = new File(saveFilePath + fileNameOnly);
					
					/*存储上传文件*/
					uploadFile.write(savefile);
					
					//项目名，文件存放路径，文件名，返回xheditor。
					newFileName = request.getContextPath()+saveDirPath + fileNameOnly;		
					
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					newFileName = "";
					err = "错误: " + ex.getMessage();
				}
				printInfo(response, err, newFileName);
			}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		/*获取web.xml中servlet的配置文件目录参数*/
		baseDir = this.getInitParameter("baseDir");
		
		/*获取文件上传存储的相当路径*/
		if (StringUtils.isBlank(baseDir)) baseDir = "/upload/";
		
		String realBaseDir = this.getServletConfig().getServletContext().getRealPath(baseDir);
		File baseFile = new File(realBaseDir);
		if (!baseFile.exists()) {
			baseFile.mkdir();
		}

		/*获取文件类型参数*/
		//fileExt = this.getInitParameter("fileExt");
		//if (StringUtils.isBlank(fileExt)) fileExt = "jpg,jpeg,gif,bmp,png";

		/*获取文件大小参数*/
		String maxSize_str = this.getInitParameter("maxSize");
		if (StringUtils.isNotBlank(maxSize_str)) maxSize = new Long(maxSize_str);
		
		/*获取文件目录类型参数*/
		dirType = this.getInitParameter("dirType");
		
		if (StringUtils.isBlank(dirType))
			dirType = "1";
		if (",0,1,2,3,".indexOf("," + dirType + ",") < 0)
			dirType = "1";
	}

	/**
	 * 使用I/O流输出 json格式的数据
	 * @param response
	 * @param err
	 * @param newFileName
	 * @throws IOException
	 */
	public void printInfo(HttpServletResponse response, String err, String newFileName) throws IOException {
		PrintWriter out = response.getWriter();		
		out.println("{\"err\":\"" + err + "\",\"msg\":\"" + newFileName + "\"}");
		out.flush();
		out.close();
	
}
}
