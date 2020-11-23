package xfb.mario.dao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


// TODO: Auto-generated Javadoc
public class LoginListener  implements ActionListener{

	private static JTextField jt; //账号输入框对象
	private static JPasswordField jp;//密码输入框对象
	
	/** The login. */
	private static JFrame login;//窗体对象
	
	/**
	 * Instantiates a new login listener.
	 *
	 * @param login the login
	 * @param jt the jt
	 * @param jp the jp
	 */
	public LoginListener(JFrame login, JTextField jt, JPasswordField jp) {
		this.login = login;//获取登录界面
		this.jt = jt; //获取登录界面中的账号输入对象
		this.jp = jp;  //获取登录界面中的密码输入框对象
	}
	
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    */
	    
	public void actionPerformed(ActionEvent e) {
		
		
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
		
			try {
				//验证账号
				UserDatadao.doSelect(conn, stmt, rs, jt, jp, login);
				//关闭对象
				try {
					MyConnectionDao.closeConnection(conn, stmt, rs);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	}

}
