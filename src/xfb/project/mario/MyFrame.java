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
	//加入线程
	private Thread t = null;
	
	/** The now BG. */
	//暂时存储当时背景
	private BackGround nowBG = null;
    
    /** The life image. */
    //生命值图片 
	public static BufferedImage lifeImage = null;
	
	/** The path. */
	private String path = System.getProperty("user.dir") + "/pic/";
	
	/** The is start. */
	//是否已经开始游戏
	private boolean isStart = false;
	
	/**
	 * Instantiates a new my frame.
	 */
	public MyFrame(){
		this.setTitle("Super Mario");
		this.setSize(900, 600);
		
		ImageIcon ig = new ImageIcon(path + "登录图标.png");//修改游戏窗口图标
		Image im = ig.getImage();
		this.setIconImage(im);
		
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width-900)/2, (height-600)/2);
		this.setResizable(false);
		//初始化图片
		VariableData.init();
		
		//创建全部场景
		for(int i = 1;i <= 7;i++){
			this.allBG.add(new BackGround(i, i==7?true:false));
		}
		
		//将第一个场景设置为当前场景
		this.nowBG = this.allBG.get(0);
		//初始化玛丽奥
		this.mario = new Mario(0, 480);
		
		//将玛丽奥放入场景中
		this.mario.setBg(nowBG);
		this.addKeyListener(this);
		
		this.t = new Thread(this);
		t.start();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
    
        
    //绘制场景
    public void paint(Graphics g) {
    	//双缓存
    	BufferedImage image = new BufferedImage(900, 600, BufferedImage.TYPE_3BYTE_BGR);
    	Graphics g2 =  image.getGraphics();
    	
    	if(this.isStart == true) {
    		
    		//游戏开始绘制关卡背景
    		g2.drawImage(this.nowBG.getBgImage(), 0, 0, this);

    		//绘制怪物
    		Iterator<Monster> iterMonster = this.nowBG.getAllMonster().iterator();
    		while(iterMonster.hasNext()) {
    			Monster e = iterMonster.next();
    			g2.drawImage(e.getShowImage(), e.getX(), e.getY(), this);
    		}
    		
			//生命值图片
			try {
				lifeImage = ImageIO.read(new File(path + "生命值.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		//绘制马里奥生命值
    		for(int i = 0; i < this.mario.getLife(); i++) {		
    			g2.drawImage(lifeImage, (720 + i*60), 50, this);
    		}
    		
    		//绘制障碍物
    		Iterator<Obstacles> iterObstacles = this.nowBG.getAllObstacles().iterator();
    	    while(iterObstacles.hasNext()) {
    	    	Obstacles ob = iterObstacles.next();
    	    	g2.drawImage(ob.getShowImage(), ob.getX(), ob.getY(), this);
    	    }
    	    //绘制马里奥
    	    g2.drawImage(this.mario.getShowImage(), this.mario.getX(), this.mario.getY(), this);
    	}else {
    		//绘制是否开始游戏界面
    		g2.drawImage(VariableData.startImage, 0, 0, this);
    	}
    	
    	//讲缓存图片绘制进去
    	g.drawImage(image, 0, 0, this);
    }
	
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	    */
	    
	public void keyTyped(KeyEvent e) {
		
	}


	
	    /* (非 Javadoc)
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
			//跳跃
			if(e.getKeyCode() == 38) {
				this.mario.jump();
			}
		}
		//回车键开始游戏
		if(e.getKeyCode() == 32) {
		   this.isStart = true;
		   //怪物开始移动
		   this.nowBG.monsterStart();
		}
		
	}


	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @param e
	    * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	    */
	    
	@Override
	public void keyReleased(KeyEvent e) {
		if(this.isStart) {
			//控制马里奥的停止
			if(e.getKeyCode() == 39) {
				this.mario.rightStop();
			}
			if(e.getKeyCode() == 37) {
				this.mario.leftStop();
			}
		}
	}

	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @see java.lang.Runnable#run()
	    */
	    
	public void run() {
		while(true) {
			   //调用paint方法绘制地图元素	   
			   this.repaint();
			   try {
				   Thread.sleep(50);
				   //达到临界处切换关卡场景
				   if(this.mario.getX() >= 840) {
					   System.out.println(this.nowBG.getSort());
					   this.nowBG = this.allBG.get(this.nowBG.getSort());
					   //将场景放入马里奥中
					   this.mario.setBg(nowBG);
					   //怪物移动
					   this.nowBG.monsterStart();
					   //重置马里奥坐标
					   this.mario.setX(0);
				   }
				   if(this.mario.isDead()) {
					   JOptionPane.showMessageDialog(this, "没过关,你这小菜鸡!");
					   System.exit(0);
				   }
				   if(this.mario.isVictory()) {
			            SendMailThread st = new SendMailThread("2262666129@qq.com", "小笨蛋", "玩个游戏看把你骄傲的，你跟你女朋友迟早分!");
			            st.start();
					   JOptionPane.showMessageDialog(this, "没想到你个小菜鸡通关了,我已经给你的女朋友发了一封邮件,你跟她迟早分！");
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
	       * 该代码在程序执行前
	       * 可以改善GUI界面
	       * 更加美观圆滑
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
