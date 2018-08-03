package com.itheima.web.action;


import com.itheima.bos.service.StandardService;
import com.itheima.domain.Standard;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.struts2.ServletActionContext;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class StandardAction extends BaseAction<Standard>{
	@Autowired
	private	StandardService standardService;

@Action(value="standardAction_save",results={
        @Result(name="success",location="/pages/base/standard.html",type="redirect"),
        @Result(name="unauthorized",location="/unauthorized.html",type="redirect"),
        @Result(name="error",location="/error.html",type="redirect"),
})
public String save(){
    System.out.println("xxxxx");

    try {
        standardService.save(model);
    } catch (Exception e) {
        e.printStackTrace();

        if(e instanceof UnauthorizedException){
            return "unauthorized";
        }else {
            return "error";
        }

     }
    return SUCCESS;
}


/**
*
 * 查询全部数据不带分页的
*/
@Action(value = "standardAction_findAll")
public String findAll() throws IOException {
	System.out.println("findAll.....");
	List<Standard> list= standardService.findAll();
	listToJson(list,new String[]{});

	return NONE;
}


/**
 * 查询数据带分页的--->注意属性注入page和rows两个属性
 */
    @Action(value = "standardAction_findByPage")
	public String findByPage() throws IOException {
        Pageable pageable=new PageRequest(page-1,rows);
        Page<Standard> page = standardService.findByPage(pageable);

        parentPage(page,null);

        return  NONE;
	}


    /**
     * 根据id查询数据进行数据回显
     */
    @Action(value = "standardAction_findById")
	public String findById() throws IOException {
        Standard standard2= standardService.finById(model.getId());
        String json = JSONObject.fromObject(standard2).toString();
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().write(json);
        return NONE;
    }

}
