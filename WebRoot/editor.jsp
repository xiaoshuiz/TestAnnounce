<%@page import="com.sun.java.swing.plaf.windows.WindowsBorders"%>
<%@page import="com.sun.java.swing.plaf.windows.resources.windows"%>
<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>咩咩信息平台发布页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta charset="utf-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	
  
	
	<link rel="stylesheet" type="text/css" href="css/editor.css">
	

  </head>
  <body>
  
 	 <%
 	 try{
  HttpSession s = request.getSession(); 
    System.out.print("asdfsadf");
   	String nickname=s.getAttribute("nickname").toString();
   	System.out.print(nickname);
   	if(nickname.equals(""))
   	{
   		response.sendRedirect("login.jsp");
   		
   	}
   	}
   	catch(Exception e)
   	{
   	response.sendRedirect("login.jsp");
   	}
  %>
  
  
  	<form action="info/infoAction!save.action" method="post">
  	 <h1>title</h1>  <input type="text" name="info.title" ></input>
  	<br/>
  	<br/>
  	<br/>
  	<br/>
   <script id="container" name="content" type="text/plain">
        这里写你的初始化内容
    </script>
    <!-- 配置文件 -->
    <script type="text/javascript" src="ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="ueditor/ueditor.all.js"></script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
        var ue = UE.getEditor('container');
    </script>
    <script type="text/javascript">
        var contents = UE.getCintent();
    </script>
    <br/>
    <br/>
  	<button class="but" type="submit" >发布</button>
   </form>
  </body>
</html>
