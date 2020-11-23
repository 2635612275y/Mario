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
		private static JTextField txtuser; //账号输入框
		
		/** The txtpassword. */
		private static JTextField txtpassword; //密码输入框
		
		/** The txt number. */
		private static JTextField txtNumber;//电话号码
		
		/** The txt grildfriend. */
		private static JTextField txtGrildfriend;//女朋友邮箱输入框

		/** The txt. */
		private static JLabel txt = new JLabel("欢迎注册超级玛丽游戏账号");
		
		/** The txt make. */
		private static JLabel txtMake = new JLabel("每一天, 乐在游戏");
		
		/** The lbluser. */
		private static JLabel lbluser = new JLabel("用户名");
		
		/** The lblpassword. */
		private static JLabel lblpassword = new JLabel("密码");
		
		/** The phone number. */
		private static JLabel phoneNumber = new JLabel("手机号");
		
		/** The lbl grildfriend. */
		private static JLabel lblGrildfriend = new JLabel("女朋友邮箱");
		
		/** The path. */
		private static String path = System.getProperty("user.dir") + "/pic/";
		    
		/**
		 * Inits the.
		 */
		public static void init() {
			frame = new JFrame("Mario Login");
			
			frame.setSize(900, 600);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //窗体关闭时结束程序
			
			ImageIcon ig = new ImageIcon(path + "登录图标.png");//修改游戏窗口图标
			Image im = ig.getImage();
			frame.setIconImage(im);
	    	
			int width = Toolkit.getDefaultToolkit().getScreenSize().width;
			int height = Toolkit.getDefaultToolkit().getScreenSize().height;
			frame.setLocation((width-900) / 2, (height-600) / 2);
			//不允许更改窗口大小
			frame.setResizable(false); 
			frame.setLayout(null);
			
		    /**
		       *登录界面图片导入，且让其透明
		    */
			ImageIcon img=new ImageIcon(path + "登录界面.jpg");
			JLabel bg=new JLabel(img);
			frame.getLayeredPane().add(bg, new Integer(Integer.MIN_VALUE));
			bg.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
			JPanel jp= (JPanel)frame.getContentPane();
			jp.setOpaque(false);
			jp.add(bg);
			
			//注册界面文本信息
	        txt.setFont(new Font("楷体", Font.BOLD, 30));
			txt.setBounds(280, 40, 400, 100);
			txt.setOpaque(false);
			frame.getLayeredPane().add(txt);
			txtMake.setFont(new Font("宋体", Font.BOLD, 25));
			txtMake.setBounds(340, 100, 400, 100);
			txtMake.setOpaque(false);
			frame.getLayeredPane().add(txtMake);
			//用户名
	        lbluser.setFont(new Font("宋体", Font.BOLD, 14));
			lbluser.setBounds(320, 218, 100, 30);
			lbluser.setOpaque(false);
			frame.getLayeredPane().add(lbluser);
			//密码
	        lblpassword.setFont(new Font("宋体", Font.BOLD, 14));
	        lblpassword.setBounds(333, 268, 100, 30);
	        lblpassword.setOpaque(false);
			frame.getLayeredPane().add(lblpassword);
			//电话号码
			phoneNumber.setFont(new Font("宋体", Font.BOLD, 14));
			phoneNumber.setBounds(320, 318, 100, 30);
			phoneNumber.setOpaque(false);
			frame.getLayeredPane().add(phoneNumber);
			//女朋友邮箱
	        lblGrildfriend.setFont(new Font("宋体", Font.BOLD, 14));
	        lblGrildfriend.setBounds(290, 368, 100, 30);
	        lblGrildfriend.setOpaque(false);
			frame.getLayeredPane().add(lblGrildfriend);
			
			//账号密码输入框部分
			txtuser = new JTextField (40);
			txtpassword = new JTextField(40);
			txtGrildfriend = new JTextField(40);
			txtNumber = new JTextField(40);
			txtuser.setBounds(380, 220, 180, 25);
			txtuser.setFont(new Font("宋体", Font.BOLD, 15));
			txtuser.setForeground(Color.BLACK);
			txtuser.setOpaque(false);
			frame.getLayeredPane().add(txtuser);
			
			txtpassword.setBounds(380, 270, 180, 25);
			txtpassword.setFont(new Font("宋体", Font.BOLD, 15));
			txtpassword.setForeground(Color.BLACK);
			txtpassword.setOpaque(false);
			frame.getLayeredPane().add(txtpassword);
			
			txtNumber.setBounds(380, 320, 180, 25);
			txtNumber.setFont(new Font("宋体", Font.BOLD, 15));
			txtNumber.setForeground(Color.BLACK);
			txtNumber.setOpaque(false);
			frame.getLayeredPane().add(txtNumber);
			
			txtGrildfriend.setBounds(380, 370, 180, 25);
			txtGrildfriend.setFont(new Font("宋体", Font.BOLD, 15));
			txtGrildfriend.setForeground(Color.BLACK);
			txtGrildfriend.setOpaque(false);
			frame.getLayeredPane().add(txtGrildfriend);
			
			//注册按钮
			btnRegister = new JButton("立即注册");
			btnRegister.setBounds(420, 440, 80, 25);
			btnRegister.setOpaque(false);
			frame.getLayeredPane().add(btnRegister);
			
			//对注册成功按钮添加监听器frame, txtuser, txtpassword, txtGrildfriend
	        RegisterListener into = new RegisterListener(frame, txtuser, txtpassword, txtGrildfriend);
	        btnRegister.addActionListener(into);
			
	        frame.setVisible(true);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

}
