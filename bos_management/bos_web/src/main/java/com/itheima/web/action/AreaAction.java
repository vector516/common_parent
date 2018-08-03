package com.itheima.web.action;

import com.itheima.bos.service.AreaService;
import com.itheima.domain.Area;
import com.itheima.domain.Standard;
import com.itheima.utils.FileUtils;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class AreaAction extends BaseAction<Area> {
    @Autowired
    private AreaService areaService;

    private File areaFile;

    public void setAreaFile(File areaFile) {
        this.areaFile = areaFile;
    }

    @Action(value = "AreaAction_fileUpload", results = {@Result(name = "success", location = "/pages/base/area.html", type = "redirect")})
    public String fileUpload() throws IOException {
        //读取文件
        System.out.println(areaFile);
        areaService.importfile(areaFile);
        return SUCCESS;
    }


    @Action(value = "AreaAction_findByPage")
    public String findByPage() throws IOException {
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<Area> page2 = areaService.findByPage(pageable);

        String[] excludes = {"subareas"};
        parentPage(page2, excludes);
        return NONE;
    }


    //分区添加时下拉列表的数据回显-->需实现的功能有模糊查询区域信息-->需要传递查询条件-->combobox设置为remote则可以传递参数q

    private String q;

    public void setQ(String q) {
        this.q = q;
    }

    @Action(value = "AreaAction_findAll")
    public String findAll() throws IOException {
        System.out.println("area findAll............");
        List<Area> list = null;
        if (StringUtils.isNotBlank(q)) {
            q = q.toUpperCase();
            list = areaService.findByQ("%" + q + "%");
        } else {
            list = areaService.findAll();
        }
        listToJson(list, new String[]{"subareas"});
        return NONE;
    }


    @Autowired
    private DataSource dataSource;

    @Action(value = "areaAction_exportPDF")
    public String exportPDF() throws JRException, SQLException, IOException {
        String realPath = ServletActionContext.getServletContext().getRealPath("/jr/test.jrxml");
        HashMap<String, Object> map = new HashMap<>();
        map.put("company", "腾讯");

        JasperReport report = JasperCompileManager.compileReport(realPath);
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, map, dataSource.getConnection());
        HttpServletResponse response = ServletActionContext.getResponse();
        OutputStream ouputStream = response.getOutputStream();

        // 设置相应参数，以附件形式保存PDF
        response.setContentType("application/pdf");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + FileUtils.encodeDownloadFilename("工作单.pdf",
                ServletActionContext.getRequest().getHeader("user-agent")));

        // 使用JRPdfExproter导出器导出pdf
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
        exporter.exportReport();// 导出
        ouputStream.close();// 关闭流

        return NONE;
    }

}
