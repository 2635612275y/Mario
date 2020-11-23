package xfb.project.mario;

import java.awt.image.BufferedImage;

import xfb.project.mario.BackGround;

// TODO: Auto-generated Javadoc
/**
 * The Class Monster.
 *
 * @date 2020-7-4
 * @author xufb
 * @version  v1.0
 */
public class Monster implements Runnable{

	/** The x. */
	//怪物坐标
	private int x;
	
	/** The y. */
	private int y;
	
	/** The startx. */
	//怪物初始坐标
	private int startx;
	
	/** The starty. */
	private int starty;
	
	/** The type. */
	//怪物类型
	private int type;
	
	/** The show image. */
	//显示图片
	private BufferedImage showImage;
	
	/** The is left change. */
	//移动方向
	private boolean isLeftChange = true;
	
	/** The up max. */
	//食人花上下移动的范围
	private int upMax = 0;
	
	/** The down max. */
	private int downMax = 0;
	
	/** The image change. */
	//定义图片变化
	private int imageChange = 0;
	
	/** The bg image. */
	//定义所在场景
	private BackGround bgImage;
	
	/** The t. */
	//加入线程
	private Thread t = null;
	
	/** The threadsuspend. */
	private boolean threadsuspend;
	
	/**
	 * Instantiates a new monster.
	 *
	 * @param x the x
	 * @param y the y
	 * @param isLeft the is left
	 * @param type the type
	 * @param bgImage the bg image
	 */
	public Monster(int x, int y, boolean isLeft, int type, BackGround bgImage) {
		this.x = x;
		this.y = y;
		this.startx = x;
		this.starty = y;
		this.isLeftChange = isLeft;
		this.type = type;
		this.bgImage = bgImage;
		//如果是三角怪
		if(this.type == 1) {
			this.showImage = VariableData.allTriangleImage.get(0);
		}

		this.t = new Thread(this);
		t.start();
	}
	
	/**
	 * Instantiates a new monster.
	 *
	 * @param x the x
	 * @param y the y
	 * @param type the type
	 * @param bgImage the bg image
	 * @param isLeft the is left
	 */
	public Monster(int x, int y, int type, BackGround bgImage, boolean isLeft) {
		this.x = x;
		this.y = y;
		this.startx = x;
		this.starty = y;
		this.isLeftChange = isLeft;
		this.type = type;
		this.bgImage = bgImage;
		//如果是蘑菇怪
		if(this.type == 3) {
			this.showImage = VariableData.allMushroomImage.get(0);
		}

		this.t = new Thread(this);
		t.start();
	}
	
	/**
	 * Instantiates a new monster.
	 *
	 * @param x the x
	 * @param y the y
	 * @param isUp the is up
	 * @param type the type
	 * @param upMax the up max
	 * @param downMax the down max
	 * @param bgImage the bg image
	 */
	public Monster(int x, int y, boolean isUp, int type, int upMax, int downMax, BackGround bgImage) {
		this.x = x;
		this.y = y;
		this.startx = x;
		this.starty = y;
		this.isLeftChange = isUp;
		this.type = type;
		this.upMax = upMax;
		this.downMax = downMax;
		this.bgImage = bgImage;
		//如果是食人花
		if(this.type == 2) {
			this.showImage = VariableData.allFlowerImage.get(0);	
		}
		
		this.t = new Thread(this);
		t.start();
	}
	
	/**
	 * Instantiates a new monster.
	 *
	 * @param type the type
	 * @param bgImage the bg image
	 * @param x the x
	 * @param y the y
	 */
	public Monster(int type, BackGround bgImage, int x, int y) {
		this.x = x;
		this.y = y;
		this.startx = x;
		this.starty = y;
		this.type = type;
		this.bgImage = bgImage;
		//如果是飞鸟
		if(this.type == 4) {
			this.showImage = VariableData.allBirdsImage.get(0);	
		}
		
		this.t = new Thread(this);
		t.start();
	}
	
	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @see java.lang.Runnable#run()
	    */
	    
