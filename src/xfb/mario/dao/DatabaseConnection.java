package xfb.mario.dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;



    
// TODO: Auto-generated Javadoc
/**
 * The Interface DatabaseConnection.
 *
 * @date 2020-7-4
 * @author xufb
 * @version  v1.0
 */
public interface DatabaseConnection {

	/**
	 * Gets the connection.
	 *
	 * @param driveName the drive name
	 * @param uri the uri
	 * @return the connection
	 * @throws Exception the exception
	 */
	//取得数据库的连接
	public static Connection getConnection(String driveName, String uri) throws Exception {
		return null;
	}
	
	/**
	 * Close connection.
	 *
	 * @param con the con
	 * @param stmt the stmt
	 * @param rs the rs
	 * @throws Exception the exception
	 */
	//关闭数据库
	public static void closeConnection(Connection con, Statement stmt, ResultSet rs) throws Exception {
	}
}
