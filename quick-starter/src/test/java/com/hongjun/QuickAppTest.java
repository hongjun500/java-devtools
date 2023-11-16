package com.hongjun;

import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.test.annotation.DirtiesContext;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

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

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

    @Test
    void testApplicationContextRunner(){
        assert contextRunner != null;
        contextRunner.run(context -> {
            log.info("测试");
            for (String beanDefinitionName : context.getBeanDefinitionNames()) {
                log.info(beanDefinitionName);
            }
        });
    }

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

    @Test
    void testArr(){
        int[] array = new int[]{1, 2, 3, 4, 5};
        int target = 6;
       /* int[] array = new int[]{3, 2, 4};
        int target = 6;*/

        int[] ints = twoSum(array, target);

        boolean unique = isUnique("s");
        log.info(ints);
    }
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                result[0] = i;
                result[1] = map.get(nums[i]);
                break;
            }
            map.put(target - nums[i], i);
        }
        return result;
        /*// 建立k-v ，一一对应的哈希表
        int[] indexs = new int[2];
        Map<Integer, Integer> hash = new HashMap<Integer,Integer>();
        for(int i = 0; i < nums.length; i++) {
            if (hash.containsKey(nums[i])) {
                indexs[0] = i;
                indexs[1] = hash.get(nums[i]);
                break;
            }
            // 将数据存入 key为补数 ，value为下标
            hash.put(target-nums[i],i);
        }
        return indexs;*/
    }

    public boolean isUnique(String astr) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < astr.toCharArray().length; i++) {
            set.add(astr.charAt(i));
        }
        CopyOnWriteArrayList<Object> objects = new CopyOnWriteArrayList<>();

        return set.size() == astr.toCharArray().length;


    }
}
