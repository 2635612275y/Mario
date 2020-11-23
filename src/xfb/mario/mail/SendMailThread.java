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

    
        /* (�� Javadoc)
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
        // ��ʾSMTP�����ʼ�����Ҫ���������֤
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.qq.com");
        // �˿�
        /* props.put("mail.smtp.port", 25); */
        // smtp��½���˺š����� ���迪��smtp��½
        props.setProperty("mail.debug", "true");
        //�޸����������ط�
        props.put("mail.user", "2635612275@qq.com");
        props.put("mail.password", "txulkxhbkyaudigf");
        // �ر���Ҫע�⣬Ҫ��sslЭ������Ϊtrue,����ᱨ530����
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // �û���������
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);

            }
        };
        // ʹ�û������Ժ���Ȩ��Ϣ�������ʼ��Ự
        Session mailSession = Session.getInstance(props, authenticator);
        // �����ʼ���Ϣ
        MimeMessage message = new MimeMessage(mailSession);
        // ���÷�����
        try {
            InternetAddress form = new InternetAddress(
                    props.getProperty("mail.user"));
            message.setFrom(form);

            // �����ռ���
            InternetAddress to = new InternetAddress(mailAdr);
            message.setRecipient(RecipientType.TO, to);

            // �����ʼ�����
            message.setSubject(subject);
            // �����ʼ���������
            message.setContent(content, "text/html;charset=UTF-8");
            // �����ʼ�
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
