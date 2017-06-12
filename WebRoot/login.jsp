<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>咩咩信息平台登陆页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/Login.css"/> 
	

  </head>
  
  <body>
   
    <div id="login">  
        <h1>Login</h1>  
         <form action="login/loginAction.action"  method="post">  
            <input type="text"  name="account"></input>  
            <input type="password" name="password"></input>  
            <button class="but" type="submit">登录</button>  
        </form>  
        <form action="login/loginAction!login2register.action" method="post">   
            <button class="but" type="submit">注册</button>  
        </form> 
    </div>  




  </body>
</html>
