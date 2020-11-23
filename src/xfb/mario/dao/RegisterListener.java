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
	

	private static JTextField jt; //�˺���������
	
	private static JTextField jp;//������������
	
	/** The email. */
	private static JTextField email;//������������
	
	/** The register. */
	private static JFrame register;//�������
	
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
		this.register = register;//��ȡע�����
		this.jt = txtuser; //��ȡע������е��˺��������
		this.jp = txtpassword;  //��ȡע������е�������������
		this.email = email;//��ȡע�������������������
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
				//���˺�������ӽ����ݿ�
				UserDatadao.doAdd(conn, jt, jp, email, register);
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
