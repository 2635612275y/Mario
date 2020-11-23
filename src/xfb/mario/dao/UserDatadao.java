package xfb.mario.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import xfb.project.mario.LoginGui;
import xfb.project.mario.MyFrame;

    
// TODO: Auto-generated Javadoc
/**
 * The Class UserDatadao.
 *
 * @date 2020-7-4
 * @author xufb
 * @version  v1.0
 */
public class UserDatadao implements User{

	    public static int count1 = 0;
	    public static int count2 = 0;
		
		/**
		 * Do add.
		 *
		 * @param conn the conn
		 * @param txtuser the txtuser
		 * @param txtpassword the txtpassword
		 * @param txtGrildfriend the txt grildfriend
		 * @param register the register
		 * @throws SQLException the SQL exception
		 */
		public static void doAdd(Connection conn, JTextField txtuser, JTextField txtpassword, 
				JTextField txtGrildfriend, JFrame register) throws SQLException{
			//���ı���Ϣ���
			String user = txtuser.getText();
			String password = txtpassword.getText();
			//����ע���˺�����
			String sql = "insert into user value('"+user+"', '"+password+"')";
			
			PreparedStatement stmt = null;		
			
			try {
				stmt = conn.prepareStatement(sql);
				stmt.executeUpdate();
				count1++;
			}catch(SQLException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "ע��ɹ�,��ȥ��¼��");
			register.setVisible(false);
			LoginGui.init();
			register.dispose();
		}

		
		/**
		 * Do select.
		 *
		 * @param conn the conn
		 * @param stmt the stmt
		 * @param rs the rs
		 * @param jt the jt
		 * @param jp the jp
		 * @param login the login
		 * @throws SQLException the SQL exception
		 */
		public static void doSelect(Connection conn, java.sql.Statement stmt, ResultSet rs, JTextField jt, JPasswordField jp, JFrame login) throws SQLException {
			String sql = "select username, userpassword from user"; 
			
			stmt = (java.sql.Statement) conn.createStatement();
			rs = ((java.sql.Statement) stmt).executeQuery(sql);
			//���ʽ����
			while(rs.next()) {
				//����get��������ȡ�˺ź����������ı���Ϣ������equal���������жϡ�
					if( jt.getText().equals(rs.getString(1)) && String.valueOf(jp.getPassword()).equals(rs.getString(2)) ) {
						
						count2++;
						JOptionPane.showMessageDialog(null,"��¼�ɹ�");//��¼�ɹ�����
						
						//��¼�ɹ���رյ�¼���棬������Ϸ���� 
						login.setVisible(false);
						new MyFrame().setVisible(true);;
						login.dispose();//���ٴ���
					}
			}
		}

}
