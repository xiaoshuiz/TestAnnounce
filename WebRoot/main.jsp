<%@ page language="java" import="java.util.*,java.sql.*"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>咩咩信息平台</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/main.css" />


</head>

<body>
	<br />
	<br />
	<%
	 try{
	
    HttpSession s = request.getSession(); 
    System.out.print("asdfsadf");
   	String nickname=s.getAttribute("nickname").toString();
   	System.out.print(nickname);
   	if(nickname.equals(""))
   	{
   		out.println("<form action='main/mainAction!main2login.action' method='post'><button class='h1' type='submit'>登陆</button></form>");
   		out.println("<br /><br /><br /><br /><br />");
   		out.println("");
   	}
   	else
   	{
   		out.println("<form action='main/mainAction!main2login.action' method='post'> <button class='h1' type='submit'>"+nickname+" <br/> press to loginout</button></form>");
   		out.println("<br />");
   		out.println("<form action='main/mainAction!main2editor.action' method='post'> <button class='h1' type='submit'>发布信息 </button></form>");
   	}
   	}
   	catch(Exception e)
   	{
   		out.println("<form action='main/mainAction!main2login.action' method='post'><button class='h1' type='submit'>登陆</button></form>");
   		out.println("<br /><br /><br /><br /><br />");
   	
   	}
    %>
	<!--  
    <form action="main/mainAction!main2login.action" method="post">
		<button class="h1" type="submit">mimimi <br/>press to loginout</button>
	</form>
		<br />
			<form action="main/mainAction!main2editor.action" method="post">
		<button class="h1" type="submit">发布信息</button>
	</form>
	-->



	<br />
	<hr color="gray" />

	<br />
	<br />
	<br />
	<br />



	<div>
		<h2>信息列表</h2>
		<br />
		<hr color="gray" />
		<br /> <br /> <br />


		<ul>

			<%
				
				// 第一步：加载驱动（驱动jar包必须加入classpath中）
				Class.forName("com.mysql.jdbc.Driver");
				// 第二步：建立连接（根据实际情况替换数据库的主机地址、端口号、数据库明、登录名、密码）
				Connection conn = DriverManager
						.getConnection(
								"jdbc:mysql://localhost/datebaseofshui?useUnicode=true&characterEncoding=utf-8",
								"root", "977484");
				System.out.println("当前连接到的数据库=" + conn.getCatalog());// 查看当前连接到的数据库名
				// 第三步：创建Statement对象
				Statement stmt = conn.createStatement();// 只读的结果集
				// 第四步：执行操作（增删改查）
				ResultSet rs = stmt.executeQuery("select title from info");
				// 处理结果
				while (rs.next()) {
					out.println("<li><a href='#'>"+rs.getString(1) +"</a></li>");
				}
				// 第五步：关闭连接
				conn.close();
			%>

			<!-- 
			
			<li><a href="#">Zurich</a></li>

			<li><a href="#">Geneva</a></li>

			<li><a href="#">Winterthur</a></li>

			<li><a href="#">Lausanne</a></li>

			<li><a href="#">Lucerne</a></li>
			
 			-->
		</ul>

	</div>




</body>
</html>
