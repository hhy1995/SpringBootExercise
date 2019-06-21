package com.springboot.demo.service.impl;

import com.springboot.demo.dao.HhyUserDao;
import com.springboot.demo.error.BusinessException;
import com.springboot.demo.model.HhyUser;
import com.springboot.demo.repository.HhyUserRepository;
import com.springboot.demo.service.HhyUserService;
import org.apache.logging.log4j.LogManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 用户服务层的实现类
 * 在服务层上，添加@Transactional注解来开启事务
 * @author hehaiyang
 */
//@Transactional
@Service
//也可以用@Component进行注解
public class HhyUserServiceImpl implements HhyUserService {


    @Resource(name = "hhyUserRepository")   //默认按照名称进行装配
    private HhyUserRepository hhyUserRepository;

    @Resource
    private RedisTemplate redisTemplate;

    private static final String ALL_USER = "ALL_USER_LIST";

    @Override
    public HhyUser findUserById(String id) {
        //首先查询redis缓存当中的所有数据，如果缓存中当中并没有查到的话，然后去数据库当中进行查找，并且将查找到的数据写回到缓存
        List<HhyUser> hhyUserList = redisTemplate.opsForList().range(ALL_USER, 0, -1);
        if (hhyUserList != null && hhyUserList.size() > 0){
            for (HhyUser user:hhyUserList) {
                return user;
            }
        }

        HhyUser hhyUser = hhyUserRepository.getOne(id);
        if (hhyUser != null){
            redisTemplate.opsForList().leftPush(ALL_USER,hhyUser);
        }
        return hhyUser;
    }

    @Override
    public List<HhyUser> findAll() {
        try{
            System.out.println("开始执行任务！");
            long start = System.currentTimeMillis();
            List<HhyUser> hhyUserList = hhyUserRepository.findAll();
            long end = System.currentTimeMillis();
            System.out.println("执行任务所花费的时间：" +(end - start) +"毫秒");
            return hhyUserList;
        }catch (Exception e){
            logger.error("method [findAll] error" + e);
            return Collections.EMPTY_LIST;
        }
    }

    //将Transactional注解在方法上
    //@Transactional
    @Override
    public HhyUser save(HhyUser hhyUser) {
        return hhyUserRepository.save(hhyUser);
//        HhyUser saveUser = hhyUserRepository.save(hhyUser);
//        //出现空指针异常
//        String error = null;
//        error.split("/");
//        return saveUser;
    }

    Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void delete(String id) {
        hhyUserRepository.deleteById(id);
        logger.info("将userId为: " + id + "的用户删除");
    }

    @Override
    public Page<HhyUser> findAll(Pageable pageable) {
        return hhyUserRepository.findAll(pageable);
    }

    @Override
    public List<HhyUser> findByName(String name) {
        return hhyUserRepository.findByName(name);
    }

    @Override
    public HhyUser findByUserName(String name) {
        return hhyUserDao.findByUserName(name);
    }


    @Override
    public List<HhyUser> findByNameLike(String name) {
        return hhyUserRepository.findByNameLike(name);
    }

    @Override
    public List<HhyUser> findByIdIn(Collection<String> ids) {
        return hhyUserRepository.findByIdIn(ids);
    }

    @Resource
    private HhyUserDao hhyUserDao;

    @Override
    public HhyUser findUserByNameAndPassword(String name, String password) {
        return hhyUserDao.findByNameAndPassword(name,password);
    }

    @Override
    @Async
    public Future<List<HhyUser>> findAsynAll() {
        try{
            System.out.println("开始执行findAsynAll任务！");
            long start = System.currentTimeMillis();
            List<HhyUser> hhyUserList = hhyUserRepository.findAll();;
            long end = System.currentTimeMillis();
            System.out.println("执行findAsynAll任务所花费的时间：" +(end - start) +"毫秒");
            return new AsyncResult<List<HhyUser>>(hhyUserList);
        }catch (Exception e){
            logger.error("method [findAsynAll] error" + e);
            return new AsyncResult<List<HhyUser>>(null);
        }
    }
    @Override
    @Retryable(value = {BusinessException.class},maxAttempts = 5,backoff = @Backoff(delay = 5000,multiplier = 2))
    public HhyUser findByNameAndPasswordRetry(String name, String password) {
        //故意抛出业务异常进行测试
        System.out.println("[findByNameAndPasswordRetry] 方法失败重试！");
        throw new BusinessException();
    }
}
