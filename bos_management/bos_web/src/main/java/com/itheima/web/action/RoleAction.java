package com.itheima.web.action;

import com.itheima.bos.service.RoleService;
import com.itheima.domain.Role;
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

@Namespace("/")
@ParentPackage("struts-default")
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
    @Autowired
    private RoleService roleService;

    @Action(value = "roleAction_findByPage")//新增action只要重启即可
    public String findByPage() throws IOException {
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<Role> page = roleService.findByPage(pageable);
        parentPage(page, new String[]{"users", "permissions", "menus"});
        return NONE;
    }


    public void setPermissionIds(Integer[] permissionIds) {
        this.permissionIds = permissionIds;
    }

    private Integer[] permissionIds;
    private String menuIds;


    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }

    @Action(value = "roleAction_save", results = {@Result(name = "success", location = "/pages/system/role.html", type = "redirect")})
    public String save() {
        roleService.save(model, permissionIds, menuIds);
        return SUCCESS;
    }


    @Action(value = "roleAction_findAll")
    public String findAll() throws IOException {
        List<Role> list = roleService.findAll();
        listToJson(list, new String[]{"users","permissions","menus"});
        return NONE;
    }


}
