package com.hongjun.designpatterns.template;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author hongjun500
 * @date 2024/1/22 14:37
 * @tool ThinkPadX1 隐士
 * Created with 2023.2.5.IntelliJ IDEA
 * Description: 用户邮件发送器
 */
@Component
public class UserEmailSender extends EmailSender<Void> {
    protected UserEmailSender(JavaMailSender javaMailSender) {
        super(javaMailSender);
    }

    @Override
    protected boolean checkEmail(String email) {
        // do something
        return false;
    }

    @Override
    protected void prepareEmail() {
        this.subject = "账号申请结果反馈";
        String emailTemplate = "<html>" +
                "<body>" +
                "<p>您好！</p>" +
                "<p>您申请的账号不通过，不通过原因：%s，请您核对信息后重新提交申请，感谢您的配合！</p>" +
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
}
