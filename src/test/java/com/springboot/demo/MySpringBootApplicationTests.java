package com.springboot.demo;

import com.springboot.demo.dao.HhyUserDao;
import com.springboot.demo.mail.SendJunkMailService;
import com.springboot.demo.model.HhyMood;
import com.springboot.demo.model.HhyUser;
import com.springboot.demo.service.HhyMoodService;
import com.springboot.demo.service.HhyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MySpringBootApplicationTests {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    public void contextLoads() {

    }

    /**
     * MySql集成Spring Boot测试
     * 注意：原书代码执行完成之后出现The server time zone value '�й���׼ʱ��' is unrecognized or represents more than one time zone.这样的问题
     * 解决办法：在数据库URL之后，加上？serverTimezone=UTC
     */
    @Test
    public void testMySql() {
        String sql = "select id,name,password from hhy_user";
        List<HhyUser> userList = (List<HhyUser>) jdbcTemplate.query(sql, new RowMapper<HhyUser>() {
            //RowMapper对查询出来的数据，封装成用户指定定义的类
            @Override
            public HhyUser mapRow(ResultSet rs, int i) throws SQLException {
                HhyUser user = new HhyUser();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });
        System.out.println("执行数据库查询");
        for (HhyUser user : userList) {
            System.out.println("User Id:" + user.getId() + ",  User Name:" + user.getName());
        }
    }

    @Resource
    private HhyUserService hhyUserService;

    @Test
    public void testRepository() {
        //查询所有的数据
        List<HhyUser> userList = hhyUserService.findAll();
        System.out.println("findAll() :" + userList.size());
//        for (HhyUser user:userList) {
//            System.out.println("User Id:" + user.getId() + ",  User Name:" + user.getName());
//        }
        //通过name查询数据
        List<HhyUser> userList2 = hhyUserService.findByName("chen");
        System.out.println("findByName() :" + userList2.size());
        //通过断言方式可以验证程序执行的正确性,如果数据不等的话，发出错误消息message
        Assert.isTrue(userList2.get(0).getName().equals("chen"), "data error!");

        //通过name模糊查询数据
        List<HhyUser> userList3 = hhyUserService.findByNameLike("en");
        System.out.println("findByNameLike() :" + userList3.size());
        //通过断言方式可以验证程序执行的正确性
        Assert.isTrue(userList2.get(0).getName().equals("chen"), "data error!");

        //通过id列表查询数据
        List<String> ids = new ArrayList<String>();
        ids.add("1");
        ids.add("2");
        ids.add("3");
        List<HhyUser> userList4 = hhyUserService.findByIdIn(ids);
        System.out.println("findByIdIn() :" + userList4.size());

        //分页查询数据
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<HhyUser> userList5 = hhyUserService.findAll(pageRequest);
        System.out.println("findAll() :" + userList5.getTotalPages() + "/" + userList5.getSize());

        //新增数据
        HhyUser user = new HhyUser();
        user.setId("4");
        user.setName("test");
        user.setPassword("test");
        hhyUserService.save(user);

        //删除数据
        hhyUserService.delete("4");
    }

    @Test
    public void testTransaction() {
        HhyUser hhyUser = new HhyUser();
        hhyUser.setId("4");
        hhyUser.setName("lalala");
        hhyUser.setPassword("lalala");
        hhyUserService.save(hhyUser);
    }

    //以下两个为Spring Data Redis为我们提供的模板类
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedis() {
        //增 ：key :name   value:hhy
        redisTemplate.opsForValue().set("name", "hhy");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);
        //删除
        redisTemplate.delete("name");
        //更新
        redisTemplate.opsForValue().set("name", "hhy2");
        //查询
        name = stringRedisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    @Test
    public void testFindById() {
        Long redisUserSize = 0L;
        HhyUser hhyUser = hhyUserService.findUserById("1");
        redisUserSize = redisTemplate.opsForList().size("ALL_USER_LIST");
        System.out.println("目前缓存的用户数量为： " + redisUserSize);
        System.out.println("当前查询的用户  id：" + hhyUser.getId() + ",name:" + hhyUser.getName() + ",password:" + hhyUser.getPassword());

        HhyUser hhyUser2 = hhyUserService.findUserById("1");
        redisUserSize = redisTemplate.opsForList().size("ALL_USER_LIST");
        System.out.println("目前缓存的用户数量为： " + redisUserSize);
        System.out.println("当前查询的用户  id：" + hhyUser2.getId() + ",name:" + hhyUser2.getName() + ",password:" + hhyUser2.getPassword());

        //查询一个id = 5的数据，不存在于Redis缓存中，存在于数据库当中(在算法测试之前直接添加到数据库当中，缓存中没有该数据)
        //所以会把在数据库查询到的数据更新在缓存中
        HhyUser hhyUser3 = hhyUserService.findUserById("1");
        redisUserSize = redisTemplate.opsForList().size("ALL_USER_LIST");
        System.out.println("当前查询的用户  id：" + hhyUser3.getId() + ",name:" + hhyUser3.getName() + ",password:" + hhyUser3.getPassword());
        System.out.println("目前缓存的用户数量为： " + redisUserSize);
    }

    Logger logger = LogManager.getLogger(this.getClass());

    @Test
    public void testLog4j2() {
        hhyUserService.delete("5");
        logger.info("delete success!");
    }

    @Resource
    private SendJunkMailService sendJunkMailService;

    @Test
    public void testMail() {
        List<HhyUser> hhyUserList = hhyUserService.findAll();
        sendJunkMailService.sendJunkMail(hhyUserList);
    }
    
    @Resource
    private HhyUserDao hhyUserDao;

    @Test
    public void testMybatis() {
        HhyUser hhyUser = hhyUserDao.findByNameAndPassword("hhy", "hhy");
       // HhyUser hhyUser = hhyUserDao.findUserByName("hhy");
        logger.info("查找到用户：id为" + hhyUser.getId() + ",姓名为：" + hhyUser.getName());
    }

    @Resource
    private HhyMoodService hhyMoodService;

    @Test
    public void testHhyMood(){
        HhyMood hhyMood = new HhyMood();
        hhyMood.setId("1");
        hhyMood.setUserId("1");
        hhyMood.setPraiseNum(2);
        hhyMood.setContent("一条测试说说");
        hhyMood.setPublishTime(new Date());
        HhyMood mood = hhyMoodService.save(hhyMood);
    }
}
