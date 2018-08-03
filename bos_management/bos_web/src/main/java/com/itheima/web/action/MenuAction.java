package com.itheima.web.action;

import com.itheima.bos.service.MenuService;
import com.itheima.domain.Menu;
import com.itheima.domain.Standard;
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

import javax.xml.transform.Source;
import java.io.IOException;
import java.util.List;

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class MenuAction extends BaseAction<Menu> {
    @Autowired
    private MenuService menuService;

    @Action(value = "menuAction_listajax")
    public String listajax() throws IOException {
        List<Menu> list = menuService.findAll();
        listToJson(list, new String[]{"roles", "childrenMenus", "parentMenu"});
        return NONE;
    }

    @Action(value = "menuAction_save", results = {@Result(name = "success", location = "/pages/system/menu.html")})
    public String save() {
        //判断如果选择了父菜单需要置为空,否则该表找不到id为0的数据,外键插入数据报瞬时态异常
        if(model.getParentMenu()!=null&&model.getId()==0){
            model.setParentMenu(null);
        }

        menuService.save(model);

        return SUCCESS;
    }


    @Action(value = "menuAction_findByPage")
    public String findByPage() throws IOException {
        System.out.println("aaaaaaaaa");
        System.out.println("page____>" + page);
        System.out.println(model);
        Pageable pageable = new PageRequest(Integer.parseInt(model.getPage())- 1, rows);
        Page<Menu> page2 = menuService.findByPage(pageable);
        parentPage(page2, new String[]{"roles", "childrenMenus", "parentMenu"});

        return NONE;
    }


    @Action(value = "menuAction_findAll")
    public String findAll() throws IOException {
        List<Menu> list = menuService.findAll();
        listToJson(list, new String[]{"page", "priority", "description", "roles", "childrenMenus", "parentMenu"});
        return NONE;
    }


    @Action(value = "menuAction_findMenu")
    public String findMenu() throws IOException {
        List<Menu> menus = menuService.findMenu();
        listToJson(menus, new String[]{"roles", "childrenMenus", "parentMenu", "children"});
        return NONE;
    }




}
