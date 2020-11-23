package xfb.project.mario;

import java.awt.image.BufferedImage;



// TODO: Auto-generated Javadoc
/**
 * The Class Mario.
 *
 * @date 2020-7-4
 * @author xufb
 * @version  v1.0
 */
public class Mario implements Runnable{
	
	/** The x. */
	//马里奥坐标
	private int x;
	
	/** The y. */
	private int y;
	
	/** The t. */
	//加入线程
	private Thread t = null;
	
	/** The xmove. */
	//马里奥移动速度
	private int xmove = 0;
	
	/** The ymove. */
	private int ymove = 0;
	
	/** The moving. */
	//当前移动中的图片
	private int moving = 0;
	
	/** The uptime. */
	//跳跃时间
	private int uptime = 0;
	
	/** The status. */
	//马里奥状态
	private String status;
	
	/** The bg. */
	//定义马里奥所在的场景
    private BackGround bg;
    
    /** The show image. */
    //显示图片
    private BufferedImage showImage;
    
    /** The life. */
    //生命值
    private int life;
	
	/** The victory. */
	//游戏是否完成
	private boolean victory = false;
    
    /** The is dead. */
    //判断马里奥是否死亡
    private boolean isDead = false;
    
    /**
     * Instantiates a new mario.
     *
     * @param x the x
     * @param y the y
     */
    public Mario(int x, int y) {
    	this.x = x;
    	this.y = y;
    	//生命值为3
    	this.life = 3;
    	//初始化马里奥图片
    	this.showImage = VariableData.allMarioImage.get(0);
    	
    	this.t = new Thread(this);
    	t.start();
    	
    	//初始状态为朝右站立
    	this.status = "right-standing";
    }
    
    /**
     * Left move.
     */
    //左走
    public void leftMove() {
    	//移动速度
    	xmove = -5;
    	//检测到跳跃动作,左跳,跳的时候保持跳跃动作状态不变
    	if(this.status.indexOf("jumping") != -1) {
    		this.status = "left-jumping";
    	}else {
    		//没有跳跃动作则继续朝指定方向前进
    		this.status = "left-moving";
    	}
    }
    
    /**
     * Right move.
     */
    public void rightMove() {
    	xmove = 5;
    	if(this.status.indexOf("jumping") != -1) {
    		this.status = "right-jumping";
    	}else {
    		this.status = "right-moving";
    	}
    }
    
    /**
     * Left stop.
     */
    //坐停
    public void leftStop() {
    	this.xmove = 0;
    	if(this.status.indexOf("jumping") != -1) {
    		this.status = "left-jumping";
    	}else {
    		//将状态改为左停
    		this.status = "left-standing";
    	}
    }
    
    /**
     * Right stop.
     */
    public void rightStop() {
    	this.xmove = 0;
    	if(this.status.indexOf("jumping") != -1){
    		this.status ="right-jumping";
    	}else {
    		this.status = "right-standing";
    	}
    }
    
    /**
     * Jump.
     */
    //跳跃
    public void jump(){
    	//原有状态检测不到跳跃动作
    	if(this.status.indexOf("jumping") == -1) {
    		//且有向左
    		if(this.status.indexOf("left") != -1) {
    			//将状态改为左跳
    			this.status = "left-jumping";
    		}else {
    			//将状态改为右跳
    			this.status = "right-jumping";
    		}
    		//上跳速度
    		ymove = -10;
    		uptime = 18;
    		
    	}
    }
    
    /**
     * Down.
     */
    //下落，跟跳跃原理一样，把速度改为向下
    public void down() {
    	//状态为向左
    	if(this.status.indexOf("left") != -1) {
    		this.status = "left-jumping";
    	}else {
    		this.status = "right-jumping";
    	}
    	ymove = 10;
    }

	
	    /* (非 Javadoc)
	    * 
	    * 
	    * @see java.lang.Runnable#run()
	    */
	    
