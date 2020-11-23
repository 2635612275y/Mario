package xfb.project.mario;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import xfb.mario.dao.RegisterListener;


    
// TODO: Auto-generated Javadoc
/**
 * The Class RegisterLogin.
 *
 * @date 2020-7-4
 * @author xufb
 * @version  v1.0
 */
public class RegisterLogin {
		
		/** The frame. */
		private static JFrame frame;

		/** The btn register. */
		private static JButton btnRegister;
		
		/** The txtuser. */
		private static JTextField txtuser; //�˺������
		
		/** The txtpassword. */
		private static JTextField txtpassword; //���������
		
		/** The txt number. */
		private static JTextField txtNumber;//�绰����
		
		/** The txt grildfriend. */
		private static JTextField txtGrildfriend;//Ů�������������

		/** The txt. */
		private static JLabel txt = new JLabel("��ӭע�ᳬ��������Ϸ�˺�");
		
		/** The txt make. */
		private static JLabel txtMake = new JLabel("ÿһ��, ������Ϸ");
		
		/** The lbluser. */
		private static JLabel lbluser = new JLabel("�û���");
		
		/** The lblpassword. */
		private static JLabel lblpassword = new JLabel("����");
		
		/** The phone number. */
		private static JLabel phoneNumber = new JLabel("�ֻ���");
		
		/** The lbl grildfriend. */
		private static JLabel lblGrildfriend = new JLabel("Ů��������");
		
		/** The path. */
		private static String path = System.getProperty("user.dir") + "/pic/";
		    
		/**
		 * Inits the.
		 */
		public static void init() {
			frame = new JFrame("Mario Login");
			
			frame.setSize(900, 600);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //����ر�ʱ��������
			
			ImageIcon ig = new ImageIcon(path + "��¼ͼ��.png");//�޸���Ϸ����ͼ��
			Image im = ig.getImage();
			frame.setIconImage(im);
	    	
			int width = Toolkit.getDefaultToolkit().getScreenSize().width;
			int height = Toolkit.getDefaultToolkit().getScreenSize().height;
			frame.setLocation((width-900) / 2, (height-600) / 2);
			//��������Ĵ��ڴ�С
			frame.setResizable(false); 
			frame.setLayout(null);
			
		    /**
		       *��¼����ͼƬ���룬������͸��
		    */
			ImageIcon img=new ImageIcon(path + "��¼����.jpg");
			JLabel bg=new JLabel(img);
			frame.getLayeredPane().add(bg, new Integer(Integer.MIN_VALUE));
			bg.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
			JPanel jp= (JPanel)frame.getContentPane();
			jp.setOpaque(false);
			jp.add(bg);
			
			//ע������ı���Ϣ
	        txt.setFont(new Font("����", Font.BOLD, 30));
			txt.setBounds(280, 40, 400, 100);
			txt.setOpaque(false);
			frame.getLayeredPane().add(txt);
			txtMake.setFont(new Font("����", Font.BOLD, 25));
			txtMake.setBounds(340, 100, 400, 100);
			txtMake.setOpaque(false);
			frame.getLayeredPane().add(txtMake);
			//�û���
	        lbluser.setFont(new Font("����", Font.BOLD, 14));
			lbluser.setBounds(320, 218, 100, 30);
			lbluser.setOpaque(false);
			frame.getLayeredPane().add(lbluser);
			//����
	        lblpassword.setFont(new Font("����", Font.BOLD, 14));
	        lblpassword.setBounds(333, 268, 100, 30);
	        lblpassword.setOpaque(false);
			frame.getLayeredPane().add(lblpassword);
			//�绰����
			phoneNumber.setFont(new Font("����", Font.BOLD, 14));
			phoneNumber.setBounds(320, 318, 100, 30);
			phoneNumber.setOpaque(false);
			frame.getLayeredPane().add(phoneNumber);
			//Ů��������
	        lblGrildfriend.setFont(new Font("����", Font.BOLD, 14));
	        lblGrildfriend.setBounds(290, 368, 100, 30);
	        lblGrildfriend.setOpaque(false);
			frame.getLayeredPane().add(lblGrildfriend);
			
			//�˺���������򲿷�
			txtuser = new JTextField (40);
			txtpassword = new JTextField(40);
			txtGrildfriend = new JTextField(40);
			txtNumber = new JTextField(40);
			txtuser.setBounds(380, 220, 180, 25);
			txtuser.setFont(new Font("����", Font.BOLD, 15));
			txtuser.setForeground(Color.BLACK);
			txtuser.setOpaque(false);
			frame.getLayeredPane().add(txtuser);
			
			txtpassword.setBounds(380, 270, 180, 25);
			txtpassword.setFont(new Font("����", Font.BOLD, 15));
			txtpassword.setForeground(Color.BLACK);
			txtpassword.setOpaque(false);
			frame.getLayeredPane().add(txtpassword);
			
			txtNumber.setBounds(380, 320, 180, 25);
			txtNumber.setFont(new Font("����", Font.BOLD, 15));
			txtNumber.setForeground(Color.BLACK);
			txtNumber.setOpaque(false);
			frame.getLayeredPane().add(txtNumber);
			
			txtGrildfriend.setBounds(380, 370, 180, 25);
			txtGrildfriend.setFont(new Font("����", Font.BOLD, 15));
			txtGrildfriend.setForeground(Color.BLACK);
			txtGrildfriend.setOpaque(false);
			frame.getLayeredPane().add(txtGrildfriend);
			
			//ע�ᰴť
			btnRegister = new JButton("����ע��");
			btnRegister.setBounds(420, 440, 80, 25);
			btnRegister.setOpaque(false);
			frame.getLayeredPane().add(btnRegister);
			
			//��ע��ɹ���ť��Ӽ�����frame, txtuser, txtpassword, txtGrildfriend
	        RegisterListener into = new RegisterListener(frame, txtuser, txtpassword, txtGrildfriend);
	        btnRegister.addActionListener(into);
			
	        frame.setVisible(true);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

}
