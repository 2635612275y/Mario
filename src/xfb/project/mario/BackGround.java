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
	//����˳��,�ڼ���
	private int sort;
	
	/** The bg image. */
	//�ĸ�����
	private BufferedImage bgImage = null;
	
	/** The all monster. */
	//�ü��ϱ������
	private List<Monster> allMonster = new ArrayList<Monster>();
	
	/** The remove monster. */
	//������ĵ���
	private List<Monster> removeMonster = new ArrayList<Monster>();
	
	/** The all obstacles. */
	//�ü��ϱ����ϰ���
	private List<Obstacles> allObstacles = new ArrayList<Obstacles>();
	
	/** The remove obstacles. */
	//��������ϰ���
	private List<Obstacles> removeObstacles = new ArrayList<Obstacles>();
	
	/** The flag. */
	//��Ϸ�Ƿ����
	private boolean flag = false;
	
	/** The game over. */
	//��Ϸ�������
	private boolean gameOver = false;
	
	/** The flag down. */
	//�����Ƿ���
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
		//���Ƶ�һ�صĻ���
		if(sort == 1) {
			//��ש
			for(int i = 0; i < 15; i++){
				this.allObstacles.add(new Obstacles(i*60, 540, 9,this));
			}
			
			//0ש���4�ʺ�
			this.allObstacles.add(new Obstacles(120, 360, 4, this));
			this.allObstacles.add(new Obstacles(300, 360, 0, this));
			this.allObstacles.add(new Obstacles(360, 360, 4, this));
			this.allObstacles.add(new Obstacles(420, 360, 0, this));
			this.allObstacles.add(new Obstacles(480, 360, 4, this));
			this.allObstacles.add(new Obstacles(540, 360, 0, this));
			this.allObstacles.add(new Obstacles(300, 180, 4, this));
			this.allObstacles.add(new Obstacles(360, 180, 0, this));
			this.allObstacles.add(new Obstacles(420, 180, 4, this));
			
			//��һ������ש��3��
			this.allObstacles.add(new Obstacles(660, 300, 3, this));
			this.allObstacles.add(new Obstacles(480, 180, 3, this));
			
			//ˮ��
			this.allObstacles.add(new Obstacles(660, 540, 6, this));
			this.allObstacles.add(new Obstacles(720, 540, 5, this));
			this.allObstacles.add(new Obstacles(660, 480, 8, this));
			this.allObstacles.add(new Obstacles(720, 480, 7, this));
			
			
			//���ƹ���
			this.allMonster.add(new Monster(600, 480, true, 1, this));
			//ʳ�˻����߶�y=420����͸߶�y=540
			this.allMonster.add(new Monster(690, 540, true, 2, 420, 540, this));
			//����
			this.allMonster.add(new Monster(4, this, -60, 120));
		}
		//���Ƶڶ���
		if(sort == 2){
			//��ש
			for(int i = 0; i < 15;i++){
				//����
				if(i != 10 && i != 11 && i != 12){
					this.allObstacles.add(new Obstacles(i*60, 540, 9, this));
				}
			}
			//����ˮ��
			//��һ��
			this.allObstacles.add(new Obstacles(60, 540, 6, this));
			this.allObstacles.add(new Obstacles(120, 540, 5, this));
			this.allObstacles.add(new Obstacles(60, 480, 6, this));
			this.allObstacles.add(new Obstacles(120, 480, 5, this));
			this.allObstacles.add(new Obstacles(60, 420, 8, this));
			this.allObstacles.add(new Obstacles(120, 420, 7, this));
			//�ڶ���
			this.allObstacles.add(new Obstacles(300, 540, 6, this));
			this.allObstacles.add(new Obstacles(360, 540, 5, this));
			this.allObstacles.add(new Obstacles(300, 480, 6, this));
			this.allObstacles.add(new Obstacles(360, 480, 5, this));
			this.allObstacles.add(new Obstacles(300, 420, 6, this));
			this.allObstacles.add(new Obstacles(360, 420, 5, this));
			this.allObstacles.add(new Obstacles(300, 360, 8, this));
			this.allObstacles.add(new Obstacles(360, 360, 7, this));
			//������
			this.allObstacles.add(new Obstacles(540, 540, 6, this));
			this.allObstacles.add(new Obstacles(600, 540, 5, this));
			this.allObstacles.add(new Obstacles(540, 480, 8, this));
			this.allObstacles.add(new Obstacles(600, 480, 7, this));
			
			
			//��һ��ʳ�˻�
			this.allMonster.add(new Monster(90, 380, true, 2, 360, 480, this));
			//���Ƶڶ���ˮ��ʳ�˻�,�������߶�Ϊ300�����Ϊ420
			this.allMonster.add(new Monster(330, 360, true, 2, 300, 420, this));
			//������
			this.allMonster.add(new Monster(570, 540, true, 2 , 420, 540, this));			
		}
		//������
		if(sort == 3) {
			for(int i = 0; i < 15; i++) {
				this.allObstacles.add(new Obstacles(i*60, 540, 9, this));
			}
			//����ש��0���ʺ�4
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
			//����ש��3
			this.allObstacles.add(new Obstacles(180, 360, 3, this));
			this.allObstacles.add(new Obstacles(660, 360, 3, this));
			//��������Ģ������
			this.allMonster.add(new Monster(720, 480, true, 1, this));
			//ԲͷĢ������
			this.allMonster.add(new Monster(500, 480, 3, this, true));
			//��
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
				//����ש���
				this.allObstacles.add(new Obstacles(60, 360, 3, this));
				//��
				this.allMonster.add(new Monster(4, this, -120, 60));
			}
		}
		//�����
		if(sort == 5) {
			for(int i = 0; i < 15; i++) {
				this.allObstacles.add(new Obstacles(i*60, 540, 9, this));
			}
            //����ʯͷש�Լ��ʺ�ש
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
			//����ש��
			this.allObstacles.add(new Obstacles(420, 300, 3, this));
			this.allObstacles.add(new Obstacles(240, 420, 3, this));
			//��������Ģ������
			this.allMonster.add(new Monster(300, 480, true, 1, this));
		}
		//�����ض��͵���
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
			//�ذ�
			for(int i = 10; i < 15; i++) {
				this.allObstacles.add(new Obstacles(i*60, 540, 9, this));
			}
			//ˮ��
			this.allObstacles.add(new Obstacles(660, 540, 6, this));
			this.allObstacles.add(new Obstacles(720, 540, 5, this));
			this.allObstacles.add(new Obstacles(660, 480, 8, this));
			this.allObstacles.add(new Obstacles(720, 480, 7, this));
			//ʳ�˻����߶�y=420����͸߶�y=540
			this.allMonster.add(new Monster(690, 540, true, 2, 420, 540, this));
			//��
			this.allMonster.add(new Monster(4, this, -120, 60));
		}
		//���һ��
		if(sort == 7) {
			for(int i = 0; i < 15; i++) {
				this.allObstacles.add(new Obstacles(i*60, 540, 9, this));
			}
			//����
			this.allObstacles.add(new Obstacles(490, 180, 11, this));
			//������x����Ϊ520
			this.allObstacles.add(new Obstacles(520, 480, 6, this));
			//�����ϰ���
			this.allObstacles.add(new Obstacles(180, 360, 1, this));
			this.allObstacles.add(new Obstacles(180, 420, 1, this));
			this.allObstacles.add(new Obstacles(180, 480, 1, this));
			this.allObstacles.add(new Obstacles(120, 420, 1, this));
			this.allObstacles.add(new Obstacles(120, 480, 1, this));
			this.allObstacles.add(new Obstacles(60, 480, 1, this));
			//����ש��
			this.allObstacles.add(new Obstacles(420, 360, 3, this));
			//��
			this.allMonster.add(new Monster(4, this, -60, 60));
			this.allMonster.add(new Monster(4, this, -200, 60));
		}
			
	}
	
	/**
	 * Monster start.
	 */
	//���˿�ʼ�ƶ�
	public void monsterStart(){
		for(int i = 0; i < this.allMonster.size(); i++) {
			this.allMonster.get(i).startMove();
		}
	}
	
	/**
	 * Reset.
	 */
	//�����ϰ���͵���
	public void reset() {
		//���Ƴ����ϰ���͵��˻�ԭ
		this.allMonster.addAll(this.removeMonster);
		this.allObstacles.addAll(this.removeObstacles);
		
		//�����ϰ���͵��˵����÷���
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
	//��ȡ����
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
