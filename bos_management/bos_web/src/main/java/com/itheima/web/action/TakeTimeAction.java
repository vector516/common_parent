package com.itheima.web.action;

import com.itheima.bos.service.TakeTimeService;
import com.itheima.domain.TakeTime;
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
public class TakeTimeAction extends BaseAction<TakeTime>{
    @Autowired
    private TakeTimeService takeTimeService;


    @Action(value = "takeTimeAction_findAll")
    public String findAll() throws IOException {
       List<TakeTime> list= takeTimeService.findAll();
       listToJson(list,new String[]{""});
        return NONE;
    }

}
