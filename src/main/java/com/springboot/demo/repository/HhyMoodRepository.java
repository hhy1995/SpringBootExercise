package com.springboot.demo.repository;

import com.springboot.demo.model.HhyMood;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 说说repository
 * @author hehaiyang
 */
public interface HhyMoodRepository extends JpaRepository<HhyMood,String> {

}
