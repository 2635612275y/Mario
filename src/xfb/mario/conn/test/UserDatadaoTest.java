package xfb.mario.conn.test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Test;

import xfb.mario.dao.MyConnectionDao;
import xfb.mario.dao.UserDatadao;


// TODO: Auto-generated Javadoc
/**
 * The Class UserDatadaoText.
 *
 * @date 2020-7-4
 * @author xufb
 * @version  v1.0
 */
public class UserDatadaoTest {

	/**
	 * Text do add.
	 */
	@Test
	public void textDoAdd() {
		Statement stmt = null;
		ResultSet rs = null;
		String driverName = "com.mysql.jdbc.Driver";
		String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=womenzhijian&useSSL=true";
		//加载数据库，建立数据库连接
		//Class.forName(driverName);
		Connection conn = null;
		try {
			conn = MyConnectionDao.getConnection(driverName, uri, conn);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		JTextField p1 = new JTextField("1234");
		JTextField p2 = new JTextField("12345");
		JTextField p3 = new JTextField("123456");
		JFrame frame = new JFrame("测试");
		try {
			UserDatadao.doAdd(conn, p1, p2, p3, frame);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(1,UserDatadao.count1);
	}
	    
	/**
	 * Text do select.
	 */
	@Test
	public void textDoSelect() {
		Statement stmt = null;
		ResultSet rs = null;
		String driverName = "com.mysql.jdbc.Driver";
		String uri = "jdbc:mysql://localhost:3306/jsu?user=root&password=womenzhijian&useSSL=true";
		//加载数据库，建立数据库连接
		//Class.forName(driverName);
		Connection conn = null;
		try {
			conn = MyConnectionDao.getConnection(driverName, uri, conn);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		JTextField q1 = new JTextField("1234");
		JPasswordField q2 = new JPasswordField("12345");
		JFrame frame = new JFrame("测试");
		try {
			UserDatadao.doSelect(conn, stmt, rs, q1, q2, frame);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(1,UserDatadao.count2);
	}
}
