package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.UserInfo;

public class UserInfoDao {

	public String login(String userName, String password) throws Exception {
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
		String sql = "SELECT * FROM datebaseofshui.user where username = '"
				+ userName + "' and userpsd = '" + password + "';";
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			return rs.getString(4);
		} else {
			return "";
		}

		// 第五步：关闭连接

	}

	public boolean register(String userName, String password, String nickname)
			throws Exception {
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
		String sql = "insert into user value(null,'" + userName + "','"
				+ password + "','" + nickname + "')";
		int rs = 1;

		try {
			rs = stmt.executeUpdate(sql);
		} catch (Exception e) {
			rs = 0;
		}

		// 第五步：关闭连接
		conn.close();
		if (rs == 1) {
			return true;
		} else {
			return false;
		}
	}

	public void dbOperate() {

	}
}