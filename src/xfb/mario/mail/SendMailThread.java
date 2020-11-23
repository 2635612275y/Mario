package xfb.mario.mail;


import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.util.Properties;


// TODO: Auto-generated Javadoc
/**
 * The Class SendMailThread.
 *
 * @date 2020-7-4
 * @author xufb
 * @version  v1.0
 */
public class SendMailThread extends Thread {
    
    /** The mail adr. */
    private String mailAdr;
    
    /** The content. */
    private String content;
    
    /** The subject. */
    private String subject;

    /**
     * Instantiates a new send mail thread.
     *
     * @param mailAdr the mail adr
     * @param subject the subject
     * @param content the content
     */
    public SendMailThread(String mailAdr, String subject, String content) {
        // TODO Auto-generated constructor stub
        super();
        this.mailAdr = mailAdr;
        this.subject = subject;
        this.content = content;
    }

    
        /* (非 Javadoc)
        * 
        * 
        * @see java.lang.Thread#run()
        */
        
    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
        try {
            sendMail(mailAdr, subject, content);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Send mail.
     *
     * @param mailAdr the mail adr
     * @param subject the subject
     * @param content the content
     * @throws Exception the exception
     */
    private void sendMail(String mailAdr, String subject, String content)
            throws Exception {
        // TODO Auto-generated method stub
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);

        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.qq.com");
        // 端口
        /* props.put("mail.smtp.port", 25); */
        // smtp登陆的账号、密码 ；需开启smtp登陆
        props.setProperty("mail.debug", "true");
        //修改下面两个地方
        props.put("mail.user", "2635612275@qq.com");
        props.put("mail.password", "txulkxhbkyaudigf");
        // 特别需要注意，要将ssl协议设置为true,否则会报530错误
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);

            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        try {
            InternetAddress form = new InternetAddress(
                    props.getProperty("mail.user"));
            message.setFrom(form);

            // 设置收件人
            InternetAddress to = new InternetAddress(mailAdr);
            message.setRecipient(RecipientType.TO, to);

            // 设置邮件标题
            message.setSubject(subject);
            // 设置邮件的内容体
            message.setContent(content, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
