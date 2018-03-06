package cn.scene.util;

import cn.scene.model.UserAuth;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * 邮件激活
 */
public class MailUtil {
    public static final String FROM = "miniscene@163.com"; //发件人的email
    public static final String PWD = "miniscene123"; //发件人密码--邮箱密码
    public static final String URL = "http://www.hsfeng.cn/scene";//项目主页
    public static final int TIMELIMIT = 1000*60*60*24; //激活邮件过期时间24小时
    public static final String TITLE = "MINISCENE账号激活";
    public static final String HOST = "smtp.163.com";
    public static final String SMTP = "smtp";

    public static UserAuth activateMail(UserAuth user) throws Exception {
        //注册邮箱
        String email  = user.getAccount();
        //当前时间戳
        Long curTime = System.currentTimeMillis();
        //激活的有效时间
        Long activateTime = curTime+TIMELIMIT;
        //激活码--用于激活邮箱账号
        String token = Md5Util.md5(email+curTime);
        user.setToken(token);
        //过期时间
        user.setTimes(activateTime);
        //发送的邮箱内容
        String content = "<p>欢迎使用MINISCENE!<br><br>请在24小时内点击下面的链接激活帐户:"+"<br><a href='"+URL+"/user/activate.html?v=1.0&token="+token+"&email="+email+"'>"+URL+"/user/activate.html?v=1.0&token="+token+"&email="+email+"</a></p>";
        //调用发送邮箱服务
        MailUtil.sendMail(email, TITLE, content);
        return user;
    }


    public static void sendMail(String email,String title,String content) throws Exception {

        Properties props = new Properties(); //加载一个配置文件
        // 使用smtp：简单邮件传输协议
        props.put("mail.smtp.host", HOST);//存储发送邮件服务器的信息
        props.put("mail.smtp.port", 465);//设置端口
        props.put("mail.smtp.auth", "true");//同时通过验证
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getInstance(props);//根据属性新建一个邮件会话
        MimeMessage message = new MimeMessage(session);//由邮件会话新建一个消息对象
        message.setFrom(new InternetAddress(FROM));//设置发件人的地址
        message.setRecipient(Message.RecipientType.CC, new InternetAddress(FROM)); //抄送
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));//设置收件人,并设置其接收类型为TO
        message.setSubject(title);//设置标题
        //设置信件内容
        message.setContent(content, "text/html;charset=utf-8"); //发送HTML邮件
        message.setSentDate(new Date());//设置发信时间
        message.saveChanges();//存储邮件信息
        //发送邮件
        Transport transport = session.getTransport(SMTP);
        transport.connect(FROM, PWD);
        transport.sendMessage(message, message.getAllRecipients());//发送邮件,其中第二个参数是所有已设好的收件人地址
        transport.close();
    }

}
