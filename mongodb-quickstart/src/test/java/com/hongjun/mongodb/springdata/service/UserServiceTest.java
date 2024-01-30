package com.hongjun.mongodb.springdata.service;

import com.hongjun.mongodb.springdata.document.Properties;
import com.hongjun.mongodb.springdata.document.Role;
import com.hongjun.mongodb.springdata.document.User;
import com.mongodb.DBRef;
import com.mongodb.client.result.UpdateResult;
import org.assertj.core.util.Lists;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hongjun500
 * @date 2024-01-30 21:27
 * @tool ThinkPadX1隐士
 * Created with 2023.2.2.IntelliJ IDEA
 * Description: UserServiceTest
 */
@DataMongoTest
class UserServiceTest {

    private List<Role> roles = Lists.newArrayList();
    private List<Properties> properties = Lists.newArrayList();

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setUp() {
        List<Role> all = mongoTemplate.findAll(Role.class);
        if (all.isEmpty()) {
            Role role = new Role();
            role.setName("admin");
            Role role1 = new Role();
            role1.setName("user");
            mongoTemplate.insert(Lists.newArrayList(role, role1), Role.class);
        }
        roles = mongoTemplate.findAll(Role.class);
        List<Properties> propertiesList = mongoTemplate.findAll(Properties.class);
        if (propertiesList.isEmpty()) {
            Properties properties = new Properties();
            properties.setName("age");
            properties.setValue("18");
            Properties properties1 = new Properties();
            properties1.setName("sex");
            properties1.setValue("男");
            mongoTemplate.insert(Lists.newArrayList(properties, properties1), Properties.class);
        }
        properties = mongoTemplate.findAll(Properties.class);
    }

    @Test
    void contextLoads() {
        System.out.println("hello world");
    }

    @Test
    void saveUser() {
        User user = new User();
        user.setName("hongjun");
        user.setRole(roles.get(0));
        // user.setProperties(properties);
        mongoTemplate.insert(user);
        List<User> all = mongoTemplate.findAll(User.class);
        assertFalse(all.isEmpty());
    }

    @Test
    void dbref() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("hongjun"));
        User one = mongoTemplate.findOne(query, User.class);
        assertNotNull(one);

        Query query1 = new Query();
        // 这里需要用到 ObjectId 类型，否则查询为空
        query1.addCriteria(Criteria.where("role.$id").is(new ObjectId(roles.get(0).getId())));
        List<User> users = mongoTemplate.find(query1, User.class);
        assertFalse(users.isEmpty());

        Query query2 = new Query();
        List<ObjectId> ids = Lists.newArrayList();
        ids.addAll(properties.stream().map(p -> new ObjectId(p.getId())).collect(Collectors.toList()));
        query2.addCriteria(Criteria.where("properties.$id").in(ids));
        List<User> users1 = mongoTemplate.find(query2, User.class);
        assertTrue(users1.isEmpty());

        // 更新的数据
        User user;
        user = one;
        // 以查询出的数据再填充一次没问题
        user.setProperties(properties);
        // mongoTemplate.save(user);

        Update update = new Update();
        // 这里会导致变成嵌套的文档数据，而非引用
        // update.set("properties", properties);



        Update.PushOperatorBuilder operatorBuilder = update.push("properties");
        properties.forEach(obj -> {
            // operatorBuilder.each(new DBRef("properties", new ObjectId(obj.getId())));
            operatorBuilder.each(new DBRef("properties", new ObjectId(obj.getId())));
        });
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, User.class);
        assertEquals(1, updateResult.getModifiedCount());
    }

    @Test
    void listAllUser() {
        List<User> all = mongoTemplate.findAll(User.class);
        assertFalse(all.isEmpty());
    }

    // @AfterEach()
    void tearDown() {
        mongoTemplate.dropCollection(User.class);
    }
}
