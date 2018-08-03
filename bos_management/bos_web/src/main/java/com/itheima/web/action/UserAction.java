package com.itheima.web.action;

import com.itheima.bos.service.UserService;
import com.itheima.domain.User;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import java.io.IOException;


@Namespace("/")
@ParentPackage("struts-default")
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

    @Autowired
    private UserService userService;


    private String checkcode;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    @Action(value = "userAction_login", results = {
            @Result(name = "success", location = "/index.html", type = "redirect"),
            @Result(name = "home", location = "/login.html", type = "redirect")
    })
    public String login() {

        String validateCode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        if (StringUtils.isNotBlank(checkcode) && StringUtils.isNotBlank(validateCode) && checkcode.equals(validateCode)) {
            //验证码一致则登录--->使用的shiro框架
            Subject subject = SecurityUtils.getSubject();

            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(model.getUsername(), model.getPassword());

            try {
                subject.login(usernamePasswordToken);
                User user = (User) subject.getPrincipal();
                if (user == null) {
                    return "home";
                }

                ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);

            } catch (Exception e) {
                e.printStackTrace();
                return "home";
            }
            return SUCCESS;
        }
        return "home";

    }


    @Action(value = "userAction_logout", results = {@Result(name = "success", location = "/login.html", type = "redirect")})
    public String logout() {
        SecurityUtils.getSubject().logout();
        return SUCCESS;
    }


    private Integer[] roleids;

    public void setRoleids(Integer[] roleids) {
        this.roleids = roleids;
    }

    @Action(value = "userAction_save", results = {@Result(name = "success", location = "/pages/system/userlist.html", type = "redirect")})
    public String save() {
        userService.save(model, roleids);
        return SUCCESS;
    }

    @Action(value = "userAction_findByPage")
    public String findByPage() throws IOException {
        Pageable pageable=new PageRequest(page-1,rows);
        Page<User> page=userService.findByPage(pageable);
        parentPage(page,new String[]{"roles"});
        return NONE;
    }



}
