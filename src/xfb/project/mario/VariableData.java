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
	//ÿ�عؿ�����
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
	public static BufferedImage startImage = null;//��Ϸ������ʼ�Ľ���
	
	/** The all flower image. */
	//ʳ�˻�ͼƬ
	public static List<BufferedImage> allFlowerImage = new ArrayList<BufferedImage>();
	
	/** The all triangle image. */
	//���ǹ�
	public static List<BufferedImage> allTriangleImage = new ArrayList<BufferedImage>();
	
	/** The all mushroom image. */
	//��ɫĢ����
	public static List<BufferedImage> allMushroomImage = new ArrayList<BufferedImage>();
	
	/** The all birds image. */
	//����
	public static List<BufferedImage> allBirdsImage = new ArrayList<BufferedImage>();
	
	/** The all obstacles image. */
	//�ϰ��Ｏ��
	public static List<BufferedImage> allObstaclesImage = new ArrayList<BufferedImage>();
	
	/** The Image path. */
	//����ͼƬ·��
	public static String ImagePath = System.getProperty("user.dir") + "/pic/";
    
	/**
	 * Inits the.
	 */
	//�����ʼ��
	public static void init() {
		//�����ͼƬ��ʼ��
		for(int i = 1; i <= 10; i++) {
			try {
				allMarioImage.add(ImageIO.read(new File(ImagePath + i + ".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			//���뱳��ͼƬ
			startImage = ImageIO.read(new File(ImagePath + "start.png"));
			bgImage = ImageIO.read(new File(ImagePath + "firststage.jpg"));
			bg2Image = ImageIO.read(new File(ImagePath + "����2.jpg"));
			bg3Image = ImageIO.read(new File(ImagePath + "����3.jpg"));
			bg4Image = ImageIO.read(new File(ImagePath + "����4.jpg"));
			bg5Image = ImageIO.read(new File(ImagePath + "����5.jpg"));
			bg6Image = ImageIO.read(new File(ImagePath + "����6.jpg"));
			overImage = ImageIO.read(new File(ImagePath + "gameover.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		//�������ͼƬ
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
		//�����ϰ���ͼƬ,�������ӣ�ש�飬���ש�飬���ӣ���ש��
		for(int i = 1; i <= 12; i++) {
			try {
				allObstaclesImage.add(ImageIO.read(new File(ImagePath + "ob" + i + ".png")));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

}
