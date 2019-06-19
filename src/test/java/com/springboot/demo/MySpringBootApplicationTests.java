package com.springboot.demo;

import com.springboot.demo.model.HhyUser;
import com.springboot.demo.service.HhyUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.ListOperations;
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
    public void testMySql(){
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
        for (HhyUser user:userList) {
            System.out.println("User Id:" + user.getId() + ",  User Name:" + user.getName());
        }
    }

    @Resource
    private HhyUserService hhyUserService;

    @Test
    public void testRepository(){
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
        Assert.isTrue(userList2.get(0).getName().equals("chen"),"data error!");

        //通过name模糊查询数据
        List<HhyUser> userList3 = hhyUserService.findByNameLike("en");
        System.out.println("findByNameLike() :" + userList3.size());
        //通过断言方式可以验证程序执行的正确性
        Assert.isTrue(userList2.get(0).getName().equals("chen"),"data error!");

        //通过id列表查询数据
        List<String> ids = new ArrayList<String>();
        ids.add("1");
        ids.add("2");
        ids.add("3");
        List<HhyUser> userList4 = hhyUserService.findByIdIn(ids);
        System.out.println("findByIdIn() :" + userList4.size());

        //分页查询数据
        PageRequest pageRequest = new PageRequest(0,10);
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
    public void testTransaction(){
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
    public void testRedis(){
        //增 ：key :name   value:hhy
        redisTemplate.opsForValue().set("name","hhy");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);
        //删除
        redisTemplate.delete("name");
        //更新
        redisTemplate.opsForValue().set("name","hhy2");
        //查询
        name = stringRedisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    @Test
    public void testFindById(){
        Long redisUserSize = 0L;
        HhyUser hhyUser = hhyUserService.findUserById("1");
        redisUserSize = redisTemplate.opsForList().size("ALL_USER_LIST");
        System.out.println("目前缓存的用户数量为： " + redisUserSize);

    }

}
