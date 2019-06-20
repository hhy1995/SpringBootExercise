package com.springboot.demo.service.impl;

import com.springboot.demo.model.HhyMood;
import com.springboot.demo.repository.HhyMoodRepository;
import com.springboot.demo.service.HhyMoodService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 微信说说服务层实现类
 * @author hehaiyang
 */
@Service
public class HhyMoodServiceImpl implements HhyMoodService {

    @Resource
    private HhyMoodRepository hhyMoodRepository;

    @Override
    public HhyMood save(HhyMood hhyMood) {
        return hhyMoodRepository.save(hhyMood);
    }
}