	@Override
	public void run() {
		while(true) {
			//判断是否与障碍物碰撞
			//定义标记游戏是否结束,520旗子所在点
			if(this.bg.isFlag() &&  this.x >= 520) {
				//游戏结束
				this.bg.setGameOver(true);
				if(this.bg.isFlagDown()) {
					//降旗后马里奥开始向下移动，将马里奥状态改为右站立
					this.status = "right-moving";
					if(this.x < 580) {
						this.x += 5;//如果就在底部马里奥自动向右移动进入城堡
					}else {
						if(this.y < 480) {
							//马里奥下移
							this.y += 5;
						}
						this.x += 5;//马里奥右移
						if(this.x >= 780) {
							//游戏胜利
							this.setVictory(true);
						}
					}
				}else {
					//如果马里奥x坐标超过520
					//如果马里奥在旗杆上马里奥下移
					if(this.y < 420) {
						this.y += 5;
					}
					if(this.y >= 420) {
						//马里奥不动，旗子下移，且状态为右战力
						this.y = 420;
						this.status = "right-standing";
					}
				}
			}else {
			boolean canLeft = true;
			boolean canRight = true;
			//能否跳跃
			boolean onLand = false;
			for(int i = 0; i < this.bg.getAllObstacles().size(); i++) {
				Obstacles ob = this.bg.getAllObstacles().get(i);
				//马里奥与障碍物相撞
				
				//不能右移
				if(ob.getX() == this.x + 60 && (ob.getY() + 50 > this.y && ob.getY() - 50 < this.y)) {
					if(ob.getType() != 3) {//不为土砖
						canRight = false;
					}
				}
				//不能左移
				if(ob.getX() == this.x - 60 && (ob.getY() + 50 > this.y && ob.getY() - 50 < this.y)) {
					if(ob.getType() != 3) {//不为土砖
						canLeft = false;
				}
			}
				//判断能否跳跃
				if(ob.getY() == this.y + 60 && (ob.getX() + 60 > this.x && ob.getX() - 60 < this.x)) {
					if(ob.getType() != 3) {//不为土砖
						onLand = true;
					}
				}
					//判断马里奥跳跃时是否撞到障碍物
					if(ob.getY() == this.y - 60 && (ob.getX() + 50 > this.x && ob.getX() - 50 < this.x)) {
						//如果是砖块
						if(ob.getType() == 0) {
							//移除砖块
							this.bg.getAllObstacles().remove(ob);
							//保存到移除的障碍物list集合中
							this.bg.getRemoveObstacles().add(ob);
						}
						//如果是问号或者隐藏的砖块
						if((ob.getType() == 4 || ob.getType() == 3) && uptime > 0){
							//隐藏砖块边成实体砖块
							ob.setType(2);
							//绘制实体砖块
							ob.setImage();
						}
						uptime = 0;
						}
					}
			//对敌人的判断
			for(int i = 0; i < this.bg.getAllMonster().size(); i++) {
				Monster e = this.bg.getAllMonster().get(i);
				if((e.getX() - 60 < this.x && e.getX() + 60 > this.x) 
						&& (e.getY() - 60 < this.y && e.getY() + 60 > this.y)) {
					//马里奥死亡;
					this.dead();
				}
				if(e.getY() == this.y + 60 && (e.getX() - 60 < this.x && e.getX() + 60 > this.x)) {
					//如果是三角怪
					if(e.getType() == 1) {
						//三角蘑菇怪死亡
						e.dead();
						//弹跳一下,让游戏更现实
						this.uptime = 4;
						//下落
						this.ymove = -10;
					}else if(e.getType() == 2) {
						//如果是食人花,马里奥死亡
						this.dead();
					}else if(e.getType() == 3) {
						//三角圆头蘑菇怪死亡
						e.dead();
						//弹跳一下,让游戏更现实
						this.uptime = 4;
						//下落
						this.ymove = -10;
					}else if(e.getType() == 4) {
						//踩到鸟
						e.dead();
						//弹跳一下,让游戏更现实
						this.uptime = 4;
						//下落
						this.ymove = -10;
					}
				}
			}
			
			if(onLand && uptime == 0) {
				if(this.status.indexOf("left") != -1) {
					if(xmove != 0) {
						this.status = "left-moving";
					}else {
						this.status = "left-standing";
				}
			}else {
				if(xmove != 0) {
					this.status = "right-moving";
				}else {
					this.status = "right-standing";
				}
			}
			}else {
				//上升状态
				if(uptime != 0) {
					uptime--;
				}else {
					this.down();
				}
				y += ymove;
			}
			//是否掉入悬崖
			if(this.y > 600) {
				this.dead();
			}
			if(canLeft && xmove < 0 || canRight && xmove > 0) {
				x += xmove;
				if(x < 0) {
					x = 0;
				}
			}
			}
			
//右走图片1234左走图片6789右跳跃下落图片5左跳跃下落图片10
			//记录位置图片，初始右1
			int temp = 0;
			//向左状态的马里奥图片
			if(this.status.indexOf("left") != -1) {
				temp += 5;
			}
			//判断是否移动
			if(this.status.indexOf("moving") != -1) {
				temp += this.moving;
				//移动的是位置图片移动
				moving++;
				if(moving == 4) {
					//四张图片后置为零，循环
					this.moving = 0;
				}
			}
			//跳跃图片
			if(this.status.indexOf("jumping") != -1) {
				temp += 4;
			}
			//根据马里奥状态显示图片
			this.showImage = VariableData.allMarioImage.get(temp);
			//马里奥移动时流畅度
			try {
				Thread.sleep(50);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
    
	/**
	 * Dead.
	 */
	//马里奥死亡方法
	public void dead() {
		this.life--;
		if(this.life == 0) {
			this.isDead = true;
		}else {
			this.bg.reset();//重置画面
			this.x = 0;//重置马里奥坐标
			this.y = 480;
			this.status = "right-standing";
		}
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
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Sets the bg.
	 *
	 * @param bg the new bg
	 */
	public void setBg(BackGround bg) {
		this.bg = bg;
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
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	
	/**
	 * Gets the life.
	 *
	 * @return the life
	 */
	public int getLife() {
		return life;
	}
	
	/**
	 * Sets the life.
	 *
	 * @param life the new life
	 */
	public void setLife(int life) {
		this.life = life;
	}
	
	/**
	 * Checks if is dead.
	 *
	 * @return true, if is dead
	 */
	public boolean isDead() {
		return isDead;
	}
	
	/**
	 * Checks if is victory.
	 *
	 * @return true, if is victory
	 */
	public boolean isVictory() {
		return victory;
	}
	
	/**
	 * Sets the victory.
	 *
	 * @param victory the new victory
	 */
	public void setVictory(boolean victory) {
		this.victory = victory;
	}
	
}
