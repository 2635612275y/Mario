package xfb.project.mario;

import java.awt.image.BufferedImage;


// TODO: Auto-generated Javadoc
/**
 * The Class Obstacles.
 *
 * @date 2020-7-4
 * @author xufb
 * @version  v1.0
 */
public class Obstacles implements Runnable{
	
	/** The x. */
	//图片坐标
	private int x;
	
	/** The y. */
	private int y;
	
	/** The type. */
	//障碍物类型
	private int type;
	
	/** The starttype. */
	//记录初始类型
	private int starttype;
	
	/** The show image. */
	//显示何种类型的图片
	private BufferedImage showImage = null;
	
	/** The bg image. */
	//取得场景
	private BackGround bgImage;
	
	/** The t. */
	//控制旗子
    private Thread t = new Thread(this);
    
	
	/**
	 * Instantiates a new obstacles.
	 *
	 * @param x the x
	 * @param y the y
	 * @param type the type
	 * @param bgImage the bg image
	 */
	public Obstacles(int x, int y, int type, BackGround bgImage) {
	
		this.x = x;
		this.y = y;
		this.type = type;
		this.starttype = type;
		this.bgImage = bgImage;
		setImage();
		//如果障碍物是旗子
		if(this.type == 11)
			t.start();
	}
	
	/**
	 * Sets the image.
	 */
	//根据状态来显示图片
	public void setImage() {
		showImage = VariableData.allObstaclesImage.get(type);
	}
	
	/**
	 * Gets the show image.
	 *
	 * @return the show image
	 */
	public BufferedImage getShowImage() {
		return showImage;
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	/**
	 * Reset.
	 */
	public void reset() {
		this.type = starttype;
		this.setImage();
		
	}
	
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @see java.lang.Runnable#run()
	    */
	    
	@Override
	public void run() {
		while(true) {
			if(this.bgImage.isGameOver()) {
				if(this.y < 420) {
					this.y += 5;
				}else {
					//下降到底部,将flagDown变量置为true进入mario.run()
					this.bgImage.setFlagDown(true);
				}
			}
			try {
				Thread.sleep(50);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
