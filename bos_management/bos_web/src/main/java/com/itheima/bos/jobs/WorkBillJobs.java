package com.itheima.bos.jobs;

import com.itheima.bos.dao.WorkBillDao;
import com.itheima.domain.Courier;
import com.itheima.domain.WorkBill;
import com.itheima.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.util.List;

public class WorkBillJobs {

    @Autowired
    private WorkBillDao workBillDao;

    public void sendMail() throws MessagingException {
        //将查到的所有工单数据发送
        List<WorkBill> workBills = workBillDao.findAll();
        if (workBills != null) {

            String content = "<table border='1px'><tr><td>工单id</td><td>工单类型 </td><td>取件状态 </td><td>快递员</td></tr>";
            String subject = "统计所有工单数据";

            for (WorkBill workBill : workBills) {
                //工单id  工单类型 取件状态 快递员
                Courier courier = workBill.getCourier();
                String cname = null;
                if (courier != null) {
                    cname = courier.getName();
                }
                content += "<tr><td>" + workBill.getId() + "</td><td>" + workBill.getType() + "</td><td>"
                        + workBill.getPickstate() + "</td><td>" + cname + "</td></tr>";
            }
//            MailUtils.sendMail("wuyunlong_516@163.com", content, subject);
            System.out.println("邮件发送成功。。。");
        }

    }


}
