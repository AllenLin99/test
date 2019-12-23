package com.gxa.ehome.util;

import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

@Component(value = "dbUtilBean")
public class DBUtil {
	
	private volatile Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private static Properties properties = null;
		
	static {
		//结合IO和静态语句块的功能,加载配置文件
		properties = new Properties();
		InputStream inputStream = null;
		try {
			//javaSE获取文件
			//inputStream = new FileInputStream("properties"+File.separator+"db.properties");
			//javaEE获取资源文件properties
			ClassLoader classLoader = DBUtil.class.getClassLoader();
			 inputStream = classLoader.getResourceAsStream("properties/db.properties");
			properties.load(inputStream);
			System.out.println(inputStream);
			System.out.println(properties.getProperty("driver"));
			/*System.out.println(properties.getProperty("url"));
			System.out.println(properties.getProperty("user"));
			System.out.println(properties.getProperty("password"));*/
			
			Class.forName("com.mysql.jdbc.Driver");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(inputStream!=null)
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
	}
	 
	
	/**
	 * 建立连接的方法
	 *  @return Connection 连接对象
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private synchronized Connection getConnection() throws ClassNotFoundException, SQLException{
	//	synchronized (this) {
	//	synchronized (DBUtil.class) {
		// 判断连接对象是否为空 或者 已经被关闭了 
		// 符合条件 就创建连接
			if(connection == null || connection.isClosed() ) {
				
				String url = properties.getProperty("url");
				String user = properties.getProperty("user");
				String password = properties.getProperty("password");
				// 核心: 生成连接
				connection = DriverManager.getConnection(url, user, password);
			}		
		//}
		return connection;
	}
	
	/**
	 * 获取预编译语句对象的方法
	 * @param sql 预编译的SQL命令
	 * @return PreparedStatement对象
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public PreparedStatement getPreparedStatement(String sql) throws ClassNotFoundException, SQLException{
		return  preparedStatement = getConnection().prepareStatement(sql);
	}
	
	
	
	/**
	 * 执行增删改的方法
	 * @param prepStat 执行增删改语句的对象
	 * @return 返回影响的行数
	 * @throws SQLException
	 */
	public int execUpdate(PreparedStatement prepStat) throws SQLException{
		return prepStat.executeUpdate();
	}
	
	/**
	 * 执行查询的方法
	 * @param prepStat 执行查询语句的对象
	 * @return 返回相应的结果集
	 * @throws SQLException
	 */
	public ResultSet  execQuery(PreparedStatement prepStat) throws SQLException{
		return resultSet= prepStat.executeQuery();
	}
	
	
	/**
	*关闭结果集对象
	*/
	public void closeSet() throws SQLException{
		if(resultSet != null)
			resultSet.close();
	}
	
	/**
	*关闭预编译对象
	*/
	public void closePrepStat() throws SQLException{
		if(preparedStatement != null)
			preparedStatement.close();
	}
	
	/**
	*关闭连接对象
	*/
	public void closeConn() throws SQLException{
		if(connection != null)
			connection.close();
	}
	
	/**
	*关闭全部对象
	*/
	public void closeAll() throws SQLException{
		closeSet();
		closePrepStat();
		closeConn();
	}
	
	public void closeAll2(AutoCloseable... closeable) throws Exception {
		
		for (AutoCloseable autoCloseable : closeable) {
			if(autoCloseable != null) {
				autoCloseable.close();
			}
		}
		
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}

	public void setPreparedStatement(PreparedStatement preparedStatement) {
		this.preparedStatement = preparedStatement;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public static Properties getProperties() {
		return properties;
	}

	public static void setProperties(Properties properties) {
		DBUtil.properties = properties;
	}
}
