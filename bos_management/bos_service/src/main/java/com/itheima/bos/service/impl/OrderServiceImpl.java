package com.itheima.bos.service.impl;

import com.itheima.bos.dao.*;
import com.itheima.bos.service.OrderService;
import com.itheima.crm.webservice03.CustomerServiceImpl;
import com.itheima.domain.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
@WebService
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private FixedAreaDao fixedAreaDao;

    @Autowired
    private SubAreaDao subAreaDao;


    @Autowired
    private CustomerServiceImpl customerService;


    @Autowired
    private WorkBillDao workBillDao;


    //保存订单的时候为其分配快递员
    @Override
    public void save(Order order) {
        System.out.println(order.toString());
        String orderType = "2";
        //根据省市区查询区域数据-->寄件人
        Area sendArea = order.getSendArea();
        Area sendArea2 = this.findByPCD(order.getSendArea().getProvince(), order.getSendArea().getCity(), order.getSendArea().getDistrict());
        //收件人的区域
        Area recArea = order.getRecArea();
        Area recArea2 = this.findByPCD(order.getRecArea().getProvince(), order.getRecArea().getCity(), order.getRecArea().getDistrict());
        String sendAddress = order.getSendAddress();

        //根据收件人的地址调用crm服务查询定区id
        String fixedAreaId = customerService.findFixedAreaIdByAddress(order.getRecAddress());
        Courier cr = null;
        if (StringUtils.isNotBlank(fixedAreaId)) {
            FixedArea fixedArea = fixedAreaDao.findOne(fixedAreaId);
            //发送短信给快递员
            Set<Courier> couriers = fixedArea.getCouriers();
            if (couriers != null & couriers.size() > 0) {
                for (Courier courier : couriers) {
                    cr = courier;
                    orderType = "1";
                    //校验该工作时间段的快递员
                    this.generateWorkBill(order, courier);
                    break;
                }
            }

        } else {
            if (sendArea2 != null) {
                String areaId = sendArea2.getId();//区域id
                List<SubArea> list = subAreaDao.findByAreaId(areaId);
                for (SubArea subArea : list) {
                    String keyWords = subArea.getKeyWords();//关键字
                    String assistKeyWords = subArea.getAssistKeyWords();
                    //如果前台输入的详细地址  模糊匹配到关键字或 辅助关键字 则认为是当前分区（小区） 直接跳出循环
                    if (sendAddress.contains(keyWords) || sendAddress.contains(assistKeyWords)) {
                        ///得到定区
                        FixedArea fixedArea = subArea.getFixedArea();
                        if (fixedArea != null) {
                            //快递员集合
                            Set<Courier> couriers = fixedArea.getCouriers();
                            if (couriers != null & couriers.size() > 0) {
                                //判断上班时间的逻辑  略
                                for (Courier courier : couriers) {
                                    orderType = "1";
                                    this.generateWorkBill(order, courier);
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }
        order.setOrderNum(UUID.randomUUID().toString());// 生成订单编号
        order.setTelephone(order.getSendMobile());//发件人的手机号码
        order.setCustomer_id(123);//客户编号
        order.setCourier(cr);// 订单关联快递员
        order.setOrderType(orderType);//数据库中 1：自动分单 2：手动分单
        order.setStatus("1");//// 订单状态 1 待取件 2 运输中 3 已签收 4 异常
        order.setOrderTime(new Date());// 订单生成时间

        order.setSendArea(sendArea2);
        order.setRecArea(recArea2);


        orderDao.save(order);
    }


    public void generateWorkBill(Order order, Courier cr) {
        WorkBill workBill = new WorkBill();
        workBill.setType("1");//1:新单   2：追   3：销
        workBill.setPickstate("1");//1：未取件 2：取件中 3：已取件
        workBill.setBuildtime(new Date());
        workBill.setAttachbilltimes(1);//追单次数
        workBill.setRemark(order.getRemark());
        workBill.setSmsNumber("1234567890123456");
        workBill.setCourier(cr);
        workBill.setOrder(order);
        workBillDao.save(workBill);
    }


    @Override
    public Area findByPCD(String province, String city, String distinct) {
        Area area = areaDao.findByPCD(province, city, distinct);
        return area;
    }


}
