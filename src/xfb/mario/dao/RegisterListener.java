package xfb.mario.dao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JTextField;


// TODO: Auto-generated Javadoc
public class RegisterListener implements ActionListener{
	

	private static JTextField jt; //账号输入框对象
	
	private static JTextField jp;//密码输入框对象
	
	/** The email. */
	private static JTextField email;//邮箱输入框对象
	
	/** The register. */
	private static JFrame register;//窗体对象
	
	/**
	 * Instantiates a new register listener.
	 *
	 * @param register the register
	 * @param txtuser the txtuser
	 * @param txtpassword the txtpassword
	 * @param email the email
	 */
	public RegisterListener(JFrame register, JTextField txtuser, JTextField txtpassword, 
			JTextField email) {
		this.register = register;//获取注册界面
		this.jt = txtuser; //获取注册界面中的账号输入对象
		this.jp = txtpassword;  //获取注册界面中的密码输入框对象
		this.email = email;//获取注册界面中邮箱输入框对象
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
				//将账号密码添加进数据库
				UserDatadao.doAdd(conn, jt, jp, email, register);
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
