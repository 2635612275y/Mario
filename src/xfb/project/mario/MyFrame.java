package xfb.project.mario;

import java.awt.Graphics;
import java.awt.Image; 
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import xfb.mario.mail.SendMailThread;

// TODO: Auto-generated Javadoc
/**
 * The Class MyFrame.
 *
 * @date 2020-7-4
 * @author xufb
 * @version  v1.0
 */
public class MyFrame extends JFrame implements KeyListener, Runnable{
	
	/** The all BG. */
	private List<BackGround> allBG = new ArrayList<BackGround>();
	
	/** The mario. */
	private Mario mario = null;
	
	/** The t. */
	//�����߳�
	private Thread t = null;
	
	/** The now BG. */
	//��ʱ�洢��ʱ����
	private BackGround nowBG = null;
    
    /** The life image. */
    //����ֵͼƬ 
	public static BufferedImage lifeImage = null;
	
	/** The path. */
	private String path = System.getProperty("user.dir") + "/pic/";
	
	/** The is start. */
	//�Ƿ��Ѿ���ʼ��Ϸ
	private boolean isStart = false;
	
	/**
	 * Instantiates a new my frame.
	 */
	public MyFrame(){
		this.setTitle("Super Mario");
		this.setSize(900, 600);
		
		ImageIcon ig = new ImageIcon(path + "��¼ͼ��.png");//�޸���Ϸ����ͼ��
		Image im = ig.getImage();
		this.setIconImage(im);
		
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width-900)/2, (height-600)/2);
		this.setResizable(false);
		//��ʼ��ͼƬ
		VariableData.init();
		
		//����ȫ������
		for(int i = 1;i <= 7;i++){
			this.allBG.add(new BackGround(i, i==7?true:false));
		}
		
		//����һ����������Ϊ��ǰ����
		this.nowBG = this.allBG.get(0);
		//��ʼ��������
		this.mario = new Mario(0, 480);
		
		//�������·��볡����
		this.mario.setBg(nowBG);
		this.addKeyListener(this);
		
		this.t = new Thread(this);
		t.start();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
    
        
    //���Ƴ���
    public void paint(Graphics g) {
    	//˫����
    	BufferedImage image = new BufferedImage(900, 600, BufferedImage.TYPE_3BYTE_BGR);
    	Graphics g2 =  image.getGraphics();
    	
    	if(this.isStart == true) {
    		
    		//��Ϸ��ʼ���ƹؿ�����
    		g2.drawImage(this.nowBG.getBgImage(), 0, 0, this);

    		//���ƹ���
    		Iterator<Monster> iterMonster = this.nowBG.getAllMonster().iterator();
    		while(iterMonster.hasNext()) {
    			Monster e = iterMonster.next();
    			g2.drawImage(e.getShowImage(), e.getX(), e.getY(), this);
    		}
    		
			//����ֵͼƬ
			try {
				lifeImage = ImageIO.read(new File(path + "����ֵ.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		//�������������ֵ
    		for(int i = 0; i < this.mario.getLife(); i++) {		
    			g2.drawImage(lifeImage, (720 + i*60), 50, this);
    		}
    		
    		//�����ϰ���
    		Iterator<Obstacles> iterObstacles = this.nowBG.getAllObstacles().iterator();
    	    while(iterObstacles.hasNext()) {
    	    	Obstacles ob = iterObstacles.next();
    	    	g2.drawImage(ob.getShowImage(), ob.getX(), ob.getY(), this);
    	    }
    	    //���������
    	    g2.drawImage(this.mario.getShowImage(), this.mario.getX(), this.mario.getY(), this);
    	}else {
    		//�����Ƿ�ʼ��Ϸ����
    		g2.drawImage(VariableData.startImage, 0, 0, this);
    	}
    	
    	//������ͼƬ���ƽ�ȥ
    	g.drawImage(image, 0, 0, this);
    }
	
	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	    */
	    
	public void keyTyped(KeyEvent e) {
		
	}


	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	    */
	    
	@Override
	public void keyPressed(KeyEvent e) {
		if(this.isStart) {
			//->
			if(e.getKeyCode() == 39) {
				this.mario.rightMove();
			}
			//<-
			
			if(e.getKeyCode() == 37) {
				this.mario.leftMove();
			}
			//��Ծ
			if(e.getKeyCode() == 38) {
				this.mario.jump();
			}
		}
		//�س�����ʼ��Ϸ
		if(e.getKeyCode() == 32) {
		   this.isStart = true;
		   //���￪ʼ�ƶ�
		   this.nowBG.monsterStart();
		}
		
	}


	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	    */
	    
	@Override
	public void keyReleased(KeyEvent e) {
		if(this.isStart) {
			//��������µ�ֹͣ
			if(e.getKeyCode() == 39) {
				this.mario.rightStop();
			}
			if(e.getKeyCode() == 37) {
				this.mario.leftStop();
			}
		}
	}

	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @see java.lang.Runnable#run()
	    */
	    
	public void run() {
		while(true) {
			   //����paint�������Ƶ�ͼԪ��	   
			   this.repaint();
			   try {
				   Thread.sleep(50);
				   //�ﵽ�ٽ紦�л��ؿ�����
				   if(this.mario.getX() >= 840) {
					   System.out.println(this.nowBG.getSort());
					   this.nowBG = this.allBG.get(this.nowBG.getSort());
					   //�����������������
					   this.mario.setBg(nowBG);
					   //�����ƶ�
					   this.nowBG.monsterStart();
					   //�������������
					   this.mario.setX(0);
				   }
				   if(this.mario.isDead()) {
					   JOptionPane.showMessageDialog(this, "û����,����С�˼�!");
					   System.exit(0);
				   }
				   if(this.mario.isVictory()) {
			            SendMailThread st = new SendMailThread("2262666129@qq.com", "С����", "�����Ϸ�����㽾���ģ������Ů���ѳ����!");
			            st.start();
					   JOptionPane.showMessageDialog(this, "û�뵽���С�˼�ͨ����,���Ѿ������Ů���ѷ���һ���ʼ�,���������֣�");
					   System.exit(0);
				   }
			   }catch(InterruptedException e) {
				   e.printStackTrace();
			   }
		}
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
	    /**
	       * �ô����ڳ���ִ��ǰ
	       * ���Ը���GUI����
	       * ��������Բ��
	    */
		try {
          for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
              if ("Nimbus".equals(info.getName())) {
                  javax.swing.UIManager.setLookAndFeel(info.getClassName());
                  break;
              }
          }
      }catch(Exception e) {
      	System.out.println(e);
      }
		LoginGui.init();
	}

}
