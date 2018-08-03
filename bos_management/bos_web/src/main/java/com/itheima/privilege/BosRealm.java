package com.itheima.privilege;

import com.itheima.bos.dao.PermissionDao;
import com.itheima.bos.dao.RoleDao;
import com.itheima.bos.dao.UserDao;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.domain.User;
import jdk.nashorn.internal.parser.Token;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BosRealm extends AuthorizingRealm {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        simpleAuthorizationInfo.addStringPermission("area");
//        simpleAuthorizationInfo.addStringPermission("areaxx");
//        simpleAuthorizationInfo.addStringPermission("courier:save");
//        simpleAuthorizationInfo.addStringPermission("standard:save");
//        simpleAuthorizationInfo.addRole("admin");

        //根据当前用户查询对应的角色和权限
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (user.getUsername().equals("admin")) {
            //如果是超级管理员查询所有的角色和权限
            List<Role> roles = roleDao.findAll();
            for (Role role : roles) {
                simpleAuthorizationInfo.addRole(role.getKeyword());
            }

            List<Permission> permissions = permissionDao.findAll();
            for (Permission permission : permissions) {
                simpleAuthorizationInfo.addStringPermission(permission.getKeyword());
            }
        } else {
            //不是超级管理员,则通过查询绑定的角色和权限进行权限分配
            List<Role> roles = roleDao.findByUser(user.getId());
            for (Role role : roles) {
                simpleAuthorizationInfo.addRole(role.getKeyword());

            }
            //根据用户查询权限
            //避免嵌套的方法是多表连接查询
            List<Permission> permissions = permissionDao.findByUser(user.getId());
            for (Permission permission : permissions) {
                simpleAuthorizationInfo.addStringPermission(permission.getKeyword());
            }


        }


        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        User user = userDao.findByUsername(username);
        if (user == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
    }
}
