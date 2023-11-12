package com.hongjun.akka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {ActorRefBean.class})
class ActorRefBeanTest {


    @Autowired
    private ActorRefBean actorRefBean;

    @Test
    void tell() {
        assert actorRefBean != null;
    }
}