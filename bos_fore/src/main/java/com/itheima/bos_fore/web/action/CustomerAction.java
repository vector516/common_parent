package com.itheima.bos_fore.web.action;

import com.itheima.bos_fore.utils.MailUtils;
import com.itheima.bos_fore.utils.SmsUtils;
import com.itheima.constant.BosConstant;
import com.itheima.crm.service05.impl.Customer;
import com.itheima.crm.service05.impl.CustomerServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
    private Log log=LogFactory.getLog(CustomerAction.class);


    private Customer customer = new Customer();

    @Override
    public Customer getModel() {
        return customer;
    }



    @Autowired
    private JmsTemplate jmsTemplate;


    @Action(value = "customerAction_sendMsg")
    public String sendMsg() {
        String random = RandomStringUtils.randomNumeric(4);
        log.info(random);
        String res = "aaaabbbbccccdddd";// SmsUtils.sendSmsByWebService(customer.getTelephone(), "你好,你的验证码是" + random);

        //为用户发送消息发到消息队列
        jmsTemplate.send("sms_message", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage mapMessage = session.createMapMessage();
                mapMessage.setString("telephone",customer.getTelephone());
                mapMessage.setString("content","恭喜注册成功速运快递会员，你的验证码是:"+random);
                return mapMessage;
            }
        });




        if (StringUtils.isNotBlank(res) && res.length() == 16) {
            ServletActionContext.getRequest().getSession().setAttribute(customer.getTelephone(), random);
            ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
            try {
                ServletActionContext.getResponse().getWriter().write(BosConstant.SUCCESS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return NONE;
    }


    private String checkcode;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    @Autowired
    private CustomerServiceImpl customerService;


    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Action(value = "customerAction_regist", results = {
            @Result(name = "success", location = "/signup-success.html", type = "redirect"),
            @Result(name = "fail", location = "/signup-fail.html", type = "redirect")
    })
    public String regist() throws MessagingException {
        //校验验证码是否正确
        String verifyCode = (String) ServletActionContext.getRequest().getSession().getAttribute(customer.getTelephone());
        if (StringUtils.isNotBlank(verifyCode) && StringUtils.isNotBlank(checkcode) && checkcode.equals(verifyCode)) {
            String regist = customerService.regist(customer);
            if (regist.length()==36) {
                redisTemplate.opsForValue().set(customer.getTelephone(), activeCode, 24, TimeUnit.HOURS);
                return SUCCESS;

            } else {
                return BosConstant.FIAL;
            }

        } else {
            return BosConstant.FIAL;
        }

    }


    @Action(value = "customerAction_findByTelephone")
    public String findByTelephone() throws IOException {

        boolean flag = customerService.findByTelephone(customer.getTelephone());
        String msg = "";
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        if (flag) {
            System.out.println("用户已存在");
            msg = "用户已存在";
        } else {
            System.out.println("用户可以使用");
            msg = "用户可以使用";
        }
        ServletActionContext.getResponse().getWriter().write(msg);

        return NONE;
    }


    //接收前传入的激活码
    private String activeCode;

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    @Action(value = "CustomerAction_activeCode", results = {@Result(name = "success", location = "/activeCode.html")})
    public String activeCode() {
        String activeCode02 = redisTemplate.opsForValue().get(customer.getTelephone());
        if (activeCode02.equals(activeCode)) {
            boolean flag = customerService.activeCode(customer.getTelephone());
            if (flag) {
                return SUCCESS;
            } else {
                return "fail";
            }

        } else {
            return "fail";
        }

    }


    //登录的功能
    @Action(value = "customerAction_login", results = {
            @Result(name = "success", location = "/myhome.html", type = "redirect"),
            @Result(name = "fail", location = "/login.html", type = "redirect")
    })
    public String login() {
        String validateCode = (String) ServletActionContext.getRequest().getSession().getAttribute("validateCode");
        if (StringUtils.isNotBlank(checkcode) && StringUtils.isNotBlank(validateCode) && checkcode.equals(validateCode)) {
            if (customer != null && StringUtils.isNotBlank(customer.getTelephone()) && StringUtils.isNotBlank(customer.getPassword())) {

                Customer customer2 = customerService.login(customer);

                if (customer2 != null) {
                    return SUCCESS;
                }
            }

        }

        return "fail";
    }


}
