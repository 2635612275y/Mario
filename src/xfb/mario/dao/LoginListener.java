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

	private static JTextField jt; //�˺���������
	private static JPasswordField jp;//������������
	
	/** The login. */
	private static JFrame login;//�������
	
	/**
	 * Instantiates a new login listener.
	 *
	 * @param login the login
	 * @param jt the jt
	 * @param jp the jp
	 */
	public LoginListener(JFrame login, JTextField jt, JPasswordField jp) {
		this.login = login;//��ȡ��¼����
		this.jt = jt; //��ȡ��¼�����е��˺��������
		this.jp = jp;  //��ȡ��¼�����е�������������
	}
	
	
	    /* (�� Javadoc)
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
		//�������ݿ⣬�������ݿ�����
		//Class.forName(driverName);
		Connection conn = null;
		try {
			conn = MyConnectionDao.getConnection(driverName, uri, conn);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
			try {
				//��֤�˺�
				UserDatadao.doSelect(conn, stmt, rs, jt, jp, login);
				//�رն���
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
