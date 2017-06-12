<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>咩咩信息平台注册页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/register.css">
	

  </head>
  
  <body>
   <form action="register/registerAction.action" method="post">  
    <div id="register">  
        <h1>register</h1>  
       
      		<input type="text" required="required" placeholder="昵称" name="nickname"></input>   
            <input type="text" required="required" placeholder="用户名" name="account"></input>  
            <input type="password" required="required" placeholder="密码" name="password"></input> 
            
            <button class="but" type="submit">注册</button>  
        
        
    </div>  
    </form>  


    


  </body>
</html>
