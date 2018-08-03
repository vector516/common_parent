package com.itheima.web.action;

import com.itheima.bos.service.WayBillService;
import com.itheima.domain.WayBill;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class WaybillAction extends BaseAction<WayBill> {

    @Autowired
    private WayBillService wayBillService;

    @Action(value = "waybillAction_save")
    public String save() throws IOException {

        wayBillService.save(model);

        ServletActionContext.getResponse().getWriter().write("1");

        return NONE;

    }


    @Action(value = "waybillAction_findAll")
    public String findAll() throws IOException {
       List<WayBill> list= wayBillService.findAll();

       listToJson(list,new String[]{""});
        return NONE;
    }


}
