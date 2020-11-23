package xfb.mario.dao;


import java.sql.*;


    
// TODO: Auto-generated Javadoc
/**
 * The Class MyConnectionDao.
 *
 * @date 2020-7-4
 * @author xufb
 * @version  v1.0
 */
public class MyConnectionDao implements DatabaseConnection{

	    
	/**
	 * Gets the connection.
	 *
	 * @param driveName the drive name
	 * @param uri the uri
	 * @param con the con
	 * @return the connection
	 * @throws Exception the exception
	 */
	public static Connection getConnection(String driveName, String uri, Connection con) throws Exception{		
		try {
			Class.forName(driveName);
			con = DriverManager.getConnection(uri);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			return con;
		}
	}
	
	    
	/**
	 * Close connection.
	 *
	 * @param con the con
	 * @param stmt the stmt
	 * @param rs the rs
	 * @throws Exception the exception
	 */
	public static void closeConnection(Connection con, Statement stmt, ResultSet rs) throws Exception {
		if(con != null) {
			try {
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt != null) {
			try {
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}

