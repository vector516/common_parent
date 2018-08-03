package com.itheima.web.action;

import com.itheima.bos.service.FixedAreaService;
import com.itheima.crm.webservice03.Customer;
import com.itheima.crm.webservice03.CustomerServiceImpl;
import com.itheima.domain.Courier;
import com.itheima.domain.FixedArea;
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
import java.util.List;
import java.util.UUID;


@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class FixedAreaAction extends BaseAction<FixedArea> {
    @Autowired
    private FixedAreaService fixedAreaService;


    @Action(value = "fixedAreaAction_save", results = {@Result(name = "success", location = "/pages/base/fixed_area.html")})
    public String save() {
        model.setId(UUID.randomUUID().toString());
        fixedAreaService.save(model);
        return SUCCESS;
    }


    @Action(value = "fixedAreaAction_pageQuery")
    public String pageQuery() throws IOException {

        Pageable pageable = new PageRequest(page - 1, rows);
        Page<FixedArea> page2 = fixedAreaService.findByPage(pageable);
        parentPage(page2, new String[]{"subareas", "couriers"});
        return NONE;
    }

    @Autowired
    private CustomerServiceImpl customerService;

    @Action(value = "fixedAreaAction_findByFixedAreaIdIsNull")
    public String findByFixedAreaIdIsNull() throws IOException {
        List<Customer> list = customerService.findByFixedAreaIdIsNull();
        listToJson(list, new String[]{});
        return NONE;
    }


    @Action(value = "fixedAreaAction_findByFixedAreaId")
    public String findByFixedAreaId() throws IOException {
        List<Customer> list = customerService.findByFixedAreaId(model.getId());
        listToJson(list, new String[]{});
        return NONE;
    }

    List<Integer> customerIds;

    public void setCustomerIds(List<Integer> customerIds) {
        this.customerIds = customerIds;
    }

    @Action(value = "fixedAreaAction_assignCustomers2FixedArea", results = {@Result(name = "success", location = "/pages/base/fixed_area.html")})
    public String assignCustomers2FixedArea() {
        System.out.println(model.getId() + "      " + customerIds);
        customerService.assignCustomers2FixedArea(model.getId(), customerIds);
        return "success";
    }

    @Action(value = "fixedAreaAction_findCusByFixedId")
    public String findCusByFixedId() throws IOException {
        System.out.println("findCusByFixedId........");
        List<Customer> list = customerService.findCusByFixedId(model.getId());
        listToJson(list,new String[]{});
        return NONE;
    }

    private Integer courierId;
    private Integer takeTimeId;

    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }

    public void setTakeTimeId(Integer takeTimeId) {
        this.takeTimeId = takeTimeId;
    }

    @Action(value = "fixedAreaAction_associationCourierToFixedArea", results = {@Result(name = "success", location = "/pages/base/fixed_area.html")})
    public String associationCourierToFixedArea(){

        fixedAreaService.associationCourierToFixedArea(model.getId(),courierId,takeTimeId);

        return SUCCESS;
    }




}
