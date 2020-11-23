package xfb.project.mario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import xfb.mario.dao.LoginListener;

    
// TODO: Auto-generated Javadoc
/**
 * The Class LoginGui.
 *
 * @date 2020-7-4
 * @author xufb
 * @version  v1.0
 */
public class LoginGui {

	/** The frame. */
	private static JFrame frame;
	
	/** The lbluser. */
	private static JLabel lbluser; //�˺ű�ǩ
	
	/** The lbluserimage. */
	private static BufferedImage lbluserimage = null;
	
	/** The lblpasswordimage. */
	private static BufferedImage lblpasswordimage = null;
	
	/** The lblpassword. */
	private static JLabel lblpassword;//�����ǩ
	
	/** The txtuser. */
	private static JTextField  txtuser; //�˺������
	
	/** The txtpassword. */
	private static JPasswordField  txtpassword; //���������
	
	/** The btn OK. */
	private static JButton btnOK;
	
	/** The btn cancel. */
	private static JButton btnCancel;
	
	/** The btn new. */
	private static JButton btnNew;
	
	/** The txt. */
	private static JLabel txt = new JLabel("���ȵ�¼������Ϸ�˺�");
	
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
		
        txt.setFont(new Font("����",Font.BOLD, 30));
		txt.setBounds(280, 70, 400, 100);
		txt.setOpaque(false);
		frame.getLayeredPane().add(txt);
        
		txtuser = new JTextField (40);
		txtpassword = new JPasswordField(40);
		
		btnOK = new JButton("��¼");
		btnCancel = new JButton("����");
		btnNew = new JButton("ע���˺�");
		
	    /**
	       *������¼�û�����֮ǰ��ͼƬ�滻
	    */
		try {
			lbluserimage = ImageIO.read(new File(path + "�û�.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		lbluser = new JLabel(new ImageIcon(lbluserimage));
		lbluser.setBounds(320, 218, lbluserimage.getWidth(), lbluserimage.getHeight());
		lbluser.setOpaque(false);
		frame.getLayeredPane().add(lbluser);
		try {
			lblpasswordimage = ImageIO.read(new File(path + "����.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		lblpassword = new JLabel(new ImageIcon(lblpasswordimage));
		lblpassword.setBounds(320, 268, lblpasswordimage.getWidth(), lblpasswordimage.getHeight());
		lblpassword.setOpaque(false);
		frame.getLayeredPane().add(lblpassword);
		
		//�˺���������򲿷�
		txtuser.setBounds(380, 220, 160, 25);
		txtuser.setFont(new Font("����", Font.BOLD, 15));
		txtuser.setForeground(Color.RED);
		txtuser.setOpaque(false);
		frame.getLayeredPane().add(txtuser);
		
		
		txtpassword.setBounds(380, 270, 160, 25);
		txtpassword.setFont(new Font("����", Font.BOLD, 15));
		txtpassword.setForeground(Color.RED);
		txtpassword.setOpaque(false);
		txtpassword.setEchoChar('*');
		frame.getLayeredPane().add(txtpassword);
		
		btnOK.setBounds(290, 340, 80, 25);
		btnOK.setOpaque(false);
		frame.getLayeredPane().add(btnOK);
		btnCancel.setBounds(530, 340, 80, 25);
		btnCancel.setOpaque(false);
		frame.getLayeredPane().add(btnCancel);
		btnNew.setBounds(410, 340, 80, 25);
		btnNew.setOpaque(false);
		frame.getLayeredPane().add(btnNew);
		
        
        //�Ե�¼��ťʵ������Ӽ�����
        LoginListener into = new LoginListener(frame, txtuser, txtpassword);
        btnOK.addActionListener(into);
        
        //�������ť����
        btnCancel.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		txtpassword.setText("");
        		txtuser.setText("");
        	}
        });
        
        //��ע�����
        btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�رյ�¼�������ע�����
				frame.setVisible(false);
				RegisterLogin.init();
				//���ٵ�¼����
				frame.dispose();
			}
        	
        });
        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
