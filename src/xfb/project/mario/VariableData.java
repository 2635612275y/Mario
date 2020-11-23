package xfb.project.mario;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

    
// TODO: Auto-generated Javadoc
/**
 * The Class VariableData.
 *
 * @date 2020-7-4
 * @author xufb
 * @version  v1.0
 */
public class VariableData {
	
	/** The all mario image. */
	public static List<BufferedImage> allMarioImage = new ArrayList<BufferedImage>();
	
	/** The bg image. */
	//每关关卡背景
	public static BufferedImage bgImage = null;
	
	/** The bg 2 image. */
	public static BufferedImage bg2Image = null;
	
	/** The bg 3 image. */
	public static BufferedImage bg3Image = null;
	
	/** The bg 4 image. */
	public static BufferedImage bg4Image = null;
	
	/** The bg 5 image. */
	public static BufferedImage bg5Image = null;
	
	/** The bg 6 image. */
	public static BufferedImage bg6Image = null;
	
	/** The over image. */
	public static BufferedImage overImage = null;
	
	/** The start image. */
	public static BufferedImage startImage = null;//游戏按键开始的界面
	
	/** The all flower image. */
	//食人花图片
	public static List<BufferedImage> allFlowerImage = new ArrayList<BufferedImage>();
	
	/** The all triangle image. */
	//三角怪
	public static List<BufferedImage> allTriangleImage = new ArrayList<BufferedImage>();
	
	/** The all mushroom image. */
	//黑色蘑菇怪
	public static List<BufferedImage> allMushroomImage = new ArrayList<BufferedImage>();
	
	/** The all birds image. */
	//飞鸟
	public static List<BufferedImage> allBirdsImage = new ArrayList<BufferedImage>();
	
	/** The all obstacles image. */
	//障碍物集合
	public static List<BufferedImage> allObstaclesImage = new ArrayList<BufferedImage>();
	
	/** The Image path. */
	//加载图片路径
	public static String ImagePath = System.getProperty("user.dir") + "/pic/";
    
	/**
	 * Inits the.
	 */
	//界面初始化
	public static void init() {
		//马里奥图片初始化
		for(int i = 1; i <= 10; i++) {
			try {
				allMarioImage.add(ImageIO.read(new File(ImagePath + i + ".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			//导入背景图片
			startImage = ImageIO.read(new File(ImagePath + "start.png"));
			bgImage = ImageIO.read(new File(ImagePath + "firststage.jpg"));
			bg2Image = ImageIO.read(new File(ImagePath + "背景2.jpg"));
			bg3Image = ImageIO.read(new File(ImagePath + "背景3.jpg"));
			bg4Image = ImageIO.read(new File(ImagePath + "背景4.jpg"));
			bg5Image = ImageIO.read(new File(ImagePath + "背景5.jpg"));
			bg6Image = ImageIO.read(new File(ImagePath + "背景6.jpg"));
			overImage = ImageIO.read(new File(ImagePath + "gameover.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		//导入敌人图片
		for(int i = 1; i <= 8; i++) {
			try {
				if(i <= 2)
					allFlowerImage.add(ImageIO.read(new File(ImagePath + "flower" + i + ".png")));
				else if(i >= 3 && i <= 4)
					allTriangleImage.add(ImageIO.read(new File(ImagePath + "triangle" + i + ".png")));
				else if(i >= 5 && i <= 6){
					allMushroomImage.add(ImageIO.read(new File(ImagePath + "mushroom" + i + ".png")));
				}
				else {
					allBirdsImage.add(ImageIO.read(new File(ImagePath + "birds" + i + ".png")));
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		//导入障碍物图片,包含旗子，砖块，金币砖块，管子，土砖块
		for(int i = 1; i <= 12; i++) {
			try {
				allObstaclesImage.add(ImageIO.read(new File(ImagePath + "ob" + i + ".png")));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

}
