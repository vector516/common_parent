package com.itheima.crm.service.impl;

import com.itheima.crm.dao.CustomerDao;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.service.CustomerService;
import com.itheima.mail.utils.MailUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.jws.WebService;
import javax.mail.MessagingException;
import java.util.List;

@Service
@Transactional
@WebService//用来作为服务端发布的,必须加
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerDao customerDao;


    @Override
    public List<Customer> findAll() {
        List<Customer> list = customerDao.findAll();
        return list;
    }

    @Override//fixedAreaId
    public List<Customer> findByFixedAreaIdIsNull() {
        List<Customer> list = customerDao.findByFixedAreaIdIsNull();
        return list;
    }

    //命名查询
    @Override
    public List<Customer> findByFixedAreaId(String fixedAreaId) {
        return customerDao.findByFixedAreaId(fixedAreaId);
    }

    @Override
    public void assignCustomers2FixedArea(String fixedAreaId, List<Integer> customerIds) {
        //先根据定区id清空已关联用户
        if (StringUtils.isNotBlank(fixedAreaId)) {
            customerDao.updataByFixedAreaId(fixedAreaId);
            if (customerIds != null && customerIds.size() > 0) {

                for (Integer customerId : customerIds) {
//                    customerDao.updateByCustomerId(fixedAreaId,customerId);
                    customerDao.setFixedId(customerId, fixedAreaId);
                }
            }

        }
    }

    @Override
    public List<Customer> findCusByFixedId(String id) {
        List<Customer> list = customerDao.findCusByFixedId(id);
        return list;
    }


    @Autowired
    @Qualifier("jmsQueueTemplate")
    private JmsTemplate jmsTemplate;


    @Override
    public String regist(Customer customer) {
        //保存数据之前先查看数据是否已经存在
        if (customer != null && StringUtils.isNotBlank(customer.getTelephone())) {
            List<Customer> list = customerDao.findByTelephone(customer.getTelephone());
            if (list != null && list.size() > 0) {
                return "0002";
            }
            String activeCode = RandomStringUtils.randomNumeric(36);
            String content = "欢迎注册速运快递，请点击下面连接激活账号，24小时内有效！<a href=" + MailUtils.activeUrl + "?telephone=" + customer.getTelephone() + "&activeCode=" + activeCode + ">激活</a>";
            try {

                jmsTemplate.send("send_mail", new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        MapMessage mapMessage = session.createMapMessage();
                        mapMessage.setString("mail",customer.getEmail());
                        mapMessage.setString("mailContent",content);
                        return mapMessage;
                    }
                });


//
                customerDao.save(customer);

                return activeCode;

            } catch (Exception e) {
                return "00001";
            }

        } else {
            return "00001";
        }

    }


    @Override
    public boolean findByTelephone(String telephone) {
        List<Customer> list = customerDao.findByTelephone(telephone);
        boolean flag = false;
        if (list != null & list.size() > 0) {
            flag = true;
        }
        return flag;
    }


    @Override
    public boolean activeCode(String telephone) {
        List<Customer> list = customerDao.findByTelephone(telephone);
        if (list != null && list.size() > 0 && (list.get(0).getType() == null)) {
            customerDao.activeCode(telephone);
            return true;
        }
        return false;
    }

    @Override
    public Customer login(Customer customer) {
        Customer customer1 = customerDao.login(customer.getTelephone(), customer.getPassword());
        return customer1;
    }

    @Override
    public String findFixedAreaIdByAddress(String address) {
        String fixedAreaId = customerDao.findFixedAreaIdByAddress(address);
        return fixedAreaId;
    }

}
