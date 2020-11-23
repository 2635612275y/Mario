package xfb.project.mario;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

    
// TODO: Auto-generated Javadoc
/**
 * The Class BackGround.
 *
 * @date 2020-7-4
 * @author xufb
 * @version  v1.0
 */
public class BackGround {

	/** The sort. */
	//场景顺序,第几关
	private int sort;
	
	/** The bg image. */
	//哪个背景
	private BufferedImage bgImage = null;
	
	/** The all monster. */
	//用集合保存敌人
	private List<Monster> allMonster = new ArrayList<Monster>();
	
	/** The remove monster. */
	//被消灭的敌人
	private List<Monster> removeMonster = new ArrayList<Monster>();
	
	/** The all obstacles. */
	//用集合保存障碍物
	private List<Obstacles> allObstacles = new ArrayList<Obstacles>();
	
	/** The remove obstacles. */
	//被消灭的障碍物
	private List<Obstacles> removeObstacles = new ArrayList<Obstacles>();
	
	/** The flag. */
	//游戏是否结束
	private boolean flag = false;
	
	/** The game over. */
	//游戏结束标记
	private boolean gameOver = false;
	
	/** The flag down. */
	//旗子是否降下
	private boolean flagDown = false;
	
	/**
	 * Instantiates a new back ground.
	 *
	 * @param sort the sort
	 * @param flag the flag
	 */
	public BackGround(int sort, boolean flag) {
		this.sort = sort;
		this.flag = flag;
		
		if(flag) {
			bgImage = VariableData.overImage;
		}else {
			if(this.sort == 1)
				bgImage = VariableData.bgImage;
			if(this.sort == 2)
				bgImage = VariableData.bg2Image;
			if(this.sort == 3)
				bgImage = VariableData.bg3Image;
			if(this.sort == 4)
				bgImage = VariableData.bg4Image;
			if(this.sort == 5)
				bgImage = VariableData.bg5Image;
			if(this.sort == 6) {
				bgImage = VariableData.bg6Image;
			}
		}
		//绘制第一关的画面
		if(sort == 1) {
			//地砖
			for(int i = 0; i < 15; i++){
				this.allObstacles.add(new Obstacles(i*60, 540, 9,this));
			}
			
			//0砖块和4问号
			this.allObstacles.add(new Obstacles(120, 360, 4, this));
			this.allObstacles.add(new Obstacles(300, 360, 0, this));
			this.allObstacles.add(new Obstacles(360, 360, 4, this));
			this.allObstacles.add(new Obstacles(420, 360, 0, this));
			this.allObstacles.add(new Obstacles(480, 360, 4, this));
			this.allObstacles.add(new Obstacles(540, 360, 0, this));
			this.allObstacles.add(new Obstacles(300, 180, 4, this));
			this.allObstacles.add(new Obstacles(360, 180, 0, this));
			this.allObstacles.add(new Obstacles(420, 180, 4, this));
			
			//第一关隐藏砖块3号
			this.allObstacles.add(new Obstacles(660, 300, 3, this));
			this.allObstacles.add(new Obstacles(480, 180, 3, this));
			
			//水管
			this.allObstacles.add(new Obstacles(660, 540, 6, this));
			this.allObstacles.add(new Obstacles(720, 540, 5, this));
			this.allObstacles.add(new Obstacles(660, 480, 8, this));
			this.allObstacles.add(new Obstacles(720, 480, 7, this));
			
			
			//绘制怪物
			this.allMonster.add(new Monster(600, 480, true, 1, this));
			//食人花最大高度y=420，最低高度y=540
			this.allMonster.add(new Monster(690, 540, true, 2, 420, 540, this));
			//飞鸟
			this.allMonster.add(new Monster(4, this, -60, 120));
		}
		//绘制第二关
		if(sort == 2){
			//地砖
			for(int i = 0; i < 15;i++){
				//悬崖
				if(i != 10 && i != 11 && i != 12){
					this.allObstacles.add(new Obstacles(i*60, 540, 9, this));
				}
			}
			//绘制水管
			//第一条
			this.allObstacles.add(new Obstacles(60, 540, 6, this));
			this.allObstacles.add(new Obstacles(120, 540, 5, this));
			this.allObstacles.add(new Obstacles(60, 480, 6, this));
			this.allObstacles.add(new Obstacles(120, 480, 5, this));
			this.allObstacles.add(new Obstacles(60, 420, 8, this));
			this.allObstacles.add(new Obstacles(120, 420, 7, this));
			//第二条
			this.allObstacles.add(new Obstacles(300, 540, 6, this));
			this.allObstacles.add(new Obstacles(360, 540, 5, this));
			this.allObstacles.add(new Obstacles(300, 480, 6, this));
			this.allObstacles.add(new Obstacles(360, 480, 5, this));
			this.allObstacles.add(new Obstacles(300, 420, 6, this));
			this.allObstacles.add(new Obstacles(360, 420, 5, this));
			this.allObstacles.add(new Obstacles(300, 360, 8, this));
			this.allObstacles.add(new Obstacles(360, 360, 7, this));
			//第三条
			this.allObstacles.add(new Obstacles(540, 540, 6, this));
			this.allObstacles.add(new Obstacles(600, 540, 5, this));
			this.allObstacles.add(new Obstacles(540, 480, 8, this));
			this.allObstacles.add(new Obstacles(600, 480, 7, this));
			
			
			//第一根食人花
			this.allMonster.add(new Monster(90, 380, true, 2, 360, 480, this));
			//绘制第二根水管食人花,上升最大高度为300，最低为420
			this.allMonster.add(new Monster(330, 360, true, 2, 300, 420, this));
			//第三根
			this.allMonster.add(new Monster(570, 540, true, 2 , 420, 540, this));			
		}
		//第三关
		if(sort == 3) {
			for(int i = 0; i < 15; i++) {
				this.allObstacles.add(new Obstacles(i*60, 540, 9, this));
			}
			//绘制砖块0和问号4
			this.allObstacles.add(new Obstacles(120, 360, 4, this));
			this.allObstacles.add(new Obstacles(360, 360, 4, this));
			this.allObstacles.add(new Obstacles(600, 360, 4, this));
			this.allObstacles.add(new Obstacles(720, 360, 4, this));
			this.allObstacles.add(new Obstacles(240, 180, 4, this));
			this.allObstacles.add(new Obstacles(660, 180, 4, this));
			this.allObstacles.add(new Obstacles(480, 180, 4, this));
			this.allObstacles.add(new Obstacles(180, 180, 0, this));
			this.allObstacles.add(new Obstacles(420, 360, 0, this));
			this.allObstacles.add(new Obstacles(480, 360, 0, this));
			this.allObstacles.add(new Obstacles(540, 180, 0, this));
			this.allObstacles.add(new Obstacles(600, 180, 0, this));
			this.allObstacles.add(new Obstacles(540, 360, 0, this));
			this.allObstacles.add(new Obstacles(780, 360, 0, this));
			//隐形砖块3
			this.allObstacles.add(new Obstacles(180, 360, 3, this));
			this.allObstacles.add(new Obstacles(660, 360, 3, this));
			//绘制三角蘑菇怪物
			this.allMonster.add(new Monster(720, 480, true, 1, this));
			//圆头蘑菇怪物
			this.allMonster.add(new Monster(500, 480, 3, this, true));
			//鸟
			this.allMonster.add(new Monster(4, this, -60, 100));
		}
		if(sort == 4) {
			for(int i = 0; i < 15; i++) {
				if(i < 2 || i > 13) {
					this.allObstacles.add(new Obstacles(i*60, 540, 9, this));
				}
				this.allObstacles.add(new Obstacles(180, 300, 0, this));
				this.allObstacles.add(new Obstacles(660, 360, 0, this));
				this.allObstacles.add(new Obstacles(660, 300, 0, this));
				this.allObstacles.add(new Obstacles(720, 360, 0, this));
				this.allObstacles.add(new Obstacles(300, 180, 0, this));
				this.allObstacles.add(new Obstacles(360, 180, 0, this));
				this.allObstacles.add(new Obstacles(420, 360, 0, this));
				this.allObstacles.add(new Obstacles(540, 360, 0, this));
				this.allObstacles.add(new Obstacles(420, 240, 0, this));
				this.allObstacles.add(new Obstacles(420, 300, 0, this));
				this.allObstacles.add(new Obstacles(420, 360, 0, this));
				this.allObstacles.add(new Obstacles(300, 340, 0, this));
				//隐形砖块儿
				this.allObstacles.add(new Obstacles(60, 360, 3, this));
				//鸟
				this.allMonster.add(new Monster(4, this, -120, 60));
			}
		}
		//第五关
		if(sort == 5) {
			for(int i = 0; i < 15; i++) {
				this.allObstacles.add(new Obstacles(i*60, 540, 9, this));
			}
            //绘制石头砖以及问号砖
			this.allObstacles.add(new Obstacles(480, 420, 1, this));
			this.allObstacles.add(new Obstacles(480, 360, 1, this));
			this.allObstacles.add(new Obstacles(480, 300, 1, this));
			this.allObstacles.add(new Obstacles(480, 240, 1, this));
			this.allObstacles.add(new Obstacles(540, 180, 1, this));
			this.allObstacles.add(new Obstacles(540, 240, 1, this));
			this.allObstacles.add(new Obstacles(600, 240, 1, this));
			this.allObstacles.add(new Obstacles(600, 300, 1, this));
			this.allObstacles.add(new Obstacles(660, 360, 1, this));
			this.allObstacles.add(new Obstacles(720, 420, 1, this));
			this.allObstacles.add(new Obstacles(780, 480, 1, this));
			this.allObstacles.add(new Obstacles(540, 360, 4, this));
			this.allObstacles.add(new Obstacles(600, 360, 4, this));
			//隐藏砖块
			this.allObstacles.add(new Obstacles(420, 300, 3, this));
			this.allObstacles.add(new Obstacles(240, 420, 3, this));
			//绘制三角蘑菇怪物
			this.allMonster.add(new Monster(300, 480, true, 1, this));
		}
		//第六关陡峭地形
		if(sort == 6) {
			this.allObstacles.add(new Obstacles(0, 540, 1, this));
			for(int i = 0; i <= 2; i++ ) {
				this.allObstacles.add(new Obstacles(120, 540 - (i*60), 1, this));
			}
			for(int i = 0; i <= 1; i++) {
				this.allObstacles.add(new Obstacles(240, 540 - (i*60), 1, this));
			}
			for(int i = 0; i <= 3; i++) {
				this.allObstacles.add(new Obstacles(360, 540 - (i*60), 1, this));
			}
			this.allObstacles.add(new Obstacles(480, 540, 1, this));
			//地板
			for(int i = 10; i < 15; i++) {
				this.allObstacles.add(new Obstacles(i*60, 540, 9, this));
			}
			//水管
			this.allObstacles.add(new Obstacles(660, 540, 6, this));
			this.allObstacles.add(new Obstacles(720, 540, 5, this));
			this.allObstacles.add(new Obstacles(660, 480, 8, this));
			this.allObstacles.add(new Obstacles(720, 480, 7, this));
			//食人花最大高度y=420，最低高度y=540
			this.allMonster.add(new Monster(690, 540, true, 2, 420, 540, this));
			//鸟
			this.allMonster.add(new Monster(4, this, -120, 60));
		}
		//最后一关
		if(sort == 7) {
			for(int i = 0; i < 15; i++) {
				this.allObstacles.add(new Obstacles(i*60, 540, 9, this));
			}
			//旗子
			this.allObstacles.add(new Obstacles(490, 180, 11, this));
			//底座，x坐标为520
			this.allObstacles.add(new Obstacles(520, 480, 6, this));
			//地上障碍物
			this.allObstacles.add(new Obstacles(180, 360, 1, this));
			this.allObstacles.add(new Obstacles(180, 420, 1, this));
			this.allObstacles.add(new Obstacles(180, 480, 1, this));
			this.allObstacles.add(new Obstacles(120, 420, 1, this));
			this.allObstacles.add(new Obstacles(120, 480, 1, this));
			this.allObstacles.add(new Obstacles(60, 480, 1, this));
			//隐藏砖块
			this.allObstacles.add(new Obstacles(420, 360, 3, this));
			//鸟
			this.allMonster.add(new Monster(4, this, -60, 60));
			this.allMonster.add(new Monster(4, this, -200, 60));
		}
			
	}
	
