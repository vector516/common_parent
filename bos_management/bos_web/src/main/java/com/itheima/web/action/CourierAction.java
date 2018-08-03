package com.itheima.web.action;

import com.itheima.bos.service.CourierService;
import com.itheima.crm.webservice03.Customer;
import com.itheima.crm.webservice03.CustomerServiceImpl;
import com.itheima.domain.Courier;
import com.itheima.domain.Standard;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.formula.functions.T;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import javax.persistence.criteria.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class CourierAction extends BaseAction<Courier> {


    @Autowired
    private CourierService courierService;

    @Action(value = "CourierAction_save", results = {@Result(name = "success", location = "/pages/base/courier.html")})
    public String save() {
        courierService.save(model);
        return SUCCESS;
    }


    @Autowired
    private CustomerServiceImpl customerService;

    @Action(value = "CourierAction_findByPage")
    public String findByPage() throws IOException {
        List<Customer> list01 = customerService.findByFixedAreaIdIsNull();
        List<Customer> list02 = customerService.findByFixedAreaId("dq001");
        System.out.println(list01.size());
        System.out.println(list02.size());

        final String courierNum = model.getCourierNum();
        final Standard standard = model.getStandard();
        final String company = model.getCompany();
        final String type = model.getType();
        //定义一个list接收所有的Predicate对象
        final ArrayList<Predicate> list=new ArrayList<>();

        //匿名内部类的方式创建条件对象
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                //root--->获取查询条件的类型
                //cb--->构建查询条件和查询传入的参数,equal,like查询;
                //需要进行非空的判断

                if(StringUtils.isNotBlank(courierNum)){
                    Predicate predicate = cb.equal(root.get("courierNum").as(String.class), courierNum);
                    list.add(predicate);
                }
                if(StringUtils.isNotBlank(company)){
                    Predicate predicate = cb.like(root.get("company").as(String.class), "%" + company + "%");
                    list.add(predicate);
                }
                if(StringUtils.isNotBlank(type)){
                    Predicate predicate = cb.equal(root.get("type").as(String.class), type);
                    list.add(predicate);
                }

                if(standard!=null&&StringUtils.isNotBlank(standard.getName())){
                    //查询关联对象,先进行内连接,再获取查询条件的类型
                    Join join = root.join("standard");
                    Predicate predicate = cb.equal(join.get("name").as(String.class), standard.getName());
                    list.add(predicate);
                }
                //将list转换为数组--->传入指定的数组类型即可返回指定的数组类型,否则是Object[]
                Predicate[] predicates = new Predicate[list.size()];
                Predicate[] array = list.toArray(predicates);

                //调用cb的方法拼接多个条件并返回--->使用and或者or关键字连接;
               Predicate and = cb.and(array);
                return and;
            }
        };


        Pageable pageable = new PageRequest(page - 1, rows);
        Page<Courier> page = courierService.findByPage(spec, pageable);
        String[] exclueds = {"fixedAreas", "taketime"};
        parentPage(page,exclueds);
        return NONE;

    }

    private String ids;

    public void setIds(String ids) {
        this.ids = ids;
    }

    @Action(value = "CourierAction_deleteAll", results = {@Result(name = "success", location = "/pages/base/courier.html", type = "redirect")})
    public String deleteAll() {
        try {
            if (!StringUtils.isNotBlank(ids)) {
                ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
                ServletActionContext.getResponse().getWriter().print("非法操作");
                return NONE;
            }
            courierService.deleteAll(ids);
        } catch (Exception e) {
            log.error(e.getMessage());
            return "fail";

        }

        courierService.deleteAll(ids);

        return "success";
    }

@Action(value = "courierAction_listajax")
    public String listajax() throws IOException {
        List<Courier> list=courierService.listajax();

        listToJson(list,new String[]{"standard","takeTime","fixedAreas"});

        return NONE;
}


}
