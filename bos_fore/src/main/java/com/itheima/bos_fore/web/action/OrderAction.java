package com.itheima.bos_fore.web.action;

import com.itheima.bos.service01.impl.Area;
import com.itheima.bos.service01.impl.Order;
import com.itheima.bos.service01.impl.OrderServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class OrderAction extends ActionSupport implements ModelDriven<Order> {
    private Order order = new Order();

    @Override
    public Order getModel() {
        return order;
    }

    //发件人的省市区
    private String sendAreaInfo;


    //收件人省市区
    private String recAreaInfo;

    public void setSendAreaInfo(String sendAreaInfo) {
        this.sendAreaInfo = sendAreaInfo;
    }

    public void setRecAreaInfo(String recAreaInfo) {
        this.recAreaInfo = recAreaInfo;
    }

    @Autowired
    private OrderServiceImpl orderService;

    @Action(value = "orderAction_add", results = {
            @Result(name = "success", location = "/orderSuccess.html", type = "redirect"),
            @Result(name = "fail", location = "/fail.html", type = "redirect")
    })
    public String add() {
        //发件人省市区的解析-->以/进行拆分
        System.out.println("add.......aaaaaaa");
        Area sendArea = new Area();//发件人
        if (StringUtils.isNotBlank(sendAreaInfo)) {
            String[] send = sendAreaInfo.split("/");
            sendArea.setProvince(send[0]);
            sendArea.setCity(send[1]);
            sendArea.setDistrict(send[2]);
            //将发件对象 放入order对象中
            order.setSendArea(sendArea);
        }
        Area recArea = new Area();//收件人
        if (StringUtils.isNotBlank(recAreaInfo)) {
            String[] rec = recAreaInfo.split("/");
            recArea.setProvince(rec[0]);
            recArea.setCity(rec[1]);
            recArea.setDistrict(rec[2]);
            //将发件对象 放入order对象中
            order.setRecArea(recArea);
        }
        // sendAreaInfo: 广东省/深圳市/宝安区// recAreaInfo: 北京市/北京市/东城区
        orderService.save(order);
        return SUCCESS;
    }

}
