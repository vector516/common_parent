package com.itheima.crm.service;

import com.itheima.crm.domain.Customer;

import javax.mail.MessagingException;
import java.util.List;

public interface CustomerService {
    public List<Customer> findAll();
    public List<Customer> findByFixedAreaIdIsNull();
    public List<Customer> findByFixedAreaId(String fixedAreaId);
    public void assignCustomers2FixedArea(String fixedAreaId,List<Integer> customerIds);
    public List<Customer> findCusByFixedId(String id);
    public String regist(Customer customer) throws MessagingException;
    public boolean findByTelephone(String telephone);
    public boolean activeCode(String telephone);
    public  Customer login(Customer customer);
    public String findFixedAreaIdByAddress(String address);
}