	/**
	 * Monster start.
	 */
	//敌人开始移动
	public void monsterStart(){
		for(int i = 0; i < this.allMonster.size(); i++) {
			this.allMonster.get(i).startMove();
		}
	}
	
	/**
	 * Reset.
	 */
	//重置障碍物和敌人
	public void reset() {
		//将移除的障碍物和敌人还原
		this.allMonster.addAll(this.removeMonster);
		this.allObstacles.addAll(this.removeObstacles);
		
		//调用障碍物和敌人的重置方法
		for(int i = 0; i < this.allObstacles.size(); i++) {
			this.allObstacles.get(i).reset();
		}
		for(int i = 0; i < this.allMonster.size(); i++) {
			this.allMonster.get(i).reset();
		}
	}
	
	/**
	 * Checks if is game over.
	 *
	 * @return true, if is game over
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * Sets the game over.
	 *
	 * @param gameOver the new game over
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/**
	 * Checks if is flag down.
	 *
	 * @return true, if is flag down
	 */
	public boolean isFlagDown() {
		return flagDown;
	}

	/**
	 * Sets the flag down.
	 *
	 * @param flagDown the new flag down
	 */
	public void setFlagDown(boolean flagDown) {
		this.flagDown = flagDown;
	}

	/**
	 * Gets the bg image.
	 *
	 * @return the bg image
	 */
	//获取背景
	public BufferedImage getBgImage() {
		return bgImage;
	}
	
	/**
	 * Gets the sort.
	 *
	 * @return the sort
	 */
	public int getSort() {
		return sort;
	}
	
	/**
	 * Checks if is flag.
	 *
	 * @return true, if is flag
	 */
	public boolean isFlag() {
		return flag;
	}
	
	/**
	 * Gets the all monster.
	 *
	 * @return the all monster
	 */
	public List<Monster> getAllMonster() {
		return allMonster;
	}
	
	/**
	 * Gets the all obstacles.
	 *
	 * @return the all obstacles
	 */
	public List<Obstacles> getAllObstacles(){
		return allObstacles;
	}
	
	/**
	 * Gets the removes the monster.
	 *
	 * @return the removes the monster
	 */
	public List<Monster> getRemoveMonster(){
		return removeMonster;
	}
	
	/**
	 * Gets the removes the obstacles.
	 *
	 * @return the removes the obstacles
	 */
	public List<Obstacles> getRemoveObstacles(){
		return removeObstacles;
	}
	
}
