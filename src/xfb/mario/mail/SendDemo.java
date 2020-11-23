package xfb.mario.mail;


import xfb.mario.dao.RegisterListener;
import xfb.mario.mail.SendMailThread;
import xfb.project.mario.RegisterLogin;

// TODO: Auto-generated Javadoc
/**
 * The Class SendDemo.
 *
 * @date 2020-7-4
 * @author xufb
 * @version  v1.0
 */
public class SendDemo {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
            SendMailThread st = new SendMailThread("2262666129@qq.com", "小笨蛋", "能通关说明你前途无量");
            st.start();
            

	}
}