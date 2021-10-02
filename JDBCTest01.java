package jdbc;

import java.sql.*;

public class JDBCTest01 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try{
			//1.注册驱动
			//只能导一个包
			Driver driver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(driver);


			//2.获取数据库连接
			/*
			url：统一资源定位符
			任何一个URL都包括：
				协议 IP地址 端口号port 资源名
				http:\\192.168.100.2:8080\aab
				协议：数据传输格式
				IP地址：网络中定位计算机
				端口号port：定位计算机上某个服务
				资源名：这个计算机上的某个资源
			*/
			String url = "jdbc:mysql://localhost:3306/bjpowernode";
			String user = "root";
			String password = "333";
			conn = DriverManager.getConnection(url, user, password);

			//输出对象内存地址
			System.out.println(conn); //com.mysql.jdbc.JDBC4Connection@1fc2b765


			//3.获取数据库操作对象
			stmt = conn.createStatement();
			//System.out.println(stmt); //com.mysql.jdbc.StatementImpl@75881071

			//通过Connection对象可创建多个Statement对象
			//Statement stmt2 = conn.createStatement();

			//4.执行SQl语句

			/*
			//insert插入数据
			String insertsql = "insert into dept(deptno, dname, loc) values(100, 'whtcc', '武汉')";

			//Statement接口中的executeUpdate方法专门用来执行DML语句的
			//该方法返回影响了数据库表中的总记录条数！
			int insertCount = stmt.executeUpdate(insertsql);
			System.out.println(insertCount);//1
			*/


			/*
			//update更新数据
			String updatesql = "update dept set deptno=55, dname='阜阳师范大学', loc='阜阳' where deptno=100";
			int updateCount = stmt.executeUpdate(updatesql);
			System.out.println(updateCount);//1
			*/


			/*
			//delete删除数据
			String deletesql = "delete from dept where deptno=55";
			int deleteCount = stmt.executeUpdate(deletesql);
			System.out.println(deleteCount);
			*/


		}catch (SQLException e){
			e.printStackTrace();
		} finally {
			//6.释放资源
			//先释放Statement，再释放Connection，分别try
			//放到finally中
			if (stmt != null) {
				try {
					stmt.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
