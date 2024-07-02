package com.hongjun.designpatterns.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author hongjun500
 * @date 2024/1/22 13:44
 * @tool ThinkPadX1 隐士
 * Created with 2023.2.5.IntelliJ IDEA
 * Description: 邮件发送器
 */
@Component
public abstract class EmailSender<T> {

    // 日志
    private static final Logger log = LoggerFactory.getLogger(EmailSender.class);


    private final JavaMailSender javaMailSender;

    protected EmailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String from;

    protected String email;

    /**
     * 邮件主题
     */
    protected String subject;

    /**
     * 邮件内容
     * 支持 html 格式
     */
    protected String contentHtml;

    /**
     * 其它信息填充
     * 可以是一个对象，也可以是一个 Map
     */
    protected T data;

    /**
     * 构建不同参数的邮件内容并发送
     * @param email 邮箱地址
     */
    public final void buildSenders(String email){
        this.email = email;
        // 1. 验证邮箱地址是否合法
        if (!checkEmail(email)) {
            throw new RuntimeException("邮箱地址不合法");
        }
        // 2. 准备邮件内容
        prepareEmail();
        // 3. 发送邮件
        doSend();
    }

    /**
     * 构建不同参数的邮件内容并发送
     * @param email 邮箱地址
     * @param data 其它信息填充
     */
    public final void buildSenders(String email, T data){
        this.email = email;
        this.data = data;
        // 1. 验证邮箱地址是否合法
        if (!checkEmail(email)) {
            throw new RuntimeException("邮箱地址不合法");
        }
        // 2. 准备邮件内容
        prepareEmail();
        // 3. 发送邮件
        doSend();
    }

    /**
     * 验证邮箱地址是否合法
     *
     * @param email 邮箱地址
     * @return true: 邮箱地址合法; false: 邮箱地址不合法
     */
    protected abstract boolean checkEmail(String email);

    /**
     * 准备发送邮件
     */
    protected abstract void prepareEmail();

    /**
     * 发送邮件
     */
    protected abstract void doSend();

    protected void sendEmail() {
        try {
            log.info("发送邮件：{}，主题：{}", email, subject);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(from);
            mailMessage.setTo(email);
            mailMessage.setSubject(subject);
            mailMessage.setText(contentHtml);
            mailMessage.setSentDate(new Date());
            javaMailSender.send(mailMessage);
        } catch (MailException e) {
            log.error("邮件发送失败", e);
            // 写入到持久化存储中
            // writeToDb();
        }
    }
}
