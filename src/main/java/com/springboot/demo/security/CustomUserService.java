package com.springboot.demo.security;

import com.springboot.demo.error.BusinessException;
import com.springboot.demo.model.HhyUser;
import com.springboot.demo.model.HhyUserRoleRel;
import com.springboot.demo.service.HhyRoleService;
import com.springboot.demo.service.HhyUserRoleRelService;
import com.springboot.demo.service.HhyUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：自定义用户服务类
 * @author hehaiyang
 */
@Service
public class CustomUserService implements UserDetailsService{

    @Resource
    private HhyUserService hhyUserService;

    @Resource
    private HhyUserRoleRelService hhyUserRoleRelService;

    @Resource
    private HhyRoleService hhyRoleService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        HhyUser hhyUser = hhyUserService.findByUserName(name);
        if(hhyUser == null){
            throw new BusinessException("用户不存在");
        }
        //获取用户所有的关联角色
        List<HhyUserRoleRel> hhyRoleList = hhyUserRoleRelService.findByUserId(hhyUser.getId());
        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
        if(hhyRoleList != null && hhyRoleList.size() > 0){
            for(HhyUserRoleRel rel:hhyRoleList){
                //获取用户关联角色名称
                String roleName = hhyRoleService.find(rel.getRoleId()).getName();
                authorityList.add(new SimpleGrantedAuthority(roleName));
            }
        }
        return new User(hhyUser.getName(), hhyUser.getPassword(), authorityList);
    }
}


