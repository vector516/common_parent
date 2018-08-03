package com.itheima.web.action;

import com.itheima.bos.service.PermissionService;
import com.itheima.domain.Permission;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Namespace("/")
@ParentPackage("struts-default")
@Controller
@Scope("prototype")
public class PermissionAction extends BaseAction<Permission> {
    @Autowired
    private PermissionService permissionService;


    @Action(value = "permissionAction_save", results = {@Result(name = "success", type = "redirect", location = "/pages/system/permission.html")})
    public String save() {
        permissionService.save(model);
        return SUCCESS;
    }

    @Action(value = "permissionAction_findAll")
    public String findAll() throws IOException {
        List<Permission> list = permissionService.findAll();
        listToJson(list, new String[]{"roles"});
        return NONE;
    }


}
