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
	//���������
	private int x;
	
	/** The y. */
	private int y;
	
	/** The t. */
	//�����߳�
	private Thread t = null;
	
	/** The xmove. */
	//������ƶ��ٶ�
	private int xmove = 0;
	
	/** The ymove. */
	private int ymove = 0;
	
	/** The moving. */
	//��ǰ�ƶ��е�ͼƬ
	private int moving = 0;
	
	/** The uptime. */
	//��Ծʱ��
	private int uptime = 0;
	
	/** The status. */
	//�����״̬
	private String status;
	
	/** The bg. */
	//������������ڵĳ���
    private BackGround bg;
    
    /** The show image. */
    //��ʾͼƬ
    private BufferedImage showImage;
    
    /** The life. */
    //����ֵ
    private int life;
	
	/** The victory. */
	//��Ϸ�Ƿ����
	private boolean victory = false;
    
    /** The is dead. */
    //�ж�������Ƿ�����
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
    	//����ֵΪ3
    	this.life = 3;
    	//��ʼ�������ͼƬ
    	this.showImage = VariableData.allMarioImage.get(0);
    	
    	this.t = new Thread(this);
    	t.start();
    	
    	//��ʼ״̬Ϊ����վ��
    	this.status = "right-standing";
    }
    
    /**
     * Left move.
     */
    //����
    public void leftMove() {
    	//�ƶ��ٶ�
    	xmove = -5;
    	//��⵽��Ծ����,����,����ʱ�򱣳���Ծ����״̬����
    	if(this.status.indexOf("jumping") != -1) {
    		this.status = "left-jumping";
    	}else {
    		//û����Ծ�����������ָ������ǰ��
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
    //��ͣ
    public void leftStop() {
    	this.xmove = 0;
    	if(this.status.indexOf("jumping") != -1) {
    		this.status = "left-jumping";
    	}else {
    		//��״̬��Ϊ��ͣ
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
    //��Ծ
    public void jump(){
    	//ԭ��״̬��ⲻ����Ծ����
    	if(this.status.indexOf("jumping") == -1) {
    		//��������
    		if(this.status.indexOf("left") != -1) {
    			//��״̬��Ϊ����
    			this.status = "left-jumping";
    		}else {
    			//��״̬��Ϊ����
    			this.status = "right-jumping";
    		}
    		//�����ٶ�
    		ymove = -10;
    		uptime = 18;
    		
    	}
    }
    
    /**
     * Down.
     */
    //���䣬����Ծԭ��һ�������ٶȸ�Ϊ����
    public void down() {
    	//״̬Ϊ����
    	if(this.status.indexOf("left") != -1) {
    		this.status = "left-jumping";
    	}else {
    		this.status = "right-jumping";
    	}
    	ymove = 10;
    }

	
	    /* (�� Javadoc)
	    * 
	    * 
	    * @see java.lang.Runnable#run()
	    */
	    
	@Override
	public void run() {
		while(true) {
			//�ж��Ƿ����ϰ�����ײ
			//��������Ϸ�Ƿ����,520�������ڵ�
			if(this.bg.isFlag() &&  this.x >= 520) {
				//��Ϸ����
				this.bg.setGameOver(true);
				if(this.bg.isFlagDown()) {
					//���������¿�ʼ�����ƶ����������״̬��Ϊ��վ��
					this.status = "right-moving";
					if(this.x < 580) {
						this.x += 5;//������ڵײ�������Զ������ƶ�����Ǳ�
					}else {
						if(this.y < 480) {
							//���������
							this.y += 5;
						}
						this.x += 5;//���������
						if(this.x >= 780) {
							//��Ϸʤ��
							this.setVictory(true);
						}
					}
				}else {
					//��������x���곬��520
					//������������������������
					if(this.y < 420) {
						this.y += 5;
					}
					if(this.y >= 420) {
						//����²������������ƣ���״̬Ϊ��ս��
						this.y = 420;
						this.status = "right-standing";
					}
				}
			}else {
			boolean canLeft = true;
			boolean canRight = true;
			//�ܷ���Ծ
			boolean onLand = false;
			for(int i = 0; i < this.bg.getAllObstacles().size(); i++) {
				Obstacles ob = this.bg.getAllObstacles().get(i);
				//��������ϰ�����ײ
				
				//��������
				if(ob.getX() == this.x + 60 && (ob.getY() + 50 > this.y && ob.getY() - 50 < this.y)) {
					if(ob.getType() != 3) {//��Ϊ��ש
						canRight = false;
					}
				}
				//��������
				if(ob.getX() == this.x - 60 && (ob.getY() + 50 > this.y && ob.getY() - 50 < this.y)) {
					if(ob.getType() != 3) {//��Ϊ��ש
						canLeft = false;
				}
			}
				//�ж��ܷ���Ծ
				if(ob.getY() == this.y + 60 && (ob.getX() + 60 > this.x && ob.getX() - 60 < this.x)) {
					if(ob.getType() != 3) {//��Ϊ��ש
						onLand = true;
					}
				}
					//�ж��������Ծʱ�Ƿ�ײ���ϰ���
					if(ob.getY() == this.y - 60 && (ob.getX() + 50 > this.x && ob.getX() - 50 < this.x)) {
						//�����ש��
						if(ob.getType() == 0) {
							//�Ƴ�ש��
							this.bg.getAllObstacles().remove(ob);
							//���浽�Ƴ����ϰ���list������
							this.bg.getRemoveObstacles().add(ob);
						}
						//������ʺŻ������ص�ש��
						if((ob.getType() == 4 || ob.getType() == 3) && uptime > 0){
							//����ש��߳�ʵ��ש��
							ob.setType(2);
							//����ʵ��ש��
							ob.setImage();
						}
						uptime = 0;
						}
					}
			//�Ե��˵��ж�
			for(int i = 0; i < this.bg.getAllMonster().size(); i++) {
				Monster e = this.bg.getAllMonster().get(i);
				if((e.getX() - 60 < this.x && e.getX() + 60 > this.x) 
						&& (e.getY() - 60 < this.y && e.getY() + 60 > this.y)) {
					//���������;
					this.dead();
				}
				if(e.getY() == this.y + 60 && (e.getX() - 60 < this.x && e.getX() + 60 > this.x)) {
					//��������ǹ�
					if(e.getType() == 1) {
						//����Ģ��������
						e.dead();
						//����һ��,����Ϸ����ʵ
						this.uptime = 4;
						//����
						this.ymove = -10;
					}else if(e.getType() == 2) {
						//�����ʳ�˻�,���������
						this.dead();
					}else if(e.getType() == 3) {
						//����ԲͷĢ��������
						e.dead();
						//����һ��,����Ϸ����ʵ
						this.uptime = 4;
						//����
						this.ymove = -10;
					}else if(e.getType() == 4) {
						//�ȵ���
						e.dead();
						//����һ��,����Ϸ����ʵ
						this.uptime = 4;
						//����
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
				//����״̬
				if(uptime != 0) {
					uptime--;
				}else {
					this.down();
				}
				y += ymove;
			}
			//�Ƿ��������
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
			
//����ͼƬ1234����ͼƬ6789����Ծ����ͼƬ5����Ծ����ͼƬ10
			//��¼λ��ͼƬ����ʼ��1
			int temp = 0;
			//����״̬�������ͼƬ
			if(this.status.indexOf("left") != -1) {
				temp += 5;
			}
			//�ж��Ƿ��ƶ�
			if(this.status.indexOf("moving") != -1) {
				temp += this.moving;
				//�ƶ�����λ��ͼƬ�ƶ�
				moving++;
				if(moving == 4) {
					//����ͼƬ����Ϊ�㣬ѭ��
					this.moving = 0;
				}
			}
			//��ԾͼƬ
			if(this.status.indexOf("jumping") != -1) {
				temp += 4;
			}
			//���������״̬��ʾͼƬ
			this.showImage = VariableData.allMarioImage.get(temp);
			//������ƶ�ʱ������
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
	//�������������
	public void dead() {
		this.life--;
		if(this.life == 0) {
			this.isDead = true;
		}else {
			this.bg.reset();//���û���
			this.x = 0;//�������������
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
