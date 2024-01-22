package com.hongjun.designpatterns.template;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailSenderTest {


    @Autowired
    private UserEmailSender userEmailSender;

    @Autowired
    private AdminEmailSender adminEmailSender;



    @Test
    void sendEmail() {
        userEmailSender.buildSenders("xx@qq.com");

        AdminEmailSender.Other other = new AdminEmailSender.Other();
        adminEmailSender.buildSenders("xxqq.com", other);
    }
}