	@Override
	public void run() {
		while(true){
			//判断怪物类型
			if(type == 1) {
				if(this.isLeftChange) {
				this.x -= 3;
			}else {
				this.x += 3;
			}
			if(imageChange == 0) {
				imageChange = 1;
			}else {
				imageChange = 0;
			}
			
			//能否移动
			boolean canLeft = true;
			boolean canRight = true;
			
			for(int i = 0; i < this.bgImage.getAllObstacles().size(); i++) {
				Obstacles ob = this.bgImage.getAllObstacles().get(i);
				//不能右移
				if(ob.getX() == this.x + 60 && (ob.getY() + 50 > this.y && ob.getY() - 50 < this.y)) {
					canRight = false;
				}
				//不能左移
				if(ob.getX() == this.x - 60 && (this.y < ob.getY() && this.type > ob.getY() - 50)) {
					canLeft = false;
				}
			}
			if(!canLeft && this.isLeftChange || this.x == 0) {
				this.isLeftChange = false;
			}else if(!canRight && !this.isLeftChange || this.x == 840) {
				this.isLeftChange = true;
			}
			
			this.showImage = VariableData.allTriangleImage.get(imageChange);
		}
			//食人花运动
	    if(type == 2) {
	    	if(this.isLeftChange) {
	    		this.y -= 5;
	    	}else {
	    		this.y += 5;
	    	}
	    	if(imageChange == 0) {
	    		imageChange = 1;
	    	}else {
	    		imageChange = 0;
	    	}
	    	//达到最大高度后改变isleftchage开始下降
	    	if(this.isLeftChange && this.y == this.upMax) {
	    		this.isLeftChange = false;
	    	}
	    	//达到最低高度时改变isleftchange开始上升
	    	if(!this.isLeftChange && this.y == this.downMax) {
	    		this.isLeftChange = true;
	    	}
	    	//图片交互改变
	    	this.showImage = VariableData.allFlowerImage.get(imageChange);
	    }
	    //蘑菇怪
	    if(type == 3) {
			this.x -= 5;
		if(this.x == 0) {
			this.x = 960;
		}
		if(imageChange == 0) {
			imageChange = 1;
		}else {
			imageChange = 0;
		}
			
		this.showImage = VariableData.allMushroomImage.get(imageChange);
	    }
	    
	    //飞鸟
	    if(type == 4) {
			this.x += 5;
		if(this.x == 840) {
			this.x = -60;
		}
		if(imageChange == 0) {
			imageChange = 1;
		}else {
			imageChange = 0;
		}
		
		this.showImage = VariableData.allBirdsImage.get(imageChange);
	    }
	    
		try {
			Thread.sleep(100);
		}catch(InterruptedException e) {
			e.printStackTrace();
		 }
	  }
		
	}
	
	/**
	 * Dead.
	 */
	//怪物死亡
	public void dead() {
		//移除死亡的三角怪图片
		this.bgImage.getAllMonster().remove(this);
		//存入remove集合
		this.bgImage.getRemoveMonster().add(this);
	}
	
	/**
	 * Reset.
	 */
	//敌人重置
	public void reset() {
		//还原坐标
		this.x = this.startx;
		this.y = this.starty;
				//还原图片
				if(this.type == 1) {
					//还原三角怪
					this.showImage = VariableData.allTriangleImage.get(0);
				}else if(this.type == 2) {
					//还原食人花
					this.showImage = VariableData.allFlowerImage.get(0);
				}
	}
	
	/**
	 * Gets the bg image.
	 *
	 * @return the bg image
	 */
	public BackGround getBgImage() {
		return bgImage;
	}
	
	/**
	 * Sets the bg image.
	 *
	 * @param bgImage the new bg image
	 */
	public void setBgImage(BackGround bgImage) {
		this.bgImage = bgImage;
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
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
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
	 * Gets the show image.
	 *
	 * @return the show image
	 */
	public BufferedImage getShowImage() {
		return showImage;
	}
	
	/**
	 * Start move.
	 */
	public void startMove() {
			t.resume();
	}
	

}
