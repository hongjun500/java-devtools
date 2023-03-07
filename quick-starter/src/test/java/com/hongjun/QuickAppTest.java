package com.hongjun;

import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hongjun500
 * @date 2023/3/2 14:02
 * @tool ThinkPadX1隐士
 * Created with 2022.2.2.IntelliJ IDEA
 * Description:
 */
@Log4j2
@SpringBootTest(classes = QuickStarterApplication.class)
public class QuickAppTest {

    @Test
    void testListMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
      /* ArrayList<Integer> integerArrayList = Lists.newArrayList(1);
       // integerArrayList.add(3);
       integerArrayList.getClass().getMethod("add", Object.class).invoke(integerArrayList, "two");
       // integerArrayList.forEach(log::info);
        integerArrayList.forEach(obj ->{
            log.info(obj);
        });*/


        List<Integer> integerArrayList2 = Lists.newArrayList();
        integerArrayList2.add(1);
        integerArrayList2.getClass().getMethod("add", Object.class).invoke(integerArrayList2, "two");
       /* integerArrayList2.forEach(obj ->{
            log.info(obj);
        });*/
        log.info("-----------------------------fori--------------");
        for (int i = 0; i < integerArrayList2.size(); i++) {
            log.info(integerArrayList2.get(i));
        }
        log.info("-----------------------------for--------------");
        for (Object integer : integerArrayList2) {
            log.info(integer);

        }
    }

}
