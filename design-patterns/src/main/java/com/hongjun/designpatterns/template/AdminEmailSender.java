package com.hongjun.designpatterns.template;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author hongjun500
 * @date 2024/1/22 14:49
 * @tool ThinkPadX1 隐士
 * Created with 2023.2.5.IntelliJ IDEA
 * Description: 管理员邮件发送器
 */
@Component
public class AdminEmailSender extends EmailSender<AdminEmailSender.Other>{
    protected AdminEmailSender(JavaMailSender javaMailSender) {
        super(javaMailSender);
    }

    @Override
    protected boolean checkEmail(String email) {
        // do something
        return false;
    }

    @Override
    protected void prepareEmail() {
        this.subject = "账号重置";
        String emailTemplate = "<html>" +
                "<body>" +
                "<p>您好！</p>" +
                "<p>您的账号已重置，新密码为：%s，请您尽快登录修改密码，感谢您的配合！</p>" +
                "<p style='text-align: right;'>XXX平台</p>" +
                "<p style='text-align: right;'>%s</p>" +
                "</body>" +
                "</html>";
        this.contentHtml = String.format(emailTemplate, data, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @Override
    protected void doSend() {
        super.sendEmail();
    }

    public static class Other {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
