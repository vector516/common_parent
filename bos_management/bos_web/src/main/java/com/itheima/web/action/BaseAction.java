package com.itheima.web.action;

import com.itheima.domain.Courier;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.formula.functions.T;
import org.apache.struts2.ServletActionContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    //log对象
    protected Log log = LogFactory.getLog(CourierAction.class);
    protected T model;

    @Override
    public T getModel() {
        return model;

    }

    //通过获得子类上的泛型类并实体化
    public BaseAction() {
        Class<? extends BaseAction> aClass = this.getClass();
        Type type = aClass.getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Class<T> class2 = (Class<T>) actualTypeArguments[0];
        try {
            model = class2.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Integer page;
    public Integer rows;

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }


    public void parentPage(Page<T> page2, String[] exclueds) throws IOException {

        long totalElements = page2.getTotalElements();
        List<T> content = page2.getContent();

        Map<String, Object> map = new HashMap<>();
        map.put("total", totalElements);
        map.put("rows", content);

        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(exclueds);

        String json = JSONObject.fromObject(map, jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().write(json);

    }


    public void listToJson(List list, String[] exclueds) throws IOException {

        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(exclueds);

        String json = JSONArray.fromObject(list, jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().write(json);

    }


}
